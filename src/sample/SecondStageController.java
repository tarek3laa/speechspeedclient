package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.net.http.HttpClient;

public class SecondStageController extends Controller {
    Presenter chosenVideo;
    int index = 0;
    private List<Video> videos;
    String[] speed = {"0.75", "1", "1.25", "1.5", "1.75", "2"};
    private Stage stage;

    public SecondStageController(Presenter chosenVideo, Stage stage) {
        this.chosenVideo = chosenVideo;
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        videos = chosenVideo.getVideos().stream().sorted(Comparator.comparingDouble(Video::getSpeed)).collect(Collectors.toList());

        Video video = videos.get(index);
        label.setText("speed : " + video.getSpeed());
        initVideo(video);
        video1.setText("0.75");
        video2.setText("1.0");
        video3.setText("1.25");
        video4.setText("1.5");
        video5.setText("1.75");
        video6.setText("2.0");
        nextButton.setText("speedup");
        prevButton.setText("slowdown");
    }

    @Override
    protected void done() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("submit.fxml"));
        loader.setController(new SubmitController(chosenVideo.getName(), getChosenSpeed(), stage));
        Parent root = loader.load();
        stage.setTitle("submit survey");
        stage.setScene(new Scene(root, 1000, 1000));
        stage.show();


    }


    @Override
    protected void nextVideo() {
        videoIndex = Math.min(videoIndex + 1, videos.size() - 1);
        Video video = videos.get(videoIndex);

        initVideo(video);
        mediaPlayer.play();
        label.setText("speed : " + video.getSpeed());

    }

    @Override
    protected void prevVideo() {
        videoIndex = Math.max(videoIndex - 1, 0);
        Video video = videos.get(videoIndex);

        initVideo(video);
        mediaPlayer.play();
        label.setText("speed : " + video.getSpeed());
    }


    private String getChosenSpeed() {
        return speed[super.getChosenIndex()];
    }
}
