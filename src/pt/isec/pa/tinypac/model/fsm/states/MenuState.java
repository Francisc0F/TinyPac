package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.TinyPac;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class MenuState extends TinyPacStateAdapter {
    public MenuState(TinyPacContext context, TinyPac tinyPac) {
        super(context, tinyPac);
        tinyPac.setCurrentlevel(2);
    }


    @Override
    public boolean viewTopPlayers() {
        changeState(new ShowTopPlayersState(context, tinyPac));
        return true;
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.MENU;
    }

}
