package tokenizer;
import java.util.*;
import java.lang.*;

public class Test {
	public static void main(String[] args) {
		Iterator<String> it=TokenLib.tokenize("Ceci est une liste de tokens");
		TokenLib.displayTokens(it);
	}
} 
