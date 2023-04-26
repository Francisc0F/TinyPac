package pt.isec.pa.tinypac.model.data;


import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.utils.Direction;

import java.util.Optional;

public class Pacman extends Organism {
    private Direction direction;
    Map.Position p;

    public Pacman(Map map, Map.Position p) {
        super(map);
        this.p = p;
    }

    public Map.Position getP() {
        return p;
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

    private void move(int dx, int dy) {
        Organism elemAtThisPlace = this.map.getOrganism(p.y(), p.x());
        Organism elemAtNewPlace = this.map.getOrganism(p.y() + dy, p.x() + dx);

        if (elemAtNewPlace instanceof Wall) {
            return;
        }

        if (elemAtNewPlace instanceof Wrap) {
            Optional<Wrap> other = this.map.findElementsOf(Wrap.class)
                    .stream()
                    .filter(item -> item != elemAtNewPlace).findFirst();
            if(other.isPresent()){
                Map.Position otherWrapPosi = this.map.getPositionOf(other.get());
                p = new Map.Position(otherWrapPosi.y(), otherWrapPosi.x());
            }
            return;
        }

        p = new Map.Position(p.y() + dy, p.x() + dx);

        if (elemAtNewPlace instanceof Food) {
            this.map.set(new Empty(this.map), p.y(), p.x());
        }
    }

    protected void left() {
        move(-1, 0);
    }

    protected void right() {
        move(1, 0);
    }

    protected void up() {
        move(0, -1);
    }

    protected void down() {
        move(0, 1);
    }

}
