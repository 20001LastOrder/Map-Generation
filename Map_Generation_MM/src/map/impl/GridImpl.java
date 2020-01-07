/**
 */
package map.impl;

import map.Grid;
import map.GridType;
import map.MapPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grid</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link map.impl.GridImpl#getUp <em>Up</em>}</li>
 *   <li>{@link map.impl.GridImpl#getDown <em>Down</em>}</li>
 *   <li>{@link map.impl.GridImpl#getLeft <em>Left</em>}</li>
 *   <li>{@link map.impl.GridImpl#getRight <em>Right</em>}</li>
 *   <li>{@link map.impl.GridImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link map.impl.GridImpl#getId <em>Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GridImpl extends MinimalEObjectImpl.Container implements Grid {
	/**
	 * The cached value of the '{@link #getUp() <em>Up</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUp()
	 * @generated
	 * @ordered
	 */
	protected Grid up;

	/**
	 * The cached value of the '{@link #getDown() <em>Down</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDown()
	 * @generated
	 * @ordered
	 */
	protected Grid down;

	/**
	 * The cached value of the '{@link #getLeft() <em>Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeft()
	 * @generated
	 * @ordered
	 */
	protected Grid left;

	/**
	 * The cached value of the '{@link #getRight() <em>Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRight()
	 * @generated
	 * @ordered
	 */
	protected Grid right;

	/**
	 * The cached value of the '{@link #getTypes() <em>Types</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypes()
	 * @generated
	 * @ordered
	 */
	protected GridType types;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GridImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MapPackage.Literals.GRID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Grid getUp() {
		if (up != null && up.eIsProxy()) {
			InternalEObject oldUp = (InternalEObject)up;
			up = (Grid)eResolveProxy(oldUp);
			if (up != oldUp) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.GRID__UP, oldUp, up));
			}
		}
		return up;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Grid basicGetUp() {
		return up;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUp(Grid newUp, NotificationChain msgs) {
		Grid oldUp = up;
		up = newUp;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.GRID__UP, oldUp, newUp);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUp(Grid newUp) {
		if (newUp != up) {
			NotificationChain msgs = null;
			if (up != null)
				msgs = ((InternalEObject)up).eInverseRemove(this, MapPackage.GRID__DOWN, Grid.class, msgs);
			if (newUp != null)
				msgs = ((InternalEObject)newUp).eInverseAdd(this, MapPackage.GRID__DOWN, Grid.class, msgs);
			msgs = basicSetUp(newUp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.GRID__UP, newUp, newUp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Grid getDown() {
		if (down != null && down.eIsProxy()) {
			InternalEObject oldDown = (InternalEObject)down;
			down = (Grid)eResolveProxy(oldDown);
			if (down != oldDown) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.GRID__DOWN, oldDown, down));
			}
		}
		return down;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Grid basicGetDown() {
		return down;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDown(Grid newDown, NotificationChain msgs) {
		Grid oldDown = down;
		down = newDown;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.GRID__DOWN, oldDown, newDown);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDown(Grid newDown) {
		if (newDown != down) {
			NotificationChain msgs = null;
			if (down != null)
				msgs = ((InternalEObject)down).eInverseRemove(this, MapPackage.GRID__UP, Grid.class, msgs);
			if (newDown != null)
				msgs = ((InternalEObject)newDown).eInverseAdd(this, MapPackage.GRID__UP, Grid.class, msgs);
			msgs = basicSetDown(newDown, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.GRID__DOWN, newDown, newDown));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Grid getLeft() {
		if (left != null && left.eIsProxy()) {
			InternalEObject oldLeft = (InternalEObject)left;
			left = (Grid)eResolveProxy(oldLeft);
			if (left != oldLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.GRID__LEFT, oldLeft, left));
			}
		}
		return left;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Grid basicGetLeft() {
		return left;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeft(Grid newLeft, NotificationChain msgs) {
		Grid oldLeft = left;
		left = newLeft;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.GRID__LEFT, oldLeft, newLeft);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLeft(Grid newLeft) {
		if (newLeft != left) {
			NotificationChain msgs = null;
			if (left != null)
				msgs = ((InternalEObject)left).eInverseRemove(this, MapPackage.GRID__RIGHT, Grid.class, msgs);
			if (newLeft != null)
				msgs = ((InternalEObject)newLeft).eInverseAdd(this, MapPackage.GRID__RIGHT, Grid.class, msgs);
			msgs = basicSetLeft(newLeft, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.GRID__LEFT, newLeft, newLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Grid getRight() {
		if (right != null && right.eIsProxy()) {
			InternalEObject oldRight = (InternalEObject)right;
			right = (Grid)eResolveProxy(oldRight);
			if (right != oldRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.GRID__RIGHT, oldRight, right));
			}
		}
		return right;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Grid basicGetRight() {
		return right;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRight(Grid newRight, NotificationChain msgs) {
		Grid oldRight = right;
		right = newRight;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.GRID__RIGHT, oldRight, newRight);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRight(Grid newRight) {
		if (newRight != right) {
			NotificationChain msgs = null;
			if (right != null)
				msgs = ((InternalEObject)right).eInverseRemove(this, MapPackage.GRID__LEFT, Grid.class, msgs);
			if (newRight != null)
				msgs = ((InternalEObject)newRight).eInverseAdd(this, MapPackage.GRID__LEFT, Grid.class, msgs);
			msgs = basicSetRight(newRight, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.GRID__RIGHT, newRight, newRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GridType getTypes() {
		return types;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypes(GridType newTypes, NotificationChain msgs) {
		GridType oldTypes = types;
		types = newTypes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.GRID__TYPES, oldTypes, newTypes);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTypes(GridType newTypes) {
		if (newTypes != types) {
			NotificationChain msgs = null;
			if (types != null)
				msgs = ((InternalEObject)types).eInverseRemove(this, MapPackage.GRID_TYPE__GRID, GridType.class, msgs);
			if (newTypes != null)
				msgs = ((InternalEObject)newTypes).eInverseAdd(this, MapPackage.GRID_TYPE__GRID, GridType.class, msgs);
			msgs = basicSetTypes(newTypes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.GRID__TYPES, newTypes, newTypes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.GRID__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.GRID__UP:
				if (up != null)
					msgs = ((InternalEObject)up).eInverseRemove(this, MapPackage.GRID__DOWN, Grid.class, msgs);
				return basicSetUp((Grid)otherEnd, msgs);
			case MapPackage.GRID__DOWN:
				if (down != null)
					msgs = ((InternalEObject)down).eInverseRemove(this, MapPackage.GRID__UP, Grid.class, msgs);
				return basicSetDown((Grid)otherEnd, msgs);
			case MapPackage.GRID__LEFT:
				if (left != null)
					msgs = ((InternalEObject)left).eInverseRemove(this, MapPackage.GRID__RIGHT, Grid.class, msgs);
				return basicSetLeft((Grid)otherEnd, msgs);
			case MapPackage.GRID__RIGHT:
				if (right != null)
					msgs = ((InternalEObject)right).eInverseRemove(this, MapPackage.GRID__LEFT, Grid.class, msgs);
				return basicSetRight((Grid)otherEnd, msgs);
			case MapPackage.GRID__TYPES:
				if (types != null)
					msgs = ((InternalEObject)types).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.GRID__TYPES, null, msgs);
				return basicSetTypes((GridType)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.GRID__UP:
				return basicSetUp(null, msgs);
			case MapPackage.GRID__DOWN:
				return basicSetDown(null, msgs);
			case MapPackage.GRID__LEFT:
				return basicSetLeft(null, msgs);
			case MapPackage.GRID__RIGHT:
				return basicSetRight(null, msgs);
			case MapPackage.GRID__TYPES:
				return basicSetTypes(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MapPackage.GRID__UP:
				if (resolve) return getUp();
				return basicGetUp();
			case MapPackage.GRID__DOWN:
				if (resolve) return getDown();
				return basicGetDown();
			case MapPackage.GRID__LEFT:
				if (resolve) return getLeft();
				return basicGetLeft();
			case MapPackage.GRID__RIGHT:
				if (resolve) return getRight();
				return basicGetRight();
			case MapPackage.GRID__TYPES:
				return getTypes();
			case MapPackage.GRID__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MapPackage.GRID__UP:
				setUp((Grid)newValue);
				return;
			case MapPackage.GRID__DOWN:
				setDown((Grid)newValue);
				return;
			case MapPackage.GRID__LEFT:
				setLeft((Grid)newValue);
				return;
			case MapPackage.GRID__RIGHT:
				setRight((Grid)newValue);
				return;
			case MapPackage.GRID__TYPES:
				setTypes((GridType)newValue);
				return;
			case MapPackage.GRID__ID:
				setId((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MapPackage.GRID__UP:
				setUp((Grid)null);
				return;
			case MapPackage.GRID__DOWN:
				setDown((Grid)null);
				return;
			case MapPackage.GRID__LEFT:
				setLeft((Grid)null);
				return;
			case MapPackage.GRID__RIGHT:
				setRight((Grid)null);
				return;
			case MapPackage.GRID__TYPES:
				setTypes((GridType)null);
				return;
			case MapPackage.GRID__ID:
				setId(ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MapPackage.GRID__UP:
				return up != null;
			case MapPackage.GRID__DOWN:
				return down != null;
			case MapPackage.GRID__LEFT:
				return left != null;
			case MapPackage.GRID__RIGHT:
				return right != null;
			case MapPackage.GRID__TYPES:
				return types != null;
			case MapPackage.GRID__ID:
				return id != ID_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //GridImpl
