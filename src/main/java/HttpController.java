import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpController {
    private final String GROUND_URL = "http://localhost:8080";
    private final HttpClient client;

    public HttpController() {
        this.client = HttpClient.newHttpClient();
    }

    public String fetchDatasetAsJson() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(getWebsiteUrl(UrlExtensions.DATASET)))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HttpURLConnection.HTTP_OK) {
                return response.body();
            } else {
                System.err.println("Statuscode: " + response.statusCode());
                throw new RuntimeException("Fehler beim Abrufen der Daten: " + response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Abrufen der Daten: " + e.getMessage());
        }
    }
    private String getWebsiteUrl(UrlExtensions extension) {
        return (extension != null) ? GROUND_URL + extension.path : GROUND_URL;
    }

    public void sendResult(String json) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(getWebsiteUrl(UrlExtensions.RESULT)))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Fehler bei der Anfrage: " + response.body());
            }
            System.out.println("Anfrage Antwort: " + response.body());
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Senden der Ergebnisse: " + e.getMessage());
        }
    }

    public enum UrlExtensions {
        DATASET("/v1/dataset"),
        RESULT("/v1/result");

        public final String path;

        UrlExtensions(String path) {
            this.path = path;
        }
    }


}