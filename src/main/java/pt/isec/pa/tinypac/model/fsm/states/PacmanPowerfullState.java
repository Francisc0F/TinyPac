package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.EvolveEvent;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

public class PacmanPowerfullState extends TinyPacStateAdapter {
    private static final long serialVersionUID = 1L;
    private int initalRoundFoodScore;
    private int initalRoundFruitScore;
    private int initalRoundGhostScore;
    private boolean foodEated = false;
    private boolean fruitEated = false;
    private boolean ghostEated = false;

    public PacmanPowerfullState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.PACMANPOWERFULLSTATE;
    }

    @Override
    public EvolveEvent evolve() {
        initalRoundFoodScore = map.getFoodScore();
        initalRoundFruitScore = map.getFruitScore();
        initalRoundGhostScore = map.getGhostScore();
        map.incGoodModeIteration();
        map.updateLiveOrganisms();
        map.incIteration();

        foodEated = initalRoundFoodScore < map.getFoodScore();
        fruitEated = initalRoundFruitScore < map.getFruitScore();
        ghostEated = initalRoundGhostScore < map.getGhostScore();

        if (map.allFoodEaten()) {
            if(mapController.getIsLastLevel()){
                changeState(TinyPacState.FINISHGAMESTATE);
                return EvolveEvent.CHANGEDSTATE;
            }
            changeState(TinyPacState.NEWLEVELSTATE);
            return EvolveEvent.CHANGEDSTATE;
        }

        if (map.godModeTimeEnded()) {
            map.setNormalMode();
            changeState(TinyPacState.UPDATECURRENTGAMESTATE);
            return EvolveEvent.CHANGEDSTATE;
        }

        if(foodEated){
            return EvolveEvent.EATEDFOOD;
        }


        if(fruitEated){
            return EvolveEvent.EATEDFRUIT;
        }

        if(ghostEated){
            return EvolveEvent.EATEDGHOST;
        }


        changeState(TinyPacState.PACMANPOWERFULLSTATE);
        return EvolveEvent.CHANGEDSTATE;
    }


    @Override
    public EvolveEvent registerDirection(Direction direction) {
        mapController.setCurrentPacmanDirection(direction);
        return null;
    }
}
