package com.mic.snake.entity;


import com.mic.snake.components.Vector2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntityChain {
    BoxCollider head;
    Entity tail;
    int length = 0;

    EntityChain(int x, int y){
        head = new BoxCollider(x, y, BoxCollider.ID.SIMPLE);
        head.direction = new Vector2D(0,0);
        tail = new Entity();
        tail.direction = new Vector2D(0,0);
        head.next = tail;
        tail.prev = head;
        while (length< 2){
            this.insert();
        }

    }

    void insert(){
        Entity newPart = new Entity();
        newPart.x = -32;
        newPart.y = -32;
        Entity last = tail.prev;
        newPart.direction = last.direction;
        last.next = newPart;
        newPart.prev = last;
        newPart.next = tail;

        tail.prev = newPart;

        length++;
    }

    ArrayList<Entity> getArrayList(){
        Entity current = head;
        ArrayList<Entity> chain = new ArrayList<Entity>();
        while (current != tail){
            chain.add(current);
            current = current.next;
        }
        return chain;
    }





}
