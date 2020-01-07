package hu.bme.mit.inf.dslreasoner.viatrasolver.partialinterpretationlanguage.neighbourhood

import hu.bme.mit.inf.dslreasoner.logic.model.logiclanguage.DefinedElement
import hu.bme.mit.inf.dslreasoner.viatrasolver.partialinterpretationlanguage.partialinterpretation.PartialInterpretation
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import java.util.logging.Handler

import static extension hu.bme.mit.inf.dslreasoner.util.CollectionsUtil.*

class Neighbourhood2Gml {

	static val neighbourhoodComputer = new PartialInterpretation2ImmutableTypeLattice
	val protected nodeNameSize = 14
	val protected nodeHeight = 40
	val protected borderDistance = 6
	val protected ratio = 11.0 / 20.0
	var protected depth = 0

	def public transform(NeighbourhoodWithTraces n, PartialInterpretation pm) {

		val Map<Object, Integer> relevantObjectToID = new HashMap
		val List<CharSequence> fullNodeText = newArrayList
		val List<CharSequence> fullEdgeText = newArrayList
		val List<CharSequence> allEdgesText = newArrayList
		val Map<AbstractNodeDescriptor, List<FurtherNodeDescriptor>> children = new HashMap

		// TODO these should not be hashmaps, as a given node can have multiple sameNamed edges to different 
		val Map<IncomingRelation, Object> edgeNameIn2targetNode = new HashMap
		val Map<OutgoingRelation, Object> edgeNameOut2targetNode = new HashMap
		val Map<IncomingRelation, List<Integer>> edgeName2inMultips = new HashMap
		val Map<OutgoingRelation, List<Integer>> edgeName2outMultips = new HashMap

		val modRep = n.modelRepresentation as HashMap
		// Store node text
		for (nodeKey : modRep.keySet) {
			fullNodeText.add(transformNode(nodeKey, modRep, relevantObjectToID))
		}

		// calculate deeper neighbourhood
		calculateDepth(modRep.keySet.get(0))
		val deeperNeighbourhood = neighbourhoodComputer.createRepresentation(pm, depth + 1, Integer.MAX_VALUE,
			Integer.MAX_VALUE)
		val deepModRep = deeperNeighbourhood.modelRepresentation as HashMap

		// Associate each deepNode to their parent
		for (deepNodeKey : deepModRep.keySet) {
			val deepNodeDesc = deepNodeKey as FurtherNodeDescriptor
			val parentDesc = deepNodeDesc.previousRepresentation as AbstractNodeDescriptor
			if (relevantObjectToID.keySet.contains(parentDesc)) {
				if (children.keySet.contains(parentDesc)) {
					parentDesc.lookup(children).add(deepNodeDesc)
				} else {
					children.put(parentDesc, newArrayList(deepNodeDesc))
				}
			}
		}

		// edge transforming
		for (currentNode : children.keySet) {
			println(currentNode)
			transformEdge(edgeNameIn2targetNode, edgeNameOut2targetNode, edgeName2inMultips, edgeName2outMultips,
				allEdgesText, currentNode, relevantObjectToID, children)
			for (edgeText : allEdgesText) {
				fullEdgeText.add(edgeText)
			}
			allEdgesText.clear
		}

//		mapPrinter(children)
		'''
			graph
			[
				«FOR nodeText : fullNodeText»
					«nodeText»
				«ENDFOR»
				«FOR edgeText : fullEdgeText»
					«edgeText»
				«ENDFOR»
			]
		'''.toString
	}

