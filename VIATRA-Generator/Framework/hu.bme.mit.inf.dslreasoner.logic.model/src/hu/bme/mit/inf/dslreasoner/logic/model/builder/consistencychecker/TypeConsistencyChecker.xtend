package hu.bme.mit.inf.dslreasoner.logic.model.builder.consistencychecker

import hu.bme.mit.inf.dslreasoner.logic.model.builder.LogicReasoner
import hu.bme.mit.inf.dslreasoner.logic.model.builder.LogicReasonerException
import hu.bme.mit.inf.dslreasoner.logic.model.builder.LogicSolverConfiguration
import hu.bme.mit.inf.dslreasoner.logic.model.logicproblem.LogicProblem
import hu.bme.mit.inf.dslreasoner.logic.model.logicproblem.LogicproblemPackage
import hu.bme.mit.inf.dslreasoner.logic.model.logicresult.LogicresultFactory
import hu.bme.mit.inf.dslreasoner.logic.model.logicresult.ModelResult
import hu.bme.mit.inf.dslreasoner.logic.model.patterns.ElementNotDefinedInSupertype
import hu.bme.mit.inf.dslreasoner.logic.model.patterns.ElementWithNoPossibleDynamicType
import hu.bme.mit.inf.dslreasoner.logic.model.patterns.PossibleDynamicType
import hu.bme.mit.inf.dslreasoner.logic.model.patterns.TypeSystemIsInconsistent
import hu.bme.mit.inf.dslreasoner.workspace.ReasonerWorkspace
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope

class TypeConsistencyChecker extends LogicReasoner{
	val extension LogicresultFactory factory = LogicresultFactory.eINSTANCE
		
	new() {
		LogicproblemPackage.eINSTANCE.class
	}
	
	override solve(LogicProblem problem, LogicSolverConfiguration configuration, ReasonerWorkspace workspace) throws LogicReasonerException {
		val statistics = createStatistics => [
			it.transformationTime = 0
			it.solverMemory = -1
		]
		
		val start = System.currentTimeMillis
		
		val queryEngine = ViatraQueryEngine.on(new EMFScope(problem))
		
		val typeSystemInconsistencyMatcher = TypeSystemIsInconsistent.Matcher.on(queryEngine)
		val elementNotDefinedInSupertype = ElementNotDefinedInSupertype.Matcher.on(queryEngine)
		val elementWithNoPossibleDynamicType = ElementWithNoPossibleDynamicType.Matcher.on(queryEngine)
		val possibleDynamicType = PossibleDynamicType.Matcher.on(queryEngine)
		
		val hasErrorPatternMatch = typeSystemInconsistencyMatcher.hasMatch(problem)
		
		statistics.solverTime = (System.currentTimeMillis - start) as int
		
		val possibleDynamicTypeStatisticEntry = problem.elements.map[e|
			'''«e.name»: [«possibleDynamicType.getAllValuesOfdynamic(problem,e).map[it.name].join(", ")»]'''
		].join("\n")
		
		if(hasErrorPatternMatch) {
			return createInconsistencyResult =>[
				it.problem = problem 
				it.statistics = statistics 
				it.statistics.entries += createStringStatisticEntry => [
					it.name = "possibleDynamicType"
					it.value = possibleDynamicTypeStatisticEntry
				]
				it.statistics.entries += createStringStatisticEntry => [
						it.name = "elementNotDefinedInSupertype"
						it.value=elementNotDefinedInSupertype.allValuesOfelement.map[it.name].join(", ")
					]
				
				it.statistics.entries += createStringStatisticEntry => [
					it.name = "elementWithNoPossibleDynamicType"
					it.value=elementWithNoPossibleDynamicType.allValuesOfelement.map[it.name].join(", ")
				]
			]
		} else {
			return createUndecidableResult => 
				[it.problem = problem it.statistics = statistics]
		}
	}
	
	override getInterpretations(ModelResult modelResult) {
		throw new UnsupportedOperationException('''This solver is unable to create interpretations!''')
	}
}