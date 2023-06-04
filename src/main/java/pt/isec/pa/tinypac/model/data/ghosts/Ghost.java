package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.*;
import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.tinypac.model.data.food.Fruit;
import pt.isec.pa.tinypac.model.data.food.PowerfullFood;
import pt.isec.pa.utils.Direction;

import java.util.*;

public abstract class Ghost extends Organism implements IGhost {
    public static final char VULNERABLE = '$';
    boolean isVulnerable = false;
    protected  boolean isDeath = false;
    protected Direction direction = Direction.UP;
    Stack<Map.Position> previousMoves = new Stack<>();
    protected Stack<Map.Position> currentCornerMoves = new Stack<>();
    protected Map.Position p;
    protected CornersOrder currentCorner;
    protected ArrayList<CornersOrder> order = new ArrayList<>(4);
    private final int distanceToChangeDirection = 5;

    private Map.Position start;

    public Ghost(Map map, Map.Position p) {
        super(map);
        this.p = p;
        start = new Map.Position(p.y(), p.x());
        if (this instanceof Inky) {
            order.add(CornersOrder.BOTTOM_RIGHT);
            order.add(CornersOrder.BOTTOM_LEFT);
            order.add(CornersOrder.TOP_RIGHT);
            order.add(CornersOrder.TOP_LEFT);
        }

        if (this instanceof Pinky) {
            order.add(CornersOrder.TOP_RIGHT);
            order.add(CornersOrder.BOTTOM_RIGHT);
            order.add(CornersOrder.TOP_LEFT);
            order.add(CornersOrder.BOTTOM_LEFT);
        }

        if (!order.isEmpty()) {
            currentCorner = order.get(0);
        }

    }

    public void reset() {
        p = new Map.Position(start.y(), start.x());
    }

    public void setVulnerable( boolean isVulnerable) {
        this.isVulnerable = isVulnerable;
    }

    public void setDeath(){
         isDeath = true;
    }

    public enum CornersOrder {
        BOTTOM_RIGHT, BOTTOM_LEFT, TOP_RIGHT, TOP_LEFT;

        public static CornersOrder nextCorner(ArrayList<CornersOrder> order, CornersOrder current) {
            int currentIndex = ((List<CornersOrder>) order).indexOf(current);
            int nextIndex = (currentIndex + 1) % ((List<CornersOrder>) order).size();
            return ((List<CornersOrder>) order).get(nextIndex);
        }

        static public Map.Position getCurrentCornerPosition(CornersOrder currentCorner, Map map) {
            return switch (currentCorner) {
                case TOP_LEFT -> new Map.Position(0, 0);
                case TOP_RIGHT -> new Map.Position(0, map.getWidth() - 1);
                case BOTTOM_LEFT -> new Map.Position(map.getHeight() - 1, 0);
                case BOTTOM_RIGHT -> new Map.Position(map.getHeight() - 1, map.getWidth() - 1);
            };
        }
    }

    @Override
    public void evolve() {
        if (direction == null) {
            return;
        }
        moveByDirection();
    }


    public void savePosition() {
        previousMoves.push(new Map.Position(p.y(), p.x()));
        addToCornerStack(new Map.Position(p.y(), p.x()));
    }

    private void addToCornerStack(Map.Position newPosition) {
        currentCornerMoves.push(newPosition);

        if (currentCornerMoves.size() > 2) {
            currentCornerMoves.remove(0);
        }
    }

    protected Map.Position checkIsPacman(int dx, int dy) {
        Map.Position newp = new Map.Position(p.y() + dy, p.x() + dx);
        Map.Position pacP = this.map.getPacmanPosition();

        if (pacP.equals(newp)) {
            killPacman();
            p = newp;
            return null;
        }
        return newp;
    }


    private void killPacman() {
        map.killPacman();
    }

    protected void move(int dx, int dy) {
        Map.Position newp = checkIsPacman(dx, dy);
        if (newp == null) return;
        moveGhost(newp.y(), newp.x());
    }

    protected void moveGhost(int y, int x) {
        Organism item = map.getOrganism(p.y(), p.x());
        if (item == null || item instanceof Ghost) {
            map.set(null, p.y(), p.x());
        }
        p = new Map.Position(y, x);
        map.set(this, p.y(), p.x());
    }

    protected Direction generateDirectionRandom() {
        ArrayList<Direction> allowedDirections = new ArrayList<>(10);

        for (Direction dir : Direction.values()) {
            if (direction.opposite() != dir) {
                Map.Position pos = getDeltaByDirection(dir);
                if (canMove(this.map.getOrganism(pos.y(), pos.x()))) {
                    allowedDirections.add(dir);
                }
            }
        }

        // if no direction is available go backwards
        if (allowedDirections.isEmpty()) {
            allowedDirections.add(direction.opposite());
        }

        return getRandomDirection(allowedDirections);
    }

