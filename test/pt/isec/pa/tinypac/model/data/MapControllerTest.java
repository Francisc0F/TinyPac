package pt.isec.pa.tinypac.model.data;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.isec.pa.utils.Direction;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        initialPosi = getCharPosi('M');
    }

    @Test
    public void TestMoveRightPacman() {
        mp.setCurrentPacmanDirection(Direction.RIGHT);


        mp.evolve();
        mp.evolve();
        printMap();

        ArrayList<Integer> finalPosi = getCharPosi('M');
        if (finalPosi.isEmpty()) {
            fail();
        }
        assertEquals((int) finalPosi.get(1), initialPosi.get(1) + 2);
    }


    @Test
    public void TestMoveLeftPacman() {
        mp.setCurrentPacmanDirection(Direction.LEFT);
        mp.evolve();
        mp.evolve();
        mp.evolve();
        mp.evolve();

        ArrayList<Integer> finalPosi = getCharPosi('M');

        assertEquals((int) finalPosi.get(1), initialPosi.get(1) - 4);
        printMap();
    }


    @Test
    public void TestMoveLeftHitWall() {
        mp.setCurrentPacmanDirection(Direction.LEFT);
        for (int i = 0; i < 1000; i++) {
            mp.evolve();
        }

        ArrayList<Integer> finalPosi = getCharPosi('M');
        System.out.println(finalPosi);
        assertEquals((int) finalPosi.get(1), initialPosi.get(1) - 8);
        printMap();
    }


    @Test
    public void TestMoveRightHitWall() {
        mp.setCurrentPacmanDirection(Direction.RIGHT);
        for (int i = 0; i < 1000; i++) {
            mp.evolve();
        }

        ArrayList<Integer> finalPosi = getCharPosi('M');
        System.out.println(finalPosi);
        assertEquals((int) finalPosi.get(1), initialPosi.get(1) + 8);
        printMap();
    }

    @Test
    public void TestMoveUPHitWall() {
        mp.setCurrentPacmanDirection(Direction.UP);
        for (int i = 0; i < 1000; i++) {
            mp.evolve();
        }

        ArrayList<Integer> finalPosi = getCharPosi('M');
        System.out.println(finalPosi);
        assertEquals((int) finalPosi.get(1), initialPosi.get(1));
        printMap();
    }


    @Test
    public void ChangeDirection() {
        go(Direction.RIGHT, 3);
        go(Direction.LEFT, 3);

        go(Direction.UP, 3);
        go(Direction.DOWN, 3);
        ArrayList<Integer> finalPosi = getCharPosi('M');
        System.out.println(finalPosi);
        assertEquals((int) finalPosi.get(1), initialPosi.get(1));
        printMap();
    }


    @Test
    public void Teleport() {
        go(Direction.LEFT, 2);
        go(Direction.UP, 3);
        go(Direction.LEFT, 3);
        go(Direction.UP, 6);
        go(Direction.LEFT, 8);

        ArrayList<Integer> finalPosi = getCharPosi('M');
        System.out.println(finalPosi);

        printMap();
        assertEquals(27, finalPosi.get(1));
        assertEquals(14, finalPosi.get(0));
    }

    @Test
    public void TeleportTwice() {
        go(Direction.LEFT, 2);
        go(Direction.UP, 3);
        go(Direction.LEFT, 3);
        go(Direction.UP, 6);
        go(Direction.LEFT, 8);

        go(Direction.LEFT, 6);
        go(Direction.RIGHT, 10);

        ArrayList<Integer> finalPosi = getCharPosi('M');
        System.out.println(finalPosi);

        printMap();
        assertEquals(5, finalPosi.get(1));
        assertEquals(14, finalPosi.get(0));
    }


    @Test
    public void EatFood() {
        go(Direction.LEFT, 2);
        assertEquals(2,mp.getPoints());
    }


    @Test
    public void FruitAppearsAfter20BallsEaten() {
        ArrayList<Integer> posi = getCharPosi('F');
        assertTrue(posi.isEmpty());

        go(Direction.LEFT, 2);
        go(Direction.UP, 3);
        go(Direction.LEFT, 3);
        go(Direction.UP, 6);
        go(Direction.LEFT, 3);
        go(Direction.UP, 9);
        go(Direction.RIGHT, 16);
        go(Direction.DOWN, 9);
        go(Direction.LEFT, 3);
        go(Direction.DOWN, 3);
        go(Direction.LEFT, 4);
        posi = getCharPosi('F');
        assertFalse(posi.isEmpty());

        go(Direction.LEFT, 3);
        posi = getCharPosi('F');
        assertTrue(posi.isEmpty());

        go(Direction.LEFT, 3);
        go(Direction.UP, 6);
        go(Direction.RIGHT, 3);
        go(Direction.UP, 3);
        go(Direction.LEFT, 3);
        go(Direction.UP, 3);
        go(Direction.LEFT, 6);

        System.out.println(mp.getPoints());
        printMap();
   /*     posi = getCharPosi('F');
        assertFalse(posi.isEmpty());*/





    }



    private void go(Direction left, int i) {
        mp.setCurrentPacmanDirection(left);
        evolveFor(i);
    }


    private void evolveFor(int n) {
        for (int i = 0; i < n; i++) {
            mp.evolve();
        }
    }

    public void printMap() {
        System.out.println();
        char[][] map = mp.getMap();
        for (char[] chars : map) {
            for (char aChar : chars)
                System.out.print(aChar);
            System.out.println();
        }
    }


    public ArrayList<Integer> getCharPosi(char m) {
        ArrayList<Integer> posi = new ArrayList<>(2);
        char[][] map = mp.getMap();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++)
                if (map[y][x] == m) {
                    posi.add(y);
                    posi.add(x);
                }
        }
        return posi;
    }

}