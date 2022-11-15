package sample;

public class Video {
    private final String path;
    private final double speed;

    public Video(String path, double speed) {
        this.path = path;
        this.speed = speed;
    }

    public String getPath() {
        return path;
    }

    public double getSpeed() {
        return speed;
    }
}