	protected def transformEdge(Map<IncomingRelation, Object> edgeNameIn2targetNode,
		Map<OutgoingRelation, Object> edgeNameOut2targetNode, Map<IncomingRelation, List<Integer>> edgeName2inMultips,
		Map<OutgoingRelation, List<Integer>> edgeName2outMultips, List<CharSequence> allEdgesText,
		AbstractNodeDescriptor currentNode, Map<Object, Integer> relevantObjectToID,
		Map<AbstractNodeDescriptor, List<FurtherNodeDescriptor>> children) {

		edgeNameIn2targetNode.clear
		edgeNameOut2targetNode.clear
		edgeName2inMultips.clear
		edgeName2outMultips.clear
		val List<Object> modifiedEdgeNames = newArrayList

		val List<FurtherNodeDescriptor> subNodes = currentNode.lookup(children)
		for (subNode : subNodes) {
//			println(subNode)
//			mapPrinter(subNode.outgoingEdges)
//			println("---------")
//			mapPrinter(subNode.incomingEdges)
//			println
//			println
			// handling outgoing edges
			for (outEdge : subNode.outgoingEdges.keySet) {
				val edgeName = (outEdge as OutgoingRelation)
				val edgePointingTo = (outEdge as OutgoingRelation).to
				val edgeOutMultip = outEdge.lookup(subNode.outgoingEdges) as Integer

				// add multiplicity and target
				if (edgeName2outMultips.containsKey(edgeName)) {
					edgeName.lookup(edgeName2outMultips).add(edgeOutMultip)
				} else {
					edgeName2outMultips.put(edgeName, newArrayList(edgeOutMultip))
				}
				edgeNameOut2targetNode.put(edgeName, edgePointingTo)

			}

		}

		// handling incoming edges
		for (outEdgeTarget : edgeNameOut2targetNode.values) {
			// currentNode = sourceParent
			for (subNode : (outEdgeTarget as AbstractNodeDescriptor).lookup(children)) {
				for (inEdge : subNode.incomingEdges.keySet) {
					val edgeName = (inEdge as IncomingRelation)
					val edgePointingFrom = (inEdge as IncomingRelation).from
					val edgeInMultip = inEdge.lookup(subNode.incomingEdges) as Integer

					if (edgePointingFrom.equals(currentNode)) {
						if (edgeName2inMultips.containsKey(edgeName)) {
							edgeName.lookup(edgeName2inMultips).add(edgeInMultip)
						} else {
							edgeName2inMultips.put(edgeName, newArrayList(edgeInMultip))
							modifiedEdgeNames.add(edgeName)
						}
						edgeNameIn2targetNode.put(edgeName, edgePointingFrom)
					}
//				edgeName2targetNode.put(edgeName, edgePointingFrom)
				}
			}

//			mapPrinter(edgeName2inMultips)
			// fill in the 0 multiplicities (INCOMING)
			for (edgeSoFar : modifiedEdgeNames) {
				val edgeAsRelation = edgeSoFar as IncomingRelation
				var inEdgesNum = edgeAsRelation.lookup(edgeName2inMultips).size
				val targetNode = outEdgeTarget as AbstractNodeDescriptor
				val targetChildrenNum = targetNode.lookup(children).size
				println("in " + edgeSoFar + "=>" + inEdgesNum + " != " + outEdgeTarget + "=>" + targetChildrenNum)
				while (inEdgesNum != targetChildrenNum) {
//					println("in" + inEdgesNum + "!=" + targetChildrenNum)
					edgeAsRelation.lookup(edgeName2inMultips).add(0)
					inEdgesNum++
				}
			}
			modifiedEdgeNames.clear

		}

		mapPrinter(edgeName2outMultips)

		// fill in the 0 multiplicities (OUTGOING)
		for (edge : edgeName2outMultips.keySet) {
			// handling outgoing edges
			var outEdgesNum = edge.lookup(edgeName2outMultips).size
			val sourceChildrenNum = currentNode.lookup(children).size

			println("out " + edge + "=>" + outEdgesNum + " != " + currentNode + "=>" + sourceChildrenNum)
			while (outEdgesNum != sourceChildrenNum) {
				edge.lookup(edgeName2outMultips).add(0)
				outEdgesNum++
			}
		}
		println("xxxxxxxx")
		mapPrinter(edgeNameIn2targetNode)
		mapPrinter(edgeNameOut2targetNode)
		
		

		updateEdgeList(currentNode, relevantObjectToID, edgeNameIn2targetNode, edgeNameOut2targetNode, edgeName2inMultips, edgeName2outMultips,
			allEdgesText)

	}

