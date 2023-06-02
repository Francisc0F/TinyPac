package pt.isec.pa.tinypac.model.data;

import java.util.Objects;

public class Wrap extends Organism implements IMazeElement, Cloneable {

    private Map.Position p;

    public Wrap(Map environment) {
        super(environment);
    }

    public void setP(Map.Position p) {
        this.p = p;
    }

    public Map.Position getP() {
        return p;
    }

    @Override
    public void evolve() {
    }

    @Override
    public char getSymbol() {
        return 'W';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Wrap otherWrap = (Wrap) obj;

        return Objects.equals(p, otherWrap.p);
    }

    @Override
    public int hashCode() {
        return p != null ? p.hashCode() : 0;
    }

    @Override
    public Wrap clone() {
        try {
            return (Wrap) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
