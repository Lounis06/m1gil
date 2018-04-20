package tokenizer;
import java.util.*;
import java.lang.*;

public class Test {
	public static void main(String[] args) {
		LinkedList<String> tokenList=new LinkedList<String>();
		tokenList.add("Ceci");
		tokenList.add("est");
		tokenList.add("une");
		tokenList.add("liste");
		tokenList.add("de");
		tokenList.add("tokens");
		Iterator<String> it=tokenList.iterator();
		TokenLib.displayTokens(it);
	}
} 
