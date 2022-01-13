package com.mic.snake.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

public class ColliderGroup {

    private ArrayList <BoxCollider> group;
    private int size;

    public ColliderGroup(){
        group = new ArrayList<>();
        size = 0;
    }

    public void add(BoxCollider e){
        group.add(e);
        size++;
    }

    public void kill(BoxCollider entity){
        group.remove(entity);

    }

    public ArrayList<BoxCollider> get(){
        return group;
    }

    public void draw(Graphics2D g2, int size){
        for (BoxCollider e: group){
            e.draw(g2, e.img, size);
        }
    }

    public void update(){
        for (BoxCollider e: group){
            e.update();
        }
    }

    @Override
    public String toString() {
        return "ColliderGroup{" +
                "group=" + group +
                ", size=" + size +
                '}';
    }
}
