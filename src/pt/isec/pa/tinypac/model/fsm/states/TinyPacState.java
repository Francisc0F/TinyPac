package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;

public enum TinyPacState {
    INITGAMESTATE,
    LOSTLIFESTATE,
    LOSTGAMESTATE,
    NEWLEVELSTATE,
    UPDATECURRENTGAMESTATE,
    PAUSEGAMESTATE,
    FINISHGAMESTATE,
    PACMANPOWERFULLSTATE;

    public ITinyPacState createState(TinyPacStateMachine context, MapController map) {
        return switch (this) {
            case INITGAMESTATE -> new InitGameState(context, map);
            case UPDATECURRENTGAMESTATE -> new UpdateCurrentGameState(context, map);
            case PACMANPOWERFULLSTATE -> new PacmanPowerfullState(context, map);

            case NEWLEVELSTATE -> new NewLevelState(context, map);
            case LOSTLIFESTATE -> new LostLifeState(context, map);

            case LOSTGAMESTATE -> new LostGameState(context, map);
            case PAUSEGAMESTATE -> new PauseGameState(context, map);

            case FINISHGAMESTATE -> new FinishGameState(context, map);
        };
    }
}
