package com.mic.snake.window;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel{

    int screenWidth, screenHeight;
    GUIFactory guiFactory;

    GUI(){
        setLayout(new CardLayout());

        guiFactory = new GUIFactory();

    }

    static class GUIFactory{
        private JPanel gameOverPanel;


        GUIFactory(){
            gameOverPanel = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            gameOverPanel.add(new JSeparator(SwingConstants.VERTICAL), c);
            gameOverPanel.setBackground(Color.black);
            Font largeFont = new Font("/images/gui.otf", Font.BOLD, 48);

            Font smallFont = new Font("/images/gui.otf", Font.PLAIN, 20);
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
        }


        public JPanel getGameOverScreen(){

            return gameOverPanel;
        }
    }
}
