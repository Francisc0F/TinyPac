package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.SavedGame;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.states.ITinyPacState;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.utils.Direction;

import java.io.Serializable;
import java.util.ArrayList;

public class TinyPacStateMachine implements Serializable {
    private static final long serialVersionUID = 1L;
    ITinyPacState state;
    MapController mapController;
    private ArrayList<SavedGame> top5 = new ArrayList<>();

    public TinyPacStateMachine() {
        mapController = new MapController();
        state = TinyPacState.INITGAMESTATE.createState(this, mapController);
    }

    public ArrayList<SavedGame> getTop5FromFile() {
        ArrayList<SavedGame> list = (ArrayList<SavedGame>) Utils.readObject(Utils.GAMEFILE);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public TinyPacState getState() {
        return state.getState();
    }

    protected void changeState(ITinyPacState newState) {
        this.state = newState;
    }

    public void registDirection(Direction direction) {
        this.state.registDirection(direction);
    }

    public int getTotalPoints() {
        return mapController.getPoints();
    }

    public int getLifesRemaining() {
        return mapController.getLifesRemaining();
    }

    public char[][] getMap() {
        return mapController.map.buildMap();
    }

    public boolean evolve() {
        this.state.evolve();
        return true;
    }

    public void pause() {
        this.state.pause();
    }

    public Direction getDirection() {
        return this.mapController.map.getPacmanDirection();
    }

    public void resume() {
        this.state.resume();
    }

    public int getCyclesSpeed() {
        return mapController.getIterationSpeed();
    }

    public boolean hasEatedFood() {
        return state.hasEatedFood();
    }

    public boolean hasEatedGhost() {
        return state.hasEatedGhost();
    }

    public boolean hasEatedFruit() {
        return state.hasEatedFruit();
    }

    public void save(String name) {
        state.save(name);
    }
    public void saveCurrentGame() {
        state.saveCurrentGame();
    }

    public boolean reachedTop5() {
        if (top5.isEmpty()) {
            return true;
        }
        SavedGame top5Last = top5.get(top5.size() - 1);
        return getTotalPoints() > top5Last.getPoints();
    }

    public int getHighestScore() {
        ArrayList<SavedGame> list = getTop5FromFile();
        if (list.isEmpty()) {
            return 0;
        }
        return list.get(0).getPoints();
    }

    public SavedGame getSavedGame(){
        try {
            return (SavedGame) Utils.readObject(Utils.SAVEDGAMEONPAUSE);
        } catch (Exception ex) {
            System.out.println("Ex getSavedGame " + ex);
        }
        return null;
    }
}