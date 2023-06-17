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
public class MapController implements Serializable {
    public static String filePattern = "level0%d.txt";
    private static final long serialVersionUID = 1L;
    private int level = 1;
    public Map map;
    private int iterationSpeed = 100;//200
    private int totalRunPoints = 0;
    private int lifesRemaining = 3;
    private static final int maxLevels = 3;

    public MapController() {
        createMap();
    }

    private void createMap() {
        map = new Map(31, 29);
    }

    private void loadFileMap(String filename) throws IOException {
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

    public void loadNextLevel() {
        totalRunPoints += map.getTotalScore();
        createMap();
        int currentLevel = level;
        level++;
        int speedIncrease = level * 10;
        iterationSpeed -= speedIncrease;
        try {
            loadFileMap(String.format(filePattern, level));
        } catch (IOException ex) {
            try {
                loadFileMap(String.format(filePattern, currentLevel));
            } catch (Exception innerEx) {
                System.out.println("Could not load: " + innerEx);
            }
        }
    }

    public int getIterationSpeed() {
        return iterationSpeed;
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
        return totalRunPoints + map.getTotalScore();
    }

    public int getLifesRemaining() {
        return lifesRemaining;
    }

    public boolean noLivesRemaining() {
        return lifesRemaining == 0;
    }

    public void decLives() {
        lifesRemaining--;
    }

    public void loadFirstLevel() {
        try {
            loadFileMap(String.format("level0%d.txt", level));
        } catch (Exception ex) {
            System.out.println("Could not load level 1: " + ex);
        }
    }

    public boolean getIsLastLevel() {
        return level == maxLevels;
    }

    public int getLevel() {
        return level;
    }
}
