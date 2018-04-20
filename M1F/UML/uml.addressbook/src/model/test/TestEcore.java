package model.test;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.junit.Test;

import addressbook.univrouen.fr.addressbook.AddressbookPackage;

public class TestEcore {
  @Test
  public void queryAddressbookStructure() {
	AddressbookPackage abPackage = AddressbookPackage.eINSTANCE;
	EList<EClassifier> eClass = abPackage.getEClassifiers();
	
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
