package pt.isec.pa.tinypac.model.data.food;

import pt.isec.pa.tinypac.model.data.*;

public class Fruit extends Organism implements IMazeElement {
    Map.Position p;

    public Fruit(Map environment, Map.Position p) {
        super(environment);
        this.p = p;
        this.map.set(new Empty(map), p.y(), p.x());
    }

    public void evolve() {
        int score = map.getFoodScore();
        if (score >= 20 && (score % 20 == 0)) {
            this.map.set(this, p.y(), p.x());
        }
    }

    @Override
    public char getSymbol() {
        return 'F';
    }

}
