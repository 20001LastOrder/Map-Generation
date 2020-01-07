/*******************************************************************************
 * Copyright (c) 2010-2017, Andras Szabolcs Nagy and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *   Andras Szabolcs Nagy - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.inf.dslreasoner.viatrasolver.reasoner.dse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.viatra.dse.api.strategy.interfaces.IStrategy;
import org.eclipse.viatra.dse.base.ThreadContext;
import org.eclipse.viatra.dse.objectives.Fitness;
import org.eclipse.viatra.dse.objectives.ObjectiveComparatorHelper;
import org.eclipse.viatra.dse.solutionstore.SolutionStore;
import org.eclipse.viatra.query.runtime.api.GenericPatternMatch;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.ViatraQueryMatcher;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import org.eclipse.xtext.xbase.lib.Pair;

import hu.bme.mit.inf.dslreasoner.logic.model.builder.DocumentationLevel;
import hu.bme.mit.inf.dslreasoner.logic.model.builder.LogicReasoner;
import hu.bme.mit.inf.dslreasoner.logic.model.logiclanguage.DefinedElement;
import hu.bme.mit.inf.dslreasoner.logic.model.logicproblem.LogicProblem;
import hu.bme.mit.inf.dslreasoner.logic.model.logicresult.InconsistencyResult;
import hu.bme.mit.inf.dslreasoner.logic.model.logicresult.LogicResult;
import hu.bme.mit.inf.dslreasoner.logic.model.logicresult.ModelResult;
import hu.bme.mit.inf.dslreasoner.viatrasolver.logic2viatra.ModelGenerationMethod;
import hu.bme.mit.inf.dslreasoner.viatrasolver.partialinterpretation2logic.PartialInterpretation2Logic;
import hu.bme.mit.inf.dslreasoner.viatrasolver.partialinterpretationlanguage.partialinterpretation.PartialInterpretation;
import hu.bme.mit.inf.dslreasoner.viatrasolver.partialinterpretationlanguage.visualisation.PartialInterpretationVisualisation;
import hu.bme.mit.inf.dslreasoner.viatrasolver.partialinterpretationlanguage.visualisation.PartialInterpretationVisualiser;
import hu.bme.mit.inf.dslreasoner.viatrasolver.reasoner.ViatraReasonerConfiguration;
import hu.bme.mit.inf.dslreasoner.workspace.ReasonerWorkspace;
import umontreal.ssj.randvar.BernoulliGen;
import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.rng.MRG31k3p;
import umontreal.ssj.rng.RandomStream;

/**
 * This exploration strategy eventually explorers the whole design space but
 * goes in the most promising directions first, based on the {@link Fitness}.
 * 
 * There are a few parameter to tune such as
 * <ul>
 * <li>maximum depth</li>
 * <li>continue the exploration from a state that satisfies the hard objectives
 * (the default that it will backtrack),</li>
 * <li>whether to continue the exploration from the newly explored state if it
 * is at least equally good than the previous one or only if it is better
 * (default is "at least equally good").</li>
 * </ul>
 * 
 */
public class SimulationBasedSearchStrategy implements IStrategy {
	
	// Services and Configuration
	private ThreadContext context;
	private ReasonerWorkspace workspace;
	private ViatraReasonerConfiguration configuration;
	private ModelGenerationMethod method;
	private PartialInterpretation2Logic partialInterpretation2Logic = new PartialInterpretation2Logic();
	private Comparator<TrajectoryWithFitness> comparator;
	private Logger logger = Logger.getLogger(IStrategy.class);
	
	// Running
	private PriorityQueue<TrajectoryWithFitness> trajectoiresToExplore;
	private SolutionStore solutionStore;
	private SolutionStoreWithCopy solutionStoreWithCopy;
	private SolutionStoreWithDiversityDescriptor solutionStoreWithDiversityDescriptor;
	private volatile boolean isInterrupted = false;
	private ModelResult modelResultByInternalSolver = null;
	private Random random = new Random();
	private List<ViatraQueryMatcher<? extends IPatternMatch>> matchers;
	private RandomVariateGen r;
	
	
	// Statistics
	private int numberOfStatecoderFail = 0;
	private int numberOfPrintedModel = 0;
	private int numberOfSolverCalls = 0;

	public SimulationBasedSearchStrategy(
			ReasonerWorkspace workspace,
			ViatraReasonerConfiguration configuration,
			ModelGenerationMethod method)
	{
		this.workspace = workspace;
		this.configuration = configuration;
		this.method = method;
	}
	
