package com.mic.snake.entity;

import java.awt.*;

public class BoxCollider extends Entity{

    public BoxCollider(int x, int y){

        this.x = x;
        this.y = y;

    }


    public void draw(Graphics2D g2, int size){
        g2.drawImage(img, x, y, size, size, null);
    }

    public boolean collideRect(BoxCollider other){
        return this.x == other.x && this.y == other.y;
    }
}
