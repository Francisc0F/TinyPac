package pt.isec.pa.tinypac.ui.gui.javafx.views.states;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.Manager;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.tinypac.ui.gui.javafx.components.*;

import java.beans.PropertyChangeListener;

public class PauseGameStateViewStack extends StackPane {
    private final TinyPacStateMachineObservable fsmObs;
    private final Manager model;
    private final Utils utils = new Utils();

    public PauseGameStateViewStack(Manager model) {
        super();
        this.model = model;
        this.fsmObs = model.getFsmObs();
        buildView();
        createObservables();
        setPanelVisible();
    }

    private void buildView() {
        setAlignment(Pos.CENTER);
        setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");

        VBox innerContentBelow = new VBox();

        innerContentBelow.getChildren().add(new HeaderScoreBarComponent(fsmObs, utils));
        innerContentBelow.getChildren().add(new BoardComponent(fsmObs, utils));
        innerContentBelow.getChildren().add(new LifesComponent(fsmObs, utils));
        innerContentBelow.getChildren().add(new LowerMenuComponent(fsmObs, utils));

        getChildren().add(innerContentBelow);

        HBox menuHBox = new HBox();
        VBox menuVBox = new VBox();
        menuHBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        menuVBox.setAlignment(Pos.CENTER);

        menuVBox.getChildren().addAll(createMenu());

        menuHBox.getChildren().add(menuVBox);
        menuHBox.setAlignment(Pos.CENTER);

        getChildren().add(menuHBox);
    }

    private void createObservables() {
        fsmObs.addPropertyChangeListener(Events.updateBoard, update());
        fsmObs.addPropertyChangeListener(Events.pauseGame, update());
        fsmObs.addPropertyChangeListener(Events.changedState, update());
    }
    private PropertyChangeListener update() {
        return evt -> Platform.runLater(this::setPanelVisible);
    }

    private VBox createMenu() {
        VBox menu = new VBox(20);
        menu.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
        menu.setAlignment(Pos.CENTER);

        Label label = new Label("Paused");
        label.setFont(utils.pixelfont);
        label.setStyle("-fx-text-fill: white; -fx-padding: 0 0 20 0");

        Button saveButton = new PacButtonComponent("Save", utils, Color.GREENYELLOW, Color.ORANGE);
        Button resumeButton = new PacButtonComponent("Resume", utils, Color.GREEN, Color.ORANGE);
        Button quitButton = new PacButtonComponent("Quit", utils, Color.BLANCHEDALMOND, Color.ORANGE);
        saveButton.setOnAction(event -> {
            this.fsmObs.saveCurrentGame();
        });

        resumeButton.setOnAction(event -> {
            this.fsmObs.resume();
        });

        quitButton.setOnAction(event -> {
            Window window = quitButton.getScene().getWindow();
            if (window instanceof Stage) {
                WindowEvent closeRequest = new WindowEvent((Stage) window, WindowEvent.WINDOW_CLOSE_REQUEST);
                window.fireEvent(closeRequest);
            }
        });

        menu.getChildren().addAll(label, saveButton, resumeButton, quitButton);

        return menu;
    }

    private void setPanelVisible() {
        setVisible(this.fsmObs.getState() == TinyPacState.PAUSEGAMESTATE);
    }
}
