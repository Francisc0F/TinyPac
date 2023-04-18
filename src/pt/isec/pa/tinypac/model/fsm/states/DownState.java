package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.TinyPac;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

class DownState extends TinyPacStateAdapter {
    DownState(TinyPacContext context, TinyPac tinyPac) {
        super(context, tinyPac);
    }

    @Override
    public boolean down() {
        changeState(new InitGameState(context, tinyPac));
        return true;
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.THIRD_FLOOR;
    }

}
