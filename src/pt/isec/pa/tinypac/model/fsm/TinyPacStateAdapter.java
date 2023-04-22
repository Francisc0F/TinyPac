package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.states.ITinyPacState;

public abstract class TinyPacStateAdapter implements ITinyPacState {
    protected MapController mapController;
    protected TinyPacStateMachine context;

    protected TinyPacStateAdapter(TinyPacStateMachine context, MapController mapController) {
        this.context = context;
        this.mapController = mapController;
    }

     protected void changeState(ITinyPacState newState) {
        context.changeState(newState);
    }

    @Override
    public boolean up() {
        return false;
    }

    @Override
    public boolean down() {
        return false;
    }

    @Override
    public boolean viewTopPlayers() {
        return false;
    }

    @Override
    public boolean evolve() {
        return false;
    }
}
