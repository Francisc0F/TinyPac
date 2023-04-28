package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.*;
import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.tinypac.model.data.food.Fruit;
import pt.isec.pa.utils.Direction;
import pt.isec.pa.utils.Position;

import java.util.Optional;

public class Blinky extends Ghost {

    public Blinky(Map map) {
        super(map);
    }

    private void move(int dx, int dy) {
        Organism elemAtNewPlace = this.map.getOrganism(p.y() + dy, p.x() + dx);

        if (elemAtNewPlace instanceof Wall) {
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

    private Direction sortNewDirection() {
        //todo generate new direction

        return direction;
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
