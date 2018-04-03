package tokenizer.tokenlib;
import java.security.InvalidParameterException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Utils {
	public static Iterator<String> tokenize(String str) {
		Enumeration<String> enumeration = (Enumeration) new StringTokenizer(str);
		Iterator<String> it = new IteratorEnumerationAdaptater<String>(enumeration);
		return it;
	}

	public static void displayTokenSequence(List<Integer> li) {
		if (li==null) throw new InvalidParameterException("Null List.");
		System.out.println("Token sequence "+li);
	}
	
	public static void displayTokenIndex(TokenIndex ti) {
		if (ti==null) throw new InvalidParameterException("Null token index.");
		System.out.println("TokenIndex "+ti+" :");
		for (int i=0; i<ti.getNbTokens(); ++i)
			System.out.println("\t"+i+": "+ti.getToken(i)+" ["+ti.getAnnotation(i)+"]");
	}
}
