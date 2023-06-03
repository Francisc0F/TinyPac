package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.food.Food;
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
    public Map map;

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
                map.set(created, y, x);
            }
            y++;
        }
    }

    public char[][] getMap() {
        return map.buildMap();
    }

    private Organism buildByChar(char symbol, int y, int x) {
        Map.Position position = new Map.Position(y, x);
        return switch (symbol) {
            case 'M' -> {
                Pacman p = new Pacman(map, position);
                this.map.setPacman(p);
                yield p;
            }
            case 'x' -> new Wall(map);
            case 'W' -> {
                Wrap wrap = new Wrap(map, position);
                this.map.addToWrapList(wrap);
                yield wrap;
            }
            case 'o' -> {
                Food f = new Food(map, position);
                this.map.addToFoodList(f);
                yield f;
            }
            case 'O' -> {
                PowerfullFood f = new PowerfullFood(map, position);
                this.map.addToPowerFullFoodList(f);
                yield f;
            }
            case 'y' -> new GhostCave(map);
            case 'Y' -> {
                this.map.setGhostsInitialPosition(position);
                yield new GhostStart(map);
            }
            case 'F' -> {
                this.map.createFruit(position);
                yield null;
            }
            default -> null;
        };
    }

    public void setCurrentPacmanDirection(Direction direction) {
        this.map.setCurrentPacmanDirection(direction);
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
