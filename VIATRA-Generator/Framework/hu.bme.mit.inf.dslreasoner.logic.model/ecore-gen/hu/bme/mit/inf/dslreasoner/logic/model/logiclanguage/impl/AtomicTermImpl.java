/**
 */
package hu.bme.mit.inf.dslreasoner.logic.model.logiclanguage.impl;

import hu.bme.mit.inf.dslreasoner.logic.model.logiclanguage.AtomicTerm;
import hu.bme.mit.inf.dslreasoner.logic.model.logiclanguage.LogiclanguagePackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Atomic Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class AtomicTermImpl extends TermImpl implements AtomicTerm {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AtomicTermImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LogiclanguagePackage.Literals.ATOMIC_TERM;
	}

} //AtomicTermImpl
