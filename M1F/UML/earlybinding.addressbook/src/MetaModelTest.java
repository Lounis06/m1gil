import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;

/**
 * Test correspondant à la question IV.2
 * 
 * Vérifie l'état du modèle ECORE enregistré dans le package model.
 */
public class MetaModelTest {
	@Test
	public void queryAddressbookStructureWithoutCode() {
		// Chargement du modèle ECORE
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("ecore", new XMIResourceFactoryImpl());
		ResourceSet resourceSet = new ResourceSetImpl();
		URI fileURI = URI.createFileURI("model/addressbook.ecore");
		Resource resource = resourceSet.getResource(fileURI, true);
		
		// Obtention du package correspondant
		EPackage ePackage = (EPackage) resource.getContents().get(0);
		EList<EClassifier> eClass = ePackage.getEClassifiers();
		
		for (EClassifier eClassifier : eClass) {
		  // Affichage du nom de la classe
		  System.out.println(eClassifier.getName());
		  
		  // Affichage des attributs
		  System.out.print("\tAttributs : ");
		  for (EObject obj : eClassifier.eContents()) {
			  if (EAttribute.class.isAssignableFrom(obj.getClass())) {
				  EAttribute a = (EAttribute) obj;
				  System.out.print(a.getName() + "(" + a.getEType().getName() + ") ");
			  }
		  }
		  System.out.println();
		  
		  // Affichage des références
		  System.out.print("\tRéférences : ");
		  for (EObject obj : eClassifier.eContents()) {
			  if (EReference.class.isAssignableFrom(obj.getClass())) {
				  EReference r = (EReference) obj;
				  System.out.print(r.getName() + "(" + r.getEType().getName() + ") ");
			  }
		  }
		  System.out.println();
			  
		  // Affichage des opérations
		  System.out.print("\tOpérations : ");
		  for (EObject obj : eClassifier.eContents()) {
			  if (EOperation.class.isAssignableFrom(obj.getClass())) {
				  EOperation op = (EOperation) obj;
				  System.out.print(op.getEType().getName() + " " + op.getName() + " ");
			  }
		  }
		  System.out.println();
		}
	}
}
