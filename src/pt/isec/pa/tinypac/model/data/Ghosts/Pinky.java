package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.utils.Corner;
import pt.isec.pa.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Pinky extends Ghost {

    private List<Corner> cornerOrder = new ArrayList<>(4);
    private List<Integer> cornerDistances = new ArrayList<>(4);
    private Corner currentCorner = Corner.TOPLEFT;

    private final int distanceToChangeDirection = 10;

    Pinky(Position p, List<Integer> cornerDistances) {
        super(p);
        cornerOrder.add(Corner.TOPRIGHT);
        cornerOrder.add(Corner.BOTTOMRIGHT);
        cornerOrder.add(Corner.TOPLEFT);
        cornerOrder.add(Corner.BOTTOMLEFT);
        this.cornerDistances = cornerDistances;
    }

    @Override
    protected void regularMove() {
       // implement pinky logic
    }
}
