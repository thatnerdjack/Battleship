package com.github.thatnerdjack.battleship;

import java.util.ArrayList;

/**
 * Created by block7 on 12/2/14.
 */
abstract public class Ship {
    String shipName;
    int shipSize;
    public int shipHealth;
    boolean isVertical;
    int topLeftX;
    int topLeftY;
    ArrayList<Integer> xCoords = new ArrayList<Integer>();
    ArrayList<Integer> yCoords = new ArrayList<Integer>();
    Map shipMap = new Map();

    public Ship(int shipSize, String shipName) {
        this.shipSize = shipSize;
        this.shipHealth = shipSize;
        this.shipName = shipName;
        if(Battleship.getRandomInt(2) == 1) {
            isVertical = false;
        } else {
            isVertical = true;
        }
    }

    public void genLocation(Map map, int topLeftX, int topLeftY, int shipSize) {
        xCoords.clear();
        yCoords.clear();
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

    public void dropToMap(Map map) {
        for(int i = 0; i < shipSize; i++) {
            int xCoord = xCoords.get(i) - 1;
            int yCoord = yCoords.get(i) - 1;
            map.rows.get(yCoord).set(xCoord, true);
            shipMap.rows.get(yCoord).set(xCoord, true);
        }
    }

    public boolean doesFit(Map map) {
        for(int i = 0; i < shipSize; i++) {
            int xCoord = xCoords.get(i) - 1;
            int yCoord = yCoords.get(i) - 1;
            if(xCoord > 9 || yCoord > 9 || map.rows.get(yCoord).get(xCoord) == true) {
                return false;
            }
        }
        return true;
    }

    public static void playerStart(Ship[] ships, Map map) {
        for(Ship ship : ships) {
            boolean running = true;
            while(running) {
                String coord = Battleship.readLine("Please enter the top left coordinate of your " + ship.shipName + ".");
                String isVerticalString = Battleship.readLine("Is your " + ship.shipName + " vertical or horizontal? Type 1 for vertical type 0 for horizontal.");
                if(isVerticalString.equals("1")) {
                    ship.isVertical = true;
                } else {
                    ship.isVertical = false;
                }
                if(Battleship.isValidAnswer(coord)) {
                    int topLeftX = Battleship.xLevelStringToInt(coord) + 1;
                    int topLeftY = Battleship.yLevelStringToInt(coord) + 1;
                    ship.genLocation(map, topLeftX, topLeftY, ship.shipSize);
                    if(!ship.doesFit(map)) {
                        System.out.println("ERROR: Invalid Position.");
                    } else {
                        ship.dropToMap(map);
                        running = false;
                        Map.printMap(map);
                    }
                }
            }
        }
    }

    public static boolean didHit(int xCoord, int yCoord, Map map) {
        if(map.rows.get(yCoord).get(xCoord)) {
            for(int i = 0; i < 5; i++) {
                AIPlayer.AIShips[i].completeHit(xCoord, yCoord);
            }
            return true;
        } else {
            return false;
        }
    }

    public void completeHit(int xCoord, int yCoord) {
        if(shipMap.rows.get(yCoord).get(xCoord)) {
            shipHealth -= 1;
            if(shipHealth < 1) {
                System.out.println("You sunk my " + shipName + "!");
            } else if(shipHealth >= 1) {
                System.out.println("You hit one of my ships!");
            }
            int deadShips = 0;
            for(int i = 0; i < 5; i++) {
                if(AIPlayer.AIShips[i].shipHealth < 1) {
                    deadShips += 1;
                }
            }
            if(deadShips == 5) {
                Battleship.running = false;
            }
        }
    }

}
