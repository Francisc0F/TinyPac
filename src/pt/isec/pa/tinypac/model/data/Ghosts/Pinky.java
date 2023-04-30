package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.Map;

public class Pinky extends Ghost {

    public Pinky(Map map, Map.Position p) {
        super(map);
        this.p = p;
    }

    @Override
    public char getSymbol() {
        return '&';
    }

    @Override
    protected void move(int dx, int dy) {
        Map.Position newp = checkIsPacman(dx, dy);
        if (newp == null) return;
        p = newp;
    }


    @Override
    public void evolve() {
        direction = getBestDirection();
        switch (direction) {
            case UP -> this.up();
            case DOWN -> this.down();
            case RIGHT -> this.right();
            case LEFT -> this.left();
        }
    }


    @Override
    public boolean savePosition() {
        return false;
    }
}
