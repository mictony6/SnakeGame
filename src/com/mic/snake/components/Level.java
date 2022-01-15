package com.mic.snake.components;

import com.mic.snake.entity.*;
import com.mic.snake.window.Game;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;



public class Level {

    int state, starCount;
    private int numOfLevelsLeft = 5;
    public Vector2D startPos = new Vector2D(48,48);


    Game g;
    Apple apple;
    LevelLoader levelLoader;
    ColliderGroup obstacles;



    public Level(Game g){
        levelLoader  = new LevelLoader();

        this.apple = new Apple(g);
        this.g = g;
        this.state = -1;


        nextLevel();

    }

    public void draw(Graphics2D g2, int size){
        obstacles.draw(g2, size);
        apple.draw(g2);
    }

    public void retry() {
        starCount = 0;

        try {
            loadLevel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextLevel(){
        starCount = 0;


        if (numOfLevelsLeft >=0 ){

            try {
                state++;
                loadLevel();
                numOfLevelsLeft--;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("You won");
            g.winGame();
        }


    }

    private void loadLevel() throws IOException {
        obstacles = levelLoader.loadLevel(state);
        for (BoxCollider e: obstacles.get()){
            if (e instanceof Star){
                starCount++;
            }
            else if (e instanceof Empty){
                startPos = new Vector2D(e.x, e.y);
                g.resetPlayer(e.x, e.y);
            }
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
