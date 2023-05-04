package pt.isec.pa.tinypac.model.data.food;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.Organism;

public class Fruit extends Organism implements IMazeElement {
    Map.Position p;

    public Fruit(Map environment) {
        super(environment);
    }

    public Fruit(Map environment, Map.Position p) {
        super(environment);
        this.p = p;
    }

    public void evolve() {
    }

    @Override
    public char getSymbol() {
        return 'F';
    }


    public Map.Position getP() {
        return p;
    }
}
