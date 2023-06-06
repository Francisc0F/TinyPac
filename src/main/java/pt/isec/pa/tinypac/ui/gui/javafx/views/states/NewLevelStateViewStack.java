package pt.isec.pa.tinypac.ui.gui.javafx.views.states;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.TinyPac;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.tinypac.ui.gui.javafx.components.*;

public class NewLevelStateViewStack extends StackPane {
    private final TinyPacStateMachineObservable fsmObs;
    private final TinyPac model;
    private final Utils utils = new Utils();

    public NewLevelStateViewStack(TinyPac model) {
        super();
        this.model = model;
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

    private void createObservables() {
        fsmObs.addPropertyChangeListener(Events.updateBoard, evt -> {
            setPanelVisible();
        });
    }

    private VBox createMenu() {
        VBox menu = new VBox(20);
        menu.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
        menu.setAlignment(Pos.CENTER);

        Label label = new Label("New level Loaded");
        label.setFont(utils.pixelfont);
        label.setStyle("-fx-text-fill: white; -fx-padding: 0 0 20 0");

    /*    Button saveButton = new PacButtonComponent("Save", utils, Color.GREENYELLOW, Color.ORANGE);
        Button resumeButton = new PacButtonComponent("Resume", utils, Color.GREEN, Color.ORANGE);
        Button quitButton = new PacButtonComponent("Quit", utils, Color.BLANCHEDALMOND, Color.ORANGE);
        saveButton.setOnAction(event -> {
            this.model.saveGame();
        });

        resumeButton.setOnAction(event -> {
            this.model.getFsmObs().resume();
        });


        quitButton.setOnAction(event -> {
            //todo quit game
        });
*/
        menu.getChildren().addAll(label);

        return menu;
    }

    private void setPanelVisible() {
        setVisible(this.fsmObs.getState() == TinyPacState.NEWLEVELSTATE);
    }
}
