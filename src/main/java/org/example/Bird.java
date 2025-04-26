package org.example;

import javax.swing.*;
import java.awt.*;

public class Bird {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int GRAVITY = 1;
    private static final int JUMP_STRENGTH = -10;

    private Image image;
    private int x, y;
    private int velocity = 0;

    public Bird(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.image = new ImageIcon(getClass().getResource("/images/dove.png")).getImage();
    }

    public void update() {
        velocity += GRAVITY;
        y += velocity;
        if (y < 0) y = 0;
    }

    public void jump() {
        velocity = JUMP_STRENGTH;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public boolean isOffScreen(int panelHeight) {
        return y > panelHeight;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getX() {
        return x;
    }
}