package com.mic.snake.window;

import com.mic.snake.components.Input;
import com.mic.snake.components.LevelLoader;
import com.mic.snake.components.Vector2D;
import com.mic.snake.entity.Apple;
import com.mic.snake.entity.EntityGroup;
import com.mic.snake.entity.Snake;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends GUI implements Runnable{
    boolean running = false;
    Thread gameThread;
    int FPS = 12;
    Apple apple;
    Snake player ;
    EntityGroup obstacles;
    LevelLoader levelLoader;
    private int level;



    public int tileSize = 24;
    public int hSlots;
    public int vSlots;
    private boolean debug = false;




    Game(int w, int h){
        super();

        levelLoader = new LevelLoader();
        obstacles = new EntityGroup();

        setBackground(Color.black);
        setDoubleBuffered(true);

        setFocusable(true);
        screenWidth = w;
        screenHeight = h;
        hSlots = screenWidth/tileSize;
        vSlots = screenHeight/tileSize;

        player = new Snake(this);
        addKeyListener(new Input(this));
        apple = new Apple(this);


        setPreferredSize(new Dimension(screenWidth,screenHeight));
        if (running){
            return;
        }

        running = true;

    }

    public static void main(String[] args) {
        Game game = new Game(720, 600);
        new Window(game);


    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void start() {
        level = 0;

        try {
            obstacles = levelLoader.loadLevel(level);
        } catch (IOException e) {
            e.printStackTrace();
        }

        startGameThread();
    }

    private void showGrid(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(1));
        for (int i = 0; i <= vSlots; i++){

            g2.drawLine(0, i*tileSize, screenWidth, i*tileSize);
        }
        for (int i = 0; i <= hSlots; i++){
            g2.drawLine(i*tileSize,0,  i*tileSize, screenHeight);
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000f/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;

            lastTime = currentTime;

            if (delta>=1){
                update();
                repaint();
                delta--;

            }
        }
    }

    void update(){

        player.update();
        if (!player.isAlive){
            gameOver();
        }
        else if (player.getPosition().x > screenWidth || player.getPosition().x < 0|| player.getPosition().y > screenHeight || player.getPosition().y<0){
            player.isAlive = false;
            gameOver();
        }
        else if (player.getPosition().equals(new Vector2D(apple.x, apple.y))){
                player.new_part();
                apple.newApple();
            }


    }

    private void gameOver() {

        setPlayerDirection(new Vector2D(0,0));

    }

    public void setPlayerDirection(Vector2D direction){
        player.setDirection(direction);

    }

    public Vector2D getPlayerDirection(){
        return player.getDirection();
    }

    void drawLevel(Graphics2D g2){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        if (debug){
            showGrid(g2);

        }

        if (player.isAlive){
            player.draw(g2);
            apple.draw(g2);
        }




        g2.dispose();


    }
}
