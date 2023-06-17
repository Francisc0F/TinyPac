package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.SavedGame;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;


public class PauseGameState extends TinyPacStateAdapter {
    private static final long serialVersionUID = 1L;
    public PauseGameState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.PAUSEGAMESTATE;
    }


    @Override
    public void resume() {
        changeState(TinyPacState.UPDATECURRENTGAMESTATE);
    }


    @Override
    public void saveCurrentGame() {
        try {
            SavedGame gameCopy = (SavedGame) Utils.clone(new SavedGame(context));
            Utils.saveObject(gameCopy, Utils.SAVEDGAMEONPAUSE);
        } catch (Exception ex) {
            System.out.println("saveCurrentGame " + ex);
        }
    }
}
