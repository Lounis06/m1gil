package util;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformXML {
	// ATTRIBUTS
	/** La feuille de style XSLT utilisée pour la transformation */
	private final String xslFilepath;
	
	
	// CONSTRUCTEUR
	public TransformXML(String xsl) {
		xslFilepath = xsl;
	}
	
	
	// COMMANDE
	/**
	 * Réalise la conversion XML -> HTML, à partir 
	 * d'un nom de fichier xml (sans préciser l'extension).
	 * Le résultat attendu sera produit dans un fichier HTML portant le même nom.
	 */
	public void transform_XSL(String xmlFilename) throws Exception {
		// Init.
		TransformerFactory tFactory = TransformerFactory.newInstance();
		
		// Transformation
		Transformer t = tFactory.newTransformer(new StreamSource(xslFilepath));
		t.transform(new StreamSource(xmlFilename + ".xml"), new StreamResult(xmlFilename + ".html"));
	}
	
	
	// POINT D'ENTREE
	public static void main(String[] args) throws Exception {
		TransformXML t = new TransformXML("../WEB2/src/main/resources/sepa-html.xsl");
		t.transform_XSL("../WEB2/src/main/resources/example");
	}
}
