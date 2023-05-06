package pt.isec.pa.tinypac;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.ui.gui.javafx.CustomEvent;
import pt.isec.pa.tinypac.ui.gui.javafx.UI_pacman;


public class Main extends Application {

    public static  TinyPacStateMachine fsm;
    public static UI_pacman gui;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setupGameEngine(TinyPacStateMachine fsm ) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.registerClient((g,t) -> {
            if (!fsm.evolve())
                g.stop();
        });

        gameEngine.registerClient((g, t) -> {
            Platform.runLater( () -> {
                gui.updateBoard(fsm.getMap());
            });
        });

        gameEngine.start(500);
    }

    @Override
    public void start(Stage stage) throws Exception {
        fsm = new TinyPacStateMachine();

        gui = new UI_pacman(fsm);
        setupGameEngine(fsm);
        System.out.println("AQUI");
        gui.start(stage, fsm.getMap());

    }
}
