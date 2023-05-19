package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.TinyPac;

public class MainJFX extends Application {

    public TinyPac model;
    public static UI_Root gui;

    @Override
    public void start(Stage stage) {
        this.model = new TinyPac();
        gui = new UI_Root(model);

        Scene scene = new Scene(gui);
        setupGameEngine();

        stage.setScene(scene);
        stage.setTitle("Tinypac");
        stage.setWidth(1000);
        stage.setHeight(700);

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            UI_Root.KeyPress keyPress = switch (keyCode) {
                case UP -> UI_Root.KeyPress.UP;
                case DOWN -> UI_Root.KeyPress.DOWN;
                case LEFT -> UI_Root.KeyPress.LEFT;
                case RIGHT -> UI_Root.KeyPress.RIGHT;
                case SPACE -> UI_Root.KeyPress.SPACE;
                case ENTER -> UI_Root.KeyPress.ENTER;
                default -> null;
            };

            if (keyPress != null) {
                gui.mapKeyToAction(keyPress);
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setupGameEngine() {
        GameEngine gameEngine = new GameEngine();
        gameEngine.registerClient((g, t) -> {
            Platform.runLater(() -> {
                this.model.getFsmObs().updateBoard();
            });
        });

        gameEngine.start(200);
    }
}
