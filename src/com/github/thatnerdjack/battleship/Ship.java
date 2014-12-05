package com.github.thatnerdjack.battleship;

import java.util.ArrayList;

/**
 * Created by block7 on 12/2/14.
 */
abstract public class Ship {
    int shipSize;
    public int shipHealth;
    boolean isVertical;
    int topLeftX;
    int topLeftY;
    ArrayList<Integer> xCoords = new ArrayList<Integer>();
    ArrayList<Integer> yCoords = new ArrayList<Integer>();

    public Ship(int shipSize) {
        this.shipSize = shipSize;
        this.shipHealth = shipSize;
        if(Battleship.getRandomInt(2) == 1) {
            isVertical = false;
        } else {
            isVertical = true;
        }
    }

    public void genLocation(Map map) {
        if(isVertical) {
            topLeftY = Battleship.getRandomInt(10 - shipSize);
            topLeftX = Battleship.getRandomInt(10);
            for(int i = 0; i < shipSize; i++) {
                xCoords.add(i, topLeftX);
                yCoords.add(i, topLeftY + i);
            }
        } else {
            topLeftX = Battleship.getRandomInt(10 - shipSize);
            topLeftY = Battleship.getRandomInt(10);
            for(int i = 0; i < shipSize; i++) {
                yCoords.add(i, topLeftY);
                xCoords.add(i, topLeftX + i);
            }
        }
        if(!doesFit(map)) {
            genLocation(map);
        }
    }

    public void dropToMap(Map map) {
        for(int i = 0; i < shipSize; i++) {
            int xCoord = xCoords.get(i) - 1;
            int yCoord = yCoords.get(i) - 1;
            map.rows.get(yCoord).set(xCoord, true);
        }
    }

    public boolean doesFit(Map map) {
        for(int i = 0; i < shipSize; i++) {
            int xCoord = xCoords.get(i) - 1;
            int yCoord = yCoords.get(i) - 1;
            if(map.rows.get(yCoord).get(xCoord) == true) {
                return false;
            }
        }
        return true;
    }

    public static void shipStart(Ship[] ships, Map map) {
        for(Ship ship : ships) {
            ship.genLocation(map);
            ship.dropToMap(map);
        }
    }

//    public boolean didHit()

}
