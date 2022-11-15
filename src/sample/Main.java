package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Main extends Application {
    final String ROOT_PATH = "src/videos/";

    @Override
    public void start(Stage primaryStage) throws Exception {


        List<Presenter> presenters = new ArrayList<>();

        presenters.add(new Presenter("Al Gore", getVideos(ROOT_PATH + "al_gore_133WpM"), 133 - 5, 133 + 5));
        presenters.add(new Presenter("Brene Browen", getVideos(ROOT_PATH + "brene_brown_154WpM"), 154 - 5, 154 + 5));
        presenters.add(new Presenter("Amy Tan", getVideos(ROOT_PATH + "amy_tan_164WpM"), 164 - 5, 164 + 5));
        presenters.add(new Presenter("Simon Sinek", getVideos(ROOT_PATH + "simon_sinek _170WpM"), 170 - 5, 170 + 5));
        presenters.add(new Presenter("Elzabeth Gilbert", getVideos(ROOT_PATH + "elzabeth_gilbert_187WpM"), 187 - 5, 187 + 5));
        presenters.add(new Presenter("Tony Robbins", getVideos(ROOT_PATH + "tony_robbins_201WbM"), 201 - 5, 201 + 5));
        Controller controller = new Controller(presenters, primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("screen.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("video speed");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();


    }

    protected List<Video> getVideos(String parent) {
        return Stream.of(new File(parent).listFiles())
                .filter(file -> !file.isDirectory())
                .map(file -> new Video(file.getAbsolutePath(), Double.parseDouble(file.getName().split(".mp4")[0]))).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
