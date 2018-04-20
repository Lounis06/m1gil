import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExistDBClient {
	public static void main(String[] args) throws Exception {
		// Init.
		URL url = new URL("http://10.130.208.232:8080/exist/rest/db/sepa");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		// Remplissage de la requête
		conn.setRequestMethod("Post");
		conn.setRequestProperty("Accept", "application/xml");
		conn.setRequestProperty("Content-type", "application/xml");
		
		
		// Connexion à la base distante
		conn.connect();
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		// Lecture de la réponse obtenue
		BufferedReader br = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));
		while (br.ready()) {
			System.out.println(br.readLine());
		}

		// Déconnexion
		conn.disconnect();
	}
}
