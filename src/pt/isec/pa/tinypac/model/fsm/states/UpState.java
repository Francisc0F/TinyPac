package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.TinyPac;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

class UpState extends TinyPacStateAdapter {
    protected UpState(TinyPacContext context, TinyPac tinyPac) {
        super(context, tinyPac);
        tinyPac.setCurrentlevel(1);
    }

    @Override
    public boolean up() {
        changeState(new UpState(context, tinyPac));
        return true;
    }

    @Override
    public boolean down() {
        changeState(new ShowTopPlayersState(context, tinyPac));
        return true;
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.FIRST_FLOOR;
    }

}
