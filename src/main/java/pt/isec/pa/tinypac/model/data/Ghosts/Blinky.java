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

    @Override
    protected void move(int dx, int dy){
        Map.Position newp = checkIsPacman(dx, dy);
        if (newp == null) return;

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

    @Override
    public char getSymbol() {
        return '@';
    }




    @Override
    public boolean savePosition() {
        return false;
    }
}
