package pt.isec.pa.tinypac.model.data;

public class MazeElement extends Organism implements IMazeElement {
    private final char symbol;

    public MazeElement(char symbol, Map environment) {
        super(environment);
        this.symbol = symbol;
    }

    @Override
    public void evolve() {

    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }

}
