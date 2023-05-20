package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.*;
import pt.isec.pa.utils.Direction;
import pt.isec.pa.utils.Position;

import java.util.ArrayList;


/**
 * Este fantasma desloca-se sempre em frente e quando chega a uma parede ou cruzamento
 * deve sortear uma das direções possíveis, voltando para trás apenas se não existirem outras
 * direções disponíveis
 */
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
                    Map.Position pos = getDeltaByDirection(dir);
                    if (canMove(this.map.getOrganism(pos.y(), pos.x()))) {
                        allowedDirections.add(dir);
                    }
                }
            }

            // if no direction is available go backwards
            if (allowedDirections.isEmpty()) {
                allowedDirections.add(direction.opposite());
            }

            direction = getRandomDirection(allowedDirections);
            moveByDirection();
        } else {
            p = newp;
        }

        savePosition();
    }

    @Override
    public char getSymbol() {
        return '@';
    }
}
