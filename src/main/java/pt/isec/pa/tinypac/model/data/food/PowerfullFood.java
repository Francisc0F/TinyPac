package pt.isec.pa.tinypac.model.data.food;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.Organism;

public class PowerfullFood extends Organism implements IMazeElement {

    public PowerfullFood(Map environment) {
        super(environment);
    }

    @Override
    public void evolve() {
        return;
    }

    @Override
    public char getSymbol() {
        return 'O';
    }

}
