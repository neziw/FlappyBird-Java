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

public class Bird {

    private final int x;
    private final int size;
    private final int gravity;
    private final int flapStrength;
    private int y;
    private int yVelocity;

    public Bird(final int x, final int y, final int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.yVelocity = 0;
        this.gravity = 1;
        this.flapStrength = -10;
    }

    public void update() {
        this.yVelocity += this.gravity;
        this.y += this.yVelocity;
        if (this.y < 0) {
            this.y = 0;
            this.yVelocity = 0;
        }
        if (this.y + this.size > 600) {
            this.y = 600 - this.size;
            this.yVelocity = 0;
        }
    }

    public void flap() {
        this.yVelocity = this.flapStrength;
    }

    public void draw(final Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(this.x, this.y, this.size, this.size);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSize() {
        return this.size;
    }
}