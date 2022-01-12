package com.mic.snake.components;

import com.mic.snake.entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LevelLoader {

    public ColliderGroup loadLevel(int i) throws IOException {

        return switch (i) {
            case 0 -> parseLevelFile("/data/level/level_level1.csv");
            case 1 -> parseLevelFile( "/data/level/level_level2.csv");
            case 2 -> parseLevelFile( "/data/level/level_level3.csv");
            default -> new ColliderGroup();
        };
    }

    ColliderGroup parseLevelFile(String path) throws IOException {
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)));

        String line;
        String delimiter = ",";
        while((line = br.readLine()) != null){
            ArrayList<Integer> row = new ArrayList<>(25);
            String[] stringValues = line.split(delimiter);
            for (String s: stringValues){
                row.add(Integer.valueOf(s));
            }

            data.add(row);
        }

        ColliderGroup colliders = new ColliderGroup();
        int tileSize = 24;
        int y = 0;
        for (ArrayList<Integer> r: data){
            int x=0;
            for (Integer c: r){

                switch (c){
                    case 0:
                        Crate newCrate = new Crate(x*tileSize, y*tileSize, BoxCollider.ID.SIMPLE);
                        colliders.add(newCrate);

                        break;
                    case 1:
                        SpikeBall newBall = new SpikeBall(x*tileSize, y*tileSize, BoxCollider.ID.SIMPLE);
                        colliders.add(newBall);
                        break;
                    case 2:
                        colliders.add(new Star(x*tileSize, y*tileSize, BoxCollider.ID.COLLECTIBLE));
                        break;
                    case 3:
                        EvilRamen newRamen = new EvilRamen(x*tileSize, y*tileSize, BoxCollider.ID.SIMPLE);
                        colliders.add(newRamen);
                        break;

                }

                x++;

            }
            y++;
        }
        return colliders;
    }


}
