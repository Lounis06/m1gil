package tokenizer.tokenlib;

import java.util.Enumeration;
import java.util.Iterator;

public class IteratorEnumerationAdaptater<E> implements Iterator<E> {
	private Enumeration<E> enumeration;
	
	public IteratorEnumerationAdaptater(Enumeration<E> enumeration) {
		this.enumeration = enumeration;
	}
	
	public boolean hasNext() {
		return enumeration.hasMoreElements();
	}
	
	public E next() {
		return enumeration.nextElement();
	}
}
