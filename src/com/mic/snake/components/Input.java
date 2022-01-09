package com.mic.snake.components;

import com.mic.snake.window.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
    Game game;

    public Input(Game g){
        game = g;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                game.setPlayerDirection(Vector2D.up());
                break;
            case KeyEvent.VK_S:
                game.setPlayerDirection(Vector2D.down());
                break;
            case KeyEvent.VK_A:
                game.setPlayerDirection(Vector2D.left());
                break;
            case KeyEvent.VK_D:
                game.setPlayerDirection(Vector2D.right());
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
    }
}
