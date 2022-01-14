package com.mic.snake.window;

import com.mic.snake.mouse.ButtonListener;
import com.mic.snake.mouse.GameStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

public class GUIManager {
    Game gameScreen;
    GUI gameOverScreen, mainMenuScreen, scoreScreen;
    public Queue<GUI> guiQueue;
    int w, h;
    GameStates currentState;

    GUIManager(int w, int h){
        this.w = w;
        this.h = h;
        gameScreen = GUIFactory.getGameScreen(w, h);
        gameOverScreen = GUIFactory.getGameOverScreen(w, h);
    }


    public void runProgram(CardLayout cardLayout, Window window){
        System.out.println("Starting Game");

        gameScreen.start();
        currentState = gameScreen.getState();
        do {

            System.out.println("looping in manager");
            System.out.println(gameScreen.getState());
            if (currentState.equals(GameStates.GAME_OVER)) {
                cardLayout.show(window.getContentPane(), "gameOverPanel");

                if (gameOverScreen.getState().equals(GameStates.RETRY)){
                    gameOverScreen.setState(GameStates.PLAYING);
                    System.out.println("Retrying...");
                    cardLayout.show(window.getContentPane(), "gamePanel");

                    gameScreen.retry();
                }
                else if (gameOverScreen.getState().equals(GameStates.EXIT)){
                    gameScreen.setState(GameStates.EXIT);
                    System.out.println("Exiting...");
                }

            }
            currentState = gameScreen.getState();
        }while (currentState!= GameStates.EXIT);
        window.dispose();




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
        Font largeFont = new Font("Zelda Oracles", Font.BOLD, 48);

        Font smallFont = new Font("Zelda Oracles", Font.PLAIN, 20);
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(largeFont);

        gameOverLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridheight = 2;
        gameOverPanel.add(gameOverLabel, c);

        JButton retryButton = new JButton("Retry");
        retryButton.setFont(smallFont);
        retryButton.setContentAreaFilled(false);
        retryButton.setForeground(Color.white);

        c.gridheight = 1;
        gameOverPanel.add(retryButton, c);

        JButton exitButton = new JButton("Exit");
        exitButton.setContentAreaFilled(false);
        exitButton.setForeground(Color.white);
        exitButton.setFont(smallFont);
        gameOverPanel.add(exitButton, c);

        gameOverPanel.add(new JSeparator(SwingConstants.VERTICAL), c);

        gameOverPanel.setVisible(true);
        gameOverPanel.setState(GameStates.GAME_OVER);

        ButtonListener listener = new ButtonListener(gameOverPanel);

        retryButton.addActionListener(listener);
        retryButton.setActionCommand("retry");
        exitButton.addActionListener(listener);
        exitButton.setActionCommand("exit");
        gameOverPanel.setState(GameStates.PLAYING);
        return gameOverPanel;
    }




    static Game getGameScreen(int w, int h){
        return new Game(w, h);
    }
}