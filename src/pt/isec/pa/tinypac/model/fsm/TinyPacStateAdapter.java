package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.states.ITinyPacState;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.utils.Direction;

public abstract class TinyPacStateAdapter implements ITinyPacState {
    protected MapController mapController;
    protected TinyPacStateMachine context;

    protected TinyPacStateAdapter(TinyPacStateMachine context, MapController mapController) {
        this.context = context;
        this.mapController = mapController;
    }

    protected void changeState(TinyPacState newState) {
        context.changeState(newState.createState(context, mapController));
    }

    @Override
    public boolean evolve() {
        return false;
    }

    @Override
    public boolean pause() {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean resume() {
        return false;
    }

    @Override
    public boolean loadNewLevel() {
        return false;
    }

    @Override
    public boolean leave() {
        return false;
    }

    @Override
    public void registDirection(Direction direction) {
    }
}
