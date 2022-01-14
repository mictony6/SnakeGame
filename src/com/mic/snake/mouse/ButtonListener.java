package com.mic.snake.mouse;

import com.mic.snake.window.GUI;
import com.mic.snake.window.GUIManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    GUI gui;

    public ButtonListener(GUI gui) {
        this.gui = gui;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "retry":
                //TODO: retry logic
                System.out.println("User wants to retry.");
                gui.setState(GameStates.RETRY);
                break;
            case "exit":
                //TODO: exit logic
                System.out.println("User wants to exit.");
                gui.setState(GameStates.EXIT);
                break;
        }
    }




}
