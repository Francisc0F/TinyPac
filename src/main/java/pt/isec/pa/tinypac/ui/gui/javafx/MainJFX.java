package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;

public class MainJFX extends Application {

    public static TinyPacStateMachine fsm;
    public static TinyPacStateMachineObservable fsmObs;
    public static UI_Root gui;

    @Override
    public void start(Stage stage)  {
        fsm = new TinyPacStateMachine();
        fsmObs = new TinyPacStateMachineObservable(fsm);

        gui = new UI_Root(fsmObs);
        setupGameEngine(fsm);
        gui.start(stage, fsm.getMap());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setupGameEngine(TinyPacStateMachine fsm ) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.registerClient((g,t) -> {
            if (!fsm.evolve())
                g.stop();

            Platform.runLater( () -> {
                gui.updateBoard(fsm.getMap());
            });
        });

        gameEngine.start(200);
    }
}
