package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.utils.Corner;

import java.util.ArrayList;
import java.util.List;

public class Pinky extends Ghost {

    private List<Corner> cornerOrder = new ArrayList<>(4);
    private List<Integer> cornerDistances = new ArrayList<>(4);
    private Corner currentCorner = Corner.TOPLEFT;

    private final int distanceToChangeDirection = 10;

    public Pinky(Map map) {
        super(map);
    }

    @Override
    public void evolve() {

    }

    @Override
    public boolean savePosition() {
        return false;
    }
}
