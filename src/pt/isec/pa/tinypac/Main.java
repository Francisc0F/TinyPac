package pt.isec.pa.tinypac;

import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.ui.text.TinyPacUI;

public class Main {
    public static void main(String[] args) {
        TinyPacContext fsm = new TinyPacContext();
        TinyPacUI ui = new TinyPacUI(fsm);
        ui.start();
    }
}
