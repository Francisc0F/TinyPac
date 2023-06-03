package pt.isec.pa.tinypac.model.data.food;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.Organism;

import java.util.Objects;

public class PowerfullFood extends Organism implements IMazeElement {
    private Map.Position p;
    private boolean eated;

    public PowerfullFood(Map environment, Map.Position position) {
        super(environment);
        p = position;
    }

    @Override
    public void evolve() {
        if (eated) {
            this.map.set(null, p.y(), p.x());
        } else {
            this.map.set(this, p.y(), p.x());
        }
    }

    public void setEated(boolean eated) {
        this.eated = eated;
    }

    @Override
    public char getSymbol() {
        return 'O';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PowerfullFood food = (PowerfullFood) obj;
        return Objects.equals(p, food.p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p);
    }
}
