package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.Map;

public class Clyde extends Ghost {

    public Clyde(Map p, Map.Position po) {
        super(p);
        this.p = po;
        savePosition();
    }

    @Override
    public void evolve() {
        direction = getClydeDirection();
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
        return '%';
    }
}
