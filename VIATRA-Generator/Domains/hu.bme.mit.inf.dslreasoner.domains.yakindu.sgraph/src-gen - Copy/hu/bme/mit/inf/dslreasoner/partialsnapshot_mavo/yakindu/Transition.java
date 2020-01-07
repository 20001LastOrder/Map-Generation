/**
 * Generated from platform:/resource/hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph/queries/hu/bme/mit/inf/dslreasoner/partialsnapshot_mavo/yakindu/patterns.vql
 */
package hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu;

import hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Vertex;
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
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
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
 *         pattern transition(t : Transition, src : Vertex, trg : Vertex) {
 *         	Transition.source(t, src);
 *         	Transition.target(t, trg);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class Transition extends BaseGeneratedEMFQuerySpecification<Transition.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.transition pattern,
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
    private hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition fT;
    
    private Vertex fSrc;
    
    private Vertex fTrg;
    
    private static List<String> parameterNames = makeImmutableList("t", "src", "trg");
    
    private Match(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
      this.fT = pT;
      this.fSrc = pSrc;
      this.fTrg = pTrg;
    }
    
    @Override
    public Object get(final String parameterName) {
      if ("t".equals(parameterName)) return this.fT;
      if ("src".equals(parameterName)) return this.fSrc;
      if ("trg".equals(parameterName)) return this.fTrg;
      return null;
    }
    
    public hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition getT() {
      return this.fT;
    }
    
    public Vertex getSrc() {
      return this.fSrc;
    }
    
    public Vertex getTrg() {
      return this.fTrg;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("t".equals(parameterName) ) {
          this.fT = (hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition) newValue;
          return true;
      }
      if ("src".equals(parameterName) ) {
          this.fSrc = (Vertex) newValue;
          return true;
      }
      if ("trg".equals(parameterName) ) {
          this.fTrg = (Vertex) newValue;
          return true;
      }
      return false;
    }
    
    public void setT(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fT = pT;
    }
    
    public void setSrc(final Vertex pSrc) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fSrc = pSrc;
    }
    
    public void setTrg(final Vertex pTrg) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTrg = pTrg;
    }
    
    @Override
    public String patternName() {
      return "hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.transition";
    }
    
    @Override
    public List<String> parameterNames() {
      return Transition.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fT, fSrc, fTrg};
    }
    
    @Override
    public Transition.Match toImmutable() {
      return isMutable() ? newMatch(fT, fSrc, fTrg) : this;
    }
    
    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"t\"=" + prettyPrintValue(fT) + ", ");
      result.append("\"src\"=" + prettyPrintValue(fSrc) + ", ");
      result.append("\"trg\"=" + prettyPrintValue(fTrg));
      return result.toString();
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(fT, fSrc, fTrg);
    }
    
    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof Transition.Match)) {
          Transition.Match other = (Transition.Match) obj;
          return Objects.equals(fT, other.fT) && Objects.equals(fSrc, other.fSrc) && Objects.equals(fTrg, other.fTrg);
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
    public Transition specification() {
      return Transition.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static Transition.Match newEmptyMatch() {
      return new Mutable(null, null, null);
    }
    
    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pSrc the fixed value of pattern parameter src, or null if not bound.
     * @param pTrg the fixed value of pattern parameter trg, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static Transition.Match newMutableMatch(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
      return new Mutable(pT, pSrc, pTrg);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pSrc the fixed value of pattern parameter src, or null if not bound.
     * @param pTrg the fixed value of pattern parameter trg, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static Transition.Match newMatch(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
      return new Immutable(pT, pSrc, pTrg);
    }
    
    private static final class Mutable extends Transition.Match {
      Mutable(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
        super(pT, pSrc, pTrg);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends Transition.Match {
      Immutable(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
        super(pT, pSrc, pTrg);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.transition pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * pattern transition(t : Transition, src : Vertex, trg : Vertex) {
   * 	Transition.source(t, src);
   * 	Transition.target(t, trg);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see Transition
   * 
   */
  public static class Matcher extends BaseMatcher<Transition.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static Transition.Matcher on(final ViatraQueryEngine engine) {
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
    public static Transition.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_T = 0;
    
    private static final int POSITION_SRC = 1;
    
    private static final int POSITION_TRG = 2;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(Transition.Matcher.class);
    
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
     * @param pSrc the fixed value of pattern parameter src, or null if not bound.
     * @param pTrg the fixed value of pattern parameter trg, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<Transition.Match> getAllMatches(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
      return rawStreamAllMatches(new Object[]{pT, pSrc, pTrg}).collect(Collectors.toSet());
    }
    
    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pSrc the fixed value of pattern parameter src, or null if not bound.
     * @param pTrg the fixed value of pattern parameter trg, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<Transition.Match> streamAllMatches(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
      return rawStreamAllMatches(new Object[]{pT, pSrc, pTrg});
    }
    
    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pSrc the fixed value of pattern parameter src, or null if not bound.
     * @param pTrg the fixed value of pattern parameter trg, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<Transition.Match> getOneArbitraryMatch(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
      return rawGetOneArbitraryMatch(new Object[]{pT, pSrc, pTrg});
    }
    
    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pSrc the fixed value of pattern parameter src, or null if not bound.
     * @param pTrg the fixed value of pattern parameter trg, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
      return rawHasMatch(new Object[]{pT, pSrc, pTrg});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pSrc the fixed value of pattern parameter src, or null if not bound.
     * @param pTrg the fixed value of pattern parameter trg, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
      return rawCountMatches(new Object[]{pT, pSrc, pTrg});
    }
    
    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pSrc the fixed value of pattern parameter src, or null if not bound.
     * @param pTrg the fixed value of pattern parameter trg, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg, final Consumer<? super Transition.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pT, pSrc, pTrg}, processor);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pT the fixed value of pattern parameter t, or null if not bound.
     * @param pSrc the fixed value of pattern parameter src, or null if not bound.
     * @param pTrg the fixed value of pattern parameter trg, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public Transition.Match newMatch(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc, final Vertex pTrg) {
      return Transition.Match.newMatch(pT, pSrc, pTrg);
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition> rawStreamAllValuesOft(final Object[] parameters) {
      return rawStreamAllValues(POSITION_T, parameters).map(hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition> getAllValuesOft() {
      return rawStreamAllValuesOft(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition> streamAllValuesOft() {
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
    public Stream<hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition> streamAllValuesOft(final Transition.Match partialMatch) {
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
    public Stream<hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition> streamAllValuesOft(final Vertex pSrc, final Vertex pTrg) {
      return rawStreamAllValuesOft(new Object[]{null, pSrc, pTrg});
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition> getAllValuesOft(final Transition.Match partialMatch) {
      return rawStreamAllValuesOft(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for t.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition> getAllValuesOft(final Vertex pSrc, final Vertex pTrg) {
      return rawStreamAllValuesOft(new Object[]{null, pSrc, pTrg}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for src.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Vertex> rawStreamAllValuesOfsrc(final Object[] parameters) {
      return rawStreamAllValues(POSITION_SRC, parameters).map(Vertex.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for src.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Vertex> getAllValuesOfsrc() {
      return rawStreamAllValuesOfsrc(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for src.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Vertex> streamAllValuesOfsrc() {
      return rawStreamAllValuesOfsrc(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for src.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Vertex> streamAllValuesOfsrc(final Transition.Match partialMatch) {
      return rawStreamAllValuesOfsrc(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for src.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Vertex> streamAllValuesOfsrc(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pTrg) {
      return rawStreamAllValuesOfsrc(new Object[]{pT, null, pTrg});
    }
    
    /**
     * Retrieve the set of values that occur in matches for src.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Vertex> getAllValuesOfsrc(final Transition.Match partialMatch) {
      return rawStreamAllValuesOfsrc(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for src.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Vertex> getAllValuesOfsrc(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pTrg) {
      return rawStreamAllValuesOfsrc(new Object[]{pT, null, pTrg}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for trg.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Vertex> rawStreamAllValuesOftrg(final Object[] parameters) {
      return rawStreamAllValues(POSITION_TRG, parameters).map(Vertex.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for trg.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Vertex> getAllValuesOftrg() {
      return rawStreamAllValuesOftrg(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for trg.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Vertex> streamAllValuesOftrg() {
      return rawStreamAllValuesOftrg(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for trg.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Vertex> streamAllValuesOftrg(final Transition.Match partialMatch) {
      return rawStreamAllValuesOftrg(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for trg.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Vertex> streamAllValuesOftrg(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc) {
      return rawStreamAllValuesOftrg(new Object[]{pT, pSrc, null});
    }
    
    /**
     * Retrieve the set of values that occur in matches for trg.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Vertex> getAllValuesOftrg(final Transition.Match partialMatch) {
      return rawStreamAllValuesOftrg(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for trg.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Vertex> getAllValuesOftrg(final hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition pT, final Vertex pSrc) {
      return rawStreamAllValuesOftrg(new Object[]{pT, pSrc, null}).collect(Collectors.toSet());
    }
    
    @Override
    protected Transition.Match tupleToMatch(final Tuple t) {
      try {
          return Transition.Match.newMatch((hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition) t.get(POSITION_T), (Vertex) t.get(POSITION_SRC), (Vertex) t.get(POSITION_TRG));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected Transition.Match arrayToMatch(final Object[] match) {
      try {
          return Transition.Match.newMatch((hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition) match[POSITION_T], (Vertex) match[POSITION_SRC], (Vertex) match[POSITION_TRG]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected Transition.Match arrayToMatchMutable(final Object[] match) {
      try {
          return Transition.Match.newMutableMatch((hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition) match[POSITION_T], (Vertex) match[POSITION_SRC], (Vertex) match[POSITION_TRG]);
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
    public static IQuerySpecification<Transition.Matcher> querySpecification() {
      return Transition.instance();
    }
  }
  
  private Transition() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static Transition instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected Transition.Matcher instantiate(final ViatraQueryEngine engine) {
    return Transition.Matcher.on(engine);
  }
  
  @Override
  public Transition.Matcher instantiate() {
    return Transition.Matcher.create();
  }
  
  @Override
  public Transition.Match newEmptyMatch() {
    return Transition.Match.newEmptyMatch();
  }
  
  @Override
  public Transition.Match newMatch(final Object... parameters) {
    return Transition.Match.newMatch((hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition) parameters[0], (hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Vertex) parameters[1], (hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Vertex) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link JvmGenericType: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Transition (visibility: PUBLIC, simpleName: Transition, identifier: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Transition, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu) (interface: false, strictFloatingPoint: false, anonymous: false)} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link JvmGenericType: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Transition (visibility: PUBLIC, simpleName: Transition, identifier: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Transition, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu) (interface: false, strictFloatingPoint: false, anonymous: false)#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final Transition INSTANCE = new Transition();
    
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
    private static final Transition.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_t = new PParameter("t", "hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Transition", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("hu.bme.mit.inf.yakindumm", "Transition")), PParameterDirection.INOUT);
    
    private final PParameter parameter_src = new PParameter("src", "hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Vertex", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("hu.bme.mit.inf.yakindumm", "Vertex")), PParameterDirection.INOUT);
    
    private final PParameter parameter_trg = new PParameter("trg", "hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Vertex", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("hu.bme.mit.inf.yakindumm", "Vertex")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_t, parameter_src, parameter_trg);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.transition";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("t","src","trg");
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
          PVariable var_src = body.getOrCreateVariableByName("src");
          PVariable var_trg = body.getOrCreateVariableByName("trg");
          new TypeConstraint(body, Tuples.flatTupleOf(var_t), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Transition")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_src), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Vertex")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_trg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Vertex")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_t, parameter_t),
             new ExportedParameter(body, var_src, parameter_src),
             new ExportedParameter(body, var_trg, parameter_trg)
          ));
          // 	Transition.source(t, src)
          new TypeConstraint(body, Tuples.flatTupleOf(var_t), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Transition")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_t, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("hu.bme.mit.inf.yakindumm", "Transition", "source")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Vertex")));
          new Equality(body, var__virtual_0_, var_src);
          // 	Transition.target(t, trg)
          new TypeConstraint(body, Tuples.flatTupleOf(var_t), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Transition")));
          PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_t, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("hu.bme.mit.inf.yakindumm", "Transition", "target")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Vertex")));
          new Equality(body, var__virtual_1_, var_trg);
          bodies.add(body);
      }
      return bodies;
    }
  }
}
