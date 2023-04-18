package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.TinyPac;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

class InitGameState extends TinyPacStateAdapter {
    InitGameState(TinyPacContext context, TinyPac tinyPac) {
        super(context, tinyPac);
    }


    @Override
    public boolean up() {
        changeState(new DownState(context, tinyPac));
        return true;
    }


    @Override
    public boolean down() {
        changeState(new UpState(context, tinyPac));
        return true;
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.MENU;
    }

}
