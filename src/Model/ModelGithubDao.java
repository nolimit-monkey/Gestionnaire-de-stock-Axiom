package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelGithubDao {

    private static final String API_URL =
            "https://api.github.com/repos/nolimit-monkey/Site-Axiom/contents/public";

    // Retourne les noms de fichiers images du dossier public (ex: photo.jpg)
    public List<String> getImageUrls() {
        List<String> urls = new ArrayList<>();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
            connection.setRequestProperty("Accept", "application/vnd.github+json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            StringBuilder reponse = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String ligne;
                while ((ligne = reader.readLine()) != null) {
                    reponse.append(ligne);
                }
            }

            // Extraire les download_url depuis le JSON retourné par l'API
            Matcher matcher = Pattern.compile("\"download_url\"\\s*:\\s*\"([^\"]+)\"")
                    .matcher(reponse);
            while (matcher.find()) {
                String url = matcher.group(1);
                if (url.matches(".*\\.(jpg|jpeg|png|gif|webp|svg)")) {
                    // Garder uniquement le nom du fichier
                    urls.add(url.substring(url.lastIndexOf('/') + 1));
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur chargement images GitHub : " + e.getMessage());
        }
        return urls;
    }
}
