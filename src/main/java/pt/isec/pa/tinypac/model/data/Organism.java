package pt.isec.pa.tinypac.model.data;

import java.io.Serializable;

public abstract class Organism implements IMazeElement, Serializable {
    protected Map map;

    protected Organism(Map map) {
        this.map = map;
    }

    abstract public void evolve();

    abstract public char getSymbol();

}
