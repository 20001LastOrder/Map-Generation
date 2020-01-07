package hu.bme.mit.inf.dslreasoner.viatrasolver.reasoner.dse

import java.util.List
import hu.bme.mit.inf.dslreasoner.viatrasolver.partialinterpretationlanguage.partialinterpretation.PartialInterpretation
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import java.util.Map
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.viatra.dse.base.ThreadContext
import java.util.TreeMap
import java.util.SortedMap

class SolutionStoreWithCopy {
	
	long runtime = 0
	List<PartialInterpretation> solutions = new LinkedList
	//public List<SortedMap<String,Integer>> additionalMatches = new LinkedList
	List<Map<EObject,EObject>> copyTraces = new LinkedList
	
	long sartTime = System.nanoTime
	List<Long> solutionTimes = new LinkedList
	
	/*def newSolution(ThreadContext context, SortedMap<String,Integer> additonalMatch) {
		additionalMatches+= additonalMatch
		newSolution(context)
	}*/
	
	def newSolution(ThreadContext context) {
		//print(System.nanoTime-initTime + ";")
		val copyStart = System.nanoTime
		val solution = context.model as PartialInterpretation
		val copier = new EcoreUtil.Copier
		val solutionCopy = copier.copy(solution) as PartialInterpretation
		copier.copyReferences
		solutions.add(solutionCopy)
		copyTraces.add(copier)
		runtime += System.nanoTime - copyStart
		solutionTimes.add(System.nanoTime-sartTime)
	}
	def getSumRuntime() {
		return runtime
	}
	def getAllRuntimes() {
		return solutionTimes
	}
	def getSolutions() {
		solutions
	}
	def getCopyTraces() {
		return copyTraces
	}
}