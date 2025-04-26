package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PipeManager {
    private static final int GAP = 160; // or height / 4
    private static final int SPAWN_INTERVAL = 1500; // ms

    private List<Pipe> pipes;
    private Timer spawnTimer;
    private Image topPipeImage;
    private Image bottomPipeImage;
    private int panelWidth;
    private int panelHeight;

    public PipeManager(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        pipes = new ArrayList<>();

        topPipeImage = new ImageIcon(getClass().getResource("/images/topPipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("/images/bottomPipe.png")).getImage();

        spawnTimer = new Timer(SPAWN_INTERVAL, e -> spawnPipes());
        spawnTimer.start();
    }

    public void update() {
        for (Pipe pipe : pipes) {
            pipe.update();
        }
    }

    public void draw(Graphics g) {
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
    }

    private void spawnPipes() {
        int randomY = (int) (-Pipe.HEIGHT / 4 - Math.random() * Pipe.HEIGHT / 2);

        Pipe topPipe = new Pipe(topPipeImage, panelWidth, randomY);
        Pipe bottomPipe = new Pipe(bottomPipeImage, panelWidth, randomY + Pipe.HEIGHT + GAP);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    public boolean checkCollision(Bird bird) {
        for (Pipe pipe : pipes) {
            if (pipe.collidesWith(bird)) {
                return true;
            }
        }
        return false;
    }

    public int updateScore(Bird bird) {
        double points = 0;
        for (Pipe pipe : pipes) {
            if (pipe.isPassedBy(bird)) {
                points+=0.5;
            }
        }
        return (int)points;
    }

    public void reset() {
        pipes.clear();
    }

    public void stop() {
        spawnTimer.stop();
    }

    public void start() {
        spawnTimer.start();
    }
}