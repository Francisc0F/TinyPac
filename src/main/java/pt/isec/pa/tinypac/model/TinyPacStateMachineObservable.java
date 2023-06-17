package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.data.EvolveEvent;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.utils.Direction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TinyPacStateMachineObservable {
    private TinyPacStateMachine fsm;
    private final PropertyChangeSupport propertyChangeSupport;

    public TinyPacStateMachineObservable(TinyPacStateMachine fsm) {
        this.fsm = fsm;
        propertyChangeSupport = new PropertyChangeSupport(this);

    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void registDirection(Direction direction) {
        this.fsm.registDirection(direction);
    }

    public char[][] getMap() {
        return this.fsm.getMap();
    }

    public void pause() {
        this.fsm.pause();
        propertyChangeSupport.firePropertyChange(Events.pauseGame, null, null);
    }


    public boolean reachedTop5() {
        return this.fsm.reachedTop5();
    }

    public void saveGame(String name) {
        this.fsm.save(name);
    }

    public void resume() {
        this.fsm.resume();
        propertyChangeSupport.firePropertyChange(Events.resumeGame, null, null);
    }

    public void update() {
        EvolveEvent event = this.fsm.evolve();
        if (event == null) {
            propertyChangeSupport.firePropertyChange(Events.updateBoard, null, null);
            return;
        }
        System.out.println("event   "+ event);
        switch (event) {
            case LOSTLIFE -> {
                propertyChangeSupport.firePropertyChange(Events.lifesUpdated, null, null);
            }
            case NEWLEVELLOADED -> {
                propertyChangeSupport.firePropertyChange(Events.levelUpdated, null, null);
            }
            case EATEDFOOD -> {
                propertyChangeSupport.firePropertyChange(Events.updateBoard, null, null);
                propertyChangeSupport.firePropertyChange(Events.foodEated, null, null);
            }
            case EATEDGHOST -> {
                propertyChangeSupport.firePropertyChange(Events.updateBoard, null, null);
                propertyChangeSupport.firePropertyChange(Events.ghostEated, null, null);
            }
            case EATEDPOWERFULLFOOD -> {
                propertyChangeSupport.firePropertyChange(Events.changedState, null, null);
                propertyChangeSupport.firePropertyChange(Events.updateBoard, null, null);
                propertyChangeSupport.firePropertyChange(Events.powerfullFoodEated, null, null);
            }
            case EATEDFRUIT -> {
                propertyChangeSupport.firePropertyChange(Events.updateBoard, null, null);
                propertyChangeSupport.firePropertyChange(Events.fruitEated, null, null);
            }
            case CHANGEDSTATE -> {
                propertyChangeSupport.firePropertyChange(Events.changedState, null, null);
            }
            default -> {
            }
        }
    }

    public int getScore() {
        return fsm.getTotalPoints();
    }

    public Direction getDirection() {
        return fsm.getDirection();
    }

    public TinyPacState getState() {
        return fsm.getState();
    }

    public int getLifes() {
        return fsm.getLifesRemaining();
    }

    public long getIterationSpeed() {
        return fsm.getCyclesSpeed();
    }

    public ArrayList<SavedGame> getTop5() {
        return fsm.getTop5FromFile();
    }

    public int getHighestScore() {
        return fsm.getHighestScore();
    }

    public void saveCurrentGame() {
        fsm.saveCurrentGame();
    }

    public SavedGame getSavedGame() {
        return fsm.getSavedGame();
    }

    public int getLevel() {
        return fsm.getCurrentLevel();
    }
}
