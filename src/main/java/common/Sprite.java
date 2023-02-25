package common;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {

    private Image[] frames;
    private int currentFrame = 0;
    private int numFrames;
    private int frameWidth;
    private int frameHeight;
    private int duration;

    public Sprite(Image[] frames, int numFrames, int frameWidth, int frameHeight, int duration) {
        super(frames[0]);
        this.frames = frames;
        this.numFrames = numFrames;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.duration = duration;
    }

    public void playAnimation() {
        currentFrame = (currentFrame + 1) % numFrames;
        setImage(frames[currentFrame]);
    }
}