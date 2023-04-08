package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.TinyPac;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class LeftState extends TinyPacStateAdapter {
    public LeftState(TinyPacContext context, TinyPac tinyPac) {
        super(context, tinyPac);
        tinyPac.setCurrentFloor(-1);
    }

    @Override
    public boolean up() {
        changeState(new UpState(context, tinyPac));
        return true;
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.NEGATIVEFLOOR;
    }

}
