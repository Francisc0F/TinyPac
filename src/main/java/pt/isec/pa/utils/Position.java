package pt.isec.pa.utils;

import java.util.ArrayList;
import java.util.List;

public class Position implements Cloneable {
    int x;
    int y;
    Direction direction = Direction.UP;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position up() {
        return new Position(x, y + 1);
    }

    public Position down() {
        return new Position(x, y - 1);
    }

    public Position left() {
        return new Position(x - 1, y);
    }

    public Position right() {
        return new Position(x + 1, y);
    }

    @Override
    public Position clone() {
        try {
            Position clone = (Position) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void updateDirection() {
        List<Direction> listToSort = new ArrayList<>(4);
        listToSort.add(Direction.UP);
        listToSort.add(Direction.DOWN);
        listToSort.add(Direction.RIGHT);
        listToSort.add(Direction.LEFT);
        int rand = 1 + (int)(Math.random() * ((1 - 3) + 1));
        List<Direction> listWithOutCurrent = listToSort.stream().filter( x -> x != direction).toList();

        direction = listWithOutCurrent.get(rand);
    }
}
