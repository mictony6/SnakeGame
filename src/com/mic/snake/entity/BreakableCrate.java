package com.mic.snake.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class BreakableCrate extends Crate{
    public boolean isBroken = false;
    public BreakableCrate(int x, int y, ID id) {
        super(x, y, id);
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/data/obstacles/obstacle_5.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void breakCrate(){
        isBroken = true;
    }




}
