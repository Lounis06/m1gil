/**
 */
package addressbook.univrouen.fr.addressbook.impl;

import addressbook.univrouen.fr.addressbook.AddressbookPackage;
import addressbook.univrouen.fr.addressbook.Adresse;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Adresse</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link addressbook.univrouen.fr.addressbook.impl.AdresseImpl#getNumero <em>Numero</em>}</li>
 *   <li>{@link addressbook.univrouen.fr.addressbook.impl.AdresseImpl#getRue <em>Rue</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdresseImpl extends MinimalEObjectImpl.Container implements Adresse {
	/**
	 * The default value of the '{@link #getNumero() <em>Numero</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumero()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMERO_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumero() <em>Numero</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumero()
	 * @generated
	 * @ordered
	 */
	protected int numero = NUMERO_EDEFAULT;

	/**
	 * The default value of the '{@link #getRue() <em>Rue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRue()
	 * @generated
	 * @ordered
	 */
	protected static final String RUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRue() <em>Rue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRue()
	 * @generated
	 * @ordered
	 */
	protected String rue = RUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdresseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AddressbookPackage.Literals.ADRESSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumero(int newNumero) {
		int oldNumero = numero;
		numero = newNumero;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AddressbookPackage.ADRESSE__NUMERO, oldNumero, numero));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRue(String newRue) {
		String oldRue = rue;
		rue = newRue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AddressbookPackage.ADRESSE__RUE, oldRue, rue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String display() {
		StringBuilder build = new StringBuilder(); {
			build.append("No : " + getNumero() + "\n");
			build.append("Rue : " + getRue() + "\n");
		}
		
		return build.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AddressbookPackage.ADRESSE__NUMERO:
				return getNumero();
			case AddressbookPackage.ADRESSE__RUE:
				return getRue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AddressbookPackage.ADRESSE__NUMERO:
				setNumero((Integer)newValue);
				return;
			case AddressbookPackage.ADRESSE__RUE:
				setRue((String)newValue);
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
			case AddressbookPackage.ADRESSE__NUMERO:
				setNumero(NUMERO_EDEFAULT);
				return;
			case AddressbookPackage.ADRESSE__RUE:
				setRue(RUE_EDEFAULT);
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
			case AddressbookPackage.ADRESSE__NUMERO:
				return numero != NUMERO_EDEFAULT;
			case AddressbookPackage.ADRESSE__RUE:
				return RUE_EDEFAULT == null ? rue != null : !RUE_EDEFAULT.equals(rue);
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
			case AddressbookPackage.ADRESSE___DISPLAY:
				return display();
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
		result.append(" (numero: ");
		result.append(numero);
		result.append(", rue: ");
		result.append(rue);
		result.append(')');
		return result.toString();
	}

} //AdresseImpl
