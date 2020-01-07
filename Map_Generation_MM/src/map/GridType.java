/**
 */
package map;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grid Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link map.GridType#getGrid <em>Grid</em>}</li>
 * </ul>
 *
 * @see map.MapPackage#getGridType()
 * @model abstract="true"
 * @generated
 */
public interface GridType extends EObject {

	/**
	 * Returns the value of the '<em><b>Grid</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link map.Grid#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid</em>' container reference.
	 * @see #setGrid(Grid)
	 * @see map.MapPackage#getGridType_Grid()
	 * @see map.Grid#getTypes
	 * @model opposite="types" transient="false"
	 * @generated
	 */
	Grid getGrid();

	/**
	 * Sets the value of the '{@link map.GridType#getGrid <em>Grid</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid</em>' container reference.
	 * @see #getGrid()
	 * @generated
	 */
	void setGrid(Grid value);

} // GridType
