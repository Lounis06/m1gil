package tp4.token;

import java.util.Iterator;
import java.util.List;

import tokenlib.Token;

public interface Indexe {
	public int addNewToken(String token, String annotation);
	public String getToken(int tokenId);
	public int getTokenId(String token);
	public String getAnnotation(int tokenId);
	public Token getTokenMapping(int tokenId);
	int getNbTokens();
	List<Integer> tokenize(Iterator<String> iterator);
}
