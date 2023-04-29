package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.*;
import pt.isec.pa.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blinky extends Ghost {

    public Blinky(Map map, Map.Position p) {
        super(map);
        this.p = p;
    }


    private void move(int dx, int dy){
        Map.Position newp = new Map.Position(p.y() + dy, p.x() + dx);
        Map.Position pacP = this.map.getPacmanPosition();

        if (pacP.equals(newp)) {
            killPacman();
            p = newp;
            return;
        }

        Organism elemAtNewPlace = this.map.getOrganism(newp.y(), newp.x());

        if (canotMove(elemAtNewPlace)) {
            ArrayList<Direction> allowedDirections = new ArrayList<>(10);

            for (Direction dir : Direction.values()) {
                if (direction.opposite() != dir) {
                    Map.Position pos = getAdjacentPosition(dir);
                    if (canMove(this.map.getOrganism(pos.y(), pos.x()))) {
                        allowedDirections.add(dir);
                    }
                }
            }

            if (allowedDirections.isEmpty()) {
                allowedDirections.add(direction.opposite());
            }

            direction = getRandomDirection(allowedDirections);
            moveByDirection();
        } else {
            p = newp;
        }
    }

    private Map.Position getAdjacentPosition(Direction direction) {
        int dx = 0, dy = 0;
        switch (direction) {
            case UP: dy = -1; break;
            case DOWN: dy = 1; break;
            case LEFT: dx = -1; break;
            case RIGHT: dx = 1; break;
        }
        return new Map.Position(p.y() + dy, p.x() + dx);
    }

    private boolean canotMove(Organism elemAtNewPlace) {
        return elemAtNewPlace instanceof Wall || elemAtNewPlace instanceof GhostCave;
    }

    private boolean canMove(Organism elemAtNewPlace) {
        return !(canotMove(elemAtNewPlace));
    }

    private void killPacman() {
        map.killPacman();
    }

    @Override
    public char getSymbol() {
        return '@';
    }

    @Override
    public void evolve() {
        if (direction == null) {
            return;
        }
        moveByDirection();
    }

    private void moveByDirection() {
        switch (direction) {
            case LEFT -> this.left();
            case RIGHT -> this.right();
            case UP -> this.up();
            case DOWN -> this.down();
        }
    }

    public static Direction getRandomDirection(List<Direction> listAvailable) {
        Random random = new Random();
        int randomIndex = random.nextInt(listAvailable.size());
        return listAvailable.get(randomIndex);
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
