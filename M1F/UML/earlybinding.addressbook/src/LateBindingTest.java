import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;

/**
 * Test correspondant à la question IV.3
 * 
 * Vérifie l'état du modèle ECORE enregistré dans le package model.
 */
public class LateBindingTest {
	@Test
	public void implementsAddressbookFromModel() throws IOException {
		// Chargement du modèle ECORE
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("ecore", new XMIResourceFactoryImpl());
		ResourceSet resourceSet = new ResourceSetImpl();
		URI fileURI = URI.createFileURI("model/addressbook.ecore");
		Resource resource = resourceSet.createResource(fileURI);
		resource.load(null);
		
		// Obtention du package correspondant
		EPackage ePackage = (EPackage) resource.getContents().get(0);
		EClass eAddressbook = (EClass) ePackage.getEClassifier("Addressbook");
		EReference eContains = (EReference) eAddressbook.getEStructuralFeature("personne");
		EAttribute eName = (EAttribute) eAddressbook.getEStructuralFeature("name");
		EObject addressbookInstance = ePackage.getEFactoryInstance().create(eAddressbook);
		addressbookInstance.eSet(eName, "Mon Carnet d'Adresses");
	}
}
