package pt.isec.pa.tinypac.model.fsm.states;

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

        if (map.godModeEnded()) {
            changeState(TinyPacState.PACMANPOWERFULLSTATE);
            return true;
        }

        if(map.allFoodEaten()){
            changeState(TinyPacState.NEWLEVELSTATE);
            return true;
        }

        if (!map.isPacmanAlive() && map.noLivesRemaining()) {
            changeState(TinyPacState.LOSTGAMESTATE);
            return true;
        }

        if (!map.isPacmanAlive() && !map.noLivesRemaining()) {
            changeState(TinyPacState.LOSTLIFESTATE);
            return true;
        }

        map.updateLiveOrganisms();
        map.incIteration();
        return true;

  /*      switch (action) {
            case PACKILLED -> ;
            case SUCCEED -> changeState(TinyPacState.UPDATECURRENTGAMESTATE);
            case GODMODE -> changeState(TinyPacState.PACMANPOWERFULLSTATE);
            case WONLEVEL -> changeState(TinyPacState.NEWLEVELSTATE);
            case ENDEDGAME -> changeState(TinyPacState.FINISHGAMESTATE);
        }*/

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
