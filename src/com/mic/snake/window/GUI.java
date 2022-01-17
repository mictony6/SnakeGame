package com.mic.snake.window;

import javax.swing.*;

public class GUI extends JPanel {
    public int screenWidth, screenHeight;
    public GameManager manager;

    public GUI(GameManager manager) {
        this.manager = manager;
        this.screenWidth = manager.w;
        this.screenHeight = manager.h;
    }


}


