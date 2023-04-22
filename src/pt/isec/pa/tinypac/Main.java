package pt.isec.pa.tinypac;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.ui.text.TinyPacCmdUI;

public class Main {
    public static void main(String[] args) {
        TinyPacStateMachine fsm = new TinyPacStateMachine();
        TinyPacCmdUI ui = new TinyPacCmdUI(fsm);
    }
}
