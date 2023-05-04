package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.tinypac.model.data.food.Fruit;
import pt.isec.pa.tinypac.model.data.food.PowerfullFood;
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

    public MapController() {
        map = new Map(31, 29);
    }

    public void loadFileMap(String filename) throws IOException {
        File file = new File(filename);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int y = 0;
        while ((st = br.readLine()) != null) {
            for (int x = 0; x < st.length(); x++) {
                Organism created = this.buildByChar(st.charAt(x), y, x);
                map.addElement(created, y, x);
            }
            y++;
        }
    }

    public char[][] getMap() {
        return map.buildMap();
    }

    private Organism buildByChar(char symbol, int y, int x) {

        return switch (symbol) {
            case 'M' -> {
                this.map.setPacman(new Pacman(map, new Map.Position(y, x)));
                yield null;
            }
            case 'x' -> new Wall(map);
            case 'W' -> new Wrap(map);
            case 'o' -> new Food(map);
            case 'y' -> new GhostCave(map);
            case 'Y' -> {
                this.map.setGhostsInitialPosition(new Map.Position(y, x));
                yield new GhostStart(map);
            }
            case 'F' -> {
                this.map.placeFruit(new Fruit(map, new Map.Position(y, x)));
                yield null;
            }
            case 'O' -> new PowerfullFood(map);
            default -> new MazeElement(symbol, map);
        };
    }

    public void setCurrentPacmanDirection(Direction direction) {
        this.map.setCurrentPacmanDirection(direction);
    }

    //todo  control current map iteration and verify
    // if is needed to increase level or reload current level
    public EvolvedAction evolve() {
        return this.map.evolve();
    }

    public int getPoints() {
        return this.map.getTotalScore();
    }

    /**
     * Save instance of MapController game
     * also identify transient info(not relevant)
     */
    public void save() {
    }

    public int getLifesRemaining() {
        return this.map.getLifesRemaining();
    }
}
