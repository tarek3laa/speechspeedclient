package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    protected Label label;
    @FXML
    protected Button playButton, prevButton, pauseButton, nextButton, doneButton;
    @FXML
    private MediaView mediaView;
    @FXML
    protected RadioButton video1, video2, video3, video4, video5, video6;
    protected File file;
    protected Media media;
    protected MediaPlayer mediaPlayer;
    protected List<Presenter> presenters;
    protected int videoIndex;
    protected Presenter chosenVideo;
    private Stage stage;

    public Controller() {
    }

    public Controller(List<Presenter> presenters, Stage stage) {
        this.presenters = presenters;
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        videoIndex = 0;
        label.setText("Video " + (videoIndex + 1) + " : " + presenters.get(videoIndex).getName());
        initVideo(presenters.get(videoIndex).getMainVideo());
    }

    @FXML
    protected void playVideo() {
        mediaPlayer.play();
    }

    @FXML
    protected void pauseVideo() {
        mediaPlayer.pause();
    }

    @FXML
    protected void done() throws IOException {
        chosenVideo = presenters.get(getChosenIndex());
        mediaPlayer.dispose();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("screen.fxml"));
        loader.setController(new SecondStageController(chosenVideo, stage));
        Parent root = loader.load();
        stage.setTitle("video speed");
        stage.setScene(new Scene(root, 1000, 1000));
        stage.show();

    }

    @FXML
    protected void nextVideo() {
        videoIndex = Math.min(videoIndex + 1, presenters.size() - 1);
        initVideo(presenters.get(videoIndex).getMainVideo());
        mediaPlayer.play();
        label.setText("Video " + (videoIndex + 1) + " : " + presenters.get(videoIndex).getName());
    }

    @FXML
    protected void prevVideo() {
        videoIndex = Math.max(videoIndex - 1, 0);
        initVideo(presenters.get(videoIndex).getMainVideo());
        mediaPlayer.play();
        label.setText("Video " + (videoIndex + 1) + " : " + presenters.get(videoIndex).getName());
    }

    protected void initVideo(Video video) {
        if (mediaPlayer != null)
            mediaPlayer.dispose();
        file = new File(video.getPath());
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    protected int getChosenIndex() {
        if (video1.isSelected())
            return 0;
        else if (video2.isSelected())
            return 1;
        else if (video3.isSelected())
            return 2;
        else if (video4.isSelected())
            return 3;
        else if (video5.isSelected())
            return 4;
        else if (video6.isSelected())
            return 5;
        return 0;
    }


}
