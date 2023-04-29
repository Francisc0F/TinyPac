package pt.isec.pa.tinypac.model.data;

public class GhostCave extends Organism {
    public GhostCave(Map map) {
        super(map);
    }

    @Override
    public void evolve() {

    }

    @Override
    public char getSymbol() {
        return 'y';
    }
}
