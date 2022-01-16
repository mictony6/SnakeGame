package com.mic.snake.window;

import com.mic.snake.mouse.GameStates;

import javax.swing.*;

public class GUI extends JPanel {
    public int screenWidth, screenHeight;
    public GUIManager manager;

    public GUI(GUIManager manager) {
        this.manager = manager;
        this.screenWidth = manager.w;
        this.screenHeight = manager.h;
    }


}


