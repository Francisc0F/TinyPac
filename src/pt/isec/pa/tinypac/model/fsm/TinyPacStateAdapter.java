package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.TinyPac;
import pt.isec.pa.tinypac.model.fsm.states.ITinyPacState;

public abstract class TinyPacStateAdapter implements ITinyPacState {
    protected TinyPac tinyPac;
    protected TinyPacContext context;

    protected TinyPacStateAdapter(TinyPacContext context, TinyPac tinyPac) {
        this.context = context;
        this.tinyPac = tinyPac;
    }

     protected void changeState(ITinyPacState newState) {
        context.changeState(newState);
    }

    @Override
    public boolean up() {
        return false;
    }

    @Override
    public boolean down() {
        return false;
    }

    @Override
    public boolean viewTopPlayers() {
        return false;
    }
    
}
