package pt.isec.pa.tinypac.model.fsm.states;

public interface ITinyPacState {
    boolean up();
    boolean down();
    boolean viewTopPlayers();


    TinyPacState getState();
}
