package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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



    public void updateBoard() {
        this.fsm.evolve();
        propertyChangeSupport.firePropertyChange(Events.updateBoard, null, null);
    }


    public void evolve() {
        propertyChangeSupport.firePropertyChange(Events.updateBoard, null, null);
    }

    public int getScore() {
        return fsm.getTotalPoints();
    }
}
