package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    private static final int WIDTH = 360;
    private static final int HEIGHT = 640;

    private Bird bird;
    private PipeManager pipeManager;
    private Score score;
    private GameState gameState;

    private Timer gameTimer;
    private Image background;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        init();
    }

    private void init() {
        background = new ImageIcon(getClass().getResource("/images/background.png")).getImage();

        bird = new Bird(WIDTH / 8, HEIGHT / 2);
        pipeManager = new PipeManager(WIDTH, HEIGHT);
        score = new Score();
        gameState = GameState.PLAYING;

        setupInput();
        gameTimer = new Timer(1000 / 60, this);
        gameTimer.start();
    }

    private void setupInput() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (gameState == GameState.PLAYING) {
                        bird.jump();
                    } else if (gameState == GameState.GAME_OVER) {
                        restart();
                    }
                }
            }
        });
    }

    private void restart() {
        bird = new Bird(WIDTH / 8, HEIGHT / 2);
        pipeManager.reset();
        score.reset();
        gameState = GameState.PLAYING;
        pipeManager.start();
        gameTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState == GameState.PLAYING) {
            bird.update();
            pipeManager.update();
            score.increment(pipeManager.updateScore(bird));

            if (pipeManager.checkCollision(bird) || bird.isOffScreen(HEIGHT)) {
                gameOver();
            }
        }
        repaint();
    }

    private void gameOver() {
        gameState = GameState.GAME_OVER;
        pipeManager.stop();
        gameTimer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
        bird.draw(g);
        pipeManager.draw(g);
        score.draw(g, gameState == GameState.GAME_OVER, WIDTH, HEIGHT);
    }
}
