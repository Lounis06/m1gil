public interface FormGenerator {
	public void addTitleHeader(String formTitle); // Titre centre
	public void addSeparator();      // Ligne de separation
	public void addNameLine();       // Nom : __________
	public void addFirstNameLine();  // Prenom : __________
	public void addEmailLine();      // Courriel : __________
	public String send();            // Retourne le flux du formulaire construit
}
