package tp3.drawingLoader;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Field;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import tp3.drawingLoader.DrawingBuilder.ShapeType;

public class ContentHandlerImpl implements ContentHandler {
	private DrawingBuilder drawingBuilder;
	
	public ContentHandlerImpl(DrawingBuilder dB) {
		super();
		this.drawingBuilder = dB;
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		return;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Début de lecture du document...");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Lecture du document terminée.");
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		switch (localName) {
		case "line":
			drawingBuilder.startShape(ShapeType.Line);
			drawingBuilder.setColor(getColor(atts.getValue("color")));
			break;
		case "circle":
			drawingBuilder.startShape(ShapeType.Circle);
			drawingBuilder.setColor(getColor(atts.getValue("color")));
			break;
		case "rectangle":
			drawingBuilder.startShape(ShapeType.Rectangle);
			drawingBuilder.setColor(getColor(atts.getValue("color")));
			break;
		case "point":
			//int n = (int) atts.getValue("x");
			//Point p = new Point(Double.parseDouble(atts.getValue("x")), Double.parseDouble(atts.getValue("y")));
		default:
			throw new IllegalArgumentException(localName + " unhandled or unimplemented yet");
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "line":
			drawingBuilder.endShape();
			break;
		case "circle":
			drawingBuilder.endShape();
			break;
		case "rectangle":
			drawingBuilder.endShape();
			break;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	private Color getColor(String c) {
		Color color;
		try {
		    Field field = Class.forName("java.awt.Color").getField(c);
		    color = (Color)field.get(null);
		} catch (Exception e) {
			throw new IllegalArgumentException(c + " is not recognized as a color");
		}
		return color;
	}
}
