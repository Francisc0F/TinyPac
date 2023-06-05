package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

public class UpdateCurrentGameState extends TinyPacStateAdapter {
    public UpdateCurrentGameState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
        map.updateLiveOrganisms();
        map.incIteration();
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.UPDATECURRENTGAMESTATE;
    }

    @Override
    public boolean evolve() {

        if (map.isGodMode()) {
            changeState(TinyPacState.PACMANPOWERFULLSTATE);
            return true;
        }

        if(map.allFoodEaten()){
            changeState(TinyPacState.NEWLEVELSTATE);
            return true;
        }

        if (map.isPacmanDeath()) {
            map.decLives();
            if (map.isPacmanDeath() && map.noLivesRemaining()) {
                changeState(TinyPacState.LOSTGAMESTATE);
                return true;
            }
            changeState(TinyPacState.LOSTLIFESTATE);
            return true;
        }

        changeState(TinyPacState.UPDATECURRENTGAMESTATE);
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
