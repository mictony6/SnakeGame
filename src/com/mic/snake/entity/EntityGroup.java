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

    public void draw(Graphics2D g2, BufferedImage img, int size){
        for (Entity e: group){
            e.draw(g2, img, size);
        }
    }

    public void update(){
        for (Entity e: group){
            e.update();
        }
    }

}
