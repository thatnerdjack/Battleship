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
        if(isVertical) {
            topLeftY = Battleship.getRandomInt(10 - shipSize);
            topLeftX = Battleship.getRandomInt(10);
        } else {
            topLeftX = Battleship.getRandomInt(10 - shipSize);
            topLeftY = Battleship.getRandomInt(10);
        }
        if(Battleship.getRandomInt(2) == 1) {
            isVertical = false;
        } else {
            isVertical = true;
        }

        if(isVertical) {
            for(int i = 0; i < shipSize; i++) {
                xCoords.add(i, topLeftX);
                yCoords.add(i, topLeftY + i);
            }
        } else {
            for(int i = 0; i < shipSize; i++) {
                yCoords.add(i, topLeftY);
                xCoords.add(i, topLeftX + i);
            }
        }
    }

    public void populateMap() {
        for(int i = 0; i < shipSize; i++) {
            int xCoord = xCoords.get(i);
            int yCoord = yCoords.get(i);
            Map.rows.get(yCoord).set(xCoord, true);
        }
    }

//    public boolean didHit()

}
