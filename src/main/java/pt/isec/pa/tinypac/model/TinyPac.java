package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;

import java.io.Serializable;

public class TinyPac implements Serializable {
    private TinyPacStateMachine fsm;
    private TinyPacStateMachineObservable fsmObs;

    public TinyPac() {
        fsm = new TinyPacStateMachine();
        fsmObs = new TinyPacStateMachineObservable(fsm);
    }

    public TinyPacStateMachineObservable getFsmObs() {
        return fsmObs;
    }
}
