package com.mic.snake.components;

import com.mic.snake.entity.Crate;
import com.mic.snake.entity.EntityGroup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelLoader {

    public EntityGroup loadLevel(int i) throws IOException {
        EntityGroup data = new EntityGroup();
        switch (i - 1) {
            case 0 -> data = parseLevelFile(0);
            case 1 -> data = parseLevelFile(1);
        }
        return data;
    }

    EntityGroup parseLevelFile(int i) throws IOException {
        String path = "/data/level/"+i+"/level.csv";
        ArrayList<ArrayList<Integer>> data = null;

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

        EntityGroup obstacles = new EntityGroup();
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
                }
                x++;
            }
            y++;
        }
        return obstacles;
    }


}
