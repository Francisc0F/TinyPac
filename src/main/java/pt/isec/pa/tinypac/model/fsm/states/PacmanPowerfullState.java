package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

public class PacmanPowerfullState extends TinyPacStateAdapter {
    public PacmanPowerfullState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.PACMANPOWERFULLSTATE;
    }

    @Override
    public boolean evolve() {
        if(map.allFoodEaten()){
            changeState(TinyPacState.NEWLEVELSTATE);
            return false;
        }

        if(map.godModeTimeEnded()){
            map.setNormalMode();
            changeState(TinyPacState.UPDATECURRENTGAMESTATE);
            return true;
        }

        map.incGoodModeIteration();
        map.updateLiveOrganisms();
        map.incIteration();
        return true;

    }


    @Override
    public void registDirection(Direction direction) {
        mapController.setCurrentPacmanDirection(direction);
    }
}
