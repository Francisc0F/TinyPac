package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;

public class PauseGameState extends TinyPacStateAdapter {
    public PauseGameState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.PAUSEGAMESTATE;
    }


    @Override
    public boolean save() {
        mapController.save();
        return true;
    }


    /**
     * Save instance of MapController game
     * also identify transient info(not relevant)
     */
    @Override
    public boolean resume() {
        return true;
    }

    @Override
    public boolean leave() {
        return true;
    }
}
