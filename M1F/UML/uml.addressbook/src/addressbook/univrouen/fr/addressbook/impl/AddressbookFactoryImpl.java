/**
 */
package addressbook.univrouen.fr.addressbook.impl;

import addressbook.univrouen.fr.addressbook.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AddressbookFactoryImpl extends EFactoryImpl implements AddressbookFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AddressbookFactory init() {
		try {
			AddressbookFactory theAddressbookFactory = (AddressbookFactory)EPackage.Registry.INSTANCE.getEFactory(AddressbookPackage.eNS_URI);
			if (theAddressbookFactory != null) {
				return theAddressbookFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AddressbookFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressbookFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AddressbookPackage.ADRESSE: return createAdresse();
			case AddressbookPackage.PERSONNE: return createPersonne();
			case AddressbookPackage.ADDRESS_BOOK: return createAddressBook();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adresse createAdresse() {
		AdresseImpl adresse = new AdresseImpl();
		return adresse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Personne createPersonne() {
		PersonneImpl personne = new PersonneImpl();
		return personne;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressBook createAddressBook() {
		AddressBookImpl addressBook = new AddressBookImpl();
		return addressBook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressbookPackage getAddressbookPackage() {
		return (AddressbookPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AddressbookPackage getPackage() {
		return AddressbookPackage.eINSTANCE;
	}

} //AddressbookFactoryImpl
