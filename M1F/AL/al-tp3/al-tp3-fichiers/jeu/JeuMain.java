public class JeuMain {
	public static void main(String[] args) {
		Manette manette = new XXXSuperControleur(); /* un certain type de manette */
		Jeu jeu = new Jeu(manette);
		jeu.mainLoop();
	}
}
