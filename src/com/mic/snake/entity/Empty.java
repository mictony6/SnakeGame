package com.mic.snake.entity;

import java.awt.*;

/**
 * Empty object. Used for spawn location of player snake.
 */
public class Empty extends BoxCollider{
    public Empty(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void draw(Graphics2D g2, int size) {}

}
