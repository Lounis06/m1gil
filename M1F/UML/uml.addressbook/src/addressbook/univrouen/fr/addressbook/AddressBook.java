/**
 */
package addressbook.univrouen.fr.addressbook;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Address Book</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link addressbook.univrouen.fr.addressbook.AddressBook#getName <em>Name</em>}</li>
 *   <li>{@link addressbook.univrouen.fr.addressbook.AddressBook#getPersonne <em>Personne</em>}</li>
 * </ul>
 *
 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getAddressBook()
 * @model
 * @generated
 */
public interface AddressBook extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getAddressBook_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link addressbook.univrouen.fr.addressbook.AddressBook#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Personne</b></em>' containment reference list.
	 * The list contents are of type {@link addressbook.univrouen.fr.addressbook.Personne}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Personne</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Personne</em>' containment reference list.
	 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getAddressBook_Personne()
	 * @model containment="true"
	 * @generated
	 */
	EList<Personne> getPersonne();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean addContact(Personne p);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean removeContact(Personne p);

} // AddressBook
