package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

public class PacmanPowerfullState extends TinyPacStateAdapter {
    public PacmanPowerfullState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);

        //todo count iterations so it can go back to normal mode(UpdateCurrentGameState)
        // in the evolve method
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.PACMANPOWERFULLSTATE;
    }


    @Override
    public boolean evolve() {
        this.mapController.evolve();
        return true;
    }


    @Override
    public void registDirection(Direction direction) {
        mapController.setCurrentPacmanDirection(direction);
    }
}
