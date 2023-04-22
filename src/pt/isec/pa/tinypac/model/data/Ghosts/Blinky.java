package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.utils.Position;

public class Blinky extends Ghost {

    Blinky(Map map) {
        super(map);
    }



    protected void regularMove() {
       /* switch (this.position.getDirection()) {
            case UP -> this.up();
            case DOWN -> this.down();
            case LEFT -> this.left();
            case RIGHT -> this.right();
        }*/
    }

   /* public void sortNewDirection(){
        this.position.updateDirection();
    }*/

    @Override
    public void evolve() {

    }

    @Override
    public boolean savePosition() {
        return false;
    }
}
