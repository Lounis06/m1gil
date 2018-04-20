package util;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {
	// ATTRIBUTS
	private boolean errorOccured;
	
	
	// REQUETE
	public boolean hasError() {
		return errorOccured;
	}
	
	
	// COMMANDES
	public void warning(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
    }

    public void error(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
        errorOccured = true;
    }

    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
        errorOccured = true;
    }
}
