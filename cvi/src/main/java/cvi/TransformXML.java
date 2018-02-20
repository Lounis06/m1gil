package cvi;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformXML {
	
	public static final String PATH = "src/main/resources/";
	
	public void transform_xsl(String filename) {
		try {
	        TransformerFactory tFactory = TransformerFactory.newInstance();
	        Transformer t = tFactory.newTransformer(new StreamSource(PATH + "tp3.cvi-html.01.xsl"));
	        t.transform(new StreamSource(PATH + filename), new StreamResult(PATH + "tp1.good1.out.html"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
