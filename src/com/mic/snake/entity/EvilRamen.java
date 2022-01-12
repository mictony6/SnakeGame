package com.mic.snake.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class EvilRamen extends BoxCollider{
    public EvilRamen(int x, int y, ID id){
        super(x, y, id);
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/data/obstacles/obstacle_4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;

    }
}
