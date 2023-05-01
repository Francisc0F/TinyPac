package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.utils.Direction;

public interface ITinyPacState {
    boolean evolve();
    boolean pause();
    boolean save();
    boolean leave();
    boolean loadNewLevel();
    boolean resume();
    TinyPacState getState();

    void registDirection(Direction direction);
}
