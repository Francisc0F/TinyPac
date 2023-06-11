package pt.isec.pa.tinypac.model;

import javafx.application.Platform;
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

    public void updateBoard() {
        if (this.fsm.hasEatedFood()) {
            Platform.runLater(() -> {
                propertyChangeSupport.firePropertyChange(Events.foodEated, null, null);
            });
        }

        if (this.fsm.hasEatedFruit()) {
            Platform.runLater(() -> {
                propertyChangeSupport.firePropertyChange(Events.fruitEated, null, null);
            });
        }

        if (this.fsm.hasEatedGhost()) {
            Platform.runLater(() -> {
                propertyChangeSupport.firePropertyChange(Events.ghostEated, null, null);
            });
        }
        this.fsm.evolve();
        Platform.runLater(() -> {
            propertyChangeSupport.firePropertyChange(Events.updateBoard, null, null);

        });
    }


    public void evolve() {
        propertyChangeSupport.firePropertyChange(Events.updateBoard, null, null);
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
}
