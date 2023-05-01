package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.tinypac.model.data.GhostCave;
import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.Organism;
import pt.isec.pa.tinypac.model.data.Wall;
import pt.isec.pa.utils.Direction;
import pt.isec.pa.utils.Position;

import java.util.*;

public abstract class Ghost extends Organism implements IGhost {
    public static final char SYMBOL = 'y';
    boolean isVulnerable = false;
    protected Direction direction = Direction.UP;
    Stack<Position> previousMoves = new Stack<Position>();

    protected Map.Position p;
    protected CornersOrder currentCorner;
    protected ArrayList<CornersOrder> order = new ArrayList<>(4);
    private final int distanceToChangeDirection = 10;

    public Ghost(Map map) {
        super(map);
        savePosition();
        if(this instanceof Inky){
            order.add(CornersOrder.BOTTOM_RIGHT);
            order.add(CornersOrder.BOTTOM_LEFT);
            order.add(CornersOrder.TOP_RIGHT);
            order.add(CornersOrder.TOP_LEFT);
        }

        if(this instanceof Pinky){
            order.add(CornersOrder.TOP_RIGHT);
            order.add(CornersOrder.BOTTOM_RIGHT);
            order.add(CornersOrder.TOP_LEFT);
            order.add(CornersOrder.BOTTOM_LEFT);
        }

        if(!order.isEmpty()){
            currentCorner = order.get(0);
        }
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
                default -> null;
            };
        }
    }

    public Map.Position getP() {
        return p;
    }


    @Override
    public void evolve() {
        if (direction == null) {
            return;
        }
        moveByDirection();
    }

    /*@Override
    public boolean savePosition() {
        previousMoves.push(position);
        return true;
    }*/


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

    @Override
    public boolean move() {
        if (!isVulnerable) {
    /*        regularMove();*/
            savePosition();
        } else {
          /*  backwardsMove();*/
        }
        return true;
    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }

    // todo postion save list and backwards move
    /*protected abstract void regularMove();*/

   /* private void backwardsMove() {
        if (previousMoves.size() == 1) {
            isVulnerable = false;
        } else {
            setPreviousMove();
        }
    }*/

    protected abstract void move(int dx, int dy);

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


    protected Map.Position getAdjacentPosition(Direction direction) {
        int dx = 0, dy = 0;
        switch (direction) {
            case UP: dy = -1; break;
            case DOWN: dy = 1; break;
            case LEFT: dx = -1; break;
            case RIGHT: dx = 1; break;
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
            Map.Position pos = getAdjacentPosition(dir);
            if (canMove(this.map.getOrganism(pos.y(), pos.x()))) {
                allowedDirections.add(dir);
            }
        }
        return allowedDirections;
    }


    //todo compare current position to corner, so it wont get stuck
    // if the current position is the best execute .nextCorner
    protected Direction getBestDirection() {
        ArrayList<Direction> allowed = getAllowedDirections();

        int currentDistanceToCorner = Map.Position.getDistance(p, CornersOrder.getCurrentCornerPosition(currentCorner, map));

        if (currentDistanceToCorner < distanceToChangeDirection) {
            currentCorner = CornersOrder.nextCorner(order, currentCorner);
        }

        int minDistance = Integer.MAX_VALUE;
        Direction bestDirection = null;

        for (Direction direction : allowed) {
            Map.Position pos = getAdjacentPosition(direction);
            int distance = Map.Position.getDistance(pos, CornersOrder.getCurrentCornerPosition(currentCorner, map));
            if (distance < minDistance) {
                minDistance = distance;
                bestDirection = direction;
            }
        }

        return bestDirection;
    }

  /*  private void setPreviousMove() {
        position = previousMoves.pop();
    }
*/
}
