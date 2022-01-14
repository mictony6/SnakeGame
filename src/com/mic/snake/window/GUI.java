package com.mic.snake.window;

import com.mic.snake.mouse.GameStates;

import javax.swing.*;

public class GUI extends JPanel {
    int screenWidth, screenHeight;



    private GameStates state;

    public GameStates getState() {
        return state;
    }

    public void setState(GameStates state) {
        this.state = state;

    }

    public void resetGUI(){

    }
}
