package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;

public class UpdateCurrentGameState extends TinyPacStateAdapter {
    public UpdateCurrentGameState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
        evolve();
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.UPDATECURRENTGAMESTATE;
    }


    @Override
    public boolean evolve() {
        return mapController.evolve();
    }
}
