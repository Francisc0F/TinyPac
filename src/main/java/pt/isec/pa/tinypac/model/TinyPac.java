package pt.isec.pa.tinypac.model;

import jdk.jshell.execution.Util;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;

public class TinyPac {
    private TinyPacStateMachine fsm;
    private TinyPacStateMachineObservable fsmObs;
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
        try {
            TinyPacStateMachine gameCopy = (TinyPacStateMachine)Utils.clone(fsm);
            Utils.saveObject(gameCopy, "savedGame.bin");
        } catch (Exception ex) {
            System.out.println("Ex CLONE " + ex);
        }
    }
}
