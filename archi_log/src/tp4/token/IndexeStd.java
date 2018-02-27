package tp4.token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tokenlib.Token;

public class IndexeStd implements Indexe {
	private List<Token> idToToken;
	private Map<String, Token> tokenToId;
	
	public IndexeStd() {
		idToToken = new ArrayList<Token>();
		tokenToId = new HashMap<String, Token>();
	}
	
	public int addNewToken(String token, String annotation) {
		String lower = token.toLowerCase();
		if(tokenToId.containsKey(lower)) {
			return this.getTokenId(lower);
		} else {
			int i = idToToken.size();
			Token t = new Token(token, i, annotation);
			idToToken.add(i, t);
			tokenToId.put(lower, t);
			return i;
		}
	}

	@Override
	public String getToken(int tokenId) {
		return idToToken.get(tokenId).getTokenStr();
	}

	@Override
	public int getTokenId(String token) {
		return tokenToId.get(token.toLowerCase()).getTokenId();
	}

	@Override
	public String getAnnotation(int tokenId) {
		return idToToken.get(tokenId).getAnnotation();
	}

	@Override
	public Token getTokenMapping(int tokenId) {
		return idToToken.get(tokenId);
	}

	@Override
	public int getNbTokens() {
		return idToToken.size();
	}

	@Override
	public List<Integer> tokenize(Iterator<String> iterator) {
		List<Integer> result = new ArrayList<Integer>();
		String s;
		while(iterator.hasNext()) {
			s = iterator.next();
			result.add(this.addNewToken(s, "<default>"));
		}
		return result;
	}

}
