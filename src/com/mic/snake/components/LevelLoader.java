package com.mic.snake.components;

import com.mic.snake.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class LevelLoader {

    public ColliderGroup loadLevel(int i) throws IOException {
        String path = "/data/level/level_level"+i+".csv";
        return parseLevelFile(path);

    }

    ColliderGroup parseLevelFile(String path) throws IOException {
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(path))));

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

                switch (c) {
                    case 0 -> colliders.add(new Crate(x * tileSize, y * tileSize, BoxCollider.ID.CRATE));
                    case 1 -> colliders.add(new SpikeBall(x * tileSize, y * tileSize, BoxCollider.ID.SPIKE));
                    case 2 -> colliders.add(new Star(x * tileSize, y * tileSize, BoxCollider.ID.STAR));
                    case 3 -> colliders.add(new Ramen(x * tileSize, y * tileSize, BoxCollider.ID.RAMEN));
                    case 4 -> colliders.add(new BreakableCrate(x * tileSize, y * tileSize, BoxCollider.ID.BREAKABLE_CRATE));
                    case 6 -> colliders.add(new Empty(x*tileSize, y*tileSize, BoxCollider.ID.EMPTY));
                }
                x++;
            }
            y++;
        }
        return colliders;
    }


}
