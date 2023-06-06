package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.utils.Direction;

public class InitGameState extends TinyPacStateAdapter {
    public InitGameState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
        try {
            mapController.loadFirstLevel();
        } catch (Exception ignored) {
            System.out.println("Not found file");
        }
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.INITGAMESTATE;
    }


    @Override
    public void registDirection(Direction direction) {
        mapController.setCurrentPacmanDirection(direction);
        changeState(TinyPacState.UPDATECURRENTGAMESTATE);
    }
}
