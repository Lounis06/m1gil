import static org.junit.Assert.assertTrue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class ValidateXML {
	// COMMANDE
	@Test
	public void should_validate_with_DOM() throws Exception {
		// Création du gestionnaire d'erreur
		SimpleErrorHandler handler = new SimpleErrorHandler();
		
		// Obtention de la factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);

		// Parsing du document XML
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setErrorHandler(new SimpleErrorHandler());
		Document document = builder.parse(new InputSource("./src/main/resources/tp1.out4.xml"));
		
		assertTrue(!handler.hasError());
	}
	
	@Test
	public void should_validate_with_SAX() throws Exception {
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
		reader.parse(new InputSource("./src/main/resources/tp1.out4.xml"));
		
		assertTrue(!handler.hasError());
	}
}
