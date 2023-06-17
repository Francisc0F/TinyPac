package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.EvolveEvent;
import pt.isec.pa.tinypac.model.data.Map;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.states.ITinyPacState;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.utils.Direction;

import java.io.Serializable;

public abstract class TinyPacStateAdapter implements ITinyPacState, Serializable {
    private static final long serialVersionUID = 1L;
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
    public EvolveEvent evolve() {
        return null;
    }

    @Override
    public EvolveEvent pause() {
        return null;
    }

    @Override
    public boolean save(String name) {
        return false;
    }

    @Override
    public void resume() {
    }

    @Override
    public void saveCurrentGame() {
    }

    @Override
    public EvolveEvent registerDirection(Direction direction) {
        return null;
    }
}
