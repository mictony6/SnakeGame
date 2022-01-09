package com.mic.snake.entity;

import com.mic.snake.components.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x = 0, y = 0;
    Entity next = null;
    Entity prev = null;

    public BufferedImage img;
    public Vector2D direction;

    public void draw(Graphics2D g2, BufferedImage img, int size){
        g2.drawImage(img, x, y,size, size, null);
    }


    public void update() {
    }
}
