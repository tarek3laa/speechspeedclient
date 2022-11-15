package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SubmitController {
    private final String presenterName;
    private final String selectedSpeed;
    private final Stage stage;
    @FXML
    private TextField textField;
    @FXML
    private Button submit;

    public SubmitController(String presenterName, String selectedSpeed, Stage stage) {
        this.presenterName = presenterName;
        this.selectedSpeed = selectedSpeed;
        this.stage = stage;
    }

    @FXML
    protected void submit() throws IOException {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:5000")).POST(HttpRequest.BodyPublishers.ofString("" +
                    "{\"email\":" + "\"" + textField.getText() + "\"," +
                    "\"selected_video\":" + "\"" + presenterName + "\"," +
                    "\"selected_speed\":" + "\"" + selectedSpeed + "\"}")).build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
            stage.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
