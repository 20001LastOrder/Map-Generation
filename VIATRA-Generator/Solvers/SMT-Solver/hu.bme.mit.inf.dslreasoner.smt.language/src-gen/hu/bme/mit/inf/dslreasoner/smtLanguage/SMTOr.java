/**
 */
package hu.bme.mit.inf.dslreasoner.smtLanguage;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SMT Or</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.inf.dslreasoner.smtLanguage.SMTOr#getOperands <em>Operands</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.inf.dslreasoner.smtLanguage.SmtLanguagePackage#getSMTOr()
 * @model
 * @generated
 */
public interface SMTOr extends SMTBoolOperation
{
  /**
   * Returns the value of the '<em><b>Operands</b></em>' containment reference list.
   * The list contents are of type {@link hu.bme.mit.inf.dslreasoner.smtLanguage.SMTTerm}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Operands</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operands</em>' containment reference list.
   * @see hu.bme.mit.inf.dslreasoner.smtLanguage.SmtLanguagePackage#getSMTOr_Operands()
   * @model containment="true"
   * @generated
   */
  EList<SMTTerm> getOperands();

} // SMTOr