	public SolutionStoreWithCopy getSolutionStoreWithCopy() {
		return solutionStoreWithCopy;
	}
	public SolutionStoreWithDiversityDescriptor getSolutionStoreWithDiversityDescriptor() {
		return solutionStoreWithDiversityDescriptor;
	}
	public int getNumberOfStatecoderFail() {
		return numberOfStatecoderFail;
	}

	@Override
	public void initStrategy(ThreadContext context) {
		this.context = context;
		this.solutionStore = context.getGlobalContext().getSolutionStore();
		ViatraQueryEngine engine = context.getQueryEngine();
//		// TODO: visualisation
		matchers = new LinkedList<ViatraQueryMatcher<? extends IPatternMatch>>();
		for(IQuerySpecification<? extends ViatraQueryMatcher<? extends IPatternMatch>> p : this.method.getAllPatterns()) {
			String name = p.getSimpleName();
			if(name.equals("currentInRelation_pattern_queries_islandNoNeighbour") 
					|| name.equals("currentInRelation_pattern_queries_seaNoNeighbour")) {
				ViatraQueryMatcher<? extends IPatternMatch> matcher = p.getMatcher(engine);
				matchers.add(matcher);
				System.out.println(name);
			}
			
		}
		
		//create a bernoulli rm generator
		RandomStream stream1 = new MRG31k3p();
		r = new BernoulliGen (stream1, 1); 
		
		this.solutionStoreWithCopy = new SolutionStoreWithCopy();
		this.solutionStoreWithDiversityDescriptor = new SolutionStoreWithDiversityDescriptor(configuration.diversityRequirement);

		final ObjectiveComparatorHelper objectiveComparatorHelper = context.getObjectiveComparatorHelper();
		this.comparator = new Comparator<TrajectoryWithFitness>() {
			@Override
			public int compare(TrajectoryWithFitness o1, TrajectoryWithFitness o2) {
				return objectiveComparatorHelper.compare(o2.fitness, o1.fitness);
			}
		};
		
		List<BatchTransformationRule<?, ?>> rulesToDelete = new ArrayList<>();
		for(BatchTransformationRule<?, ?> rule : context.getGlobalContext().getTransformations()) {
			if(!rule.getRuleSpecification().getName().matches(".*(Grid).*(Grid)")) {
				rulesToDelete.add(rule);
				context.getGlobalContext().getSpecificationRuleMap().remove(rule.getRuleSpecification());
			}
		}
		
		context.getGlobalContext().getTransformations().removeAll(rulesToDelete);
		
		trajectoiresToExplore = new PriorityQueue<TrajectoryWithFitness>(11, comparator);
	}

