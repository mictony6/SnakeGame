package com.mic.snake.components;

import com.mic.snake.window.Window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * Handles game keyboard inputs.
 * @author Michael Anthony Bitoon
 */
public class Input extends KeyAdapter {

    Window window;

    public Input(Window window){
        this.window = window;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                window.getGame().setPlayerDirection(Vector2D.up());
                break;
            case KeyEvent.VK_S:
                window.getGame().setPlayerDirection(Vector2D.down());
                break;
            case KeyEvent.VK_A:
                window.getGame().setPlayerDirection(Vector2D.left());
                break;
            case KeyEvent.VK_D:
                window.getGame().setPlayerDirection(Vector2D.right());
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
    }
}
