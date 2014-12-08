package com.github.thatnerdjack.battleship;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by block7 on 12/2/14.
 */
public class Battleship {
    static int shotCount = 0;
    final static String possibleXCoords = "abcdefghij";
    final static String possibleYNums = "123456789";
    static Ship[] ships;
    static boolean running;

    public static String readLine(String prompt) {
        String line = null;
        Console c = System.console();
        if (c != null) {
            line = c.readLine(prompt);
        } else {
            System.out.println(prompt);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                //Ignore
            }
        }
        return line;
    }

    public static boolean isValidAnswer(String input) {
        if(input.length() < 2) {
            System.out.println("Not a valid coordinate.");
            return false;
        }
        String xCoord = String.valueOf(input.charAt(0));
        String yNum = String.valueOf(input.charAt(1));
        if (!possibleXCoords.contains(xCoord)) {
            return false;
        } else if (!possibleYNums.contains(yNum)) {
            return false;
        } else {
            return true;
        }
    }

    public static int xLevelStringToInt(String input) {
        input = String.valueOf(input.charAt(0));
        if (input.equals("a")) {
            return 0;
        } else if (input.equals("b")) {
            return 1;
        } else if (input.equals("c")) {
            return 2;
        } else if (input.equals("d")) {
            return 3;
        } else if (input.equals("e")) {
            return 4;
        } else if (input.equals("f")) {
            return 5;
        } else if (input.equals("g")) {
            return 6;
        } else if (input.equals("h")) {
            return 7;
        } else if (input.equals("i")) {
            return 8;
        } else {
            return 9;
        }
    }

    public static int yLevelStringToInt(String input) {
        if (input.length() == 3) {
            return 10;
        } else {
            String tryInput = String.valueOf(input.charAt(1));
            int iValue = Integer.parseInt(tryInput);
            return iValue - 1;
        }
    }

    public static int getRandomInt(int max) {
        return (int) (Math.random() * max + 1);
    }

    public static void main(String args[]) {
        System.out.println("Hello! Welcome to Battleship!");
        Map rawMap = new Map();
        Map playerMap = new Map();
        ShipCarrier shipCarrier = new ShipCarrier();
        ShipBattle shipBattle = new ShipBattle();
        ShipSubmarine shipSubmarine = new ShipSubmarine();
        ShipDestroyer shipDestroyer = new ShipDestroyer();
        ShipPatrol shipPatrol = new ShipPatrol();
        ships = new Ship[]{shipCarrier, shipBattle, shipSubmarine, shipDestroyer, shipPatrol};
        Ship.shipStart(ships, rawMap);
        System.out.println("The ships' have been placed on the map and their positions randomized.");
        System.out.println("Your task is to destroy these ships.");
        running = true;
        while (running) {
            Map.printMap(rawMap);
            String answer = readLine("Please enter your target coordinates:");
            shotCount += 1;
            answer = answer.toLowerCase();
            if (isValidAnswer(answer)) {
                int xCoord = xLevelStringToInt(answer);
                int yCoord = yLevelStringToInt(answer);
                if (Ship.didHit(xCoord, yCoord, rawMap)) {
                    playerMap.rows.get(yCoord).set(xCoord, true);
                    System.out.println("You've shot " + shotCount + " times.");
                } else {
                    System.out.println("You missed.");
                }
            }

        }
        System.out.println("Game Over!");
        System.out.println("You shot " + shotCount + " times.");
        System.out.println("Thanks for playing!");
    }
}