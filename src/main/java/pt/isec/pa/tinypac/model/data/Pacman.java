package pt.isec.pa.tinypac.model.data;


import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.tinypac.model.data.food.Fruit;
import pt.isec.pa.tinypac.model.data.food.PowerfullFood;
import pt.isec.pa.utils.Direction;

import java.util.Optional;

public class Pacman extends Organism {
    private Direction direction;
    private Map.Position p;

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
        Organism elemAtNewPlace = this.map.getOrganism(p.y() + dy, p.x() + dx);

        if (canNotMove(elemAtNewPlace)) {
            return;
        }

        if (elemAtNewPlace instanceof Wrap) {
            Optional<Wrap> other = this.map.findElementsOf(Wrap.class)
                    .stream()
                    .filter(item -> item != elemAtNewPlace).findFirst();
            if (other.isPresent()) {
                Map.Position otherWrapPosi = this.map.getPositionOf(other.get());
                p = new Map.Position(otherWrapPosi.y(), otherWrapPosi.x());
            }
            return;
        }

        p = new Map.Position(p.y() + dy, p.x() + dx);

        if (elemAtNewPlace instanceof PowerfullFood) {
            map.setGodMode();
            this.map.set(new Empty(this.map), p.y(), p.x());
        }


        if (elemAtNewPlace instanceof Food) {
            map.incFoodScore();
            this.map.set(new Empty(this.map), p.y(), p.x());
        }

        Fruit currentFruit = this.map.getFruit();
        if (currentFruit != null) {
            Map.Position f = currentFruit.getP();
            if (f.equals(p)) {
                map.incFruitScore();
                this.map.setNoFruit();
                this.map.set(new Empty(this.map), p.y(), p.x());

            }
        }
    }

    private boolean canNotMove(Organism item) {
        return item instanceof Wall || item instanceof GhostCave || item instanceof GhostStart;
    }

    private void left() {
        move(-1, 0);
    }

    private void right() {
        move(1, 0);
    }

    private void up() {
        move(0, -1);
    }

    private void down() {
        move(0, 1);
    }

}
