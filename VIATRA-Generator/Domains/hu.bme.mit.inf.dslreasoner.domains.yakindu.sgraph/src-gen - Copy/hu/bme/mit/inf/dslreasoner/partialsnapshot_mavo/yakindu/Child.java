/**
 * Generated from platform:/resource/hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph/queries/hu/bme/mit/inf/dslreasoner/partialsnapshot_mavo/yakindu/patterns.vql
 */
package hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu;

import hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.CompositeElement;
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
 *         /////////
 *         // Synchronization
 *         /////////
 *         
 *         //{@literal @}Constraint(severity="error", message="error", key = {s})
 *         //pattern synchHasNoOutgoing(s : Synchronization) {
 *         //	neg find transition(_, s, _);
 *         //}
 *         //
 *         //{@literal @}Constraint(severity="error", message="error", key = {s})
 *         //pattern synchHasNoIncoming(s : Synchronization) {
 *         //	neg find transition(_, _, s);
 *         //}
 *         //
 *         //{@literal @}Constraint(severity="error", message="error", key = {s})
 *         //pattern SynchronizedIncomingInSameRegion(s : Synchronization, v1 : Vertex, v2 : Vertex) {
 *         //	find transition(t1, v1, s);
 *         //	find transition(t2, v2, s);
 *         //	t1!=t2;
 *         //	Region.vertices(r, v1);
 *         //	Region.vertices(r, v2);
 *         //} or {
 *         //	find transition(t1, s, v1);
 *         //	find transition(t2, s, v2);
 *         //	t1!=t2;
 *         //	Region.vertices(r, v1);
 *         //	Region.vertices(r, v2);
 *         //}
 *         //
 *         //{@literal @}Constraint(severity="error", message="error", key = {s})
 *         //pattern notSynchronizingStates(s : Synchronization) {
 *         //	neg find hasMultipleOutgoingTrainsition(s);
 *         //	neg find hasMultipleIncomingTrainsition(s);
 *         //}
 *         
 *         //pattern hasMultipleOutgoingTrainsition(v : Synchronization) {
 *         //	find transition(_, v, trg1);
 *         //	find transition(_, v, trg2);
 *         //	trg1 != trg2;
 *         //}
 *         //
 *         //pattern hasMultipleIncomingTrainsition(v : Synchronization) {
 *         //	find transition(_, src1, v);
 *         //	find transition(_, src2, v);
 *         //	src1 != src2;
 *         //}
 *         //
 *         //{@literal @}Constraint(severity="error", message="error", key = {s})
 *         //pattern SynchronizedRegionsAreNotSiblings(s : Synchronization, v1 : Vertex, v2 : Vertex) {
 *         //	find transition(_, v1, s);
 *         //	find transition(_, v2, s);
 *         //	CompositeElement.regions.vertices(r1, v1);
 *         //	CompositeElement.regions.vertices(r2, v2);
 *         //	r1 != r2;
 *         //} or {
 *         //	find transition(_, s, v1);
 *         //	find transition(_, s, v2);
 *         //	CompositeElement.regions.vertices(r1, v1);
 *         //	CompositeElement.regions.vertices(r2, v2);
 *         //	r1 != r2;
 *         //}
 *         
 *         ///////////////////////////////
 *         // Extra
 *         //
 *         //{@literal @}Constraint(severity="error", message="error", key = {s})
 *         //pattern SynchronizedRegionDoesNotHaveParent(s : Synchronization, v : Vertex) {
 *         //	find transition(_, v, s);
 *         //	neg find child(_,v);
 *         //} or {
 *         //	find transition(_, s, v);
 *         //	neg find child(_,v);
 *         //}
 *         
 *         pattern child(parent: CompositeElement, child: Vertex) {
 *         	CompositeElement.regions.vertices(parent, child);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class Child extends BaseGeneratedEMFQuerySpecification<Child.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.child pattern,
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
    private CompositeElement fParent;
    
    private Vertex fChild;
    
    private static List<String> parameterNames = makeImmutableList("parent", "child");
    
    private Match(final CompositeElement pParent, final Vertex pChild) {
      this.fParent = pParent;
      this.fChild = pChild;
    }
    
    @Override
    public Object get(final String parameterName) {
      if ("parent".equals(parameterName)) return this.fParent;
      if ("child".equals(parameterName)) return this.fChild;
      return null;
    }
    
    public CompositeElement getParent() {
      return this.fParent;
    }
    
    public Vertex getChild() {
      return this.fChild;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("parent".equals(parameterName) ) {
          this.fParent = (CompositeElement) newValue;
          return true;
      }
      if ("child".equals(parameterName) ) {
          this.fChild = (Vertex) newValue;
          return true;
      }
      return false;
    }
    
    public void setParent(final CompositeElement pParent) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fParent = pParent;
    }
    
    public void setChild(final Vertex pChild) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fChild = pChild;
    }
    
    @Override
    public String patternName() {
      return "hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.child";
    }
    
    @Override
    public List<String> parameterNames() {
      return Child.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fParent, fChild};
    }
    
    @Override
    public Child.Match toImmutable() {
      return isMutable() ? newMatch(fParent, fChild) : this;
    }
    
    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"parent\"=" + prettyPrintValue(fParent) + ", ");
      result.append("\"child\"=" + prettyPrintValue(fChild));
      return result.toString();
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(fParent, fChild);
    }
    
    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof Child.Match)) {
          Child.Match other = (Child.Match) obj;
          return Objects.equals(fParent, other.fParent) && Objects.equals(fChild, other.fChild);
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
    public Child specification() {
      return Child.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static Child.Match newEmptyMatch() {
      return new Mutable(null, null);
    }
    
    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pParent the fixed value of pattern parameter parent, or null if not bound.
     * @param pChild the fixed value of pattern parameter child, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static Child.Match newMutableMatch(final CompositeElement pParent, final Vertex pChild) {
      return new Mutable(pParent, pChild);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pParent the fixed value of pattern parameter parent, or null if not bound.
     * @param pChild the fixed value of pattern parameter child, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static Child.Match newMatch(final CompositeElement pParent, final Vertex pChild) {
      return new Immutable(pParent, pChild);
    }
    
    private static final class Mutable extends Child.Match {
      Mutable(final CompositeElement pParent, final Vertex pChild) {
        super(pParent, pChild);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends Child.Match {
      Immutable(final CompositeElement pParent, final Vertex pChild) {
        super(pParent, pChild);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.child pattern,
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
   * // Synchronization
   * /////////
   * 
   * //{@literal @}Constraint(severity="error", message="error", key = {s})
   * //pattern synchHasNoOutgoing(s : Synchronization) {
   * //	neg find transition(_, s, _);
   * //}
   * //
   * //{@literal @}Constraint(severity="error", message="error", key = {s})
   * //pattern synchHasNoIncoming(s : Synchronization) {
   * //	neg find transition(_, _, s);
   * //}
   * //
   * //{@literal @}Constraint(severity="error", message="error", key = {s})
   * //pattern SynchronizedIncomingInSameRegion(s : Synchronization, v1 : Vertex, v2 : Vertex) {
   * //	find transition(t1, v1, s);
   * //	find transition(t2, v2, s);
   * //	t1!=t2;
   * //	Region.vertices(r, v1);
   * //	Region.vertices(r, v2);
   * //} or {
   * //	find transition(t1, s, v1);
   * //	find transition(t2, s, v2);
   * //	t1!=t2;
   * //	Region.vertices(r, v1);
   * //	Region.vertices(r, v2);
   * //}
   * //
   * //{@literal @}Constraint(severity="error", message="error", key = {s})
   * //pattern notSynchronizingStates(s : Synchronization) {
   * //	neg find hasMultipleOutgoingTrainsition(s);
   * //	neg find hasMultipleIncomingTrainsition(s);
   * //}
   * 
   * //pattern hasMultipleOutgoingTrainsition(v : Synchronization) {
   * //	find transition(_, v, trg1);
   * //	find transition(_, v, trg2);
   * //	trg1 != trg2;
   * //}
   * //
   * //pattern hasMultipleIncomingTrainsition(v : Synchronization) {
   * //	find transition(_, src1, v);
   * //	find transition(_, src2, v);
   * //	src1 != src2;
   * //}
   * //
   * //{@literal @}Constraint(severity="error", message="error", key = {s})
   * //pattern SynchronizedRegionsAreNotSiblings(s : Synchronization, v1 : Vertex, v2 : Vertex) {
   * //	find transition(_, v1, s);
   * //	find transition(_, v2, s);
   * //	CompositeElement.regions.vertices(r1, v1);
   * //	CompositeElement.regions.vertices(r2, v2);
   * //	r1 != r2;
   * //} or {
   * //	find transition(_, s, v1);
   * //	find transition(_, s, v2);
   * //	CompositeElement.regions.vertices(r1, v1);
   * //	CompositeElement.regions.vertices(r2, v2);
   * //	r1 != r2;
   * //}
   * 
   * ///////////////////////////////
   * // Extra
   * //
   * //{@literal @}Constraint(severity="error", message="error", key = {s})
   * //pattern SynchronizedRegionDoesNotHaveParent(s : Synchronization, v : Vertex) {
   * //	find transition(_, v, s);
   * //	neg find child(_,v);
   * //} or {
   * //	find transition(_, s, v);
   * //	neg find child(_,v);
   * //}
   * 
   * pattern child(parent: CompositeElement, child: Vertex) {
   * 	CompositeElement.regions.vertices(parent, child);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see Child
   * 
   */
  public static class Matcher extends BaseMatcher<Child.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static Child.Matcher on(final ViatraQueryEngine engine) {
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
    public static Child.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_PARENT = 0;
    
    private static final int POSITION_CHILD = 1;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(Child.Matcher.class);
    
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
     * @param pParent the fixed value of pattern parameter parent, or null if not bound.
     * @param pChild the fixed value of pattern parameter child, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<Child.Match> getAllMatches(final CompositeElement pParent, final Vertex pChild) {
      return rawStreamAllMatches(new Object[]{pParent, pChild}).collect(Collectors.toSet());
    }
    
    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pParent the fixed value of pattern parameter parent, or null if not bound.
     * @param pChild the fixed value of pattern parameter child, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<Child.Match> streamAllMatches(final CompositeElement pParent, final Vertex pChild) {
      return rawStreamAllMatches(new Object[]{pParent, pChild});
    }
    
    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pParent the fixed value of pattern parameter parent, or null if not bound.
     * @param pChild the fixed value of pattern parameter child, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<Child.Match> getOneArbitraryMatch(final CompositeElement pParent, final Vertex pChild) {
      return rawGetOneArbitraryMatch(new Object[]{pParent, pChild});
    }
    
    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pParent the fixed value of pattern parameter parent, or null if not bound.
     * @param pChild the fixed value of pattern parameter child, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final CompositeElement pParent, final Vertex pChild) {
      return rawHasMatch(new Object[]{pParent, pChild});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pParent the fixed value of pattern parameter parent, or null if not bound.
     * @param pChild the fixed value of pattern parameter child, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final CompositeElement pParent, final Vertex pChild) {
      return rawCountMatches(new Object[]{pParent, pChild});
    }
    
    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pParent the fixed value of pattern parameter parent, or null if not bound.
     * @param pChild the fixed value of pattern parameter child, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final CompositeElement pParent, final Vertex pChild, final Consumer<? super Child.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pParent, pChild}, processor);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pParent the fixed value of pattern parameter parent, or null if not bound.
     * @param pChild the fixed value of pattern parameter child, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public Child.Match newMatch(final CompositeElement pParent, final Vertex pChild) {
      return Child.Match.newMatch(pParent, pChild);
    }
    
    /**
     * Retrieve the set of values that occur in matches for parent.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<CompositeElement> rawStreamAllValuesOfparent(final Object[] parameters) {
      return rawStreamAllValues(POSITION_PARENT, parameters).map(CompositeElement.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for parent.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<CompositeElement> getAllValuesOfparent() {
      return rawStreamAllValuesOfparent(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for parent.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<CompositeElement> streamAllValuesOfparent() {
      return rawStreamAllValuesOfparent(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for parent.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<CompositeElement> streamAllValuesOfparent(final Child.Match partialMatch) {
      return rawStreamAllValuesOfparent(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for parent.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<CompositeElement> streamAllValuesOfparent(final Vertex pChild) {
      return rawStreamAllValuesOfparent(new Object[]{null, pChild});
    }
    
    /**
     * Retrieve the set of values that occur in matches for parent.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<CompositeElement> getAllValuesOfparent(final Child.Match partialMatch) {
      return rawStreamAllValuesOfparent(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for parent.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<CompositeElement> getAllValuesOfparent(final Vertex pChild) {
      return rawStreamAllValuesOfparent(new Object[]{null, pChild}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for child.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Vertex> rawStreamAllValuesOfchild(final Object[] parameters) {
      return rawStreamAllValues(POSITION_CHILD, parameters).map(Vertex.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for child.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Vertex> getAllValuesOfchild() {
      return rawStreamAllValuesOfchild(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for child.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Vertex> streamAllValuesOfchild() {
      return rawStreamAllValuesOfchild(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for child.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Vertex> streamAllValuesOfchild(final Child.Match partialMatch) {
      return rawStreamAllValuesOfchild(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for child.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Vertex> streamAllValuesOfchild(final CompositeElement pParent) {
      return rawStreamAllValuesOfchild(new Object[]{pParent, null});
    }
    
    /**
     * Retrieve the set of values that occur in matches for child.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Vertex> getAllValuesOfchild(final Child.Match partialMatch) {
      return rawStreamAllValuesOfchild(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for child.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Vertex> getAllValuesOfchild(final CompositeElement pParent) {
      return rawStreamAllValuesOfchild(new Object[]{pParent, null}).collect(Collectors.toSet());
    }
    
    @Override
    protected Child.Match tupleToMatch(final Tuple t) {
      try {
          return Child.Match.newMatch((CompositeElement) t.get(POSITION_PARENT), (Vertex) t.get(POSITION_CHILD));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected Child.Match arrayToMatch(final Object[] match) {
      try {
          return Child.Match.newMatch((CompositeElement) match[POSITION_PARENT], (Vertex) match[POSITION_CHILD]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected Child.Match arrayToMatchMutable(final Object[] match) {
      try {
          return Child.Match.newMutableMatch((CompositeElement) match[POSITION_PARENT], (Vertex) match[POSITION_CHILD]);
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
    public static IQuerySpecification<Child.Matcher> querySpecification() {
      return Child.instance();
    }
  }
  
  private Child() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static Child instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected Child.Matcher instantiate(final ViatraQueryEngine engine) {
    return Child.Matcher.on(engine);
  }
  
  @Override
  public Child.Matcher instantiate() {
    return Child.Matcher.create();
  }
  
  @Override
  public Child.Match newEmptyMatch() {
    return Child.Match.newEmptyMatch();
  }
  
  @Override
  public Child.Match newMatch(final Object... parameters) {
    return Child.Match.newMatch((hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.CompositeElement) parameters[0], (hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Vertex) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link JvmGenericType: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Child (visibility: PUBLIC, simpleName: Child, identifier: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Child, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu) (interface: false, strictFloatingPoint: false, anonymous: false)} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link JvmGenericType: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Child (visibility: PUBLIC, simpleName: Child, identifier: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Child, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu) (interface: false, strictFloatingPoint: false, anonymous: false)#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final Child INSTANCE = new Child();
    
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
    private static final Child.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_parent = new PParameter("parent", "hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.CompositeElement", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("hu.bme.mit.inf.yakindumm", "CompositeElement")), PParameterDirection.INOUT);
    
    private final PParameter parameter_child = new PParameter("child", "hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Vertex", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("hu.bme.mit.inf.yakindumm", "Vertex")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_parent, parameter_child);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.child";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("parent","child");
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
          PVariable var_parent = body.getOrCreateVariableByName("parent");
          PVariable var_child = body.getOrCreateVariableByName("child");
          new TypeConstraint(body, Tuples.flatTupleOf(var_parent), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "CompositeElement")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_child), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Vertex")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_parent, parameter_parent),
             new ExportedParameter(body, var_child, parameter_child)
          ));
          // 	CompositeElement.regions.vertices(parent, child)
          new TypeConstraint(body, Tuples.flatTupleOf(var_parent), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "CompositeElement")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_parent, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("hu.bme.mit.inf.yakindumm", "CompositeElement", "regions")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Region")));
          PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("hu.bme.mit.inf.yakindumm", "Region", "vertices")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("hu.bme.mit.inf.yakindumm", "Vertex")));
          new Equality(body, var__virtual_1_, var_child);
          bodies.add(body);
      }
      return bodies;
    }
  }
}
