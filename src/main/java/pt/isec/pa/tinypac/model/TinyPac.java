package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


class SavedGame implements Serializable {
    private final Date date = new Date();
    private TinyPacStateMachine data;
    private String username;

    public SavedGame(TinyPacStateMachine data) {
        this.data = data;
        this.username = "unnamed";
    }

    public SavedGame(TinyPacStateMachine data, String username) {
        this.data = data;
        this.username = username;
    }

    public int getPoints() {
        return data.getTotalPoints();
    }

}

public class TinyPac implements Serializable {
    private TinyPacStateMachine fsm;
    private TinyPacStateMachineObservable fsmObs;
    private ArrayList<SavedGame> top5 = new ArrayList<>();
    private Utils utils;
    // todo save/load games

    public TinyPac() {
        utils = new Utils();
        fsm = new TinyPacStateMachine();
        fsmObs = new TinyPacStateMachineObservable(fsm);
    }

    public TinyPacStateMachineObservable getFsmObs() {
        return fsmObs;
    }

    public void saveGame() {
        top5 = getTop5FromFile();

        try {

            SavedGame gameCopy = (SavedGame) Utils.clone(new SavedGame(fsm));
            /*     TinyPacStateMachine gameCopy = (TinyPacStateMachine) Utils.clone(fsm);*/
            top5.add(gameCopy);
            Comparator<SavedGame> pointsComparator = Comparator.comparingInt(SavedGame::getPoints).reversed();
            top5.sort(pointsComparator);
            if (top5.size() > 5) {
                top5 = (ArrayList<SavedGame>) top5.subList(0, 5);
            }

            System.out.println("top5" + top5);
            Utils.saveObject(top5, Utils.GAMEFILE);
        } catch (Exception ex) {
            System.out.println("Ex CLONE " + ex);
        }
    }

    public boolean reachedTop5() {
        if (top5.isEmpty()) {
            return true;
        }
        SavedGame top5Last = top5.get(top5.size() - 1);
        return fsm.getTotalPoints() > top5Last.getPoints();
    }


    private ArrayList<SavedGame> getTop5FromFile() {
        ArrayList<SavedGame> list = (ArrayList<SavedGame>) Utils.readObject(Utils.GAMEFILE);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
