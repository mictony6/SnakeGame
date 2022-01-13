package com.mic.snake.window;

import javax.swing.*;

public class Window {
    JFrame mainFrame;


    Window(Game game){

        mainFrame = new JFrame("Snake");
        mainFrame.add(game);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        mainFrame.pack();

        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);






        game.start();
    }

}
