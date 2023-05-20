package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.utils.Position;

import java.util.Stack;

public interface IGhost {
    void savePosition() throws CloneNotSupportedException;
    boolean getIsVulnerable();
    Stack<Position> previousMoves = new Stack<Position>();
}
