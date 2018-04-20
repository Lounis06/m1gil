package xml;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import addressbook.univrouen.fr.addressbook.AddressBook;

public class AddressBookHandler {
	// CONSTANTE
	public static final String SAVEFILE = "resource/save.addressbook";
	
	// COMMANDES
	/**
	 * Sauvegarde le carnet d'adresses dans le fichier XML,
	 * associé à ce gestionnaire de sauvegarde
	 */
	public void save(AddressBook book) throws Exception {
		// Obtention du ResourceSet
		ResourceSet resourceSet = getResourceSet();
		
		// Utilisation du resourceSet pour la sauvegarde du contenu
		Resource resource = resourceSet.createResource(URI.createFileURI(SAVEFILE));
		if (resource == null) {
			throw new AssertionError();
		}
		resource.getContents().add(book);
		resource.save(null);
	}
	
	/**
	 * Renvoie le carnet d'adresses stocké dans le fichier XML,
	 * associé à ce gestionnaire de sauvegarde
	 */
	public AddressBook load() throws Exception {
		// Obtention du ResourceSet
		ResourceSet resourceSet = getResourceSet();
		
		// Utilisation du resourceSet pour la sauvegarde du contenu
		Resource resource = resourceSet.getResource(URI.createFileURI(SAVEFILE), true);
		resource.load(null);
		return (AddressBook) resource.getContents().get(0);
	}
	
	// OUTIL
	/**
	 * Construit le ResourceSet nécessaire aux opérations de lecture/écriture
	 */
	private ResourceSet getResourceSet() {
		// Paramétrage du ResourceSet
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("addressbook", new XMIResourceFactoryImpl());

        // Obtention d'un nouveau ResourceSet
        return new ResourceSetImpl();
	}
}
