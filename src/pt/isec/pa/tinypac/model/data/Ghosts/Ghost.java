package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.Organism;
import pt.isec.pa.utils.Direction;
import pt.isec.pa.utils.Position;

import java.util.Stack;

public abstract class Ghost extends Organism implements IGhost {
    public static final char SYMBOL = 'y';
    boolean isVulnerable = false;
    protected Direction direction = Direction.UP;
    Stack<Position> previousMoves = new Stack<Position>();
    protected Map.Position p;

    public Ghost(Map map) {
        super(map);
        savePosition();
    }

    /*@Override
    public boolean savePosition() {
        previousMoves.push(position);
        return true;
    }*/

    @Override
    public boolean move() {
        if (!isVulnerable) {
    /*        regularMove();*/
            savePosition();
        } else {
          /*  backwardsMove();*/
        }
        return true;
    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }


    /*protected abstract void regularMove();*/

   /* private void backwardsMove() {
        if (previousMoves.size() == 1) {
            isVulnerable = false;
        } else {
            setPreviousMove();
        }
    }*/

    @Override
    public boolean getIsVulnerable() {
        return this.isVulnerable;
    }

   /* private void setPreviousMove() {
        position = previousMoves.pop();
    }

    protected void up() {
        position = position.up();
    }

    protected void down() {
        position = position.down();
    }

    protected void left() {
        position = position.left();
    }

    protected void right() {
        position = position.right();
    }*/
}
