package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;

public class TinyPac {
    TinyPacStateMachine fsm;
    TinyPacStateMachineObservable fsmObs;
    // todo save/load games

    public TinyPac(){
        fsm = new TinyPacStateMachine();
        fsmObs = new TinyPacStateMachineObservable(fsm);
    }

    public TinyPacStateMachineObservable getFsmObs() {
        return fsmObs;
    }
}