	@Override
	public void explore() {
		if (!context.checkGlobalConstraints()) {
			logger.info("Global contraint is not satisifed in the first state. Terminate.");
			return;
		}
		if (configuration.searchSpaceConstraints.maxDepth == 0) {
			logger.info("Maximal depth is reached in the initial solution. Terminate.");
			return;
		}
		
		final Fitness firstFittness = context.calculateFitness();
		//checkForSolution(firstFittness);
		
		final ObjectiveComparatorHelper objectiveComparatorHelper = context.getObjectiveComparatorHelper();
		final Object[] firstTrajectory = context.getTrajectory().toArray(new Object[0]);
		TrajectoryWithFitness currentTrajectoryWithFittness = new TrajectoryWithFitness(firstTrajectory, firstFittness);
		trajectoiresToExplore.add(currentTrajectoryWithFittness);
		
		
		
		
		//if(configuration)
		visualiseCurrentState();
//		for(ViatraQueryMatcher<? extends IPatternMatch> matcher : matchers) {
//			System.out.println(matcher.getPatternName());
//			System.out.println("---------");
//			for(IPatternMatch m : matcher.getAllMatches()) {
//				System.out.println(m);
//			}
//			System.out.println("---------");
//		}

		int i = 0;
		
		mainLoop: while (!isInterrupted && !configuration.progressMonitor.isCancelled()) {

			if (currentTrajectoryWithFittness == null) {
				if (trajectoiresToExplore.isEmpty()) {
					logger.debug("State space is fully traversed.");
					return;
				} else {
					currentTrajectoryWithFittness = selectState();
					if (logger.isDebugEnabled()) {
						logger.debug("Current trajectory: " + Arrays.toString(context.getTrajectory().toArray()));
						logger.debug("New trajectory is chosen: " + currentTrajectoryWithFittness);
					}
					context.getDesignSpaceManager().executeTrajectoryWithMinimalBacktrackWithoutStateCoding(currentTrajectoryWithFittness.trajectory);
				}
			}
			
		
			List<Object> activationIds = selectActivation();
			activationIds.removeIf(it -> ((Pair<String, Integer>)it).getKey().matches(".*(Grid).*(Grid)"));
			
			List<Object> activationIdsToExecute = new ArrayList<>();
			
			if(i > 3) {
				findActivation(activationIds, activationIdsToExecute, 1, "Sea", "Island");
				findActivation(activationIds, activationIdsToExecute, 0, "Island", "Sea");
			}

			Iterator<Object> iterator = activationIds.iterator();
			if(activationIdsToExecute.size() > 0) {
				 Collections.shuffle(activationIdsToExecute);
				 iterator = activationIdsToExecute.iterator();
			}
			i ++;
			while (!isInterrupted && !configuration.progressMonitor.isCancelled() && iterator.hasNext()) {
				final Object nextActivation = iterator.next();
//				if (!iterator.hasNext()) {
//					logger.debug("Last untraversed activation of the state.");
//					trajectoiresToExplore.remove(currentTrajectoryWithFittness);
//				}
				logger.debug("Executing new activation: " + nextActivation);
				context.executeAcitvationId(nextActivation);

				visualiseCurrentState();
				
				boolean consistencyCheckResult = checkConsistency(currentTrajectoryWithFittness);
				if(consistencyCheckResult == true) { continue mainLoop; }

				if (context.isCurrentStateAlreadyTraversed()) {
					logger.info("The new state is already visited.");
					context.backtrack();
				} else if (!context.checkGlobalConstraints()) {
					logger.debug("Global contraint is not satisifed.");
					context.backtrack();
				} else {
					final Fitness nextFitness = context.calculateFitness();
					checkForSolution(nextFitness);
					if (context.getDepth() > configuration.searchSpaceConstraints.maxDepth) {
						logger.debug("Reached max depth.");
						context.backtrack();
						continue;
					}

					TrajectoryWithFitness nextTrajectoryWithFittness = new TrajectoryWithFitness(
							context.getTrajectory().toArray(), nextFitness);
//					trajectoiresToExplore.add(nextTrajectoryWithFittness);

					int compare = objectiveComparatorHelper.compare(currentTrajectoryWithFittness.fitness,
							nextTrajectoryWithFittness.fitness);
					if (compare < 0) {
						logger.debug("Better fitness, moving on: " + nextFitness);
						currentTrajectoryWithFittness = nextTrajectoryWithFittness;
						continue mainLoop;
					} else if (compare == 0) {
						logger.debug("Equally good fitness, moving on: " + nextFitness);
						currentTrajectoryWithFittness = nextTrajectoryWithFittness;
						continue mainLoop;
					} else {
						logger.debug("Worse fitness.");
						currentTrajectoryWithFittness = null;
						continue mainLoop;
					}
				}
			}

			logger.debug("State is fully traversed.");
			trajectoiresToExplore.remove(currentTrajectoryWithFittness);
			currentTrajectoryWithFittness = null;

		}
		logger.info("Interrupted.");
	}
	
	private void findActivation(List<Object> activationIds, List<Object> activationIdsToExecute, int position, String match, String other) {
		for(IPatternMatch m : matchers.get(position).getAllMatches()) {
			String name = ((DefinedElement)m.get(3)).getName();
			int sample = (int)r.nextDouble();
			for(Object id : activationIds) {
				DefinedElement a = (DefinedElement)((GenericPatternMatch)context.getActivationById(id).getAtom()).get(5);
				if(sample == 1 && name.equals((a.getName())) && ((Pair<String, Integer>)id).getKey().contains(match)){
					activationIdsToExecute.add(id);
				}else if(sample == 0 && name.equals((a.getName())) && ((Pair<String, Integer>)id).getKey().contains(other)) {
					activationIdsToExecute.add(id);
				}
			}
		}
	}

	private List<Object> selectActivation() {
		List<Object> activationIds;
		try {
			activationIds = new ArrayList<Object>(context.getUntraversedActivationIds());
			Collections.shuffle(activationIds);
		} catch (NullPointerException e) {
			numberOfStatecoderFail++;
			activationIds = Collections.emptyList();
		}
		return activationIds;
	}

