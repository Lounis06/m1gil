package tokenizer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import tokenizer.tokenlib.TokenIndex;
import tokenizer.tokenlib.TokenIndexStd;
import tokenizer.tokenlib.Utils;

public class Test {
	public static void main(String[] args) {
		TokenIndex ti=new TokenIndexStd();	// Votre indexe.
		
		// Nous pourrons remplacer :
		
		LinkedList<String> tokenList=new LinkedList<String>();
		tokenList.add("Ceci");
		tokenList.add("est");
		tokenList.add("une");
		tokenList.add("liste");
		tokenList.add("de");
		tokenList.add("tokens");
		Iterator<String> it=tokenList.iterator();
		// Ajoute la séquence à l'indexe :
		List<Integer> is=ti.tokenize(it);
		Utils.displayTokenSequence(is);	// Séquence de tokens
		Utils.displayTokenIndex(ti);	// État de l'indexe

		// Par :
		
		Iterator<String> it2=Utils.tokenize("Ceci est une manière plus simple de tokenizer");
		is=ti.tokenize(it2);
		Utils.displayTokenSequence(is);	// Séquence de tokens
		Utils.displayTokenIndex(ti);	// État de l'indexe
	}
} 
