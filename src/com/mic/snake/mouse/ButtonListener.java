package com.mic.snake.mouse;

import com.mic.snake.window.GameManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    GameManager manager;

    public ButtonListener(GameManager manager) {
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
