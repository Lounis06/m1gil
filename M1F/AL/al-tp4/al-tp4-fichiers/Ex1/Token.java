import java.util.*;
import java.lang.*;

public class Token {
	// La chaine de caracteres representant le token:
	private String	_token;
	// L'identificateur numerique du token:
	private int	_tokenId;
	// Une annotation quelconque:
	private String _annotation;
	
	public Token(String token, int tokenId, String annotation) {
		_token=token;
		_tokenId=tokenId;
		_annotation=annotation;
	}

	public String getTokenStr() { return _token; }
	public int getTokenId() { return _tokenId; }
	public String getAnnotation() { return _annotation; }
} 
