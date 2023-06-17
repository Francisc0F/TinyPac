package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;

public class Manager {
    private TinyPacStateMachine fsm;
    private TinyPacStateMachineObservable fsmObs;

    public Manager() {
        fsm = new TinyPacStateMachine();
        fsmObs = new TinyPacStateMachineObservable(fsm);
    }

    public TinyPacStateMachineObservable getFsmObs() {
        return fsmObs;
    }

    public void setSavedGame(TinyPacStateMachine fsm) {
        this.fsm = fsm;
        fsmObs = new TinyPacStateMachineObservable(fsm);
    }
}
