package pt.isec.pa.tinypac.model.data;


public abstract class Organism implements IMazeElement {
    protected Map map;

    protected Organism(Map map) {
        this.map = map;
    }

    abstract public void evolve();

    abstract public char getSymbol();

}
