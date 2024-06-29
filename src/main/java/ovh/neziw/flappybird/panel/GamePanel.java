/*
 * This file is part of "FlappyBird", licensed under MIT License.
 *
 *  Copyright (c) 2024 neziw
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package ovh.neziw.flappybird.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import ovh.neziw.flappybird.entity.Bird;
import ovh.neziw.flappybird.entity.Pillar;

public class GamePanel extends JPanel implements ActionListener {

    private final List<Pillar> pillars;
    private final Timer timer;
    private final int pillarGap;
    private final int pillarDistance;
    private Bird bird;
    private int score;
    private boolean gameOver;

    public GamePanel() {
        this.setBackground(Color.CYAN);
        this.bird = new Bird(100, 250, 20);
        this.pillars = new ArrayList<>();

        this.pillarGap = 200;
        this.pillarDistance = 300;
        for (int i = 0; i < 1000; i++) {
            this.pillars.add(new Pillar(800 + i * this.pillarDistance, this.pillarGap));
        }

        this.timer = new Timer(20, this);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_SPACE && !GamePanel.this.gameOver) {
                    GamePanel.this.bird.flap();
                }
                if (event.getKeyCode() == KeyEvent.VK_R && GamePanel.this.gameOver) {
                    GamePanel.this.restartGame();
                }
            }
        });
        this.score = 0;
        this.gameOver = false;
    }

    public void runGame() {
        this.timer.start();
    }

    private void drawScore(final Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
        graphics.drawString("Score: " + this.score, 20, 30);
    }

    private void drawGameOver(final Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Arial", Font.BOLD, 48));
        graphics.drawString("Game Over", 250, 250);
        graphics.setFont(new Font("Arial", Font.PLAIN, 24));
        graphics.drawString("Press R to respawn", 275, 300);
    }

    private void restartGame() {
        this.bird = new Bird(100, 250, 20);
        this.pillars.clear();
        for (int i = 0; i < 1000; i++) {
            this.pillars.add(new Pillar(800 + i * this.pillarDistance, this.pillarGap));
        }
        this.score = 0;
        this.gameOver = false;
        this.timer.start();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (!this.gameOver) {
            this.bird.update();
            if (this.bird.getY() < 0 || this.bird.getY() + this.bird.getSize() > 600) {
                this.gameOver = true;
                this.timer.stop();
            }

            for (final Pillar pillar : this.pillars) {
                pillar.update();
                if (pillar.isOffScreen()) {
                    pillar.resetPosition(this.pillars.get(this.pillars.size() - 1).getX() + this.pillarDistance);
                    this.score++;
                }
                if (pillar.collidesWith(this.bird)) {
                    this.gameOver = true;
                    this.timer.stop();
                }
            }
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.bird.draw(graphics);
        for (final Pillar pillar : this.pillars) {
            pillar.draw(graphics);
        }
        this.drawScore(graphics);
        if (this.gameOver) {
            this.drawGameOver(graphics);
        }
    }
}