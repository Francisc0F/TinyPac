package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import pt.isec.pa.tinypac.model.TinyPac;
import pt.isec.pa.tinypac.ui.gui.javafx.views.WelcomeScreen;
import pt.isec.pa.utils.Direction;

public class UI_Root extends BorderPane {

    TinyPac model;

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

    public UI_Root(TinyPac model) {
        this.model = model;
        buildInitialScreen();

    }

    private void buildInitialScreen() {
        setWidth(800);
        setCenter(new WelcomeScreen(model));
    }

    public boolean mapKeyToAction(KeyPress keyPress) {
        return switch (keyPress) {
            case UP -> {
                this.model.getFsmObs().registDirection(Direction.UP);
                yield true;
            }
            case DOWN -> {
                this.model.getFsmObs().registDirection(Direction.DOWN);
                yield true;
            }
            case RIGHT -> {
                this.model.getFsmObs().registDirection(Direction.RIGHT);
                yield true;
            }
            case LEFT -> {
                this.model.getFsmObs().registDirection(Direction.LEFT);
                yield true;
            }
            case ENTER -> {
                //fsm.pause();
                yield true;
            }
            case SPACE -> {
                //fsm.leave();
                yield false;
            }
        };
    }

}
