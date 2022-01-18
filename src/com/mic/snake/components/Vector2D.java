package com.mic.snake.components;

import java.util.Objects;

/**
 * Custom Vector class for dealing with game coordinates.
 * @author Michael Anthony Bitoon
 */
public class Vector2D {
    public float x;
    public float y;

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;

    }

    float magnitude(){
        float x2 = x*x;
        float y2 = y*y;
        double res = Math.sqrt(x2+y2);
        return (float)res;
    }

    public Vector2D normalize(){
        float m = this.magnitude();
        if (x == 0){
            return new Vector2D(0, m/this.y);
        }
        else if (y == 0){
            return new Vector2D(m/this.x, 0);
        }
        return new Vector2D(m/this.x, m/this.y);

    }

    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y+other.y);
    }

    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y-other.y);
    }

    public Vector2D scale(float s){
        return new Vector2D(this.x*s, this.y*s);
    }

    public Vector2D scale(int s){
        return new Vector2D(this.x*s, this.y*s);
    }

    public float dot(Vector2D other){
        return (this.x*other.x)+(this.y*other.y);
    }

    public static Vector2D up(){
        return new Vector2D(0,-1);
    }
    public static Vector2D down(){
        return new Vector2D(0, 1);
    }
    public static Vector2D left(){
        return new Vector2D(-1, 0);
    }

    public static Vector2D right(){
        return new Vector2D(1, 0);
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return (vector2D.x-x)==0 && (vector2D.y-y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
