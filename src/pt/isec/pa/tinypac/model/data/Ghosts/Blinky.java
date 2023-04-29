package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.*;
import pt.isec.pa.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blinky extends Ghost {

    public Blinky(Map map) {
        super(map);
    }

    private void move(int dx, int dy) {
        Organism elemAtNewPlace = this.map.getOrganism(p.y() + dy, p.x() + dx);

        if (elemAtNewPlace instanceof Wall) {
            direction = getRandomDirection(direction);
            return;
        } else {
            p = new Map.Position(p.y() + dy, p.x() + dx);
        }
    }


    @Override
    public void evolve() {
        if (direction == null) {
            return;
        }
        switch (direction) {
            case LEFT -> this.left();
            case RIGHT -> this.right();
            case UP -> this.up();
            case DOWN -> this.down();
        }
    }

    public static Direction getRandomDirection(Direction currentDirection) {
        Random random = new Random();
        Direction[] directions = Direction.values();

        // Remove the current direction from the list of possible directions
        List<Direction> possibleDirections = new ArrayList<>();
        for (Direction direction : directions) {
            if (direction != currentDirection) {
                possibleDirections.add(direction);
            }
        }

        // Select a random direction from the array of possible directions
        int randomIndex = random.nextInt(possibleDirections.size());
        return possibleDirections.get(randomIndex);
    }

    private void left() {
        move(-1, 0);
    }

    private void right() {
        move(1, 0);
    }

    private void up() {
        move(0, -1);
    }

    private void down() {
        move(0, 1);
    }

    @Override
    public boolean savePosition() {
        return false;
    }
}
