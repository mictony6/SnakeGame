package com.mic.snake.components;

import com.mic.snake.entity.*;
import com.mic.snake.window.Game;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


public class Level {

    int levelNUM, starCount;
    private int numOfLevelsLeft = 5;
    public Vector2D startPos = new Vector2D(48,48);


    Game g;
    Apple apple;
    LevelLoader levelLoader;
    ColliderGroup obstacles;
    Stack<BoxCollider> stars;
    Stack<BoxCollider> ramens;



    public Level(Game g){
        levelLoader  = new LevelLoader();
        obstacles = new ColliderGroup();
        stars = new Stack<>();
        ramens = new Stack<>();
        this.apple = new Apple(g);
        this.g = g;
        this.levelNUM = -1;


        nextLevel();

    }

    public void draw(Graphics2D g2, int size){
        obstacles.draw(g2, size);
        apple.draw(g2);
    }

    public void retry() {
        try {
            loadLevel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextLevel(){

        if (numOfLevelsLeft >=0 ){

            try {
                levelNUM++;
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
        starCount = 0;
        obstacles.clear();
        ramens.clear();
        for (BoxCollider e: levelLoader.loadLevel(levelNUM).get()){
            switch (e.getId()) {
                case STAR -> {
                    starCount++;
                    obstacles.add(e);
                }
                case RAMEN -> ramens.push(e);
                case EMPTY -> {
                    startPos = new Vector2D(e.x, e.y);
                    g.resetPlayer(e.x, e.y);
                }
                default -> obstacles.add(e);
            }
        }
    }

    public ArrayList<BoxCollider> getObstacleGroup() {
        return obstacles.get();
    }




    public void killObject(BoxCollider o){
        obstacles.kill(o);
        if (o instanceof Star){
            starCount--;
        }

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
        if (starCount == 0  && levelNUM > 0){
            nextLevel();
            newApple();
        }
    }

    public void update(){
        checkStarCount();
    }


    public void newRamen() {

        if (!ramens.empty()){
            obstacles.add(ramens.pop());
        }
    }
}
