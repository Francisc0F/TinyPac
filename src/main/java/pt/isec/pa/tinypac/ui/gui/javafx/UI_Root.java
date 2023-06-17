package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import pt.isec.pa.tinypac.model.Manager;
import pt.isec.pa.tinypac.ui.gui.javafx.views.WelcomeScreen;
import pt.isec.pa.utils.Direction;

public class UI_Root extends BorderPane {

    Manager model;

    public enum KeyPress {
        UP(KeyCode.UP),
        DOWN(KeyCode.DOWN),
        LEFT(KeyCode.LEFT),
        RIGHT(KeyCode.RIGHT),
        SPACE(KeyCode.SPACE),
        ENTER(KeyCode.ENTER);

        private final KeyCode keyCode;

        KeyPress(KeyCode keyCode) {
            this.keyCode = keyCode;
        }
    }

    public UI_Root(Manager model) {
        this.model = model;
        buildInitialScreen();

    }

    private void buildInitialScreen() {
        setWidth(800);
        setCenter(new WelcomeScreen(model));
    }

    public void mapKeyToAction(KeyPress keyPress) {
        switch (keyPress) {
            case UP -> {
                this.model.getFsmObs().registDirection(Direction.UP);
            }
            case DOWN -> {
                this.model.getFsmObs().registDirection(Direction.DOWN);
            }
            case RIGHT -> {
                this.model.getFsmObs().registDirection(Direction.RIGHT);
            }
            case LEFT -> {
                this.model.getFsmObs().registDirection(Direction.LEFT);
            }
            case ENTER -> {
                this.model.getFsmObs().pause();
            }
            case SPACE -> {
                Window window = getScene().getWindow();
                if (window instanceof Stage) {
                    WindowEvent closeRequest = new WindowEvent((Stage) window, WindowEvent.WINDOW_CLOSE_REQUEST);
                    window.fireEvent(closeRequest);
                }
            }
        }
    }

}
