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
package ovh.neziw.flappybird.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Pillar {

    private final int speed;
    private final int gap;
    private final int width;
    private int heightTop;
    private int heightBottom;
    private int x;

    public Pillar(final int startX, final int gap) {
        this.x = startX;
        this.gap = gap;
        this.width = 60;

        final Random random = new Random();
        this.heightTop = random.nextInt(300) + 50;
        this.heightBottom = 600 - (this.heightTop + this.gap);

        this.speed = 5;

        this.resetHeight();
    }

    private void resetHeight() {
        final Random random = new Random();
        this.heightTop = random.nextInt(300) + 50;
        this.heightBottom = 600 - (this.heightTop + this.gap);
    }

    public void update() {
        this.x -= this.speed;
    }

    public boolean isOffScreen() {
        return this.x + this.width < 0;
    }

    public void resetPosition(final int newX) {
        this.x = newX;
        this.resetHeight();
    }

    public void draw(final Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(this.x, 0, this.width, this.heightTop);
        graphics.fillRect(this.x, 600 - this.heightBottom, this.width, this.heightBottom);
    }

    public boolean collidesWith(final Bird bird) {
        final int birdRight = bird.getX() + bird.getSize();
        final int birdBottom = bird.getY() + bird.getSize();

        if (birdRight > this.x && bird.getX() < this.x + this.width) {
            return bird.getY() < this.heightTop || birdBottom > 600 - this.heightBottom;
        }
        return false;
    }

    public int getX() {
        return this.x;
    }
}