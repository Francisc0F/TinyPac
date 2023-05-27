package pt.isec.pa.utils;

public enum Direction {
    UP(-1, 0, -90),
    DOWN(1, 0, 90),
    LEFT(0, -1, 180),
    RIGHT(0, 1, 0);
    private final int deltaX;
    private final int deltaY;
    private final int angle;
    Direction(int y, int x, int angle){
        this.deltaX = x;
        this.deltaY = y;
        this.angle = angle;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getAngle(){
        return angle;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public Direction opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }

}
