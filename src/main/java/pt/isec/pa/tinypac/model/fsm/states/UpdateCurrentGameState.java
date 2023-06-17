package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.EvolveEvent;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

public class UpdateCurrentGameState extends TinyPacStateAdapter {
    private static final long serialVersionUID = 1L;
    private int initalRoundFoodScore;
    private int initalRoundFruitScore;
    private boolean foodEated;
    private boolean fruitEated;
    public UpdateCurrentGameState(TinyPacStateMachine context, MapController mapController) {
        super(context, mapController);
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.UPDATECURRENTGAMESTATE;
    }

    @Override
    public EvolveEvent evolve() {
        initalRoundFoodScore = map.getFoodScore();
        initalRoundFruitScore = map.getFruitScore();
        map.updateLiveOrganisms();
        map.incIteration();
        foodEated = initalRoundFoodScore < map.getFoodScore();
        fruitEated = initalRoundFruitScore < map.getFruitScore();

        if (map.isGodMode()) {
            changeState(TinyPacState.PACMANPOWERFULLSTATE);
            return EvolveEvent.EATEDPOWERFULLFOOD;
        }

        if(map.allFoodEaten()){
            if(mapController.getIsLastLevel()){
                changeState(TinyPacState.FINISHGAMESTATE);
                return EvolveEvent.CHANGEDSTATE;
            }
            changeState(TinyPacState.NEWLEVELSTATE);
            return EvolveEvent.NEWLEVELLOADED;
        }

        if (map.isPacmanDeath()) {
            mapController.decLives();
            if (map.isPacmanDeath() && mapController.noLivesRemaining()) {
                changeState(TinyPacState.LOSTGAMESTATE);
                return EvolveEvent.LOSTLIFE;
            }
            changeState(TinyPacState.LOSTLIFESTATE);
            return EvolveEvent.LOSTLIFE;
        }

        if(foodEated){
           return EvolveEvent.EATEDFOOD;
        }

        if(fruitEated){
            return EvolveEvent.EATEDFRUIT;
        }

        changeState(TinyPacState.UPDATECURRENTGAMESTATE);
        return EvolveEvent.CHANGEDSTATE;
    }

    @Override
    public EvolveEvent pause() {
        changeState(TinyPacState.PAUSEGAMESTATE);
        return EvolveEvent.CHANGEDSTATE;
    }

    @Override
    public EvolveEvent registerDirection(Direction direction) {
        mapController.setCurrentPacmanDirection(direction);
        return null;
    }
}
