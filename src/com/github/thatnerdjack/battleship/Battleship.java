package com.github.thatnerdjack.battleship;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by block7 on 12/2/14.
 */
public class Battleship {
    int shotCount = 0;
    final static String possibleXCoords = "abcdefghijkl";
    final static String possibleYNums = "123456789";

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
        String xCoord = input.substring(1);
        String yNum = input.substring(2);
        if(!possibleXCoords.contains(xCoord)) {
            return false;
        } else if(!possibleYNums.contains(yNum)) {
            return false;
        } else {
            return true;
        }
    }

//    public static int xLevelString(String input) {
//        String string = "b";
//        string.
//    }

    public static int yLevelStringToInt(String input) {
        if(input.length() == 3) {
            return 10;
        } else {
            String tryInput = String.valueOf(input.charAt(2));
            int iValue = Integer.parseInt(tryInput);
            return iValue;
        }
    }

    public static int getRandomInt(int max) {
        return (int)(Math.random()*max + 1);
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
        Ship[] ships = {shipCarrier, shipBattle, shipSubmarine, shipDestroyer, shipPatrol};
        Ship.shipStart(ships, rawMap);
        System.out.println("The ships' have been placed on the map and their positions randomized.");
        System.out.println("Your task is to destroy these ships.");
        boolean running = true;
        while(running) {
            String answer = readLine("Please enter your target coordinates:");
            answer = answer.toLowerCase();
            if(isValidAnswer(answer)) {

            }
        }
    }

}
