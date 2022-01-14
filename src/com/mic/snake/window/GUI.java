package com.mic.snake.window;

import javax.swing.*;

public class GUI extends JPanel {
    int screenWidth, screenHeight;

    enum GAME_STATES{
        MENU,
        PLAYING,
        EXIT,
        PAUSED,
        INFINITE,
        GAME_OVER
    }

    private GAME_STATES state;

    public GAME_STATES getState() {
        return state;
    }

    public void setState(GAME_STATES state) {
        this.state = state;
    }
}
