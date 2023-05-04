package pt.isec.pa.tinypac.model.data;

public class Wrap extends Organism implements IMazeElement {

    public Wrap(Map environment) {
        super(environment);
    }

    @Override
    public void evolve() {

    }

    @Override
    public char getSymbol() {
        return 'W';
    }

}
