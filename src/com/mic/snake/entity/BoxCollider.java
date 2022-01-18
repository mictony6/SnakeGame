package com.mic.snake.entity;

import java.awt.*;

/**
 * Sub-class of Entity that allows for checking collisions and identifier.
 * @author Michael Anthony Bitoon
 */
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

    /**
     * Used to determine the object's identity.
     */
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

    /**
     * Check if object collides with another BoxCollider.
     * @param other the other object.
     * @return
     */
    public boolean collidesWith(BoxCollider other){
        return this.x == other.x && this.y == other.y;
    }
}
