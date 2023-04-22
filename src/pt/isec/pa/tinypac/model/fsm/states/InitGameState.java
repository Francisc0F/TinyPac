package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class InitGameState extends TinyPacStateAdapter {
    public InitGameState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);

        try {
            mapController.loadFileMap("level01.txt");
        } catch (Exception ignored) {
            System.out.println("Not found file");
        }
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.INITGAMESTATE;
    }
}
