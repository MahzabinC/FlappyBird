package org.example;

import java.awt.*;

public class Score {
    private int value = 0;

    public void increment(int points) {
        value += points;
    }

    public void reset() {
        value = 0;
    }

    public void draw(Graphics g, boolean gameOver, int panelWidth, int panelHeight) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.BOLD, 32));
        if (gameOver) {
            g.drawString("Game Over: " + value, panelWidth / 4, panelHeight / 2);
        } else {
            g.drawString("Score: " + value, 10, 35);
        }
    }
}