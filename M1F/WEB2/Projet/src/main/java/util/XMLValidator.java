package util;

import org.xml.sax.XMLReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public abstract class XMLValidator {
	/**
	 * Vérifie si la chaîne fournie en paramètre, décrit du contenu XML
	 * valide, par rapport
	 *
	 * @throws Exception
	 */
	// COMMANDE
	public boolean validate(String xml) throws Exception {
		// Création du gestionnaire d'erreur
		SimpleErrorHandler handler = new SimpleErrorHandler();
		
		// Obtention de la factory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);

		// Parsing du document XML
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		reader.setErrorHandler(new SimpleErrorHandler());
		reader.parse(xml);
		
		return !handler.hasError();
	}
}
