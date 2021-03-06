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
    static Ship[] playerShips;
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
        input = input.substring(1);
        if (input.equals("1")) {
            return 0;
        } else if (input.equals("2")) {
            return 1;
        } else if (input.equals("3")) {
            return 2;
        } else if (input.equals("4")) {
            return 3;
        } else if (input.equals("5")) {
            return 4;
        } else if (input.equals("6")) {
            return 5;
        } else if (input.equals("7")) {
            return 6;
        } else if (input.equals("8")) {
            return 7;
        } else if (input.equals("9")) {
            return 8;
        } else {
            return 9;
        }
    }

    public static int getRandomInt(int max) {
        return (int) (Math.random() * max + 1);
    }

    public static void main(String args[]) {
        System.out.println("Hello! Welcome to Battleship!");

        Map playerMap = new Map();
        Map hitMap = new Map();
        Map missMap = new Map();

        AIPlayer computer = new AIPlayer();
        computer.shipStart();

        System.out.println("The ships' have been placed on the map and their positions randomized.");
        System.out.println("Your task is to destroy these ships.");
        System.out.println("Now you will place your ships.");

        ShipCarrier playerShipCarrier = new ShipCarrier();
        ShipBattle playerShipBattle = new ShipBattle();
        ShipSubmarine playerShipSubmarine = new ShipSubmarine();
        ShipDestroyer playerShipDestroyer = new ShipDestroyer();
        ShipPatrol playerShipPatrol = new ShipPatrol();
        playerShips = new Ship[]{playerShipCarrier, playerShipBattle, playerShipSubmarine, playerShipDestroyer, playerShipPatrol};
        Ship.playerStart(playerShips, playerMap);

        running = true;
        while (running) {
            Map.printMap(computer.AIMap);
            System.out.println("Enemy Map:");
            Map.printMap(hitMap, missMap);
            System.out.println("Your Map:");
            Map.printMap(playerMap);
            String answer = readLine("Please enter your target coordinates:");
            shotCount += 1;
            answer = answer.toLowerCase();
            if (isValidAnswer(answer)) {
                int xCoord = xLevelStringToInt(answer);
                int yCoord = yLevelStringToInt(answer);
                if (Ship.didHit(xCoord, yCoord, computer.AIMap)) {
                    hitMap.rows.get(yCoord).set(xCoord, true);
                    System.out.println("You've shot " + shotCount + " times.");
                } else {
                    missMap.rows.get(yCoord).set(xCoord, true);
                    System.out.println("You missed.");
                }
            } else {
                System.out.println("Invalid coordinate.");
            }

        }
        System.out.println("Game Over!");
        System.out.println("You shot " + shotCount + " times.");
        System.out.println("Thanks for playing!");
    }
}