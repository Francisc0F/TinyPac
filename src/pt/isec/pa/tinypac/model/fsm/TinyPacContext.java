package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.TinyPac;
import pt.isec.pa.tinypac.model.fsm.states.MenuState;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.states.ITinyPacState;

public class TinyPacContext {
    ITinyPacState state;
    TinyPac tinyPac;

    public TinyPacContext() {
        tinyPac = new TinyPac(0);
        state = new MenuState(this, tinyPac);
    }

    public TinyPacState getState() {
        return state.getState();
    }

     protected void changeState(ITinyPacState newState) {
        this.state = newState;
    }

    public boolean up() {
        return state.up();
    }

    public boolean down() {
        return state.down();
    }

    // get data

    public int getCurrentFloor() {
        return tinyPac.getCurrentFloor();
    }
}