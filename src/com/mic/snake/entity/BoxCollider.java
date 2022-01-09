package com.mic.snake.entity;

import java.awt.*;

public class BoxCollider extends Entity{

    public BoxCollider(){
        super();
    }


    //temporary draw method
    public void draw(Graphics2D g2, int size) {
        g2.fillRect(x, y, size, size);
    }
}
