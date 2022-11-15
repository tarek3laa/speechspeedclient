package sample;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Presenter {
    private final String name;
    private final List<Video> videos;
    private final int min;
    private final int max;

    public Presenter(String name, List<Video> videos, int min, int max) {
        this.name = name;
        this.videos = videos;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public Video getMainVideo() {
        return videos.stream().filter(video -> video.getSpeed() == 1).collect(Collectors.toList()).get(0);
    }
}
