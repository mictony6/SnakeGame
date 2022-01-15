package com.mic.snake.mouse;

import com.mic.snake.window.GUI;
import com.mic.snake.window.GUIManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    GUIManager manager;

    public ButtonListener(GUIManager manager) {
        this.manager = manager;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "retry" -> manager.setState(GameStates.RETRY);
            case "exit" -> manager.setState(GameStates.EXIT);
            case "play" -> manager.setState(GameStates.PLAYING);
        }
    }




}
