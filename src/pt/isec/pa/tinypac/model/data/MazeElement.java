package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.utils.Position;

public class MazeElement implements IMazeElement{
    private final char symbol;
    protected Position position;
    public MazeElement(char symbol){
        this.symbol = symbol;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    public boolean isFood(){
        return getSymbol() == 'o';
    }

    public boolean isPowerFullFood(){
        return getSymbol() == 'O';
    }

    public boolean isGhost(){
        return getSymbol() == 'y';
    }

    public boolean isGhostPortal(){
        return getSymbol() == 'Y';
    }

    public boolean isWall(){
        return getSymbol() == 'x';
    }

    public boolean isWrap(){
        return getSymbol() == 'W';
    }

    public boolean isFruitPlace(){
        return getSymbol() == 'F';
    }


    @Override
    public String toString() {
        return "MazeElement{" +
                "symbol=" + symbol +
                '}';
    }
}
