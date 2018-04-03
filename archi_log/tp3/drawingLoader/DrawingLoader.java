package tp3.drawingLoader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderAdapter;

public class DrawingLoader {
	private ContentHandler contentHandler;
	
	public DrawingLoader(ContentHandler contentHandler) {
		if(contentHandler == null) { throw new IllegalArgumentException("contentHandler is null"); }
		this.contentHandler = contentHandler;
	}
	
	public void loadXML(File file) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
		    final DocumentBuilder builder = factory.newDocumentBuilder();		
		    final Document document = builder.parse(file);
		}
		catch (final ParserConfigurationException e) {
		    e.printStackTrace();
		}
		catch (final SAXException e) {
		    e.printStackTrace();
		}
		catch (final IOException e) {
		    e.printStackTrace();
		}

		contentHandler.startDocument();
	}

}
