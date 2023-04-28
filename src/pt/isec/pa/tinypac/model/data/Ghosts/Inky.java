package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.utils.Position;

public class Inky extends Ghost{

    public Inky(Map p){
        super(p);
    }

    @Override
    public void evolve() {

    }

    @Override
    public boolean savePosition() {
        return false;
    }
}
