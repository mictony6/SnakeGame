package com.mic.snake.entity;

import com.mic.snake.components.Vector2D;
import com.mic.snake.window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * Special entity sub-class for th apple.
 */
public class Apple extends Entity{
    Game game;

    public Apple(Game game){
        this.game = game;
        initialize();
        getImage();
    }

    private void getImage(){
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/data/obstacles/obstacle_6.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Spawn offscreen at the start of the game.
     */
    private void initialize() {
        x = y = -24;

    }

    /**
     * Generate random location.
     */
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

    public Vector2D getPosition(){
        return new Vector2D(x, y);
    }

}