	def updateEdgeList(AbstractNodeDescriptor currentNode, Map<Object, Integer> relevantObjectToID,
		Map<IncomingRelation, Object> edgeNameIn2targetNode, 
		Map<OutgoingRelation, Object> edgeNameOut2targetNode,
		Map<IncomingRelation, List<Integer>> edgeName2inMultips,
		Map<OutgoingRelation, List<Integer>> edgeName2outMultips, List<CharSequence> allEdgesTexts) {

		for (outEdge : edgeNameOut2targetNode.keySet) {
			// TODO
			val sourceID = currentNode.lookup(relevantObjectToID)
			val targetNode = outEdge.lookup(edgeNameOut2targetNode)
			val targetID = targetNode.lookup(relevantObjectToID)
			val edgeName = outEdge.type
			
			//finding corresponding Incoming edge
			var correspInEdgeSet = edgeNameIn2targetNode.keySet.filter[type.equals(edgeName) && lookup(edgeNameIn2targetNode).equals(currentNode)]
			println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy")
			println(currentNode)
			println(outEdge)
			println(targetNode)
			println(edgeNameIn2targetNode)
			println(correspInEdgeSet.size)
			val correspInEdge = correspInEdgeSet.get(0)
			

			allEdgesTexts.add(
				'''
					edge
						[
							source  «sourceID»
							target  «targetID»
							label	"«edgeName»"
							graphics
							[
								fill	"#000000"
								targetArrow	"standard"
								Line
								[
								]
							]
							LabelGraphics
							[
								text	"«edgeName»"
								fontSize	12
								fontName	"Dialog"
								configuration	"AutoFlippingLabel"
								model	"six_pos"
								position	"head"
							]
							«IF edgeName2outMultips.containsKey(outEdge)»
								LabelGraphics
								[
									text	"«outEdge.lookup(edgeName2outMultips).toString»"
									fontSize	12
									fontName	"Dialog"
									configuration	"AutoFlippingLabel"
									model	"six_pos"
									position	"stail"
								]
							«ENDIF»
							«IF edgeName2inMultips.containsKey(correspInEdge)»
								LabelGraphics
								[
									text	"«correspInEdge.lookup(edgeName2inMultips).toString»"
									fontSize	12
									fontName	"Dialog"
									configuration	"AutoFlippingLabel"
									model	"six_pos"
									position	"ttail"
								]
							«ENDIF»
						]
				'''
			)
		}
	}

	def calculateDepth(Object node) {
		var keyDescriptor = node

		while (!keyDescriptor.class.equals(LocalNodeDescriptor)) {
			keyDescriptor = (keyDescriptor as FurtherNodeDescriptor).previousRepresentation
			depth++
		}

	}

	def protected transformNode(Object nodeKey, Map<Object, Integer> modRep, Map<Object, Integer> objectToID) {

		var topKeyDescriptor = nodeKey

		while (!topKeyDescriptor.class.equals(LocalNodeDescriptor)) {
			topKeyDescriptor = (topKeyDescriptor as FurtherNodeDescriptor).previousRepresentation
		}

		var keyType = (topKeyDescriptor as LocalNodeDescriptor).types

		if (!keyType.empty) {
			val id = objectToID.size
			objectToID.put(nodeKey, id)
			'''
				node
					[
						id	«id»
						graphics
						[
							w	«((keyType.toString.length+4)*nodeNameSize + 2 * borderDistance)* ratio»
							h	«nodeHeight»
							type	"roundrectangle"
							fill	"#FFCC00"
							outline	"#000000"
						]
						LabelGraphics
						[
						]
						LabelGraphics
						[
							text	"«keyType.toString» («nodeKey.lookup(modRep)»)" 
							fontSize	«nodeNameSize»
							fontName	"Dialog"
						]
					]
			'''
		}
	}

	def protected transformEdges(FurtherNodeDescriptor deepNodeKey, Map<Object, Integer> deepModRep,
		Map<Object, Integer> objectToID) {
		print(depth)
		println(deepNodeKey.incomingEdges)
		println(deepNodeKey.outgoingEdges)
	// get 
	}

	def mapPrinter(Map x) {
		for (m : x.keySet) {
			println("New Entry --")
			println("    key = " + m)
			println("    value = " + m.lookup(x))
		}
	}

	def printer2(Set<FurtherNodeDescriptor> descriptors) {
		for (x : descriptors) {
			println(x)
		}
	}
}
