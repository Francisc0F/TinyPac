package pt.isec.pa.tinypac.model.data;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.isec.pa.utils.Direction;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapControllerTest {
    MapController mp;
    ArrayList<Integer> initialPosi;

    @BeforeEach
    public void load() {
        mp = new MapController();
        try {
            mp.loadFileMap("level01.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialPosi = getInitialPosi();
    }

    @Test
    public void TestMoveRightPacman() {
        mp.setCurrentPacmanDirection(Direction.RIGHT);


        mp.evolve();
        mp.evolve();

        ArrayList<Integer> finalPosi = (ArrayList<Integer>) getInitialPosi();

        assertEquals((int) finalPosi.get(1), initialPosi.get(1) + 2);
    }


    @Test
    public void TestMoveLeftPacman() {
        mp.setCurrentPacmanDirection(Direction.LEFT);
        mp.evolve();
        mp.evolve();
        mp.evolve();
        mp.evolve();

        ArrayList<Integer> finalPosi = (ArrayList<Integer>) getInitialPosi();

        assertEquals((int) finalPosi.get(1), initialPosi.get(1) - 4);
        printMap();
    }


    @Test
    public void TestMoveUPPacman() {
        mp.setCurrentPacmanDirection(Direction.UP);
        mp.evolve();

        ArrayList<Integer> finalPosi = (ArrayList<Integer>) getInitialPosi();

        assertEquals((int) finalPosi.get(1), initialPosi.get(1));
        printMap();
    }

    public void printMap() {
        System.out.println();
        for (int y = 0; y < mp.getMap().length; y++) {
            for (int x = 0; x < mp.getMap()[0].length; x++)
                System.out.print(mp.getMap()[y][x]);
            System.out.println();
        }
    }


    public ArrayList<Integer> getInitialPosi() {
        ArrayList<Integer> posi = new ArrayList<>(2);
        for (int y = 0; y < mp.getMap().length; y++) {
            for (int x = 0; x < mp.getMap()[0].length; x++)
                if (mp.getMap()[y][x] == 'M') {
                    posi.add(y);
                    posi.add(x);
                }
        }
        return posi;
    }

}