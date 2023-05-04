package pt.isec.pa.tinypac.model.data;

public class GhostStart extends Organism {
    public GhostStart(Map map) {
        super(map);
    }

    @Override
    public void evolve() {

    }

    @Override
    public char getSymbol() {
        return 'Y';
    }
}
