package com.mic.snake.entity;

import com.mic.snake.components.Vector2D;
import com.mic.snake.window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Snake {
    BufferedImage bodyPart, headDown, headRight, headLeft, headUp ;
    BufferedImage tailLeft, tailRight, tailUp, tailDown;
    EntityChain snake;
    public boolean isAlive;
    Game game;
    public Vector2D direction = new Vector2D(1,0);

    public Snake(Game g){
        isAlive = true;
        game = g;
        snake = new EntityChain(0,0);
        try {
            headDown = ImageIO.read(Objects.requireNonNull(getClass().getResource("/snake_head_down.png")));
            headUp = ImageIO.read(Objects.requireNonNull(getClass().getResource("/snake_head_up.png")));
            headLeft = ImageIO.read(Objects.requireNonNull(getClass().getResource("/snake_head_left.png")));
            headRight = ImageIO.read(Objects.requireNonNull(getClass().getResource("/snake_head_right.png")));
            bodyPart = ImageIO.read(Objects.requireNonNull(getClass().getResource("/snake_body.png")));

            tailLeft = ImageIO.read(Objects.requireNonNull(getClass().getResource("/snake_tail_left.png")));
            tailRight = ImageIO.read(Objects.requireNonNull(getClass().getResource("/snake_tail_right.png")));
            tailUp = ImageIO.read(Objects.requireNonNull(getClass().getResource("/snake_tail_up.png")));
            tailDown = ImageIO.read(Objects.requireNonNull(getClass().getResource("/snake_tail_down.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

    public Vector2D getDirection() {
        return direction;
    }

    public void update(){
        Entity part = snake.tail.prev;
        while (part != snake.head){

            if(part.x== snake.head.x && part.y == snake.head.y){
                isAlive = false;
            }
            part.x = part.prev.x;
            part.y = part.prev.y;
            part = part.prev;
        }

        move();

    }

    void move(){
        if (snake.head.direction.equals(Vector2D.up())&& direction.equals(Vector2D.down())){
            direction = snake.head.direction;
        }
        else if  (snake.head.direction.equals(Vector2D.down())&& direction.equals(Vector2D.up())){
            direction = snake.head.direction;
        }
        else if  (snake.head.direction.equals(Vector2D.left())&& direction.equals(Vector2D.right())){
            direction = snake.head.direction;
        }
        else if  (snake.head.direction.equals(Vector2D.right())&& direction.equals(Vector2D.left())){
            direction = snake.head.direction;
        }
        snake.head.x += direction.x* game.tileSize;
        snake.head.y += direction.y* game.tileSize;

    }

    BufferedImage getImage(Entity part){
        BufferedImage currentImage = null;

        if (part == snake.head) {
            if (direction.x >0) {
                currentImage = headRight;


            }
            else if (direction.x < 0){
                currentImage = headLeft;
            }
            else if (direction.y > 0){
                currentImage = headDown;

            }
            else{
                currentImage = headUp;
            }
            part.direction = direction;
            return currentImage;
        }

        else if (part.next.equals(snake.tail)){

            if (Vector2D.up().equals(part.direction)) {
                currentImage = tailUp;
            } else if (Vector2D.down().equals(part.direction)) {
                currentImage = tailDown;
            } else if (Vector2D.right().equals(part.direction)) {
                currentImage = tailRight;
            } else if (Vector2D.left().equals(part.direction)) {
                currentImage = tailLeft;
            }

        }
        else{

            currentImage = bodyPart;
        }
        Vector2D delta = new Vector2D(part.prev.x - part.x , (part.prev.y- part.y)).normalize();
        part.direction = new Vector2D(delta.x, delta.y);
        return currentImage;



    }

    public void draw(Graphics2D g2){
        Entity part = snake.head;
        int body_size = game.tileSize;

        while (part != snake.tail){
            BufferedImage currentImage;
            currentImage = getImage(part);
            if (part.next.equals(snake.tail)){
                g2.drawImage(currentImage, part.x, part.y, game.tileSize, game.tileSize, null);
                break;
            }
            g2.drawImage(currentImage, part.x+(game.tileSize -body_size)/2, part.y+(game.tileSize-body_size)/2, body_size, body_size, null);
            part = part.next;
//            body_size -=1;
//            if (body_size < 12){
//                body_size = 12;
//            }
        }

    }

    public void new_part(){
        snake.insert();
    }

    public Vector2D getPosition(){
        return new Vector2D(snake.head.x, snake.head.y);
    }

}
