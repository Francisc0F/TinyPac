package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.utils.Direction;

public interface ITinyPacState   {
    boolean evolve();
    boolean pause();
    boolean save(String name);
    boolean leave();
    boolean loadNewLevel();
    boolean resume();
    boolean hasEatedFood();
    boolean hasEatedFruit();
    boolean hasEatedGhost();
    TinyPacState getState();

    void registDirection(Direction direction);
}
