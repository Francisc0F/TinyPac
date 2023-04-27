package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.states.InitGameState;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.states.ITinyPacState;
import pt.isec.pa.tinypac.model.fsm.states.UpdateCurrentGameState;
import pt.isec.pa.utils.Direction;

public class TinyPacStateMachine {
    ITinyPacState state;
    MapController mapController;

    public TinyPacStateMachine() {

        mapController = new MapController();
        state = new InitGameState(this, mapController);
    }

    public TinyPacState getState() {
        return state.getState();
    }

    protected void changeState(ITinyPacState newState) {
        this.state = newState;
    }

    public void registDirection(Direction direction) {
        mapController.setCurrentPacmanDirection(direction);
        changeState(new UpdateCurrentGameState(this, mapController));
    }

    public char[][] getMap() {
        return mapController.getMap();
    }

    public boolean evolve() {
        changeState(new UpdateCurrentGameState(this, mapController));
        return true;
    }
}