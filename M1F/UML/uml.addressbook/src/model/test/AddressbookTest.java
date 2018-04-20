package model.test;

import org.junit.Test;

import addressbook.univrouen.fr.addressbook.AddressBook;
import addressbook.univrouen.fr.addressbook.AddressbookFactory;
import addressbook.univrouen.fr.addressbook.Adresse;
import addressbook.univrouen.fr.addressbook.Personne;
import xml.AddressBookHandler;

/** 
 * Définit des tests JUnit, pour tester les fonctionnalités développées 
 */
public class AddressbookTest {
	// METHODES DE TEST
	@Test
	public void createAddressbookTest() {
		// GIVEN
		Personne[] pers = initTestData();
		AddressBook carnet = AddressbookFactory.eINSTANCE.createAddressBook();
		carnet.setName("MonCarnet");
		
		// WHEN
		boolean test = carnet.addContact(pers[0])
				&& !carnet.addContact(pers[0])
				&& carnet.addContact(pers[1])
				&& carnet.addContact(pers[2]);
		
		// THEN
		if (!test) throw new AssertionError();
		for (Personne p : carnet.getPersonne()) {
			System.out.println(p.display());
			System.out.println(p.getAdresse().display());
			System.out.println("-----");
		}
	}
	
	@Test
	public void removeAddressbookTest() {
		// GIVEN
		Personne[] pers = initTestData();
		AddressBook carnet = AddressbookFactory.eINSTANCE.createAddressBook();
		carnet.setName("MonCarnet");
		
		// WHEN
		boolean test = carnet.addContact(pers[0])
				&& carnet.addContact(pers[1])
				&& carnet.removeContact(pers[0])
				&& !carnet.removeContact(pers[2]);
		
		// THEN
		if (!test) throw new AssertionError();
		if (carnet.getPersonne().size() != 1) throw new AssertionError();
	}
	
	@Test
	public void saveAddressbookTest() throws Exception {
		// GIVEN
		Personne[] pers = initTestData();
		AddressBook carnet = AddressbookFactory.eINSTANCE.createAddressBook();
		carnet.setName("MonCarnet");
		
		// WHEN / THEN
		carnet.addContact(pers[0]);
		carnet.addContact(pers[1]);
		carnet.addContact(pers[2]);
		new AddressBookHandler().save(carnet);
	}
	
	@Test
	public void loadAddressbookTest() throws Exception {
		// GIVEN / WHEN
		AddressBook carnet = new AddressBookHandler().load();
		
		// THEN
		if (carnet.getPersonne().size() != 3) throw new AssertionError();
	}
	
	
	// OUTIL
	/**
	 * Définit un jeu d'essais de plusieurs personnes
	 */
	private Personne[] initTestData() {
		// Création du carnet
		Personne[] pers = new Personne[3];
		
		// Ajout des personnes
		// -- Personne n°1
		pers[0] = AddressbookFactory.eINSTANCE.createPersonne(); {
			pers[0].setAge(46);
			pers[0].setNom("WORLD");
			pers[0].setPrenom("Hello");
			
			Adresse adr = AddressbookFactory.eINSTANCE.createAdresse(); {
				adr.setNumero(41);
				adr.setRue("Rue of peace");
			}
			pers[0].setAdresse(adr);
		}
		
		// -- Personne n°2
		pers[1] = AddressbookFactory.eINSTANCE.createPersonne(); {
			pers[1].setAge(16);
			pers[1].setNom("MUNDO");
			pers[1].setPrenom("Hola");
			
			Adresse adr = AddressbookFactory.eINSTANCE.createAdresse(); {
				adr.setNumero(117);
				adr.setRue("Rua de la pais");
			}
			pers[1].setAdresse(adr);
		}
		
		// -- Personne n°3
		pers[2] = AddressbookFactory.eINSTANCE.createPersonne(); {
			pers[2].setAge(67);
			pers[2].setNom("LE MONDE");
			pers[2].setPrenom("Bonjour");
			
			Adresse adr = AddressbookFactory.eINSTANCE.createAdresse(); {
				adr.setNumero(17);
				adr.setRue("Rue de la paix");
			}
			pers[2].setAdresse(adr);
		}
		
		return pers;
	}
}

