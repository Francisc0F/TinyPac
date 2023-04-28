package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.Map;

public class Clyde extends Ghost {

    public Clyde(Map p) {
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
