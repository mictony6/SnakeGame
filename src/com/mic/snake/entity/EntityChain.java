package com.mic.snake.entity;


import com.mic.snake.components.Vector2D;

import java.util.ArrayList;

/**
 * A chain of entity objects like a linked list.
 */
public class EntityChain {
    BoxCollider head;
    Entity tail;
    int length = 0;

    EntityChain(int x, int y){


        head = new BoxCollider(x, y, BoxCollider.ID.EMPTY);
        head.direction = new Vector2D(0,0);
        tail = new Entity();
        tail.direction = new Vector2D(0,0);
        head.next = tail;
        tail.prev = head;
        while (length< 2){
            this.insert();
        }

    }

    /**
     * Inserts new Entity at the end of the chain.
     */
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

    /**
     * @deprecated Unused.
     * @return
     */
    ArrayList<Entity> getArrayList(){
        Entity current = head;
        ArrayList<Entity> chain = new ArrayList<>();
        while (current != tail){
            chain.add(current);
            current = current.next;
        }
        return chain;
    }





}
