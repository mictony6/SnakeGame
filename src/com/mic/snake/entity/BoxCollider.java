package com.mic.snake.entity;

import java.awt.*;

public class BoxCollider extends Entity{
    public enum ID{
        CRATE,
        STAR,
        RAMEN,
        BREAKABLE_CRATE,
        EMPTY,
        SPIKE,
        APPLE
    }

    private ID id;


    public ID getId() {
        return id;
    }

    public BoxCollider(int x, int y, ID id){
        this.id = id;
        this.x = x;
        this.y = y;

    }


    public void draw(Graphics2D g2, int size){
        g2.drawImage(img, x, y, size, size, null);
    }

    public boolean collidesWith(BoxCollider other){
        return this.x == other.x && this.y == other.y;
    }
}
