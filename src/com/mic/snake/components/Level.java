package com.mic.snake.components;

import com.mic.snake.entity.*;
import com.mic.snake.window.Game;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Level {
    public enum TYPE{
        STORY,
        INFINITE
    }

    TYPE type;
    int state, starCount;
    Apple apple;
    LevelLoader levelLoader;
    Game g;

    ColliderGroup obstacles;


    public Level(TYPE type, Game g){
        levelLoader  = new LevelLoader();
        this.type = type;
        this.state = 0;
        try {
            this.obstacles = levelLoader.loadLevel(state);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.apple = new Apple(g);
        this.g = g;
    }

    public void draw(Graphics2D g2, int size){
        obstacles.draw(g2, size);
        apple.draw(g2);
    }

    public void nextLevel(){
        try {
            state++;
            obstacles = levelLoader.loadLevel(state);
            for (BoxCollider e: obstacles.get()){
                if (e instanceof Star){
                    starCount++;
                }
                else if (e instanceof Empty){
                    g.resetPlayer(e.x, e.y);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BoxCollider> getObstacleGroup() {
        return obstacles.get();
    }

    public void killObject(BoxCollider o){
        if (o instanceof Star){
            starCount--;
        }
        obstacles.kill(o);
    }

    boolean appleCollidesWithObstacles(){
        for (BoxCollider e:
                obstacles.get()) {
            if (new Vector2D(e.x, e.y).equals(apple.getPosition())){
                return true;
            }
        }
        return false;
    }

    public void newApple(){
        //get new apple position
        do{
            apple.newApple();

        } while (appleCollidesWithObstacles());
    }

    public boolean checkPlayerToApple(Vector2D playerPosition){
        return playerPosition.equals(apple.getPosition());
    }

    void checkStarCount(){
        if (starCount==0 && state > 0){
            nextLevel();
            newApple();
        }
    }

    public void update(){
        checkStarCount();
    }

}
