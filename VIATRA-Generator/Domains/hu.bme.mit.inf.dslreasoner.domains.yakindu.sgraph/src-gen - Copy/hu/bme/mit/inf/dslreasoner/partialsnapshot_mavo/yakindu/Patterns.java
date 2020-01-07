/**
 * Generated from platform:/resource/hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph/queries/hu/bme/mit/inf/dslreasoner/partialsnapshot_mavo/yakindu/patterns.vql
 */
package hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu;

import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Child;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.ChoiceHasNoIncoming;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.ChoiceHasNoOutgoing;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.EntryInRegion;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.HasMultipleRegions;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.IncomingToEntry;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.MultipleEntryInRegion;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.MultipleTransitionFromEntry;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.NoEntryInRegion;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.NoOutgoingTransitionFromEntry;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.NoStateInRegion;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.OutgoingFromExit;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.OutgoingFromFinal;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.StateInRegion;
import hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu.Transition;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in patterns.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file patterns.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.inf.dslreasoner.partialsnapshot_mavo.yakindu, the group contains the definition of the following patterns: <ul>
 * <li>entryInRegion</li>
 * <li>noEntryInRegion</li>
 * <li>multipleEntryInRegion</li>
 * <li>transition</li>
 * <li>incomingToEntry</li>
 * <li>noOutgoingTransitionFromEntry</li>
 * <li>multipleTransitionFromEntry</li>
 * <li>outgoingFromExit</li>
 * <li>outgoingFromFinal</li>
 * <li>noStateInRegion</li>
 * <li>StateInRegion</li>
 * <li>choiceHasNoOutgoing</li>
 * <li>choiceHasNoIncoming</li>
 * <li>child</li>
 * <li>hasMultipleRegions</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Patterns extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Patterns instance() {
    if (INSTANCE == null) {
        INSTANCE = new Patterns();
    }
    return INSTANCE;
  }
  
  private static Patterns INSTANCE;
  
  private Patterns() {
    querySpecifications.add(EntryInRegion.instance());
    querySpecifications.add(NoEntryInRegion.instance());
    querySpecifications.add(MultipleEntryInRegion.instance());
    querySpecifications.add(Transition.instance());
    querySpecifications.add(IncomingToEntry.instance());
    querySpecifications.add(NoOutgoingTransitionFromEntry.instance());
    querySpecifications.add(MultipleTransitionFromEntry.instance());
    querySpecifications.add(OutgoingFromExit.instance());
    querySpecifications.add(OutgoingFromFinal.instance());
    querySpecifications.add(NoStateInRegion.instance());
    querySpecifications.add(StateInRegion.instance());
    querySpecifications.add(ChoiceHasNoOutgoing.instance());
    querySpecifications.add(ChoiceHasNoIncoming.instance());
    querySpecifications.add(Child.instance());
    querySpecifications.add(HasMultipleRegions.instance());
  }
  
  public EntryInRegion getEntryInRegion() {
    return EntryInRegion.instance();
  }
  
  public EntryInRegion.Matcher getEntryInRegion(final ViatraQueryEngine engine) {
    return EntryInRegion.Matcher.on(engine);
  }
  
  public NoEntryInRegion getNoEntryInRegion() {
    return NoEntryInRegion.instance();
  }
  
  public NoEntryInRegion.Matcher getNoEntryInRegion(final ViatraQueryEngine engine) {
    return NoEntryInRegion.Matcher.on(engine);
  }
  
  public MultipleEntryInRegion getMultipleEntryInRegion() {
    return MultipleEntryInRegion.instance();
  }
  
  public MultipleEntryInRegion.Matcher getMultipleEntryInRegion(final ViatraQueryEngine engine) {
    return MultipleEntryInRegion.Matcher.on(engine);
  }
  
  public Transition getTransition() {
    return Transition.instance();
  }
  
  public Transition.Matcher getTransition(final ViatraQueryEngine engine) {
    return Transition.Matcher.on(engine);
  }
  
  public IncomingToEntry getIncomingToEntry() {
    return IncomingToEntry.instance();
  }
  
  public IncomingToEntry.Matcher getIncomingToEntry(final ViatraQueryEngine engine) {
    return IncomingToEntry.Matcher.on(engine);
  }
  
  public NoOutgoingTransitionFromEntry getNoOutgoingTransitionFromEntry() {
    return NoOutgoingTransitionFromEntry.instance();
  }
  
  public NoOutgoingTransitionFromEntry.Matcher getNoOutgoingTransitionFromEntry(final ViatraQueryEngine engine) {
    return NoOutgoingTransitionFromEntry.Matcher.on(engine);
  }
  
  public MultipleTransitionFromEntry getMultipleTransitionFromEntry() {
    return MultipleTransitionFromEntry.instance();
  }
  
  public MultipleTransitionFromEntry.Matcher getMultipleTransitionFromEntry(final ViatraQueryEngine engine) {
    return MultipleTransitionFromEntry.Matcher.on(engine);
  }
  
  public OutgoingFromExit getOutgoingFromExit() {
    return OutgoingFromExit.instance();
  }
  
  public OutgoingFromExit.Matcher getOutgoingFromExit(final ViatraQueryEngine engine) {
    return OutgoingFromExit.Matcher.on(engine);
  }
  
  public OutgoingFromFinal getOutgoingFromFinal() {
    return OutgoingFromFinal.instance();
  }
  
  public OutgoingFromFinal.Matcher getOutgoingFromFinal(final ViatraQueryEngine engine) {
    return OutgoingFromFinal.Matcher.on(engine);
  }
  
  public NoStateInRegion getNoStateInRegion() {
    return NoStateInRegion.instance();
  }
  
  public NoStateInRegion.Matcher getNoStateInRegion(final ViatraQueryEngine engine) {
    return NoStateInRegion.Matcher.on(engine);
  }
  
  public StateInRegion getStateInRegion() {
    return StateInRegion.instance();
  }
  
  public StateInRegion.Matcher getStateInRegion(final ViatraQueryEngine engine) {
    return StateInRegion.Matcher.on(engine);
  }
  
  public ChoiceHasNoOutgoing getChoiceHasNoOutgoing() {
    return ChoiceHasNoOutgoing.instance();
  }
  
  public ChoiceHasNoOutgoing.Matcher getChoiceHasNoOutgoing(final ViatraQueryEngine engine) {
    return ChoiceHasNoOutgoing.Matcher.on(engine);
  }
  
  public ChoiceHasNoIncoming getChoiceHasNoIncoming() {
    return ChoiceHasNoIncoming.instance();
  }
  
  public ChoiceHasNoIncoming.Matcher getChoiceHasNoIncoming(final ViatraQueryEngine engine) {
    return ChoiceHasNoIncoming.Matcher.on(engine);
  }
  
  public Child getChild() {
    return Child.instance();
  }
  
  public Child.Matcher getChild(final ViatraQueryEngine engine) {
    return Child.Matcher.on(engine);
  }
  
  public HasMultipleRegions getHasMultipleRegions() {
    return HasMultipleRegions.instance();
  }
  
  public HasMultipleRegions.Matcher getHasMultipleRegions(final ViatraQueryEngine engine) {
    return HasMultipleRegions.Matcher.on(engine);
  }
}
