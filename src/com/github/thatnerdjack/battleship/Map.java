package com.github.thatnerdjack.battleship;

import java.util.ArrayList;

/**
 * Created by block7 on 12/2/14.
 */
public class Map {
    public ArrayList<ArrayList<Boolean>> rows = new ArrayList<ArrayList<Boolean>>();

    public Map() {
        for(int i = 0; i < 10; i++) {
            rows.add(new ArrayList<Boolean>());
        }
        for(ArrayList<Boolean> innerList : rows) {
            for(int i = 0; i < 10; i++) {
                innerList.add(i, false);
            }
        }
    }

    public static void printMap(Map map) {
        for(ArrayList<Boolean> innerList : map.rows) {
            String returnString = "";
            for(Boolean isShipThere : innerList) {
                if(isShipThere) {
                    returnString += "*";
                } else {
                    returnString += "_";
                }
                returnString += " ";
            }
            System.out.println(returnString);
        }
    }

    public static void printMap(Map hitMap, Map missMap) {
        for(int i = 0; i < 10; i++) {
            String returnString = "";
            for(int i2 = 0; i2 < 10; i2++) {
                boolean didHit = hitMap.rows.get(i).get(i2);
                boolean didMiss = missMap.rows.get(i).get(i2);
                if(didHit) {
                    returnString += "•";
                } else if(didMiss) {
                    returnString += "^";
                } else {
                    returnString += "_";
                }
                returnString += " ";
            }
            System.out.println(returnString);
        }
    }

}
