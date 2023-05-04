package pt.isec.pa.tinypac.model.data;

public class Wall extends Organism implements IMazeElement {

    public Wall(Map environment) {
        super(environment);
    }

    @Override
    public void evolve() {

    }

    @Override
    public char getSymbol() {
        return ' ';
    }

}
