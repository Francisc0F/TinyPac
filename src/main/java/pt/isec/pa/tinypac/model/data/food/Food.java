package pt.isec.pa.tinypac.model.data.food;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.Organism;

import java.util.Objects;

public class Food extends Organism implements IMazeElement {
    private Map.Position p;
    private boolean eated = false;
    public Food(Map environment, Map.Position p) {
        super(environment);
        this.p = p;
    }

    public void setEated(boolean eated) {
        this.eated = eated;
    }

    @Override
    public void evolve() {
        if (eated) {
            this.map.set(null, p.y(), p.x());
        } else {
            this.map.set(this, p.y(), p.x());
        }
    }
    @Override
    public char getSymbol() {
        return 'o';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Food food = (Food) obj;
        return Objects.equals(p, food.p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p);
    }
}






