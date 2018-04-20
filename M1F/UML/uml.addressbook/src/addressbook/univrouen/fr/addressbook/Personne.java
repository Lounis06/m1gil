/**
 */
package addressbook.univrouen.fr.addressbook;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Personne</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link addressbook.univrouen.fr.addressbook.Personne#getNom <em>Nom</em>}</li>
 *   <li>{@link addressbook.univrouen.fr.addressbook.Personne#getPrenom <em>Prenom</em>}</li>
 *   <li>{@link addressbook.univrouen.fr.addressbook.Personne#getAge <em>Age</em>}</li>
 *   <li>{@link addressbook.univrouen.fr.addressbook.Personne#getAdresse <em>Adresse</em>}</li>
 *   <li>{@link addressbook.univrouen.fr.addressbook.Personne#getIdentifier <em>Identifier</em>}</li>
 * </ul>
 *
 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getPersonne()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='nomFormat ageMin'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot nomFormat='nom.matches(\'^[A-Z]*$\')' ageMin='age >= 16'"
 * @generated
 */
public interface Personne extends EObject {
	/**
	 * Returns the value of the '<em><b>Nom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nom</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nom</em>' attribute.
	 * @see #setNom(String)
	 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getPersonne_Nom()
	 * @model
	 * @generated
	 */
	String getNom();

	/**
	 * Sets the value of the '{@link addressbook.univrouen.fr.addressbook.Personne#getNom <em>Nom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nom</em>' attribute.
	 * @see #getNom()
	 * @generated
	 */
	void setNom(String value);

	/**
	 * Returns the value of the '<em><b>Prenom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prenom</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prenom</em>' attribute.
	 * @see #setPrenom(String)
	 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getPersonne_Prenom()
	 * @model
	 * @generated
	 */
	String getPrenom();

	/**
	 * Sets the value of the '{@link addressbook.univrouen.fr.addressbook.Personne#getPrenom <em>Prenom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prenom</em>' attribute.
	 * @see #getPrenom()
	 * @generated
	 */
	void setPrenom(String value);

	/**
	 * Returns the value of the '<em><b>Age</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Age</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Age</em>' attribute.
	 * @see #setAge(int)
	 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getPersonne_Age()
	 * @model default="0"
	 * @generated
	 */
	int getAge();

	/**
	 * Sets the value of the '{@link addressbook.univrouen.fr.addressbook.Personne#getAge <em>Age</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Age</em>' attribute.
	 * @see #getAge()
	 * @generated
	 */
	void setAge(int value);

	/**
	 * Returns the value of the '<em><b>Adresse</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adresse</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adresse</em>' containment reference.
	 * @see #setAdresse(Adresse)
	 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getPersonne_Adresse()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Adresse getAdresse();

	/**
	 * Sets the value of the '{@link addressbook.univrouen.fr.addressbook.Personne#getAdresse <em>Adresse</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adresse</em>' containment reference.
	 * @see #getAdresse()
	 * @generated
	 */
	void setAdresse(Adresse value);

	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getPersonne_Identifier()
	 * @model transient="true" changeable="false" derived="true"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String display();

} // Personne
