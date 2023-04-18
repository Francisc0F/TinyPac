package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.utils.Position;

public class Blinky extends Ghost {

    Blinky(Position p) {
        super(p);
    }

    @Override
    protected void regularMove() {
        switch (this.position.getDirection()) {
            case UP -> this.up();
            case DOWN -> this.down();
            case LEFT -> this.left();
            case RIGHT -> this.right();
        }
    }

    public void sortNewDirection(){
        this.position.updateDirection();
    }
}
