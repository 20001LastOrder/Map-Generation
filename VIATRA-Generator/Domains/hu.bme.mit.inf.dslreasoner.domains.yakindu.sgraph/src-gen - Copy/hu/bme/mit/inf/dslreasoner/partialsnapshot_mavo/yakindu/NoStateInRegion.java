/**
 * Generated from platform:/resource/hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph/queries/hu/bme/mit/inf/dslreasoner/partialsnapshot_mavo/yakindu/patterns.vql
 */
package hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu;

import hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Region;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.StateInRegion;
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
 *         // State vs Region
 *         /////////
 *         
 *         {@literal @}Constraint(severity="error", message="error", key = {region})
 *         pattern noStateInRegion(region: Region) {
 *         	neg find StateInRegion(region,_);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class NoStateInRegion extends BaseGeneratedEMFQuerySpecification<NoStateInRegion.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.noStateInRegion pattern,
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
    private Region fRegion;
    
    private static List<String> parameterNames = makeImmutableList("region");
    
    private Match(final Region pRegion) {
      this.fRegion = pRegion;
    }
    
    @Override
    public Object get(final String parameterName) {
      if ("region".equals(parameterName)) return this.fRegion;
      return null;
    }
    
    public Region getRegion() {
      return this.fRegion;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("region".equals(parameterName) ) {
          this.fRegion = (Region) newValue;
          return true;
      }
      return false;
    }
    
    public void setRegion(final Region pRegion) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fRegion = pRegion;
    }
    
    @Override
    public String patternName() {
      return "hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.noStateInRegion";
    }
    
    @Override
    public List<String> parameterNames() {
      return NoStateInRegion.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fRegion};
    }
    
    @Override
    public NoStateInRegion.Match toImmutable() {
      return isMutable() ? newMatch(fRegion) : this;
    }
    
    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"region\"=" + prettyPrintValue(fRegion));
      return result.toString();
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(fRegion);
    }
    
    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof NoStateInRegion.Match)) {
          NoStateInRegion.Match other = (NoStateInRegion.Match) obj;
          return Objects.equals(fRegion, other.fRegion);
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
    public NoStateInRegion specification() {
      return NoStateInRegion.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static NoStateInRegion.Match newEmptyMatch() {
      return new Mutable(null);
    }
    
    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pRegion the fixed value of pattern parameter region, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static NoStateInRegion.Match newMutableMatch(final Region pRegion) {
      return new Mutable(pRegion);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pRegion the fixed value of pattern parameter region, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static NoStateInRegion.Match newMatch(final Region pRegion) {
      return new Immutable(pRegion);
    }
    
    private static final class Mutable extends NoStateInRegion.Match {
      Mutable(final Region pRegion) {
        super(pRegion);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends NoStateInRegion.Match {
      Immutable(final Region pRegion) {
        super(pRegion);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.noStateInRegion pattern,
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
   * // State vs Region
   * /////////
   * 
   * {@literal @}Constraint(severity="error", message="error", key = {region})
   * pattern noStateInRegion(region: Region) {
   * 	neg find StateInRegion(region,_);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see NoStateInRegion
   * 
   */
  public static class Matcher extends BaseMatcher<NoStateInRegion.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static NoStateInRegion.Matcher on(final ViatraQueryEngine engine) {
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
    public static NoStateInRegion.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_REGION = 0;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(NoStateInRegion.Matcher.class);
    
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
     * @param pRegion the fixed value of pattern parameter region, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<NoStateInRegion.Match> getAllMatches(final Region pRegion) {
      return rawStreamAllMatches(new Object[]{pRegion}).collect(Collectors.toSet());
    }
    
    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pRegion the fixed value of pattern parameter region, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<NoStateInRegion.Match> streamAllMatches(final Region pRegion) {
      return rawStreamAllMatches(new Object[]{pRegion});
    }
    
    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pRegion the fixed value of pattern parameter region, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<NoStateInRegion.Match> getOneArbitraryMatch(final Region pRegion) {
      return rawGetOneArbitraryMatch(new Object[]{pRegion});
    }
    
    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pRegion the fixed value of pattern parameter region, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final Region pRegion) {
      return rawHasMatch(new Object[]{pRegion});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pRegion the fixed value of pattern parameter region, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final Region pRegion) {
      return rawCountMatches(new Object[]{pRegion});
    }
    
    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pRegion the fixed value of pattern parameter region, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final Region pRegion, final Consumer<? super NoStateInRegion.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pRegion}, processor);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pRegion the fixed value of pattern parameter region, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public NoStateInRegion.Match newMatch(final Region pRegion) {
      return NoStateInRegion.Match.newMatch(pRegion);
    }
    
    /**
     * Retrieve the set of values that occur in matches for region.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Region> rawStreamAllValuesOfregion(final Object[] parameters) {
      return rawStreamAllValues(POSITION_REGION, parameters).map(Region.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for region.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Region> getAllValuesOfregion() {
      return rawStreamAllValuesOfregion(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for region.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Region> streamAllValuesOfregion() {
      return rawStreamAllValuesOfregion(emptyArray());
    }
    
    @Override
    protected NoStateInRegion.Match tupleToMatch(final Tuple t) {
      try {
          return NoStateInRegion.Match.newMatch((Region) t.get(POSITION_REGION));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected NoStateInRegion.Match arrayToMatch(final Object[] match) {
      try {
          return NoStateInRegion.Match.newMatch((Region) match[POSITION_REGION]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected NoStateInRegion.Match arrayToMatchMutable(final Object[] match) {
      try {
          return NoStateInRegion.Match.newMutableMatch((Region) match[POSITION_REGION]);
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
    public static IQuerySpecification<NoStateInRegion.Matcher> querySpecification() {
      return NoStateInRegion.instance();
    }
  }
  
  private NoStateInRegion() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static NoStateInRegion instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected NoStateInRegion.Matcher instantiate(final ViatraQueryEngine engine) {
    return NoStateInRegion.Matcher.on(engine);
  }
  
  @Override
  public NoStateInRegion.Matcher instantiate() {
    return NoStateInRegion.Matcher.create();
  }
  
  @Override
  public NoStateInRegion.Match newEmptyMatch() {
    return NoStateInRegion.Match.newEmptyMatch();
  }
  
  @Override
  public NoStateInRegion.Match newMatch(final Object... parameters) {
    return NoStateInRegion.Match.newMatch((hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Region) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link JvmGenericType: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.NoStateInRegion (visibility: PUBLIC, simpleName: NoStateInRegion, identifier: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.NoStateInRegion, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu) (interface: false, strictFloatingPoint: false, anonymous: false)} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link JvmGenericType: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.NoStateInRegion (visibility: PUBLIC, simpleName: NoStateInRegion, identifier: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.NoStateInRegion, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu) (interface: false, strictFloatingPoint: false, anonymous: false)#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final NoStateInRegion INSTANCE = new NoStateInRegion();
    
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
    private static final NoStateInRegion.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_region = new PParameter("region", "hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Region", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("hu.bme.mit.inf.yakindumm", "Region")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_region);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.noStateInRegion";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("region");
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
          PVariable var_region = body.getOrCreateVariableByName("region");
          PVariable var___0_ = body.getOrCreateVariableByName("_<0>");
          new TypeConstraint(body, Tuples.flatTupleOf(var_region), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Region")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_region, parameter_region)
          ));
          // 	neg find StateInRegion(region,_)
          new NegativePatternCall(body, Tuples.flatTupleOf(var_region, var___0_), StateInRegion.instance().getInternalQueryRepresentation());
          bodies.add(body);
      }
      {
          PAnnotation annotation = new PAnnotation("Constraint");
          annotation.addAttribute("severity", "error");
          annotation.addAttribute("message", "error");
          annotation.addAttribute("key", Arrays.asList(new Object[] {
                              new ParameterReference("region")
                              }));
          addAnnotation(annotation);
      }
      return bodies;
    }
  }
}
