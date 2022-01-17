package com.mic.snake.sound;


import com.mic.snake.mouse.GameStates;
import com.mic.snake.window.GameManager;

public class SoundManager {
    public Sound main, gameOver, hit, collect, boost, newItem, newLevel, win;
    GameStates lastState;
    Sound currentPlaying;

    public SoundManager(){
        main = new Sound("main");
        gameOver = new Sound("game_over");
        hit = new Sound("hit");
        collect = new Sound("got_apple");
        boost = new Sound("boost");
        newItem = new Sound("new_ramen");
        newLevel = new Sound("new_level");
        win = new Sound("win");


        currentPlaying = main;

        currentPlaying.loop();
        lastState = GameStates.MENU;
    }


    public void update(GameStates currentState) {
        switch (currentState){
            case MENU, PLAYING -> {
                if (lastState != GameStates.MENU && lastState!= GameStates.PLAYING){
                    currentPlaying.stop();
                    currentPlaying = main;

                    currentPlaying.loop();
                }

            }
            case GAME_OVER -> {
                if (lastState != GameStates.GAME_OVER) {
                    currentPlaying.stop();
                    currentPlaying = gameOver;
                    currentPlaying.play();
                }
            }
            case COLLECT -> collect.play();
            case NEW_LEVEL -> newLevel.play();
            case BOOST -> boost.play();
            case NEW_RAMEN -> newItem.play();
            case WIN -> {
                currentPlaying.stop();
                currentPlaying = win;
                currentPlaying.play();
            }
            case HIT -> hit.play();
        }
        lastState = currentState;



    }


}
