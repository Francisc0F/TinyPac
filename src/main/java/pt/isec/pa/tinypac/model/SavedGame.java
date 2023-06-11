package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;

import java.io.Serializable;
import java.util.Date;

public class SavedGame implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Date date = new Date();
    private TinyPacStateMachine data;
    private String username;

    public SavedGame(TinyPacStateMachine data) {
        this.data = data;
        this.username = "unnamed";
    }

    public SavedGame(TinyPacStateMachine data, String username) {
        this.data = data;
        this.username = username;
    }

    public TinyPacStateMachine getData() {
        return data;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return data.getTotalPoints();
    }

}