	private void checkForSolution(final Fitness fittness) {
		if (fittness.isSatisifiesHardObjectives()) {
			if (solutionStoreWithDiversityDescriptor.isDifferent(context)) {
				solutionStoreWithCopy.newSolution(context);
				solutionStoreWithDiversityDescriptor.newSolution(context);
				solutionStore.newSolution(context);
				configuration.progressMonitor.workedModelFound(configuration.solutionScope.numberOfRequiredSolution);

				logger.debug("Found a solution.");
			}
		}
	}

	@Override
	public void interruptStrategy() {
		isInterrupted = true;
	}

	public void actionSelection(List<Object> actions) {
		
	}

	private TrajectoryWithFitness selectState() {
		int randomNumber = random.nextInt(configuration.randomBacktrackChance);
		if (randomNumber == 0) {
			int elements = trajectoiresToExplore.size();
			int randomElementIndex = random.nextInt(elements);
			logger.debug("Randomly backtract to the " + randomElementIndex + " best solution...");
			Iterator<TrajectoryWithFitness> iterator = trajectoiresToExplore.iterator();
			while (randomElementIndex != 0) {
				iterator.next();
				randomElementIndex--;
			}
			TrajectoryWithFitness res = iterator.next();
			if (res == null) {
				return trajectoiresToExplore.element();
			} else {
				return res;
			}
		} else {
			return trajectoiresToExplore.element();
		}
	}	
	
	public void visualiseCurrentState() {
		PartialInterpretationVisualiser partialInterpretatioVisualiser = configuration.debugCongiguration.partialInterpretatioVisualiser;
		if(partialInterpretatioVisualiser != null && this.configuration.documentationLevel == DocumentationLevel.FULL && workspace != null) {
			PartialInterpretation p = (PartialInterpretation) (context.getModel());
			int id = ++numberOfPrintedModel;
			if (id % configuration.debugCongiguration.partalInterpretationVisualisationFrequency == 0) {
				PartialInterpretationVisualisation visualisation = partialInterpretatioVisualiser.visualiseConcretization(p);
				visualisation.writeToFile(workspace, String.format("state%09d.png", id));
			}
		}
	}

	protected boolean checkConsistency(TrajectoryWithFitness t) {
		LogicReasoner internalIncosnsitencyDetector = configuration.internalConsistencyCheckerConfiguration.internalIncosnsitencyDetector;
		if (internalIncosnsitencyDetector!= null) {
			int id = ++numberOfSolverCalls;
			if (id % configuration.internalConsistencyCheckerConfiguration.incternalConsistencyCheckingFrequency == 0) {
				try {
					PartialInterpretation interpretation = (PartialInterpretation) (context.getModel());
					PartialInterpretation copied = EcoreUtil.copy(interpretation);
					this.partialInterpretation2Logic.transformPartialIntepretation2Logic(copied.getProblem(), copied);
					LogicProblem newProblem = copied.getProblem();

					this.configuration.typeScopes.maxNewElements = interpretation.getMaxNewElements();
					this.configuration.typeScopes.minNewElements = interpretation.getMinNewElements();
					LogicResult result = internalIncosnsitencyDetector.solve(newProblem, configuration, workspace);
					if (result instanceof InconsistencyResult) {
						logger.debug("Solver found an Inconsistency!");
						removeSubtreeFromQueue(t);
						return true;
					} else if (result instanceof ModelResult) {
						logger.debug("Solver found a model!");
						solutionStore.newSolution(context);

						this.modelResultByInternalSolver = (ModelResult) result;
						return true;
					} else {
						logger.debug("Failed consistency check.");
						return false;
					}
				} catch (Exception e) {
					logger.debug("Problem with internal consistency checking: "+e.getMessage());
					e.printStackTrace();
					return false;
				}
			}

		}
		return false;
	}

	protected void removeSubtreeFromQueue(TrajectoryWithFitness t) {
		PriorityQueue<TrajectoryWithFitness> previous = this.trajectoiresToExplore;
		this.trajectoiresToExplore = new PriorityQueue<>(this.comparator);
		for (TrajectoryWithFitness trajectoryWithFitness : previous) {
			if (!containsAsSubstring(trajectoryWithFitness.trajectory, t.trajectory)) {
				this.trajectoiresToExplore.add(trajectoryWithFitness);
			} else {
				logger.debug("State has been excluded due to inherent inconsistency");
			}
		}
	}

	private boolean containsAsSubstring(Object[] full, Object[] substring) {
		if (substring.length > full.length) {
			return false;
		} else if (substring.length == full.length) {
			return Arrays.equals(full, substring);
		} else {
			Object[] part = Arrays.copyOfRange(full, 0, substring.length);
			return Arrays.equals(part, substring);
		}
	}
}