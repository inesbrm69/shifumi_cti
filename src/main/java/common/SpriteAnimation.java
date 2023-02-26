package common;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class SpriteAnimation extends AnimationTimer {
    private final Image[] frames;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;
    private final Duration duration;
    private int lastIndex;
    private javafx.geometry.Rectangle2D[] viewports;

    public SpriteAnimation(Image[] frames, int count, int columns, int offsetX, int offsetY, int width, int height, Duration duration) {
        this.frames = frames;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.lastIndex = -1;
        this.viewports = new javafx.geometry.Rectangle2D[count];
        for (int i = 0; i < count; i++) {
            int x = (i % columns) * width + offsetX;
            int y = (i / columns) * height + offsetY;
            viewports[i] = new javafx.geometry.Rectangle2D(x, y, width, height);
        }
    }

    @Override
    public void handle(long l) {

    }
}