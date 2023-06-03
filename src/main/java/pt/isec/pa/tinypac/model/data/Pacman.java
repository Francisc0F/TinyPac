package pt.isec.pa.tinypac.model.data;


import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.tinypac.model.data.food.Fruit;
import pt.isec.pa.tinypac.model.data.food.PowerfullFood;
import pt.isec.pa.utils.Direction;

import java.util.*;

public class Pacman extends Organism implements Cloneable {
    private final Map.Position start;
    private Direction direction;
    private Map.Position p;
    private Wrap enteredWrap = null;
    private boolean isDeath = false;


    public Pacman(Map map, Map.Position p) {
        super(map);
        start = new Map.Position(p.y(), p.x());
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

    public Direction getDirection() {
        return direction;
    }

    public boolean getIsDeath() {
        return isDeath;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public char getSymbol() {
        return 'M';
    }

    private void move(int dx, int dy) {
        if (enteredWrap != null) {
            Map.Position previousWrapEntered = enteredWrap.getP();
            map.set(enteredWrap.clone(), previousWrapEntered.y(), previousWrapEntered.x());
            enteredWrap = null;
        }

        Organism elemAtNewPlace = this.map.getOrganism(p.y() + dy, p.x() + dx);
        if (canNotMove(elemAtNewPlace)) {
            movePacaman(0, 0);
            return;
        }

        if (elemAtNewPlace instanceof Wrap) {
            Optional<Wrap> other = this.map.findElementsOf(Wrap.class)
                    .stream()
                    .filter(item -> item != elemAtNewPlace).findFirst();
            if (other.isPresent()) {
                enteredWrap = other.get().clone();
                Map.Position otherWrapPosi = this.map.getPositionOf(other.get());
                enteredWrap.setP(otherWrapPosi);
                movePacmanTo(otherWrapPosi.y(), otherWrapPosi.x());
            }
            return;
        }

        if (elemAtNewPlace == null) {
            movePacaman(dy, dx);
        }

        if (elemAtNewPlace instanceof PowerfullFood || elemAtNewPlace instanceof Food) {
            map.eatFood(elemAtNewPlace);
            movePacaman(dy, dx);
        }

        if (elemAtNewPlace instanceof Fruit) {
            map.incFruitScore();
            movePacaman(dy, dx);
        }
    }

    private void movePacaman(int dy, int dx) {
        if(isDeath){
            return;
        }
        map.set(null, p.y(), p.x());
        p = new Map.Position(p.y() + dy, p.x() + dx);
        this.map.set(this, p.y(), p.x());
    }

    private void movePacmanTo(int y, int x) {
        if(isDeath){
            return;
        }


        map.set(null, p.y(), p.x());
        p = new Map.Position(y, x);
        this.map.set(this, p.y(), p.x());
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

    @Override
    public Pacman clone() {
        try {
            return (Pacman) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void reset(){
        p = new Map.Position(start.y(), start.x());
        isDeath = false;
    }

    public void kill() {
        isDeath = true;
    }
}
