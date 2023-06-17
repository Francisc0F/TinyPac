package pt.isec.pa.tinypac.ui.gui.javafx.views.states;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.Manager;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.tinypac.ui.gui.javafx.components.BoardComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.HeaderScoreBarComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.LifesComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.LowerMenuComponent;
import pt.isec.pa.utils.MusicPlayer;

import java.beans.PropertyChangeListener;

public class LostLifeStateViewStack extends StackPane {
    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils = new Utils();

    public LostLifeStateViewStack(Manager model) {
        super();
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


    private VBox createMenu() {
        VBox menu = new VBox(20);
        Label label = new Label("READY");
        label.setFont(utils.pixelfont);
        label.setStyle("-fx-text-fill: white; -fx-padding: 0 0 20 0");
        menu.getChildren().addAll(label);
        return menu;
    }

    private void createObservables() {
        fsmObs.addPropertyChangeListener(Events.lifesUpdated, update());
        fsmObs.addPropertyChangeListener(Events.updateBoard, update());
        fsmObs.addPropertyChangeListener(Events.changedState, update());
    }
    private PropertyChangeListener update() {
        return evt -> Platform.runLater(this::setPanelVisible);
    }

    private boolean setPanelVisible() {
        boolean isVisible = this.fsmObs.getState() == TinyPacState.LOSTLIFESTATE;
        if(isVisible && !isVisible()){
            MusicPlayer.playMusic(MusicPlayer.pacman_death);
        }
        setVisible(isVisible);

        return isVisible;
    }
}
