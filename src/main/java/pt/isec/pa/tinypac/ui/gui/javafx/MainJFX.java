package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.Manager;

public class MainJFX extends Application {

    public Manager model;
    public static UI_Root gui;

    @Override
    public void start(Stage stage) {
        this.model = new Manager();
        gui = new UI_Root(model);

        Scene scene = new Scene(gui);
        setupGameEngine();

        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            // Create a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sair");
            alert.setHeaderText("De certeza que quer sair do jogo?");

            // Show the confirmation dialog and wait for user response
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            // If the user chooses to cancel, consume the event (prevent window closure)
            if (result != ButtonType.OK) {
                event.consume();
            }
        });
        stage.setTitle("Tinypac");
        stage.setWidth(1000);
        stage.setHeight(900);

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

        this.model.getFsmObs().addPropertyChangeListener(Events.iterationSpeedChanged, evt -> {
            gameEngine.stop();
            gameEngine.registerClient((g, t) -> {
                this.model.getFsmObs().update();
            });

            gameEngine.start(this.model.getFsmObs().getIterationSpeed());
        });

        gameEngine.registerClient((g, t) -> {
            this.model.getFsmObs().update();
        });

        gameEngine.start(this.model.getFsmObs().getIterationSpeed());
    }
}
