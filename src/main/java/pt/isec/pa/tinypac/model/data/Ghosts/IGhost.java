package pt.isec.pa.tinypac.model.data.Ghosts;

import pt.isec.pa.utils.Position;

import java.util.Stack;

public interface IGhost {
    boolean savePosition();
    boolean move();
    boolean getIsVulnerable();
    Stack<Position> previousMoves = new Stack<Position>();
}
