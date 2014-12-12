package com.github.thatnerdjack.battleship;

/**
 * Created by block7 on 12/12/14.
 */
public class AIPlayer {
    ShipCarrier aiShipCarrier;
    ShipBattle aiShipBattle;
    ShipSubmarine aiShipSubmarine;
    ShipDestroyer aiShipDestroyer;
    ShipPatrol aiShipPatrol;
    public static Ship[] AIShips;
    static Map AIMap = new Map();

    public AIPlayer() {
        aiShipCarrier = new ShipCarrier();
        aiShipBattle = new ShipBattle();
        aiShipSubmarine = new ShipSubmarine();
        aiShipDestroyer = new ShipDestroyer();
        aiShipPatrol = new ShipPatrol();
        AIShips = new Ship[]{aiShipCarrier, aiShipBattle, aiShipSubmarine, aiShipDestroyer, aiShipPatrol};
    }

    public static void shipStart() {
        for(Ship ship : AIShips) {
            boolean running = true;
            while(running) {
                int topLeftX = Battleship.getRandomInt(10 - ship.shipSize);
                int topLeftY = Battleship.getRandomInt(10 - ship.shipSize);
                ship.genLocation(AIMap, topLeftX, topLeftY, ship.shipSize);
                if(ship.doesFit(AIMap)) {
                    running = false; //START HERE!
                    ship.dropToMap(AIMap);
                }
            }
        }
    }

}
