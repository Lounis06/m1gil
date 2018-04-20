/**
 */
package addressbook.univrouen.fr.addressbook.util;

import addressbook.univrouen.fr.addressbook.*;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage
 * @generated
 */
public class AddressbookValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final AddressbookValidator INSTANCE = new AddressbookValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "addressbook.univrouen.fr.addressbook";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressbookValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return AddressbookPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case AddressbookPackage.ADRESSE:
				return validateAdresse((Adresse)value, diagnostics, context);
			case AddressbookPackage.PERSONNE:
				return validatePersonne((Personne)value, diagnostics, context);
			case AddressbookPackage.ADDRESS_BOOK:
				return validateAddressBook((AddressBook)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAdresse(Adresse adresse, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(adresse, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersonne(Personne personne, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(personne, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(personne, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(personne, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(personne, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(personne, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(personne, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(personne, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(personne, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(personne, diagnostics, context);
		if (result || diagnostics != null) result &= validatePersonne_nomFormat(personne, diagnostics, context);
		if (result || diagnostics != null) result &= validatePersonne_ageMin(personne, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the nomFormat constraint of '<em>Personne</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String PERSONNE__NOM_FORMAT__EEXPRESSION = "nom.matches('^[A-Z]*$')";

	/**
	 * Validates the nomFormat constraint of '<em>Personne</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersonne_nomFormat(Personne personne, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(AddressbookPackage.Literals.PERSONNE,
				 personne,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "nomFormat",
				 PERSONNE__NOM_FORMAT__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the ageMin constraint of '<em>Personne</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String PERSONNE__AGE_MIN__EEXPRESSION = "age >= 16";

	/**
	 * Validates the ageMin constraint of '<em>Personne</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersonne_ageMin(Personne personne, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(AddressbookPackage.Literals.PERSONNE,
				 personne,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "ageMin",
				 PERSONNE__AGE_MIN__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddressBook(AddressBook addressBook, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(addressBook, diagnostics, context);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //AddressbookValidator
