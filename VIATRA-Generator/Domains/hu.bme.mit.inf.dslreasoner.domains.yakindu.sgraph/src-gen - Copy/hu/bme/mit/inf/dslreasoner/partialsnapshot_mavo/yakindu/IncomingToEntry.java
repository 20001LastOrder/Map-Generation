/**
 * Generated from platform:/resource/hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph/queries/hu/bme/mit/inf/dslreasoner/partialsnapshot_mavo/yakindu/patterns.vql
 */
package hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu;

import hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Entry;
import hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
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
 *         {@literal @}Constraint(severity="error", message="error", key = {e})
 *         pattern incomingToEntry(t : Transition, e : Entry) {
 *         	find transition(t, _, e);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class IncomingToEntry extends BaseGeneratedEMFQuerySpecification<IncomingToEntry.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.incomingToEntry pattern,
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
    private Transition fT;
    
    private Entry fE;
    
    private static List<String> parameterNames = makeImmutableList("t", "e");
    
    private Match(final Transition pT, final Entry pE) {
      this.fT = pT;
      this.fE = pE;
    }
    
    @Override
    public Object get(final String parameterName) {
      if ("t".equals(parameterName)) return this.fT;
      if ("e".equals(parameterName)) return this.fE;
      return null;
    }
    
    public Transition getT() {
      return this.fT;
    }
    
    public Entry getE() {
      return this.fE;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("t".equals(parameterName) ) {
          this.fT = (Transition) newValue;
          return true;
      }
      if ("e".equals(parameterName) ) {
          this.fE = (Entry) newValue;
          return true;
      }
      return false;
    }
    
    public void setT(final Transition pT) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fT = pT;
    }
    
    public void setE(final Entry pE) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fE = pE;
    }
    
    @Override
    public String patternName() {
      return "hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.incomingToEntry";
    }
    
    @Override
    public List<String> parameterNames() {
      return IncomingToEntry.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fT, fE};
    }
    
    @Override
    public IncomingToEntry.Match toImmutable() {
      return isMutable() ? newMatch(fT, fE) : this;
    }
    
    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"t\"=" + prettyPrintValue(fT) + ", ");
      result.append("\"e\"=" + prettyPrintValue(fE));
      return result.toString();
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(fT, fE);
    }
    
    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof IncomingToEntry.Match)) {
          IncomingToEntry.Match other = (IncomingToEntry.Match) obj;
          return Objects.equals(fT, other.fT) && Objects.equals(fE, other.fE);
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
    public IncomingToEntry specification() {
      return IncomingToEntry.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static IncomingToEntry.Match newEmptyMatch() {
      return new Mutable(null, null);
    }
    
    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pE the fixed value of pattern parameter e, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static IncomingToEntry.Match newMutableMatch(final Transition pT, final Entry pE) {
      return new Mutable(pT, pE);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pE the fixed value of pattern parameter e, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static IncomingToEntry.Match newMatch(final Transition pT, final Entry pE) {
      return new Immutable(pT, pE);
    }
    
    private static final class Mutable extends IncomingToEntry.Match {
      Mutable(final Transition pT, final Entry pE) {
        super(pT, pE);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends IncomingToEntry.Match {
      Immutable(final Transition pT, final Entry pE) {
        super(pT, pE);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.incomingToEntry pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * {@literal @}Constraint(severity="error", message="error", key = {e})
   * pattern incomingToEntry(t : Transition, e : Entry) {
   * 	find transition(t, _, e);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see IncomingToEntry
   * 
   */
  public static class Matcher extends BaseMatcher<IncomingToEntry.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static IncomingToEntry.Matcher on(final ViatraQueryEngine engine) {
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
    public static IncomingToEntry.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_T = 0;
    
    private static final int POSITION_E = 1;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(IncomingToEntry.Matcher.class);
    
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
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pE the fixed value of pattern parameter e, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<IncomingToEntry.Match> getAllMatches(final Transition pT, final Entry pE) {
      return rawStreamAllMatches(new Object[]{pT, pE}).collect(Collectors.toSet());
    }
    
    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pE the fixed value of pattern parameter e, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<IncomingToEntry.Match> streamAllMatches(final Transition pT, final Entry pE) {
      return rawStreamAllMatches(new Object[]{pT, pE});
    }
    
    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pE the fixed value of pattern parameter e, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<IncomingToEntry.Match> getOneArbitraryMatch(final Transition pT, final Entry pE) {
      return rawGetOneArbitraryMatch(new Object[]{pT, pE});
    }
    
    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pE the fixed value of pattern parameter e, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final Transition pT, final Entry pE) {
      return rawHasMatch(new Object[]{pT, pE});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pE the fixed value of pattern parameter e, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final Transition pT, final Entry pE) {
      return rawCountMatches(new Object[]{pT, pE});
    }
    
    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pE the fixed value of pattern parameter e, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final Transition pT, final Entry pE, final Consumer<? super IncomingToEntry.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pT, pE}, processor);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pE the fixed value of pattern parameter e, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public IncomingToEntry.Match newMatch(final Transition pT, final Entry pE) {
      return IncomingToEntry.Match.newMatch(pT, pE);
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Transition> rawStreamAllValuesOft(final Object[] parameters) {
      return rawStreamAllValues(POSITION_T, parameters).map(Transition.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Transition> getAllValuesOft() {
      return rawStreamAllValuesOft(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Transition> streamAllValuesOft() {
      return rawStreamAllValuesOft(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Transition> streamAllValuesOft(final IncomingToEntry.Match partialMatch) {
      return rawStreamAllValuesOft(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Transition> streamAllValuesOft(final Entry pE) {
      return rawStreamAllValuesOft(new Object[]{null, pE});
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Transition> getAllValuesOft(final IncomingToEntry.Match partialMatch) {
      return rawStreamAllValuesOft(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Transition> getAllValuesOft(final Entry pE) {
      return rawStreamAllValuesOft(new Object[]{null, pE}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for e.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Entry> rawStreamAllValuesOfe(final Object[] parameters) {
      return rawStreamAllValues(POSITION_E, parameters).map(Entry.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for e.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Entry> getAllValuesOfe() {
      return rawStreamAllValuesOfe(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for e.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Entry> streamAllValuesOfe() {
      return rawStreamAllValuesOfe(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for e.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Entry> streamAllValuesOfe(final IncomingToEntry.Match partialMatch) {
      return rawStreamAllValuesOfe(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for e.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Entry> streamAllValuesOfe(final Transition pT) {
      return rawStreamAllValuesOfe(new Object[]{pT, null});
    }
    
    /**
     * Retrieve the set of values that occur in matches for e.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Entry> getAllValuesOfe(final IncomingToEntry.Match partialMatch) {
      return rawStreamAllValuesOfe(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for e.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Entry> getAllValuesOfe(final Transition pT) {
      return rawStreamAllValuesOfe(new Object[]{pT, null}).collect(Collectors.toSet());
    }
    
    @Override
    protected IncomingToEntry.Match tupleToMatch(final Tuple t) {
      try {
          return IncomingToEntry.Match.newMatch((Transition) t.get(POSITION_T), (Entry) t.get(POSITION_E));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected IncomingToEntry.Match arrayToMatch(final Object[] match) {
      try {
          return IncomingToEntry.Match.newMatch((Transition) match[POSITION_T], (Entry) match[POSITION_E]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected IncomingToEntry.Match arrayToMatchMutable(final Object[] match) {
      try {
          return IncomingToEntry.Match.newMutableMatch((Transition) match[POSITION_T], (Entry) match[POSITION_E]);
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
    public static IQuerySpecification<IncomingToEntry.Matcher> querySpecification() {
      return IncomingToEntry.instance();
    }
  }
  
  private IncomingToEntry() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static IncomingToEntry instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected IncomingToEntry.Matcher instantiate(final ViatraQueryEngine engine) {
    return IncomingToEntry.Matcher.on(engine);
  }
  
  @Override
  public IncomingToEntry.Matcher instantiate() {
    return IncomingToEntry.Matcher.create();
  }
  
  @Override
  public IncomingToEntry.Match newEmptyMatch() {
    return IncomingToEntry.Match.newEmptyMatch();
  }
  
  @Override
  public IncomingToEntry.Match newMatch(final Object... parameters) {
    return IncomingToEntry.Match.newMatch((hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition) parameters[0], (hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Entry) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link JvmGenericType: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.IncomingToEntry (visibility: PUBLIC, simpleName: IncomingToEntry, identifier: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.IncomingToEntry, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu) (interface: false, strictFloatingPoint: false, anonymous: false)} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link JvmGenericType: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.IncomingToEntry (visibility: PUBLIC, simpleName: IncomingToEntry, identifier: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.IncomingToEntry, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu) (interface: false, strictFloatingPoint: false, anonymous: false)#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final IncomingToEntry INSTANCE = new IncomingToEntry();
    
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
    private static final IncomingToEntry.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_t = new PParameter("t", "hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("hu.bme.mit.inf.yakindumm", "Transition")), PParameterDirection.INOUT);
    
    private final PParameter parameter_e = new PParameter("e", "hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Entry", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("hu.bme.mit.inf.yakindumm", "Entry")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_t, parameter_e);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.incomingToEntry";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("t","e");
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
          PVariable var_t = body.getOrCreateVariableByName("t");
          PVariable var_e = body.getOrCreateVariableByName("e");
          PVariable var___0_ = body.getOrCreateVariableByName("_<0>");
          new TypeConstraint(body, Tuples.flatTupleOf(var_t), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Transition")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_e), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Entry")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_t, parameter_t),
             new ExportedParameter(body, var_e, parameter_e)
          ));
          // 	find transition(t, _, e)
          new PositivePatternCall(body, Tuples.flatTupleOf(var_t, var___0_, var_e), hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Transition.instance().getInternalQueryRepresentation());
          bodies.add(body);
      }
      {
          PAnnotation annotation = new PAnnotation("Constraint");
          annotation.addAttribute("severity", "error");
          annotation.addAttribute("message", "error");
          annotation.addAttribute("key", Arrays.asList(new Object[] {
                              new ParameterReference("e")
                              }));
          addAnnotation(annotation);
      }
      return bodies;
    }
  }
}
