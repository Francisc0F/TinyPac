package pt.isec.pa.tinypac.model.data.food;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.Organism;

public class Food extends Organism implements IMazeElement {

    public Food( Map environment) {
        super(environment);
    }

    @Override
    public void evolve() {
    }

    @Override
    public char getSymbol() {
        return 'o';
    }
}