package pt.isec.pa.tinypac.model.data;

public class TinyPac {
    private int currentFloor;

    public TinyPac(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    @Override
    public String toString() {
        return "Elevator:currentFloor=" + currentFloor;
    }
}
