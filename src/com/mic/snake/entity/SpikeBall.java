package com.mic.snake.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class SpikeBall extends BoxCollider{
    public SpikeBall(int x, int y){
        super();
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/data/obstacles/obstacle_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;

    }
    public void draw(Graphics2D g2, int size){
        g2.drawImage(img, x, y, size, size, null);
    }

}
