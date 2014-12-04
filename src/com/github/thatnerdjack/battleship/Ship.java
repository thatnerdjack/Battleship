package com.github.thatnerdjack.battleship;

import java.util.ArrayList;

/**
 * Created by block7 on 12/2/14.
 */
abstract public class Ship {
    int shipSize;
    boolean isVertical;
    int topLeftX;
    int topLeftY;
    ArrayList<Integer> xCoords = new ArrayList<Integer>();
    ArrayList<Integer> yCoords = new ArrayList<Integer>();

    public Ship(int shipSize) {
        this.shipSize = shipSize;
        if(Battleship.getRandomInt(2) == 1) {
            isVertical = false;
        } else {
            isVertical = true;
        }
    }

    public void genLocation() {
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
        if(!doesFit()) {
            genLocation();
        }
    }

    public void dropToMap() {
        for(int i = 0; i < shipSize; i++) {
            int xCoord = xCoords.get(i) - 1;
            int yCoord = yCoords.get(i) - 1;
            Map.rows.get(yCoord).set(xCoord, true);
        }
    }

    public boolean doesFit() {
        for(int i = 0; i < shipSize; i++) {
            int xCoord = xCoords.get(i) - 1;
            int yCoord = yCoords.get(i) - 1;
            if(Map.rows.get(yCoord).get(xCoord) == true) {
                return false;
            }
        }
        return true;
    }

//    public boolean didHit()

}
