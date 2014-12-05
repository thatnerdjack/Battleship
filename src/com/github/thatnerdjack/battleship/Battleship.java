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

    public static int getRandomInt(int max) {
        return (int)(Math.random()*max + 1);
    }

    public static void main(String args[]) {
        Map rawMap = new Map();
        Map playerMap = new Map();
        ShipCarrier shipCarrier = new ShipCarrier();
        ShipBattle shipBattle = new ShipBattle();
        ShipSubmarine shipSubmarine = new ShipSubmarine();
        ShipDestroyer shipDestroyer = new ShipDestroyer();
        ShipPatrol shipPatrol = new ShipPatrol();
        Ship[] ships = {shipCarrier, shipBattle, shipSubmarine, shipDestroyer, shipPatrol};
        Ship.shipStart(ships, rawMap);
    }

}
