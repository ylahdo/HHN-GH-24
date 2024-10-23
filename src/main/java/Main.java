
import com.google.gson.Gson;
import data.Dataset;
import data.Result;

public class Main {
    public static void main(String[] args) {

        HttpController httpController = new HttpController();
        DataProcessor dataProcessor = new DataProcessor();
        Gson gson = new Gson();


        try {

            String jsonDataset = httpController.fetchDatasetAsJson();
            Dataset dataset = gson.fromJson(jsonDataset, Dataset.class);
            Result result = dataProcessor.processDataset(dataset);

            String jsonResult = gson.toJson(result);

            httpController.sendResult(jsonResult);

            System.out.println("Erfolgreich abgeschlossen! Das Ergebnis wurde gesendet.");
        } catch (Exception e) {
            System.err.println("Ein Fehler ist aufgetreten: " + e.getMessage());
        }
    }
}