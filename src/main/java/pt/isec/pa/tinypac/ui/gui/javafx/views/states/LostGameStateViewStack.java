package pt.isec.pa.tinypac.ui.gui.javafx.views.states;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import pt.isec.pa.utils.MusicPlayer;

import java.beans.PropertyChangeListener;

public class LostGameStateViewStack extends StackPane {
    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils = new Utils();

    public LostGameStateViewStack(Manager model) {
        super();
        this.fsmObs = model.getFsmObs();
        buildView();
        createObservables();
        setPanelVisible();
    }


    private void buildView() {
        setAlignment(Pos.CENTER);
        //hgroup.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");
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

    private VBox createMenu() {
        VBox menu = new VBox(20);
        menu.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
        menu.setAlignment(Pos.CENTER);

        Label label = new Label("You have lost the game");
        label.setFont(utils.pixelfont);
        label.setStyle("-fx-text-fill: white; -fx-padding: 0 0 20 0");

        Button quitButton = new PacButtonComponent("Quit", utils, Color.BLANCHEDALMOND, Color.ORANGE);

        quitButton.setOnAction(event -> {
            Window window = quitButton.getScene().getWindow();
            if (window instanceof Stage) {
                WindowEvent closeRequest = new WindowEvent((Stage) window, WindowEvent.WINDOW_CLOSE_REQUEST);
                window.fireEvent(closeRequest);
            }
        });


        if (fsmObs.reachedTop5()) {
            VBox formSaveName = new VBox();
            Label topLabel = new Label("You have reached the top 5");
            topLabel.setFont(utils.pixelfont);
            topLabel.setStyle("-fx-text-fill: white; -fx-padding: 0 0 20 0");
            TextField inputField = new TextField();
            inputField.setFont(utils.pixelfont);
            PacButtonComponent submitButton = new PacButtonComponent("Guardar", utils, Color.BLANCHEDALMOND, Color.ORANGE);
            submitButton.setOnAction(event -> {
                String userInput = inputField.getText();
                System.out.println("User input: " + userInput);
                menu.getChildren().clear();
                menu.getChildren().addAll(label, quitButton);
                fsmObs.saveGame(userInput);
            });

            HBox submitWrapper = new HBox();
            submitWrapper.setAlignment(Pos.CENTER);
            submitWrapper.getChildren().add(submitButton);
            submitWrapper.setPadding(new Insets(10, 0, 0, 0));
            formSaveName.getChildren().addAll(topLabel, inputField, submitWrapper);

            menu.getChildren().addAll(formSaveName);
        } else {
            menu.getChildren().clear();
            menu.getChildren().addAll(label, quitButton);
        }

        return menu;
    }

    private void createObservables() {
        fsmObs.addPropertyChangeListener(Events.lifesUpdated, update());
        fsmObs.addPropertyChangeListener(Events.changedState, update());
    }

    private PropertyChangeListener update() {
        return evt -> Platform.runLater(this::setPanelVisible);
    }

    private boolean setPanelVisible() {
        boolean isVisible = this.fsmObs.getState() == TinyPacState.LOSTGAMESTATE;
        if(isVisible && !isVisible()){
            MusicPlayer.playMusic(MusicPlayer.pacman_death);
        }
        setVisible(isVisible);
        return isVisible;
    }
}
