package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.GhostCave;
import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.Organism;
import pt.isec.pa.tinypac.model.data.Wall;
import pt.isec.pa.utils.Direction;

import java.util.*;

public abstract class Ghost extends Organism implements IGhost {
    public static final char SYMBOL = 'y';
    boolean isVulnerable = false;
    protected Direction direction = Direction.UP;
    Stack<Map.Position> previousMoves = new Stack<Map.Position>();
    protected Stack<Map.Position> currentCornerMoves = new Stack<>();
    protected Map.Position p;
    protected CornersOrder currentCorner;
    protected ArrayList<CornersOrder> order = new ArrayList<>(4);
    private final int distanceToChangeDirection = 5;
    private int previousDistanceToCorner = 0;

    public Ghost(Map map) {
        super(map);
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


    public void savePosition() {
        previousMoves.push(new Map.Position(p.x(), p.y()));
        currentCornerMoves.push(new Map.Position(p.y(), p.x()));
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
        p = newp;
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


    //todo compare current position to corner, so it wont get stuck
    // if the current position is the best execute .nextCorner
    protected Direction getNextDirection() {
        int currentDistanceToCorner = Map.Position.getDistance(p, CornersOrder.getCurrentCornerPosition(currentCorner, map));

        if (currentDistanceToCorner <= distanceToChangeDirection) {
            currentCorner = CornersOrder.nextCorner(order, currentCorner);
            currentCornerMoves = new Stack();
            // System.out.println("currentCorner: " + currentCorner);
        }
        ArrayList<Direction> allowed = getAllowedDirections();



        int minDistance = Integer.MAX_VALUE;
        Direction nextDirection = null;
        //System.out.println("allowed: " + allowed);
        for (Direction direction : removeRepeatedPositions(allowed)) {
            int distance = getDeltaDirectionDistance(direction, currentCorner);
            if (distance < minDistance) {
                minDistance = distance;
                nextDirection = direction;
            }
        }

        //System.out.println("currentDistanceToCorner: " + currentDistanceToCorner);

        //previousDistanceToCorner = currentDistanceToCorner;
        return nextDirection;
    }

    private List<Direction> removeRepeatedPositions(ArrayList<Direction> allowed){
        return allowed.stream().filter(item -> !positionAlreadyVisitedInThisCorner(getDeltaByDirection(item))).toList();
    }

    private boolean positionAlreadyVisitedInThisCorner( Map.Position newP){
        if(!currentCornerMoves.isEmpty()){
            Optional<Map.Position> p =  currentCornerMoves.stream().filter(item -> item.equals(newP)).findFirst();
            return p.isPresent();
        }else{
            return false;
        }
    }

    private int getDeltaDirectionDistance(Direction direction, CornersOrder corner) {
        Map.Position pos = getDeltaByDirection(direction);
        return Map.Position.getDistance(pos, CornersOrder.getCurrentCornerPosition(corner, map));
    }

  /*  private void setPreviousMove() {
        position = previousMoves.pop();
    }
*/
}
