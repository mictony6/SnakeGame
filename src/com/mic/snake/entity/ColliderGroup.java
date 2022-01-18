package com.mic.snake.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

/**
 * Class for handling a group of BoxColliders.
 * @author Michael Anthony Bitoon
 */
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
        size --;

    }

    public ArrayList<BoxCollider> get(){
        return group;
    }

    /**
     * Calls draw method on each member of the group.
     * @param g2
     * @param size
     */
    public void draw(Graphics2D g2, int size){
        for (BoxCollider e: group){
            e.draw(g2, e.img, size);
        }
    }

    /**
     * Calls update method on each BoxCollider member.
     */
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

    /**
     * Clears group.
     */
    public void clear(){
        group.clear();
        size = 0 ;
    }

    public int length(){
        return size;
    }
}
