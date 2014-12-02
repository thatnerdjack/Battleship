package com.github.thatnerdjack.battleship;

import java.util.ArrayList;

/**
 * Created by block7 on 12/2/14.
 */
public class Map {

    public Map() {
        ArrayList<ArrayList<Boolean>> rows = new ArrayList<ArrayList<Boolean>>();
        for(int i = 0; i < 10; i++) {
            rows.add(new ArrayList<Boolean>());
        }
        for(ArrayList<Boolean> innerList : rows) {
            for(int i = 0; i < 10; i++) {
                innerList.add(i, false);
            }
        }
    }

}
