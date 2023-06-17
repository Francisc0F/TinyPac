package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.SavedGame;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;

import java.util.ArrayList;
import java.util.Comparator;

public class FinishGameState extends TinyPacStateAdapter {
    public FinishGameState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.FINISHGAMESTATE;
    }

    @Override
    public boolean save(String name) {
        ArrayList<SavedGame> top5 = context.getTop5FromFile();
        try {

            SavedGame gameCopy = (SavedGame) Utils.clone(new SavedGame(context, name));
            top5.add(gameCopy);
            Comparator<SavedGame> pointsComparator = Comparator.comparingInt(SavedGame::getPoints).reversed();
            top5.sort(pointsComparator);
            if (top5.size() > 5) {
                top5 = (ArrayList<SavedGame>) top5.subList(0, 5);
            }

            System.out.println("FinishGameState" + top5);
            Utils.saveObject(top5, Utils.GAMEFILE);
            return true;
        } catch (Exception ex) {
            System.out.println("Ex FinishGameState " + ex);
            return false;
        }
    }
}
