package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.Map;

public class Clyde extends Ghost {
    public static final char SYMBOL = '%';

    public Clyde(Map p, Map.Position po) {
        super(p, po);
        savePosition();
    }

    @Override
    public void evolve() {
        if (!this.map.ghostsEnabled()) {
            return;
        }

        if (isDeath) {
            if (map.getOrganism(p.y(), p.x()) instanceof Clyde) {
                map.set(null, p.y(), p.x());
            }
            return;
        }

        if (isVulnerable) {
            Map.Position next = previousMoves.pop();
            moveGhost(next.y(), next.x());
            if (previousMoves.size() == 0) {
                isVulnerable = false;
            }
            return;
        }

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
        return isVulnerable ? VULNERABLE : SYMBOL;
    }
}
