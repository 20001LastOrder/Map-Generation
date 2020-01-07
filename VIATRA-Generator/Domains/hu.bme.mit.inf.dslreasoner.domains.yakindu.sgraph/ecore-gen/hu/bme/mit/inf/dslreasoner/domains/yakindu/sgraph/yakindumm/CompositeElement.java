/**
 */
package hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.CompositeElement#getRegions <em>Regions</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.YakindummPackage#getCompositeElement()
 * @model abstract="true"
 * @generated
 */
public interface CompositeElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Regions</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.Region}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regions</em>' containment reference list.
	 * @see hu.bme.mit.inf.dslreasoner.domains.yakindu.sgraph.yakindumm.YakindummPackage#getCompositeElement_Regions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Region> getRegions();

} // CompositeElement
