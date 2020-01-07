package hu.bme.mit.inf.dslreasoner.viatrasolver.logic2viatra

import hu.bme.mit.inf.dslreasoner.logic.model.builder.DocumentationLevel
import hu.bme.mit.inf.dslreasoner.logic.model.logicproblem.LogicProblem
import hu.bme.mit.inf.dslreasoner.viatra2logic.viatra2logicannotations.TransfomedViatraQuery
import hu.bme.mit.inf.dslreasoner.viatrasolver.logic2viatra.patterns.PatternProvider
import hu.bme.mit.inf.dslreasoner.viatrasolver.logic2viatra.rules.GoalConstraintProvider
import hu.bme.mit.inf.dslreasoner.viatrasolver.logic2viatra.rules.RefinementRuleProvider
import hu.bme.mit.inf.dslreasoner.viatrasolver.partialinterpretationlanguage.partialinterpretation.PartialInterpretation
import hu.bme.mit.inf.dslreasoner.workspace.ReasonerWorkspace
import java.util.Collection
import java.util.List
import java.util.Set
import org.eclipse.viatra.query.runtime.api.IPatternMatch
import org.eclipse.viatra.query.runtime.api.IQuerySpecification
import org.eclipse.viatra.query.runtime.api.ViatraQueryMatcher
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PQuery
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule
import org.eclipse.xtend.lib.annotations.Data

class ModelGenerationStatistics {
	public var long transformationExecutionTime = 0
	public var long metricCalculationTime = 0
	
	synchronized def addExecutionTime(long amount) {
		transformationExecutionTime+=amount
	}
	
	synchronized def addMetricCalculationTime(long amount) {
		metricCalculationTime+=amount
	}
	
	public var long PreliminaryTypeAnalisisTime = 0
}
@Data class ModelGenerationMethod {
	 ModelGenerationStatistics statistics
	 
	 Collection<? extends BatchTransformationRule<?,?>> objectRefinementRules
	 Collection<? extends BatchTransformationRule<?,?>> relationRefinementRules
	 
	 List<MultiplicityGoalConstraintCalculator> unfinishedMultiplicities
	 Collection<? extends IQuerySpecification<? extends ViatraQueryMatcher<? extends IPatternMatch>>> unfinishedWF
	 
	 Collection<? extends IQuerySpecification<? extends ViatraQueryMatcher<? extends IPatternMatch>>> invalidWF
	 
	 Collection<? extends IQuerySpecification<? extends ViatraQueryMatcher<? extends IPatternMatch>>> allPatterns
}
enum TypeInferenceMethod {
	Generic, PreliminaryAnalysis
}

class ModelGenerationMethodProvider {
	private val PatternProvider patternProvider = new PatternProvider
	private val RefinementRuleProvider refinementRuleProvider = new RefinementRuleProvider
	private val GoalConstraintProvider goalConstraintProvider = new GoalConstraintProvider
	
	public def ModelGenerationMethod createModelGenerationMethod(
		LogicProblem logicProblem,
		PartialInterpretation emptySolution,
		ReasonerWorkspace workspace,
		boolean nameNewElements,
		TypeInferenceMethod typeInferenceMethod,
		ScopePropagator scopePropagator,
		DocumentationLevel debugLevel
	) {
		val statistics = new ModelGenerationStatistics
		val writeFiles = (debugLevel === DocumentationLevel.NORMAL || debugLevel === DocumentationLevel.FULL)
		
		val Set<PQuery> existingQueries = logicProblem
			.relations
			.map[annotations]
			.flatten
			.filter(TransfomedViatraQuery)
			.map[it.patternPQuery as PQuery]
			.toSet
		
		val queries = patternProvider.generateQueries(logicProblem,emptySolution,statistics,existingQueries,workspace,typeInferenceMethod,writeFiles) 
		val //LinkedHashMap<Pair<Relation, ? extends Type>, BatchTransformationRule<GenericPatternMatch, ViatraQueryMatcher<GenericPatternMatch>>>
			objectRefinementRules = refinementRuleProvider.createObjectRefinementRules(queries,scopePropagator,nameNewElements,statistics)
		val relationRefinementRules = refinementRuleProvider.createRelationRefinementRules(queries,statistics)
		
		val unfinishedMultiplicities = goalConstraintProvider.getUnfinishedMultiplicityQueries(queries)
		val unfinishedWF = queries.getUnfinishedWFQueries.values
		
		val invalidWF = queries.getInvalidWFQueries.values
		
		return new ModelGenerationMethod(
			statistics,
			objectRefinementRules.values,
			relationRefinementRules.values,
			unfinishedMultiplicities,
			unfinishedWF,
			invalidWF,
			queries.allQueries
		)
	}
}
