package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.Map;


public class Inky extends Ghost {


    public Inky(Map map, Map.Position po) {
        super(map, po);
        savePosition();
    }

    @Override
    public void evolve() {
        if(!this.map.ghostsEnabled()){
            return;
        }
        if(isVulnerable){
            Map.Position next = previousMoves.pop();
            moveGhost(next.y(), next.x());
            if(previousMoves.size() == 0){
                isVulnerable = false;
            }
            return;
        }
        direction = getNextDirection();
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
        return '#';
    }
}
