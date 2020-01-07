/**
 */
package map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see map.MapFactory
 * @model kind="package"
 * @generated
 */
public interface MapPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "map";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "ca.mcgill.ecse459.map";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ca.mcgill.ecse459.map";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MapPackage eINSTANCE = map.impl.MapPackageImpl.init();

	/**
	 * The meta object id for the '{@link map.impl.GridImpl <em>Grid</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see map.impl.GridImpl
	 * @see map.impl.MapPackageImpl#getGrid()
	 * @generated
	 */
	int GRID = 0;

	/**
	 * The feature id for the '<em><b>Up</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID__UP = 0;

	/**
	 * The feature id for the '<em><b>Down</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID__DOWN = 1;

	/**
	 * The feature id for the '<em><b>Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID__LEFT = 2;

	/**
	 * The feature id for the '<em><b>Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID__RIGHT = 3;

	/**
	 * The feature id for the '<em><b>Types</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID__TYPES = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID__ID = 5;

	/**
	 * The number of structural features of the '<em>Grid</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Grid</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link map.impl.MapImpl <em>Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see map.impl.MapImpl
	 * @see map.impl.MapPackageImpl#getMap()
	 * @generated
	 */
	int MAP = 1;

	/**
	 * The feature id for the '<em><b>Grids</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__GRIDS = 0;

	/**
	 * The number of structural features of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link map.impl.GridTypeImpl <em>Grid Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see map.impl.GridTypeImpl
	 * @see map.impl.MapPackageImpl#getGridType()
	 * @generated
	 */
	int GRID_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Grid</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_TYPE__GRID = 0;

	/**
	 * The number of structural features of the '<em>Grid Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Grid Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link map.impl.IslandImpl <em>Island</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see map.impl.IslandImpl
	 * @see map.impl.MapPackageImpl#getIsland()
	 * @generated
	 */
	int ISLAND = 3;

	/**
	 * The feature id for the '<em><b>Grid</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISLAND__GRID = GRID_TYPE__GRID;

	/**
	 * The number of structural features of the '<em>Island</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISLAND_FEATURE_COUNT = GRID_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Island</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISLAND_OPERATION_COUNT = GRID_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link map.impl.SeaImpl <em>Sea</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see map.impl.SeaImpl
	 * @see map.impl.MapPackageImpl#getSea()
	 * @generated
	 */
	int SEA = 4;

	/**
	 * The feature id for the '<em><b>Grid</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEA__GRID = GRID_TYPE__GRID;

	/**
	 * The number of structural features of the '<em>Sea</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEA_FEATURE_COUNT = GRID_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Sea</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEA_OPERATION_COUNT = GRID_TYPE_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link map.Grid <em>Grid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grid</em>'.
	 * @see map.Grid
	 * @generated
	 */
	EClass getGrid();

	/**
	 * Returns the meta object for the reference '{@link map.Grid#getUp <em>Up</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Up</em>'.
	 * @see map.Grid#getUp()
	 * @see #getGrid()
	 * @generated
	 */
	EReference getGrid_Up();

	/**
	 * Returns the meta object for the reference '{@link map.Grid#getDown <em>Down</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Down</em>'.
	 * @see map.Grid#getDown()
	 * @see #getGrid()
	 * @generated
	 */
	EReference getGrid_Down();

	/**
	 * Returns the meta object for the reference '{@link map.Grid#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left</em>'.
	 * @see map.Grid#getLeft()
	 * @see #getGrid()
	 * @generated
	 */
	EReference getGrid_Left();

	/**
	 * Returns the meta object for the reference '{@link map.Grid#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right</em>'.
	 * @see map.Grid#getRight()
	 * @see #getGrid()
	 * @generated
	 */
	EReference getGrid_Right();

	/**
	 * Returns the meta object for the containment reference '{@link map.Grid#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Types</em>'.
	 * @see map.Grid#getTypes()
	 * @see #getGrid()
	 * @generated
	 */
	EReference getGrid_Types();

	/**
	 * Returns the meta object for the attribute '{@link map.Grid#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see map.Grid#getId()
	 * @see #getGrid()
	 * @generated
	 */
	EAttribute getGrid_Id();

	/**
	 * Returns the meta object for class '{@link map.Map <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map</em>'.
	 * @see map.Map
	 * @generated
	 */
	EClass getMap();

	/**
	 * Returns the meta object for the containment reference list '{@link map.Map#getGrids <em>Grids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Grids</em>'.
	 * @see map.Map#getGrids()
	 * @see #getMap()
	 * @generated
	 */
	EReference getMap_Grids();

	/**
	 * Returns the meta object for class '{@link map.GridType <em>Grid Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grid Type</em>'.
	 * @see map.GridType
	 * @generated
	 */
	EClass getGridType();

	/**
	 * Returns the meta object for the container reference '{@link map.GridType#getGrid <em>Grid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grid</em>'.
	 * @see map.GridType#getGrid()
	 * @see #getGridType()
	 * @generated
	 */
	EReference getGridType_Grid();

	/**
	 * Returns the meta object for class '{@link map.Island <em>Island</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Island</em>'.
	 * @see map.Island
	 * @generated
	 */
	EClass getIsland();

	/**
	 * Returns the meta object for class '{@link map.Sea <em>Sea</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sea</em>'.
	 * @see map.Sea
	 * @generated
	 */
	EClass getSea();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MapFactory getMapFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link map.impl.GridImpl <em>Grid</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see map.impl.GridImpl
		 * @see map.impl.MapPackageImpl#getGrid()
		 * @generated
		 */
		EClass GRID = eINSTANCE.getGrid();

		/**
		 * The meta object literal for the '<em><b>Up</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRID__UP = eINSTANCE.getGrid_Up();

		/**
		 * The meta object literal for the '<em><b>Down</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRID__DOWN = eINSTANCE.getGrid_Down();

		/**
		 * The meta object literal for the '<em><b>Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRID__LEFT = eINSTANCE.getGrid_Left();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRID__RIGHT = eINSTANCE.getGrid_Right();

		/**
		 * The meta object literal for the '<em><b>Types</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRID__TYPES = eINSTANCE.getGrid_Types();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRID__ID = eINSTANCE.getGrid_Id();

		/**
		 * The meta object literal for the '{@link map.impl.MapImpl <em>Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see map.impl.MapImpl
		 * @see map.impl.MapPackageImpl#getMap()
		 * @generated
		 */
		EClass MAP = eINSTANCE.getMap();

		/**
		 * The meta object literal for the '<em><b>Grids</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP__GRIDS = eINSTANCE.getMap_Grids();

		/**
		 * The meta object literal for the '{@link map.impl.GridTypeImpl <em>Grid Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see map.impl.GridTypeImpl
		 * @see map.impl.MapPackageImpl#getGridType()
		 * @generated
		 */
		EClass GRID_TYPE = eINSTANCE.getGridType();

		/**
		 * The meta object literal for the '<em><b>Grid</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRID_TYPE__GRID = eINSTANCE.getGridType_Grid();

		/**
		 * The meta object literal for the '{@link map.impl.IslandImpl <em>Island</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see map.impl.IslandImpl
		 * @see map.impl.MapPackageImpl#getIsland()
		 * @generated
		 */
		EClass ISLAND = eINSTANCE.getIsland();

		/**
		 * The meta object literal for the '{@link map.impl.SeaImpl <em>Sea</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see map.impl.SeaImpl
		 * @see map.impl.MapPackageImpl#getSea()
		 * @generated
		 */
		EClass SEA = eINSTANCE.getSea();

	}

} //MapPackage
