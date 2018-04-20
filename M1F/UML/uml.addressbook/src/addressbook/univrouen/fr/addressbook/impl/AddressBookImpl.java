/**
 */
package addressbook.univrouen.fr.addressbook.impl;

import addressbook.univrouen.fr.addressbook.AddressBook;
import addressbook.univrouen.fr.addressbook.AddressbookPackage;
import addressbook.univrouen.fr.addressbook.Personne;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Address Book</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link addressbook.univrouen.fr.addressbook.impl.AddressBookImpl#getName <em>Name</em>}</li>
 *   <li>{@link addressbook.univrouen.fr.addressbook.impl.AddressBookImpl#getPersonne <em>Personne</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AddressBookImpl extends MinimalEObjectImpl.Container implements AddressBook {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPersonne() <em>Personne</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersonne()
	 * @generated
	 * @ordered
	 */
	protected EList<Personne> personne;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AddressBookImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AddressbookPackage.Literals.ADDRESS_BOOK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AddressbookPackage.ADDRESS_BOOK__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Personne> getPersonne() {
		if (personne == null) {
			personne = new EObjectContainmentEList<Personne>(Personne.class, this, AddressbookPackage.ADDRESS_BOOK__PERSONNE);
		}
		return personne;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean addContact(Personne p) {
		if (p == null || getPersonne().contains(p)) {
			return false;
		}
		
		return getPersonne().add(p);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean removeContact(Personne p) {
		if (p == null) {
			return false;
		}
		
		return getPersonne().remove(p);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AddressbookPackage.ADDRESS_BOOK__PERSONNE:
				return ((InternalEList<?>)getPersonne()).basicRemove(otherEnd, msgs);
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
			case AddressbookPackage.ADDRESS_BOOK__NAME:
				return getName();
			case AddressbookPackage.ADDRESS_BOOK__PERSONNE:
				return getPersonne();
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
			case AddressbookPackage.ADDRESS_BOOK__NAME:
				setName((String)newValue);
				return;
			case AddressbookPackage.ADDRESS_BOOK__PERSONNE:
				getPersonne().clear();
				getPersonne().addAll((Collection<? extends Personne>)newValue);
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
			case AddressbookPackage.ADDRESS_BOOK__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AddressbookPackage.ADDRESS_BOOK__PERSONNE:
				getPersonne().clear();
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
			case AddressbookPackage.ADDRESS_BOOK__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AddressbookPackage.ADDRESS_BOOK__PERSONNE:
				return personne != null && !personne.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case AddressbookPackage.ADDRESS_BOOK___ADD_CONTACT__PERSONNE:
				return addContact((Personne)arguments.get(0));
			case AddressbookPackage.ADDRESS_BOOK___REMOVE_CONTACT__PERSONNE:
				return removeContact((Personne)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //AddressBookImpl
