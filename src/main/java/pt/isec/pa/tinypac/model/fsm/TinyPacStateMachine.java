package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.states.ITinyPacState;
import pt.isec.pa.utils.Direction;

import java.io.Serializable;

public class TinyPacStateMachine implements Serializable {
    ITinyPacState state;
    MapController mapController;

    public TinyPacStateMachine() {
        mapController = new MapController();
        state = TinyPacState.INITGAMESTATE.createState(this, mapController);
    }

    public TinyPacState getState() {
        return state.getState();
    }

    protected void changeState(ITinyPacState newState) {
        this.state = newState;
    }

    public void registDirection(Direction direction){
        this.state.registDirection(direction);
    }

    public int getTotalPoints() {
        return mapController.getPoints();
    }

    public int getLifesRemaining() {
        return mapController.getLifesRemaining();
    }

    public char[][] getMap() {
        return mapController.map.buildMap();
    }

    public boolean evolve() {
        this.state.evolve();
        return true;
    }

    public void pause() {
        this.state.pause();
    }

    public void leave() {
        this.state.leave();
    }

    public Direction getDirection() {
        return this.mapController.map.getPacmanDirection();
    }

    public void resume() {
        this.state.resume();
    }

    public int getCyclesSpeed(){
        return mapController.getIterationSpeed();
    }

    public boolean hasEatedFood() {
        return state.hasEatedFood();
    }

    public boolean hasEatedGhost() {
        return state.hasEatedGhost();
    }

    public boolean hasEatedFruit() {
        return state.hasEatedFruit();
    }
}