/**
 */
package map;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grid</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link map.Grid#getUp <em>Up</em>}</li>
 *   <li>{@link map.Grid#getDown <em>Down</em>}</li>
 *   <li>{@link map.Grid#getLeft <em>Left</em>}</li>
 *   <li>{@link map.Grid#getRight <em>Right</em>}</li>
 *   <li>{@link map.Grid#getTypes <em>Types</em>}</li>
 *   <li>{@link map.Grid#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see map.MapPackage#getGrid()
 * @model
 * @generated
 */
public interface Grid extends EObject {
	/**
	 * Returns the value of the '<em><b>Up</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link map.Grid#getDown <em>Down</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Up</em>' reference.
	 * @see #setUp(Grid)
	 * @see map.MapPackage#getGrid_Up()
	 * @see map.Grid#getDown
	 * @model opposite="down"
	 * @generated
	 */
	Grid getUp();

	/**
	 * Sets the value of the '{@link map.Grid#getUp <em>Up</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Up</em>' reference.
	 * @see #getUp()
	 * @generated
	 */
	void setUp(Grid value);

	/**
	 * Returns the value of the '<em><b>Down</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link map.Grid#getUp <em>Up</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Down</em>' reference.
	 * @see #setDown(Grid)
	 * @see map.MapPackage#getGrid_Down()
	 * @see map.Grid#getUp
	 * @model opposite="up"
	 * @generated
	 */
	Grid getDown();

	/**
	 * Sets the value of the '{@link map.Grid#getDown <em>Down</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Down</em>' reference.
	 * @see #getDown()
	 * @generated
	 */
	void setDown(Grid value);

	/**
	 * Returns the value of the '<em><b>Left</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link map.Grid#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left</em>' reference.
	 * @see #setLeft(Grid)
	 * @see map.MapPackage#getGrid_Left()
	 * @see map.Grid#getRight
	 * @model opposite="right"
	 * @generated
	 */
	Grid getLeft();

	/**
	 * Sets the value of the '{@link map.Grid#getLeft <em>Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' reference.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(Grid value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link map.Grid#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' reference.
	 * @see #setRight(Grid)
	 * @see map.MapPackage#getGrid_Right()
	 * @see map.Grid#getLeft
	 * @model opposite="left"
	 * @generated
	 */
	Grid getRight();

	/**
	 * Sets the value of the '{@link map.Grid#getRight <em>Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(Grid value);

	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link map.GridType#getGrid <em>Grid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' containment reference.
	 * @see #setTypes(GridType)
	 * @see map.MapPackage#getGrid_Types()
	 * @see map.GridType#getGrid
	 * @model opposite="grid" containment="true"
	 * @generated
	 */
	GridType getTypes();

	/**
	 * Sets the value of the '{@link map.Grid#getTypes <em>Types</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Types</em>' containment reference.
	 * @see #getTypes()
	 * @generated
	 */
	void setTypes(GridType value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see map.MapPackage#getGrid_Id()
	 * @model id="true"
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link map.Grid#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

} // Grid
