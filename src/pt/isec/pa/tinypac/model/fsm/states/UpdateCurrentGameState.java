package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.EvolvedAction;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

public class UpdateCurrentGameState extends TinyPacStateAdapter {
    public UpdateCurrentGameState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.UPDATECURRENTGAMESTATE;
    }


    @Override
    public boolean evolve() {
        EvolvedAction action = mapController.evolve();
        switch (action) {
            case PACKILLED -> changeState(TinyPacState.LOSTLIFESTATE);
            case SUCCEED -> changeState(TinyPacState.UPDATECURRENTGAMESTATE);
            case GODMODE -> changeState(TinyPacState.PACMANPOWERFULLSTATE);
            case WONLEVEL -> changeState(TinyPacState.NEWLEVELSTATE);
            case ENDEDGAME -> changeState(TinyPacState.FINISHGAMESTATE);
        }

        return true;
    }


    @Override
    public boolean pause() {
        changeState(TinyPacState.PAUSEGAMESTATE);
        return true;
    }


    @Override
    public void registDirection(Direction direction) {
        mapController.setCurrentPacmanDirection(direction);
    }
}
