/**
 */
package map;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link map.Map#getGrids <em>Grids</em>}</li>
 * </ul>
 *
 * @see map.MapPackage#getMap()
 * @model
 * @generated
 */
public interface Map extends EObject {
	/**
	 * Returns the value of the '<em><b>Grids</b></em>' containment reference list.
	 * The list contents are of type {@link map.Grid}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grids</em>' containment reference list.
	 * @see map.MapPackage#getMap_Grids()
	 * @model containment="true"
	 * @generated
	 */
	EList<Grid> getGrids();

} // Map
