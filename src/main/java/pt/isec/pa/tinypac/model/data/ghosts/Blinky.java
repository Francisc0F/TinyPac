package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.*;
import pt.isec.pa.utils.Direction;

import java.util.List;


/**
 * Este fantasma desloca-se sempre em frente e quando chega a uma parede ou cruzamento
 * deve sortear uma das direções possíveis, voltando para trás apenas se não existirem outras
 * direções disponíveis
 */
public class Blinky extends Ghost {

    public Blinky(Map map, Map.Position p) {
        super(map);
        this.p = p;
        savePosition();
    }

    @Override
    public void evolve() {
        direction = getBlinkyDirection();
        switch (direction) {
            case UP -> this.up();
            case DOWN -> this.down();
            case RIGHT -> this.right();
            case LEFT -> this.left();
        }
        savePosition();
    }





    @Override
    public char getSymbol() {
        return '@';
    }
}
