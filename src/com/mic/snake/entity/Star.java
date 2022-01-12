package com.mic.snake.entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Star extends BoxCollider{
    public Star(int x, int y, ID id) {
        super(x, y, id);
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/data/obstacles/obstacle_3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
