package org.example;

import java.awt.*;

public class Pipe {
    public static final int WIDTH = 65;
    public static final int HEIGHT = 400;
    private static final int SPEED = -4;

    private Image image;
    private int x;
    private int y;
    private boolean passed = false;

    public Pipe(Image image, int startX, int startY) {
        this.image = image;
        this.x = startX;
        this.y = startY;
    }

    public void update() {
        x += SPEED;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public boolean collidesWith(Bird bird) {
        return getBounds().intersects(bird.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public boolean isPassedBy(Bird bird) {
        if (!passed && x + WIDTH < bird.getX()) {
            passed = true;
            return true;
        }
        return false;
    }
}