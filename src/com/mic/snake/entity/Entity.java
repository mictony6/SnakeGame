package com.mic.snake.entity;

import com.mic.snake.components.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Base class of all game objects.
 */
public class Entity{
    public int x = 0, y = 0;
    Entity next = null;
    Entity prev = null;

    public BufferedImage img;
    public Vector2D direction;

    /**
     * Draw the entity on screen.
     * @param g2
     * @param img requires a specified image.
     * @param size
     */
    public void draw(Graphics2D g2, BufferedImage img, int size){
        g2.drawImage(img, x, y,size, size, null);
    }

    /**
     * Method that can be overridden.
     */
    public void update() {
    }
}
