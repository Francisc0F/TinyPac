package pt.isec.pa.tinypac.model.data;

public class Empty extends Organism implements IMazeElement {

    public Empty(Map environment) {
        super(environment);
    }

    @Override
    public void evolve() {

    }

    @Override
    public char getSymbol() {
        return '?';
    }

}
