package com.mic.snake.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

public class EntityGroup {

    private ArrayList <Entity> group;
    private int size;

    public EntityGroup(){
        group = new ArrayList<>();
        size = 0;
    }

    public void add(Entity e){
        group.add(e);
        size++;
    }

    public void kill(Entity entity){
        for (Entity e:
             group) {
            if (e.equals(entity)){
                group.remove(e);
                size --;
            }

        }
    }

    public ArrayList get(){
        return group;
    }

    public void draw(Graphics2D g2, int size){
        for (Entity e: group){
            e.draw(g2, e.img, size);
        }
    }

    public void update(){
        for (Entity e: group){
            e.update();
        }
    }

    @Override
    public String toString() {
        return "EntityGroup{" +
                "group=" + group +
                ", size=" + size +
                '}';
    }
}
