package com.mic.snake.components;

import com.mic.snake.entity.Crate;
import com.mic.snake.entity.ColliderGroup;
import com.mic.snake.entity.EvilRamen;
import com.mic.snake.entity.SpikeBall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelLoader {

    public ColliderGroup loadLevel(int i) throws IOException {

        return switch (i) {
            case 0 -> parseLevelFile("res/data/level/0/level.csv");
            case 1 -> parseLevelFile( "res/data/level/2/level.csv");
            case 2 -> parseLevelFile( "res/data/level/3/level.csv");
            default -> new ColliderGroup();
        };
    }

    ColliderGroup parseLevelFile(String path) throws IOException {
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(path));

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

        ColliderGroup obstacles = new ColliderGroup();
        int tileSize = 24;
        int y = 0;
        for (ArrayList<Integer> r: data){
            int x=0;
            for (Integer c: r){

                switch (c){
                    case 0:
                        Crate newCrate = new Crate(x*tileSize, y*tileSize);
                        obstacles.add(newCrate);

                        break;
                    case 1:
                        SpikeBall newBall = new SpikeBall(x*tileSize, y*tileSize);
                        obstacles.add(newBall);
                        break;
                    case 2:
                        // TODO: star collectibles
                        break;
                    case 3:
                        EvilRamen newRamen = new EvilRamen(x*tileSize, y*tileSize);
                        obstacles.add(newRamen);
                        break;

                }

                x++;

            }
            y++;
        }
        return obstacles;
    }


}
