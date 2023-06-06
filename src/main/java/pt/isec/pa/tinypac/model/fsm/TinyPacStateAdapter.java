package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.states.ITinyPacState;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.utils.Direction;

import java.io.Serializable;

public abstract class TinyPacStateAdapter implements ITinyPacState, Serializable {
    protected MapController mapController;
    protected TinyPacStateMachine context;
    protected Map map;
    protected TinyPacStateAdapter(TinyPacStateMachine context, MapController mapController) {
        this.context = context;
        this.mapController = mapController;
        this.map = this.mapController.map;
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
