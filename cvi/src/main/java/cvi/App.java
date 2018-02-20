package cvi;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	String filename = "tp1.good1.xml";
        System.out.println( "Hello World!" );
        TransformXML t = new TransformXML();
        t.transform_xsl(filename);
    }
}
