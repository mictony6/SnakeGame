package com.mic.snake.window;

import com.mic.snake.components.Input;
import com.mic.snake.sound.SoundManager;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    GameManager manager ;
    SoundManager soundManager;

    Window(int screenWidth, int screenHeight){
        super("Snake");
        CardLayout cardLayout = new CardLayout();
        setLayout(cardLayout);

        manager = new GameManager(screenWidth, screenHeight);


        add(manager.gameScreen, "gamePanel");
        add(manager.gameOverScreen, "gameOverPanel");
        add(manager.mainMenuScreen, "menuPanel");
        add(manager.winScreen, "winPanel");



        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setFocusable(true);
        addKeyListener(new Input(this));

        manager.runProgram(cardLayout, this);
    }

    public Game getGame(){
        return manager.gameScreen;
    }
}
