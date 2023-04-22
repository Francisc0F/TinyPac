package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.utils.Direction;

import java.io.*;


/**
 * The map control is represented by a MapController class and takes advantage of a Maze class
 * to store the grid corresponding to the environment and various existing organisms.
 * <p>
 * The map control is represented by a MapController class, which is responsible for managing and
 * manipulating the map.The MapController class uses a Map class to store
 * the grid corresponding to the environment.
 */
public class MapController {
    private int level;
    private Map map;
    private GameEngine gameEngine;


    public MapController() {
        map = new Map(31, 29);
    }

    public void loadFileMap(String filename) throws IOException {
        File file = new File(filename);

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        int y = 0;
        while ((st = br.readLine()) != null) {
            for (int x = 0; x < st.length(); x++) {
                map.addElement(this.addOrganismByFileSymbol(st.charAt(x)), y, x);
            }
            y++;
        }
    }

    public char[][] getMap() {
        return map.getMap();
    }

    private Organism addOrganismByFileSymbol(char symbol) {
        return switch (symbol) {
            case 'M' -> new Pacman(map);
            case 'x' -> new Wall(map);
            case 'o' -> new Food(map);
            default -> new MazeElement(symbol, map);
        };
    }


    public void setCurrentPacmanDirection(Direction direction) {
        this.map.setCurrentPacmanDirection(direction);
    }

    public boolean evolve() {
        return this.map.evolve();
    }
}
