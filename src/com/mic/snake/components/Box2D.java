package com.mic.snake.components;


public class Box2D implements RigidBody{
    Vector2D size;
    Vector2D position;
    Vector2D min, max;

    public Box2D(int w, int h, int x, int y){
        this.size = new Vector2D(w,h);
        this.position = new Vector2D(x,y);

        Vector2D halfSize = new Vector2D(size.x/2, size.y/2);
        this.min = new Vector2D(position.x-halfSize.x, position.y+halfSize.y);
        this.max = new Vector2D(position.x+halfSize.x, position.y-halfSize.y);


    }




}
