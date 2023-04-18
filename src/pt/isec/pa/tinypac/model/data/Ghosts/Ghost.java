package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.utils.Position;

import java.util.Stack;

public abstract class Ghost implements IGhost {
    Position position;
    boolean isVulnerable = false;
    Stack<Position> previousMoves = new Stack<Position>();

    Ghost(Position p) {
        position = p;
        savePosition();
    }

    @Override
    public boolean savePosition() {
        previousMoves.push(position);
        return true;
    }

    @Override
    public boolean move() {
        if (!isVulnerable) {
            regularMove();
            savePosition();
        } else {
            backwardsMove();
        }
        return true;
    }

    protected abstract void regularMove();

    private void backwardsMove() {
        if (previousMoves.size() == 1) {
            isVulnerable = false;
        } else {
            setPreviousMove();
        }
    }

    @Override
    public boolean getIsVulnerable() {
        return this.isVulnerable;
    }

    private void setPreviousMove() {
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
    }
}
