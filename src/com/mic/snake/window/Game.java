package com.mic.snake.window;

import com.mic.snake.components.Input;
import com.mic.snake.components.LevelLoader;
import com.mic.snake.components.Vector2D;
import com.mic.snake.entity.*;

import java.awt.*;
import java.io.IOException;

public class Game extends GUI implements Runnable{
    boolean running = false;
    Thread gameThread;
    int FPS = 12;
    Apple apple;
    Snake player ;
    ColliderGroup obstacles;
    ColliderGroup collectibles;
    LevelLoader levelLoader;
    private int level = 0;
    private boolean debug;



    public int tileSize = 24;
    public int hSlots;
    public int vSlots;




    Game(int w, int h){
        super();

        debug = false;
        levelLoader = new LevelLoader();
        obstacles = new ColliderGroup();
        collectibles = new ColliderGroup();





        setBackground(new Color(50,80,35));
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
        System.out.println(level);
        //Load level file
        try {
            obstacles = levelLoader.loadLevel(level);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Generate apple location
        do{
            apple.newApple();

        } while (appleCollidesWithObstacles());


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
            delta += (currentTime-lastTime);

            lastTime = currentTime;

            if (delta-drawInterval>=0){
                update();
                repaint();
                delta-=drawInterval;

            }
        }
    }

    boolean appleCollidesWithObstacles(){
        for (BoxCollider e:
             obstacles.get()) {
            if (new Vector2D(e.x, e.y).equals(apple.getPosition())){
                return true;
            }
        }
        return false;
    }

    void update(){
        System.out.println(player.getScore());
        player.update();
        for (BoxCollider e: obstacles.get()){
            if( player.collidesWith(e) && e.getId().equals(BoxCollider.ID.SIMPLE)){
                player.isAlive = false;
            }
        }
        if (!player.isAlive){
            gameOver();
        }
        else if (player.getPosition().x > screenWidth || player.getPosition().x < 0|| player.getPosition().y > screenHeight || player.getPosition().y<0){
            player.isAlive = false;
        }
        else if (player.getPosition().equals(new Vector2D(apple.x, apple.y))){
                player.new_part();



                if (player.getScore() == 5){
                    level++;
                    player.reset(3*tileSize, 9*tileSize);
                    try{
                        obstacles = levelLoader.loadLevel(level);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }


                do{
                    apple.newApple();

                } while (appleCollidesWithObstacles());


            }



    }

    private void gameOver() {

        setPlayerDirection(new Vector2D(0,0));

    }

    public void setPlayerDirection(Vector2D direction){
        player.setDirection(direction);

    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        if (debug){
            showGrid(g2);
        }

        obstacles.draw(g2, 24);
        if (player.isAlive){
            player.draw(g2);
            apple.draw(g2);
        }




        g2.dispose();


    }
}
