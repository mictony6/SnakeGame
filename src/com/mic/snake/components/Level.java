package com.mic.snake.components;

import com.mic.snake.entity.*;
import com.mic.snake.mouse.GameStates;
import com.mic.snake.window.Game;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


/**
 * Manages level resources imports and allows for dynamic switching in game level and resetting it.
 * @author Michael Anthony Bitoon
 */
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


    /**
     * Constructor for Level class.
     * @param g A component used to be able to access the game's methods useful for providing levels.
     */
    public Level(Game g){

        levelLoader  = new LevelLoader();
        obstacles = new ColliderGroup();
        stars = new Stack<>();
        ramens = new Stack<>();
        this.apple = new Apple(g);
        this.g = g;
        this.levelNUM = 0;


        try {
            numOfLevelsLeft--;
            loadLevel();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Draws the game level.
     */
    public void draw(Graphics2D g2, int size){

        obstacles.draw(g2, size);
        apple.draw(g2);
    }
    /**
     * Reload the current level.
     */
    public void retry() {

        try {
            loadLevel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Advance the next level or win.
     */
    public void nextLevel(){

        g.getSoundManger().update(GameStates.NEW_LEVEL);

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

    /**
     * Loads the current level number.
     * @throws IOException Resources may not be found.
     */
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
    /**
     * @return List of obstacles in the level.
     */
    public ArrayList<BoxCollider> getObstacleGroup() {

        return obstacles.get();
    }



    /**
     * Removes object from the group.
     * @param o Object to be removed.
     */
    public void killObject(BoxCollider o){

        obstacles.kill(o);
        if (o instanceof Star){
            starCount--;
        }

    }
    /**
     * Checks if apple's random position is inside one of the obstacles.
     */
    boolean appleCollidesWithObstacles(){

        for (BoxCollider e:
                obstacles.get()) {
            if (new Vector2D(e.x, e.y).equals(apple.getPosition())){
                return true;
            }
        }
        return false;
    }
    /**
     * Generate new apple spawn point.
     */
    public void newApple(){

        //get new apple position
        do{
            apple.newApple();

        } while (appleCollidesWithObstacles());
    }

    /**
     * Check if player collides with apple.
     * @param playerPosition
     * @return
     */
    public boolean checkPlayerToApple(Vector2D playerPosition){
        return playerPosition.equals(apple.getPosition());
    }

    /**
     * Checks if all the stars are collected in game.
     */
    void checkStarCount(){

        if (starCount == 0  && levelNUM > 0){
            nextLevel();
            newApple();
        }
    }


    public void update(){
        checkStarCount();
    }

    /**
     * Adds a new ramen object in the game from the stack.
     */
    public void newRamen() {

        if (!ramens.empty()){
            obstacles.add(ramens.pop());
        }
    }
}
