package pt.isec.pa.tinypac.model.data;


import pt.isec.pa.tinypac.model.data.ghosts.*;
import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.tinypac.model.data.food.Fruit;
import pt.isec.pa.tinypac.model.data.food.PowerfullFood;
import pt.isec.pa.utils.Direction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * The Map class is responsible for
 * storing information about the walls, obstacles, and paths of the maze.
 */
public class Map implements Serializable {

    private static final long serialVersionUID = 1L;
    public int getGhostScore() {
        return ghostScore;
    }

    private class GhostsPoints implements Iterator<Integer>, Serializable {
        private int number;

        public GhostsPoints() {
            number = 50;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            int currentNumber = number;
            number += 50;
            return currentNumber;
        }

        public void reset() {
            number = 50;
        }
    }

    private final int godModeIterationTime = 50;
    private int godModeIteration = 0;
    private int ghostScore = 0;

    private GhostsPoints ghostPoints = new GhostsPoints();
    private boolean isGodMode = false;
    private List<Wrap> wrapList = new ArrayList<>(2);
    private List<Food> foodList = new ArrayList<>(295);
    private List<PowerfullFood> powerfullFoodList = new ArrayList<>(4);

    public void incGoodModeIteration() {
        godModeIteration++;
    }

    public boolean godModeTimeEnded() {
        return godModeIteration >= godModeIterationTime;
    }

    private void setGodMode() {
        ghostPoints.reset();
        godModeIteration = 0;
        isGodMode = true;
        setGhostsVulnerable(true);
    }

    public void setNormalMode() {
        isGodMode = false;
        setGhostsVulnerable(false);
    }

    public void incIteration() {
        iteration++;
    }

    public Direction getPacmanDirection() {
        if (this.pacman != null) {
            return this.pacman.getDirection();
        }
        return null;
    }

    public void addToFoodList(Food f) {
        this.foodList.add(f);
    }

    public void addToWrapList(Wrap f) {
        this.wrapList.add(f);
    }

    public void addToPowerFullFoodList(PowerfullFood f) {
        ghostPoints.reset();
        this.powerfullFoodList.add(f);
    }

    public void setGhostsVulnerable(boolean isVulnerable) {
        for (var organism : ghosts)
            organism.setVulnerable(isVulnerable);
    }

    public boolean isGodMode() {
        return isGodMode;
    }

    public void eatGhost(Ghost ghost) {
        ghost.setDeath();
        ghostScore += ghostPoints.next();
    }

    public record Position(int y, int x) implements Serializable{

        static public int getDistance(Position pos1, Position pos2) {
            int dy = pos1.y - pos2.y;
            int dx = pos1.x - pos2.x;
            return (int) Math.sqrt(dy * dy + dx * dx);
        }
    }

    private final int height;

    private final int width;
    private Maze maze;
    private ArrayList<Ghost> ghosts = new ArrayList<>(4);
    private Pacman pacman;
    private Fruit fruit;
    private int iteration = 1;
    private int foodEaten = 0;
    private int fruitScore = 0;
    private int powerfullFoodScore = 0;
    private int ghostsEntrance = 5;


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

    public void resetLevel() {
        iteration = 0;

        for (var ghost : ghosts)
            ghost.reset();

        pacman.reset();
    }

    public int getWidth() {
        return width;
    }


    public int getTotalScore() {
        return this.getFoodScore() +
                this.getPowerfullFoodScore() +
                this.getFruitScore() +
                ghostScore;
    }

    private int getPowerfullFoodScore() {
        return powerfullFoodScore;
    }

    public int getFruitScore() {
        return fruitScore;
    }

    public void killPacman() {
        this.pacman.kill();
    }

    public int getFoodScore() {
        return foodEaten;
    }

    private void incFoodScore() {
        foodEaten++;
    }

    public Map.Position getPacmanPosition() {
        return pacman.getP();
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
        /*this.ghosts.add(new Clyde(this, p));
        this.ghosts.add(new Blinky(this, p));
        this.ghosts.add(new Pinky(this, p));
        */this.ghosts.add(new Inky(this, p));
    }

    public void createFruit(Map.Position p) {
        this.fruit = new Fruit(this, p);
    }

    public Position getPositionOf(Organism organism) {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if (maze.get(y, x) == organism)
                    return new Position(y, x);
        return null;
    }

    public void setCurrentPacmanDirection(Direction direction) {
        pacman.setDirection(direction);
    }

    public boolean ghostsEnabled() {
        return iteration > ghostsEntrance;
    }

    public boolean isPacmanDeath() {
        if(this.pacman !=null){
            return this.pacman.getIsDeath();
        }
        return true;
    }

    public boolean allFoodEaten() {
        List<Food> food = findElementsOf(Food.class);
        List<PowerfullFood> powerfulFoods = findElementsOf(PowerfullFood.class);
        return food.size() == 0 && powerfulFoods.size() == 0;
    }

    public boolean eatFood(Organism food) {
        if (food instanceof Food) {
            ((Food) food).setEated(true);
            incFoodScore();
            return true;
        }

        if (food instanceof PowerfullFood) {
            ((PowerfullFood) food).setEated(true);
            setGodMode();
            return true;
        }
        return false;
    }

    public void updateLiveOrganisms() {
        List<Organism> list = new ArrayList<>();
        list.add(fruit);
        list.addAll(foodList);
        list.addAll(powerfullFoodList);
        list.addAll(wrapList);
        ArrayList<Ghost> alive = new ArrayList<>(ghosts.stream().filter(Ghost::getIsAlive).toList());
        list.addAll(alive);
        list.add(pacman);

        for (var organism : list)
            organism.evolve();
    }

    public char[][] buildMap() {
        return maze.getMaze();
    }
}
