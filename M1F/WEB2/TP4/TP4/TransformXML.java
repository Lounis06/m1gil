package cri;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformXML {

	// feuille de style xslt
	private final String xsltFilepath;

	// constructeur
	public TransformXML(String file) {
		xsltFilepath = file;
	}

	public void transform_XSL(String file) {
		TransformerFactory fac = TransformerFactory.newInstance();
		Transformer t;
		try {
			t = fac.newTransformer(new StreamSource(xsltFilepath));
			t.transform(new StreamSource(file + ".xml"), new StreamResult(file + ".html"));

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		TransformXML t = new TransformXML("src/main/resources/tp4.cvi-html.2.xsl");
		t.transform_XSL("src/main/resources/tp1.good01");
	}
}