    @Override
    public boolean getIsVulnerable() {
        return this.isVulnerable;
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


    protected Map.Position getDeltaByDirection(Direction direction) {
        int dx = 0, dy = 0;
        switch (direction) {
            case UP:
                dy = -1;
                break;
            case DOWN:
                dy = 1;
                break;
            case LEFT:
                dx = -1;
                break;
            case RIGHT:
                dx = 1;
                break;
        }
        return new Map.Position(p.y() + dy, p.x() + dx);
    }

    protected void moveByDirection() {
        switch (direction) {
            case LEFT -> this.left();
            case RIGHT -> this.right();
            case UP -> this.up();
            case DOWN -> this.down();
        }
    }

    protected boolean canotMove(Organism elemAtNewPlace) {
        return elemAtNewPlace instanceof Wall || elemAtNewPlace instanceof GhostCave;
    }

    protected boolean canMove(Organism elemAtNewPlace) {
        return !(canotMove(elemAtNewPlace));
    }


    public static Direction getRandomDirection(List<Direction> listAvailable) {
        Random random = new Random();
        int randomIndex = random.nextInt(listAvailable.size());
        return listAvailable.get(randomIndex);
    }


    protected ArrayList<Direction> getAllowedDirections() {
        ArrayList<Direction> allowedDirections = new ArrayList<>(4);

        for (Direction dir : Direction.values()) {
            Map.Position pos = getDeltaByDirection(dir);
            if (canMove(this.map.getOrganism(pos.y(), pos.x()))) {
                allowedDirections.add(dir);
            }
        }
        return allowedDirections;
    }


    /**
     * @return next direction for
     * pinky & inky corner logic
     */
    protected Direction getNextDirection() {

        int currentDistanceToCorner = Map.Position.getDistance(p, CornersOrder.getCurrentCornerPosition(currentCorner, map));

        if (currentDistanceToCorner <= distanceToChangeDirection) {
            currentCorner = CornersOrder.nextCorner(order, currentCorner);
            currentCornerMoves = new Stack();
        }

        ArrayList<Direction> allowed = getAllowedDirections();


        int minDistance = Integer.MAX_VALUE;
        Direction nextDirection = null;

        List<Direction> filtered = removeRepeatedPositions(allowed);

        if (filtered.isEmpty()) {
            nextDirection = direction.opposite();
            // this will fill the stack so it can go backwards
            addToCornerStack(new Map.Position(p.y(), p.x()));
        } else {
            for (Direction direction : filtered) {
                int distance = getDeltaDirectionDistance(direction, currentCorner);
                if (distance <= minDistance) {
                    minDistance = distance;
                    nextDirection = direction;
                }
            }
        }
        return nextDirection;
    }

    protected Direction getBlinkyDirection() {
        Map.Position pos = getDeltaByDirection(direction);
        if (canMove(this.map.getOrganism(pos.y(), pos.x()))) {
            return direction;
        }
        return generateDirectionRandom();
    }


    private boolean isPacmanOnDirection(Direction direction) {
        Map.Position pacP = this.map.getPacmanPosition();
        int x = p.x(), y = p.y();
        while (true) {
            x += direction.getDeltaX();
            y += direction.getDeltaY();
            if (canotMove(this.map.getOrganism(y, x))) {
                return false;
            }

            if (pacP.equals(new Map.Position(y, x))) {
                return true;
            }
        }
    }

    /**
     * pursuit mode on finding pacaman in available directions
     *
     * @return Direction clyde next direction
     */
    protected Direction getClydeDirection() {
        List<Direction> allowed = getAllowedDirections();
        for (Direction dir : allowed) {
            if (isPacmanOnDirection(dir)) {
                return dir;
            }
        }
        return getBlinkyDirection();
    }

    /**
     * @param allowed list
     * @return filtered by current corner position
     */
    private List<Direction> removeRepeatedPositions(ArrayList<Direction> allowed) {
        return allowed.stream().filter(item -> !positionAlreadyVisitedInThisCorner(getDeltaByDirection(item))).toList();
    }

    private boolean positionAlreadyVisitedInThisCorner(Map.Position newP) {
        if (!currentCornerMoves.isEmpty()) {

            Optional<Map.Position> p = currentCornerMoves.stream().filter(item -> item.equals(newP)).findFirst();
            return p.isPresent();
        } else {
            return false;
        }
    }

    /**
     * @param direction
     * @param corner
     * @return distance
     * for a given position on a direction delta
     * for a given corner in the map
     */
    private int getDeltaDirectionDistance(Direction direction, CornersOrder corner) {
        Map.Position pos = getDeltaByDirection(direction);
        return Map.Position.getDistance(pos, CornersOrder.getCurrentCornerPosition(corner, map));
    }

    @Override
    public Ghost clone() {
        try {
            return (Ghost) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
