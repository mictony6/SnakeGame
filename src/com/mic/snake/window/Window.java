package com.mic.snake.window;

import javax.swing.*;
import java.awt.*;

public class Window {
    JFrame mainFrame;


    Window(Game game){

        mainFrame = new JFrame("Snake");
        mainFrame.add(game);

        mainFrame.setDefaultCloseOperation(3);
        mainFrame.setResizable(false);

        mainFrame.pack();

        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);






        game.start();
    }

}
