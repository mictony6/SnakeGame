package com.mic.snake.window;

import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class GUIManager {
    Game gameScreen;
    GUI gameOverScreen, mainMenuScreen, scoreScreen;
    public Queue<GUI> guiQueue;
    int w, h;

    GUIManager(int w, int h){
        this.w = w;
        this.h = h;
        gameScreen = GUIFactory.getGameScreen(w, h);
        gameOverScreen = GUIFactory.getGameOverScreen(w, h);
    }


    public void runProgram(CardLayout cardLayout, Window window){
        System.out.println("Starting Game");
        gameScreen.start();
        GUI.GAME_STATES currentState = gameScreen.getState();

        if (currentState.equals(GUI.GAME_STATES.GAME_OVER)){
            System.out.println("Game Over");
            cardLayout.show(window.getContentPane(),"gameOverPanel");
        }
    }



}

class GUIFactory{

    static GUI getGameOverScreen(int w, int h){
        GUI gameOverPanel = new GUI();
        gameOverPanel.setLayout(new GridBagLayout());
        gameOverPanel.setDoubleBuffered(true);
        gameOverPanel.setPreferredSize(new Dimension(w,h));
        GridBagConstraints c = new GridBagConstraints();

        gameOverPanel.add(new JSeparator(SwingConstants.VERTICAL), c);
        gameOverPanel.setBackground(Color.black);
        Font largeFont = new Font("Arial", Font.BOLD, 48);

        Font smallFont = new Font("Arial", Font.PLAIN, 20);
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(largeFont);

        gameOverLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridheight = 2;
        gameOverPanel.add(gameOverLabel, c);

        JButton retryButton = new JButton("Retry");
        retryButton.setContentAreaFilled(false);
        retryButton.setForeground(Color.white);

        c.gridheight = 1;
        gameOverPanel.add(retryButton, c);

        JButton exitButton = new JButton("Exit");
        exitButton.setContentAreaFilled(false);
        exitButton.setForeground(Color.white);
        gameOverPanel.add(exitButton, c);

        gameOverPanel.add(new JSeparator(SwingConstants.VERTICAL), c);

        gameOverPanel.setVisible(true);
        return gameOverPanel;
    }




    static Game getGameScreen(int w, int h){
        return new Game(w, h);
    }
}