package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.Map;


public class Inky extends Ghost {


    public Inky(Map p, Map.Position po) {
        super(p);
        this.p = po;
    }


    @Override
    public void evolve() {
        direction = getBestDirection();
        System.out.println(direction);
        switch (direction) {
            case UP -> this.up();
            case DOWN -> this.down();
            case RIGHT -> this.right();
            case LEFT -> this.left();
        }
    }

    @Override
    public char getSymbol() {
        return '#';
    }

    @Override
    protected void move(int dx, int dy) {
        Map.Position newp = checkIsPacman(dx, dy);
        if (newp == null) return;
        p = newp;
    }

    @Override
    public boolean savePosition() {
        return false;
    }
}
