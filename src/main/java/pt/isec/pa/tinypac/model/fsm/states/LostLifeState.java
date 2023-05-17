package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

public class LostLifeState extends TinyPacStateAdapter {

    public LostLifeState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
        map.decLives();
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.LOSTLIFESTATE;
    }


    @Override
    public void registDirection(Direction direction) {
        mapController.setCurrentPacmanDirection(direction);
        changeState(TinyPacState.UPDATECURRENTGAMESTATE);
    }
}
