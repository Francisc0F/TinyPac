package pt.isec.pa.tinypac.model.data.food;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.Organism;

public class Fruit extends Organism implements IMazeElement {

    public Fruit(Map environment) {
        super(environment);
    }

    public void evolve() {
    }

    @Override
    public char getSymbol() {
        return 'F';
    }

}
