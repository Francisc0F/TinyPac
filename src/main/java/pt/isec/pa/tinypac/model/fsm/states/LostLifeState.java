package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.EvolveEvent;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

public class LostLifeState extends TinyPacStateAdapter {
    private static final long serialVersionUID = 1L;
    public LostLifeState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
        map.resetLevel();
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.LOSTLIFESTATE;
    }

    @Override
    public EvolveEvent registerDirection(Direction direction) {
        mapController.setCurrentPacmanDirection(direction);
        changeState(TinyPacState.UPDATECURRENTGAMESTATE);
        return EvolveEvent.CHANGEDSTATE;
    }
}
