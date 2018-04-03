package tokenizer;

import java.util.ArrayList;
import java.util.List;

import tokenizer.tokenlib.TokenIndex;
import tokenizer.tokenlib.TokenIndexStd;

public class Client {
	public static void main(String[] args)  {
		TokenIndex index = new TokenIndexStd();
		index.addNewToken("Un", "det");
		index.addNewToken("token", "noun");
		index.addNewToken("est", "verb");
		index.addNewToken("un", "det");
		index.addNewToken("couple", "noun");
		System.out.println(index.getNbTokens());
		
		List<String> tokens = new ArrayList<String>();
		tokens.add("Un");
		tokens.add("token");
		tokens.add("est");
		tokens.add("un");
		tokens.add("couple");
		for(String s: tokens) {
			int i = index.getTokenId(s);
			System.out.println(i + " : " + s + " est un " + index.getAnnotation(i));
		}
		
	}
}
