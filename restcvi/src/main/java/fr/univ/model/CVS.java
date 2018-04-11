package fr.univ.model;

import java.io.Serializable;

public class CVS implements Serializable {
	private static final long serialVersionUID = 2L;
	private static int id = 1;
	private String nom;
	private String prenom;
	
	public CVS(String n, String p) {
		nom = n;
		prenom = p;
	}
	
	@Override
	public String toString() {
		return new String("("+id+") : "+nom.toUpperCase()+" "+prenom);
	}
}
