package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.utils.Position;

public class Inky extends Ghost{

    Inky(Position p){
        super(p);
    }
    @Override
    protected void regularMove() {
        // equal to Pinky but diferent order
    }
}
