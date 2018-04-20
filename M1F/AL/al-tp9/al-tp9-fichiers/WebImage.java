public class WebImage implements IWebImage {
	// url de l'image :
	private final String _URL;
	
	// Données factices:
	private int _data;

	public WebImage(String URL) {
		_URL=URL;
		
		// Téléchargement de l'image (simulation) :
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Download error !");
		}
		_data=12345;
	}

	public void display(int length, int height) {
		// Simulation d'affichage :
		System.out.println("Je suis "+_URL+" affichée en "+length+"x"+height);
	}
}

