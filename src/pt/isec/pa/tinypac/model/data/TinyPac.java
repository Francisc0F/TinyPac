package pt.isec.pa.tinypac.model.data;

public class TinyPac {
    private int currentlevel;

    public TinyPac(int currentFloor) {
        this.currentlevel = currentFloor;
    }

    public int getCurrentlevel() {
        return currentlevel;
    }

    public void setCurrentlevel(int currentlevel) {
        this.currentlevel = currentlevel;
    }

    @Override
    public String toString() {
        return "Elevator:currentFloor=" + currentlevel;
    }
}
