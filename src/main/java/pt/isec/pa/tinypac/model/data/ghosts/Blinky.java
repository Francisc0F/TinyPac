package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.*;

/**
 * Este fantasma desloca-se sempre em frente e quando chega a uma parede ou cruzamento
 * deve sortear uma das direções possíveis, voltando para trás apenas se não existirem outras
 * direções disponíveis
 */
public class Blinky extends Ghost {
    public final static char SYMBOL = '@';

    public Blinky(Map map, Map.Position p) {
        super(map, p);
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
        return isVulnerable ? VULNERABLE : SYMBOL;
    }
}
