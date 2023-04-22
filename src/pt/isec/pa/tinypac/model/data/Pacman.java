package pt.isec.pa.tinypac.model.data;


import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.utils.Direction;

public class Pacman extends Organism {
    private Direction direction;

    Pacman(Map map) {
        super(map);
    }

    @Override
    public void evolve() {
        if (direction == null) {
            return;
        }
        switch (direction) {
            case LEFT -> this.left();
            case RIGHT -> this.right();
            case UP -> this.up();
            case DOWN -> this.down();
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public char getSymbol() {
        return 'M';
    }

    protected void left() {
        Map.Position p = this.map.getPositionOf(this);
        Organism elemAtThisPlace = this.map.getOrganism(p.y(), p.x());
        Organism elemAtLeftPlace = this.map.getOrganism(p.y(), p.x() - 1);
        this.map.set(elemAtThisPlace, p.y(), p.x() - 1);

        if (elemAtLeftPlace instanceof Food) {
            this.map.set(null, p.y(), p.x());
        } else {
            this.map.set(elemAtLeftPlace, p.y(), p.x());
        }
    }

    protected void right() {
        Map.Position p = this.map.getPositionOf(this);
        Organism elemAtThisPlace = this.map.getOrganism(p.y(), p.x());
        Organism elemAtRightPlace = this.map.getOrganism(p.y(), p.x() + 1);

        if (!(elemAtRightPlace instanceof Wall)) {
            this.map.set(elemAtThisPlace, p.y(), p.x() + 1);
        }
        if (elemAtRightPlace instanceof Food) {
            this.map.set(null, p.y(), p.x());
        } else {
            this.map.set(elemAtRightPlace, p.y(), p.x());
        }
    }

    protected void up() {
        Map.Position p = this.map.getPositionOf(this);
        map.addElement(new Pacman(map), p.y() + 1, p.x());
    }

    protected void down() {
        Map.Position p = this.map.getPositionOf(this);
        map.addElement(new Pacman(map), p.y() - 1, p.x());
    }

}
