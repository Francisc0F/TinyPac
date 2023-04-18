package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.TinyPac;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class ShowTopPlayersState extends TinyPacStateAdapter {
    public ShowTopPlayersState(TinyPacContext context, TinyPac tinyPac) {
        super(context, tinyPac);
        tinyPac.setCurrentlevel(0);
    }

    @Override
    public boolean up() {
        changeState(new UpState(context, tinyPac));
        return true;
    }

    @Override
    public boolean down() {
        changeState(new LeftState(context, tinyPac));
        return true;
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.GROUND_FLOOR;
    }


}
