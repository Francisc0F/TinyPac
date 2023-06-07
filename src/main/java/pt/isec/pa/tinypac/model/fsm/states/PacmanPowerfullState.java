package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

public class PacmanPowerfullState extends TinyPacStateAdapter {
    private int initalRoundFoodScore = map.getFoodScore();
    private int initalRoundFruitScore = map.getFruitScore();
    private int initalRoundGhostScore = map.getGhostScore();
    private boolean foodEated = false;
    private boolean fruitEated = false;
    private boolean ghostEated = false;

    public PacmanPowerfullState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);

        map.incGoodModeIteration();
        map.updateLiveOrganisms();
        map.incIteration();

        foodEated = initalRoundFoodScore < map.getFoodScore();
        fruitEated = initalRoundFruitScore < map.getFruitScore();
        ghostEated = initalRoundGhostScore < map.getGhostScore();
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.PACMANPOWERFULLSTATE;
    }

    @Override
    public boolean evolve() {
        if (map.allFoodEaten()) {
            changeState(TinyPacState.NEWLEVELSTATE);
            return false;
        }

        if (map.godModeTimeEnded()) {
            map.setNormalMode();
            changeState(TinyPacState.UPDATECURRENTGAMESTATE);
            return true;
        }


        changeState(TinyPacState.PACMANPOWERFULLSTATE);
        return true;
    }

    @Override
    public boolean hasEatedFood(){
        return this.foodEated;
    }

    @Override
    public boolean hasEatedFruit(){
        return this.fruitEated;
    }

    @Override
    public boolean hasEatedGhost(){
        return this.ghostEated;
    }

    @Override
    public void registDirection(Direction direction) {
        mapController.setCurrentPacmanDirection(direction);
    }
}
