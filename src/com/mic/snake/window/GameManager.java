package com.mic.snake.window;

import com.mic.snake.components.GameFont;
import com.mic.snake.mouse.ButtonListener;
import com.mic.snake.mouse.GameStates;
import com.mic.snake.sound.SoundManager;

import javax.swing.*;
import java.awt.*;


public class GameManager {
    Game gameScreen;
    SoundManager soundManager;
    GUI gameOverScreen, mainMenuScreen, winScreen;
    public int w, h;
    GameStates currentState = GameStates.MENU;
    ButtonListener listener;

    GameManager(int w, int h){
        this.soundManager = new SoundManager();
        this.w = w;
        this.h = h;
        listener = new ButtonListener(this);
        gameScreen = GUIFactory.getGameScreen(this);
        gameOverScreen = GUIFactory.getGameOverScreen(this, listener);
        mainMenuScreen = GUIFactory.getMenuScreen(this, listener);
        winScreen = GUIFactory.getWinScreen(this, listener);


    }


    public void runProgram(CardLayout cardLayout, Window window){
        System.out.println("Starting Game");
        gameScreen.setSoundManager(soundManager);

        do {
            soundManager.update(currentState);


            switch (currentState) {
                case PLAYING -> {
                    cardLayout.show(window.getContentPane(), "gamePanel");
                    gameScreen.start();
                }
                case RETRY -> {
                    cardLayout.show(window.getContentPane(), "gamePanel");
                    gameScreen.retry();
                    setState(GameStates.PLAYING);


                }
                case GAME_OVER -> cardLayout.show(window.getContentPane(), "gameOverPanel");
                case MENU -> cardLayout.show(window.getContentPane(), "menuPanel");
                case WIN -> cardLayout.show(window.getContentPane(), "winPanel");


            }

        }while (currentState!= GameStates.EXIT);
        window.dispose();
    }


    public void setState(GameStates state) {
        currentState = state;
    }

    public GameStates getState() {
        return currentState;
    }


}

class GUIFactory {

    static JButton makeButton(String title, String actionCommand, Font font, ButtonListener listener) {
        JButton button = new JButton(title);
        button.addActionListener(listener);
        button.setActionCommand(actionCommand);
        button.setForeground(Color.white);
        button.setFont(font);
        button.setContentAreaFilled(false);

        return button;
    }

    static GUI getGameOverScreen(GameManager manager, ButtonListener listener) {
        GameFont fonts = new GameFont();
        GUI gameOverPanel = new GUI(manager);
        gameOverPanel.setLayout(new GridBagLayout());
        gameOverPanel.setDoubleBuffered(true);
        gameOverPanel.setPreferredSize(new Dimension(manager.w, manager.h));
        gameOverPanel.setBackground(Color.black);

        GridBagConstraints c = new GridBagConstraints();


        gameOverPanel.add(new JSeparator(SwingConstants.VERTICAL), c);

        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 4;

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(fonts.getLargeFont());
        gameOverLabel.setForeground(Color.white);
        gameOverPanel.add(gameOverLabel, c);


        c.gridx = 2;
        c.gridheight = 1;
        c.gridy = 2;

        JButton retryButton = makeButton("Retry", "retry", fonts.getSmallFont(), listener);
        gameOverPanel.add(retryButton, c);

        c.gridy = 3;

        JButton exitButton = makeButton("Exit", "exit", fonts.getSmallFont(), listener);
        gameOverPanel.add(exitButton, c);


        gameOverPanel.setVisible(true);
        return gameOverPanel;
    }

    static GUI getMenuScreen(GameManager manager, ButtonListener listener) {
        GameFont fonts = new GameFont();
        GUI menuPanel = new GUI(manager);
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBackground(new Color(50, 80, 35));
        menuPanel.setPreferredSize(new Dimension(manager.w, manager.h));


        GridBagConstraints c = new GridBagConstraints();


        c.gridheight = 2;
        c.gridwidth = 4;
        c.gridy = 0;
        c.gridx = 0;
        JLabel titleLabel = new JLabel("Snake");
        titleLabel.setFont(fonts.getLargeFont());
        titleLabel.setForeground(Color.white);
        menuPanel.add(titleLabel, c);


        c.gridheight = 1;
        c.gridy = 2;
        JButton playButton = makeButton("Play", "play", fonts.getSmallFont(), listener);
        menuPanel.add(playButton, c);


        c.gridy = 3;
        JButton exitButton = makeButton("Exit", "exit", fonts.getSmallFont(), listener);
        menuPanel.add(exitButton, c);

        return menuPanel;


    }

    static GUI getWinScreen(GameManager manager, ButtonListener listener) {
        GUI winPanel = new GUI(manager);
        GameFont fonts = new GameFont();


        winPanel.setBackground(new Color(120, 50, 10));
        winPanel.setLayout(new GridBagLayout());
        winPanel.setPreferredSize(new Dimension(manager.w, manager.h));
        GridBagConstraints c = new GridBagConstraints();

        JLabel winLabel = new JLabel("You Won!");
        winLabel.setFont(fonts.getLargeFont());
        winLabel.setForeground(Color.white);
        winPanel.add(winLabel, c);
        winPanel.setVisible(true);

        return winPanel;
    }


    static Game getGameScreen(GameManager manager) {
        return new Game(manager);
    }
}

