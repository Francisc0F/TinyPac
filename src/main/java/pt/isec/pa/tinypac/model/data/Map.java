package pt.isec.pa.tinypac.model.data;


import pt.isec.pa.tinypac.model.data.Ghosts.*;
import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.tinypac.model.data.food.Fruit;
import pt.isec.pa.tinypac.model.data.food.PowerfullFood;
import pt.isec.pa.utils.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * The Map class is responsible for
 * storing information about the walls, obstacles, and paths of the maze.
 */
public class Map {
    private final int godModeIterationTime = 20;
    private int godModeIteration = 0;
    private boolean isGodMode = false;

    public void incGoodModeIteration() {
        godModeIteration++;
    }

    public boolean godModeEnded() {
        return godModeIteration >= godModeIterationTime;
    }

    public void setGodMode() {
        godModeIteration = 0;
        isGodMode = true;
    }

    public void incIteration() {
        iteration++;
    }

    public record Position(int y, int x) {

        static public int getDistance(Position pos1, Position pos2) {
            int dy = pos1.y - pos2.y;
            int dx = pos1.x - pos2.x;
            return (int) Math.sqrt(dy * dy + dx * dx);
        }
    }

    private final int height;
    private final int width;
    private Maze maze;
    private Direction currentPacmanDirection;
    private ArrayList<Ghost> ghosts = new ArrayList<>(4);
    private Pacman pacman;
    private Fruit fruit;
    private Map.Position defaultFruitPosition;
    private Position ghostsInitialPosition;
    private int iteration = 1;
    private int foodEaten = 0;
    private int fruitScore = 0;
    private int powerfullFoodScore = 0;
    private int ghostsEntrance = 5;
    private int lifesRemaining = 3;

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new Maze(height, width);
    }


    public void incFruitScore() {
        fruitScore += 25;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLifesRemaining() {
        return lifesRemaining;
    }

    public int getTotalScore() {
        return this.getFoodScore() + this.getPowerfullFoodScore() + this.getFruitScore();
    }

    private int getPowerfullFoodScore() {
        return powerfullFoodScore;
    }

    private int getFruitScore() {
        return fruitScore;
    }

    public void killPacman() {
        this.pacman = null;
    }

    public int getFoodScore() {
        return foodEaten;
    }

    public void incFoodScore() {
        foodEaten++;
    }

    public Map.Position getPacmanPosition() {
        return pacman.getP();
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setNoFruit() {
        fruit = null;
    }

    public void addElement(Organism organism, int y, int x) {
        maze.set(y, x, organism);
    }

    public void set(Organism organism, int y, int x) {
        maze.set(y, x, organism);
    }

    public Organism getOrganism(int y, int x) {
        IMazeElement element = maze.get(y, x);
        if (element instanceof Organism organism)
            return organism;
        return null;
    }

    public <T extends Organism> List<T> findElementsOf(Class<T> type) {
        List<T> result = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Organism o = (Organism) maze.get(y, x);
                if (type.isInstance(o)) {
                    result.add(type.cast(o));
                }
            }
        }

        return result;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public void setGhostsInitialPosition(Map.Position p) {
        this.ghostsInitialPosition = p;

        /* this.ghosts.add(new Clyde(this));*/
        this.ghosts.add(new Blinky(this, ghostsInitialPosition));
        this.ghosts.add(new Pinky(this, ghostsInitialPosition));
        this.ghosts.add(new Inky(this, ghostsInitialPosition));
    }

    public void placeFruit(Fruit fruit) {
        Map.Position p = fruit.getP();
        defaultFruitPosition = new Map.Position(p.y(), p.x());
    }

    public Position getPositionOf(Organism organism) {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if (maze.get(y, x) == organism)
                    return new Position(y, x);
        return null;
    }

    public void setCurrentPacmanDirection(Direction direction) {
        this.currentPacmanDirection = direction;
    }

    private boolean ghostsEnabled() {
        return iteration > ghostsEntrance;
    }

    public boolean isPacmanAlive() {
        return this.pacman != null;
    }


    public boolean noLivesRemaining() {
        return lifesRemaining == 0;
    }

    public void decLives() {
        lifesRemaining--;
    }

    public boolean allFoodEaten() {
        List<Food> food = findElementsOf(Food.class);
        List<PowerfullFood> powerfulFoods = findElementsOf(PowerfullFood.class);
        return food.size() == 0 && powerfulFoods.size() == 0;
    }


    public void updateLiveOrganisms() {
        List<Organism> lst = new ArrayList<>();
        this.pacman.setDirection(currentPacmanDirection);
        lst.add(this.pacman);

        if (ghostsEnabled()) {
            lst.addAll(this.ghosts);
        }


        placeFruit();
        for (var organism : lst)
            organism.evolve();
    }
/*
    public EvolvedAction evolve() {
        if (this.pacman == null) {
            lifesRemaining--;
            if (lifesRemaining == 0) {
                return EvolvedAction.LOSTLEVEL;
            }
            return EvolvedAction.PACKILLED;
        }

        updateLiveOrganisms();

        iteration++;
        return EvolvedAction.SUCCEED;
    }*/

    private void placeFruit() {
        if (this.fruit == null && getFoodScore() > 0 &&
                getFoodScore() % 20 == 0) {
            this.fruit = new Fruit(this, defaultFruitPosition);
        }
    }

    public char[][] buildMap() {
        char[][] char_board = maze.getMaze();


        if (fruit != null) {
            Map.Position p = fruit.getP();
            char_board[p.y()][p.x()] = 'F';
        } else if (defaultFruitPosition != null) {
            char_board[defaultFruitPosition.y()][defaultFruitPosition.x()] = ' ';
        }

        if (ghostsEnabled()) {
            ghosts.forEach(x -> {
                Map.Position xp = x.getP();
                char_board[xp.y()][xp.x()] = x.getSymbol();
            });
        }

        if (pacman != null) {
            Map.Position pacP = pacman.getP();
            char_board[pacP.y()][pacP.x()] = 'M';
        }
        return char_board;
    }
}
