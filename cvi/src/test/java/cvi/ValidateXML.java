package cvi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class ValidateXML {
	
	@Test
	public void should_validate_with_DOM() throws Exception {
		SimpleErrorHandler simpleErrorHandler = new SimpleErrorHandler();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", 
		      "http://www.w3.org/2001/XMLSchema");

		DocumentBuilder builder = factory.newDocumentBuilder();

		builder.setErrorHandler(simpleErrorHandler);
		
		try {
			builder.parse(new InputSource("src/main/resources/tp1.good1.xml"));
			assertFalse(simpleErrorHandler.hasError());
			
			simpleErrorHandler.reinit();
			builder.parse(new InputSource("src/main/resources/tp1.bad1.xml"));
			assertTrue(simpleErrorHandler.hasError());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
	@Test
	public void should_validate_with_SAX() throws Exception {
		SimpleErrorHandler simpleErrorHandler = new SimpleErrorHandler();
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);

		SAXParser parser = factory.newSAXParser();
		parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", 
		      "http://www.w3.org/2001/XMLSchema");

		XMLReader reader = parser.getXMLReader();
		reader.setErrorHandler(simpleErrorHandler);
		
		try {
			reader.parse(new InputSource("src/main/resources/tp1.good1.xml"));
			assertFalse(simpleErrorHandler.hasError());
			
			simpleErrorHandler.reinit();
			reader.parse(new InputSource("src/main/resources/tp1.bad1.xml"));
			assertTrue(simpleErrorHandler.hasError());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
