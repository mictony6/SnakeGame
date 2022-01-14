package com.mic.snake.window;

import com.mic.snake.components.Input;
import com.mic.snake.components.Level;
import com.mic.snake.components.Vector2D;
import com.mic.snake.entity.BoxCollider;
import com.mic.snake.entity.Snake;
import com.mic.snake.entity.Star;
import com.mic.snake.mouse.GameStates;

import java.awt.*;
import java.util.ArrayList;

public class Game extends GUI {
    boolean running = false;
    final int FPS = 12;
    Snake player ;
    private Level gameLevel;
    private boolean debug;
    private int ramenBoost = 0;



    public int tileSize = 24;
    public int hSlots;
    public int vSlots;




    Game(int w, int h){
        super();

        debug = false;
        gameLevel = new Level(Level.TYPE.STORY , this);

        setBackground(new Color(50,80,35));
        setDoubleBuffered(true);

        setFocusable(true);
        screenWidth = w;
        screenHeight = h;
        hSlots = screenWidth/tileSize;
        vSlots = screenHeight/tileSize;

        player = new Snake(this);





        setPreferredSize(new Dimension(screenWidth,screenHeight));
        if (running){
            return;
        }

        running = true;

    }

    public void start() {
        player.isAlive = true;
        gameLevel.newApple();
        setState(GameStates.PLAYING);
        run();

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

//    @Override
    public void run() {
        double drawInterval = 1000000000f/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (getState().equals(GameStates.PLAYING) ){

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



    void update(){
        player.update();
        gameLevel.update();


        ArrayList<BoxCollider> toBeRemoved = new ArrayList<>();

        //check for obstacle collision
        for (BoxCollider e: gameLevel.getObstacleGroup()){
            if( player.collidesWith(e) ){
                switch(e.getId()){
                    case SIMPLE :
                        player.isAlive = false;
                        break;
                    case COLLECTIBLE:
                        if (!(e instanceof Star)) {
                            ramenBoost++;
                        }
                        toBeRemoved.add(e);
                        break;
                    case BREAKABLE:
                        if( ramenBoost > 0){
                            toBeRemoved.add(e);
                            ramenBoost--;
                            break;
                        }
                        else{
                            player.isAlive = false;
                        }
                }
            }
        }

        for (BoxCollider e: toBeRemoved){
            gameLevel.killObject(e);
        }

        if (!player.isAlive){
            gameOver();

        }
        else if (player.getPosition().x > screenWidth || player.getPosition().x < 0|| player.getPosition().y > screenHeight || player.getPosition().y<0){
            player.isAlive = false;
        }
        else if (gameLevel.checkPlayerToApple(player.getPosition())){
                player.new_part();
                //test level switching
                if (player.getScore() == 5){
                    gameLevel.nextLevel();

                }
                gameLevel.newApple();





        }



    }

    private void gameOver() {
        setPlayerDirection(new Vector2D(0,0));
        setState(GameStates.GAME_OVER);
        System.out.println("inside Game Over");

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

        gameLevel.draw(g2, 24);
        if (player.isAlive){
            player.draw(g2);
            gameLevel.draw(g2, tileSize);
        }


        g2.dispose();


    }

    public void resetPlayer(int x, int y) {
        player.reset(x, y);
    }

    public void retry(){
        setState(GameStates.PLAYING);
        player.isAlive = true;
        gameLevel.retry();
        run();

    }


}
