package com.mic.snake.entity;

import com.mic.snake.window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Apple extends Entity{
    Game game;

    public Apple(Game game){
        this.game = game;
        initialize();
        getImage();
    }

    private void getImage(){
        try {
            img = ImageIO.read(getClass().getResource("/images/apple.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        newApple();

    }

    public void newApple(){
        Random random = new Random();

        x = random.nextInt(0, game.hSlots)*game.tileSize;
        y = random.nextInt(0, game.vSlots)*game.tileSize;
    }

    public void draw(Graphics2D g2){
        if (img == null){
            return;}
        g2.drawImage(img, x, y, game.tileSize, game.tileSize, null);

    }

}
