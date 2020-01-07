/**
 * Generated from platform:/resource/hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph/queries/hu/bme/mit/inf/dslreasoner/partialsnapshot_mavo/yakindu/patterns.vql
 */
package hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu;

import hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Choice;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Transition;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.PAnnotation;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.ParameterReference;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PVisibility;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuples;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * A pattern-specific query specification that can instantiate Matcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         /////////
 *         // Choice
 *         /////////
 *         
 *         {@literal @}Constraint(severity="error", message="error", key = {c})
 *         pattern choiceHasNoOutgoing(c : Choice) {
 *         	neg find transition(_, c, _);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class ChoiceHasNoOutgoing extends BaseGeneratedEMFQuerySpecification<ChoiceHasNoOutgoing.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.choiceHasNoOutgoing pattern,
   * to be used in conjunction with {@link Matcher}.
   * 
   * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
   * Each instance is a (possibly partial) substitution of pattern parameters,
   * usable to represent a match of the pattern in the result of a query,
   * or to specify the bound (fixed) input parameters when issuing a query.
   * 
   * @see Matcher
   * 
   */
  public static abstract class Match extends BasePatternMatch {
    private Choice fC;
    
    private static List<String> parameterNames = makeImmutableList("c");
    
    private Match(final Choice pC) {
      this.fC = pC;
    }
    
    @Override
    public Object get(final String parameterName) {
      if ("c".equals(parameterName)) return this.fC;
      return null;
    }
    
    public Choice getC() {
      return this.fC;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("c".equals(parameterName) ) {
          this.fC = (Choice) newValue;
          return true;
      }
      return false;
    }
    
    public void setC(final Choice pC) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fC = pC;
    }
    
    @Override
    public String patternName() {
      return "hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.choiceHasNoOutgoing";
    }
    
    @Override
    public List<String> parameterNames() {
      return ChoiceHasNoOutgoing.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fC};
    }
    
    @Override
    public ChoiceHasNoOutgoing.Match toImmutable() {
      return isMutable() ? newMatch(fC) : this;
    }
    
    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"c\"=" + prettyPrintValue(fC));
      return result.toString();
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(fC);
    }
    
    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof ChoiceHasNoOutgoing.Match)) {
          ChoiceHasNoOutgoing.Match other = (ChoiceHasNoOutgoing.Match) obj;
          return Objects.equals(fC, other.fC);
      } else {
          // this should be infrequent
          if (!(obj instanceof IPatternMatch)) {
              return false;
          }
          IPatternMatch otherSig  = (IPatternMatch) obj;
          return Objects.equals(specification(), otherSig.specification()) && Arrays.deepEquals(toArray(), otherSig.toArray());
      }
    }
    
    @Override
    public ChoiceHasNoOutgoing specification() {
      return ChoiceHasNoOutgoing.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static ChoiceHasNoOutgoing.Match newEmptyMatch() {
      return new Mutable(null);
    }
    
    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static ChoiceHasNoOutgoing.Match newMutableMatch(final Choice pC) {
      return new Mutable(pC);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static ChoiceHasNoOutgoing.Match newMatch(final Choice pC) {
      return new Immutable(pC);
    }
    
    private static final class Mutable extends ChoiceHasNoOutgoing.Match {
      Mutable(final Choice pC) {
        super(pC);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends ChoiceHasNoOutgoing.Match {
      Immutable(final Choice pC) {
        super(pC);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.choiceHasNoOutgoing pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * /////////
   * // Choice
   * /////////
   * 
   * {@literal @}Constraint(severity="error", message="error", key = {c})
   * pattern choiceHasNoOutgoing(c : Choice) {
   * 	neg find transition(_, c, _);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see ChoiceHasNoOutgoing
   * 
   */
  public static class Matcher extends BaseMatcher<ChoiceHasNoOutgoing.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static ChoiceHasNoOutgoing.Matcher on(final ViatraQueryEngine engine) {
      // check if matcher already exists
      Matcher matcher = engine.getExistingMatcher(querySpecification());
      if (matcher == null) {
          matcher = (Matcher)engine.getMatcher(querySpecification());
      }
      return matcher;
    }
    
    /**
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * @return an initialized matcher
     * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
     * 
     */
    public static ChoiceHasNoOutgoing.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_C = 0;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ChoiceHasNoOutgoing.Matcher.class);
    
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    private Matcher() {
      super(querySpecification());
    }
    
    /**
     * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<ChoiceHasNoOutgoing.Match> getAllMatches(final Choice pC) {
      return rawStreamAllMatches(new Object[]{pC}).collect(Collectors.toSet());
    }
    
    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<ChoiceHasNoOutgoing.Match> streamAllMatches(final Choice pC) {
      return rawStreamAllMatches(new Object[]{pC});
    }
    
    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<ChoiceHasNoOutgoing.Match> getOneArbitraryMatch(final Choice pC) {
      return rawGetOneArbitraryMatch(new Object[]{pC});
    }
    
    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final Choice pC) {
      return rawHasMatch(new Object[]{pC});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final Choice pC) {
      return rawCountMatches(new Object[]{pC});
    }
    
    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final Choice pC, final Consumer<? super ChoiceHasNoOutgoing.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pC}, processor);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public ChoiceHasNoOutgoing.Match newMatch(final Choice pC) {
      return ChoiceHasNoOutgoing.Match.newMatch(pC);
    }
    
    /**
     * Retrieve the set of values that occur in matches for c.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Choice> rawStreamAllValuesOfc(final Object[] parameters) {
      return rawStreamAllValues(POSITION_C, parameters).map(Choice.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for c.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Choice> getAllValuesOfc() {
      return rawStreamAllValuesOfc(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for c.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Choice> streamAllValuesOfc() {
      return rawStreamAllValuesOfc(emptyArray());
    }
    
    @Override
    protected ChoiceHasNoOutgoing.Match tupleToMatch(final Tuple t) {
      try {
          return ChoiceHasNoOutgoing.Match.newMatch((Choice) t.get(POSITION_C));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected ChoiceHasNoOutgoing.Match arrayToMatch(final Object[] match) {
      try {
          return ChoiceHasNoOutgoing.Match.newMatch((Choice) match[POSITION_C]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected ChoiceHasNoOutgoing.Match arrayToMatchMutable(final Object[] match) {
      try {
          return ChoiceHasNoOutgoing.Match.newMutableMatch((Choice) match[POSITION_C]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    /**
     * @return the singleton instance of the query specification of this pattern
     * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
     * 
     */
    public static IQuerySpecification<ChoiceHasNoOutgoing.Matcher> querySpecification() {
      return ChoiceHasNoOutgoing.instance();
    }
  }
  
  private ChoiceHasNoOutgoing() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static ChoiceHasNoOutgoing instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected ChoiceHasNoOutgoing.Matcher instantiate(final ViatraQueryEngine engine) {
    return ChoiceHasNoOutgoing.Matcher.on(engine);
  }
  
  @Override
  public ChoiceHasNoOutgoing.Matcher instantiate() {
    return ChoiceHasNoOutgoing.Matcher.create();
  }
  
  @Override
  public ChoiceHasNoOutgoing.Match newEmptyMatch() {
    return ChoiceHasNoOutgoing.Match.newEmptyMatch();
  }
  
  @Override
  public ChoiceHasNoOutgoing.Match newMatch(final Object... parameters) {
    return ChoiceHasNoOutgoing.Match.newMatch((hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Choice) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link JvmGenericType: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.ChoiceHasNoOutgoing (visibility: PUBLIC, simpleName: ChoiceHasNoOutgoing, identifier: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.ChoiceHasNoOutgoing, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu) (interface: false, strictFloatingPoint: false, anonymous: false)} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link JvmGenericType: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.ChoiceHasNoOutgoing (visibility: PUBLIC, simpleName: ChoiceHasNoOutgoing, identifier: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.ChoiceHasNoOutgoing, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu) (interface: false, strictFloatingPoint: false, anonymous: false)#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final ChoiceHasNoOutgoing INSTANCE = new ChoiceHasNoOutgoing();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private static final Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternal();
      return null;
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private static final ChoiceHasNoOutgoing.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_c = new PParameter("c", "hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Choice", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("hu.bme.mit.inf.yakindumm", "Choice")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_c);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.choiceHasNoOutgoing";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("c");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() {
      setEvaluationHints(new QueryEvaluationHint(null, QueryEvaluationHint.BackendRequirement.UNSPECIFIED));
      Set<PBody> bodies = new LinkedHashSet<>();
      {
          PBody body = new PBody(this);
          PVariable var_c = body.getOrCreateVariableByName("c");
          PVariable var___0_ = body.getOrCreateVariableByName("_<0>");
          PVariable var___1_ = body.getOrCreateVariableByName("_<1>");
          new TypeConstraint(body, Tuples.flatTupleOf(var_c), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Choice")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_c, parameter_c)
          ));
          // 	neg find transition(_, c, _)
          new NegativePatternCall(body, Tuples.flatTupleOf(var___0_, var_c, var___1_), Transition.instance().getInternalQueryRepresentation());
          bodies.add(body);
      }
      {
          PAnnotation annotation = new PAnnotation("Constraint");
          annotation.addAttribute("severity", "error");
          annotation.addAttribute("message", "error");
          annotation.addAttribute("key", Arrays.asList(new Object[] {
                              new ParameterReference("c")
                              }));
          addAnnotation(annotation);
      }
      return bodies;
    }
  }
}
