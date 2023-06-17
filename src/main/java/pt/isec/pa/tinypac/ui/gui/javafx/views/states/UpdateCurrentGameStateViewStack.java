package pt.isec.pa.tinypac.ui.gui.javafx.views.states;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.Manager;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.tinypac.ui.gui.javafx.components.BoardComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.HeaderScoreBarComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.LifesComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.LowerMenuComponent;

import java.beans.PropertyChangeListener;

public class UpdateCurrentGameStateViewStack extends VBox {
    private final Manager model;
    private final Utils utils = new Utils();

    public UpdateCurrentGameStateViewStack(Manager model) {
        super();
        this.model = model;
        buildView();
        createObservables();
        setPanelVisible();
    }

    private void buildView() {
        setAlignment(Pos.CENTER);
        setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");
        getChildren().add(new HeaderScoreBarComponent(model.getFsmObs(), utils));
        getChildren().add(new BoardComponent(model.getFsmObs(), utils));
        getChildren().add(new LifesComponent(model.getFsmObs(), utils));
        getChildren().add(new LowerMenuComponent(model.getFsmObs(), utils));
    }

    private void createObservables() {
        model.getFsmObs().addPropertyChangeListener(Events.updateBoard, update());
        model.getFsmObs().addPropertyChangeListener(Events.changedState, update());
    }
    private PropertyChangeListener update() {
        return evt -> Platform.runLater(this::setPanelVisible);
    }

    private void setPanelVisible() {
        setVisible(model.getFsmObs().getState() == TinyPacState.UPDATECURRENTGAMESTATE);
    }
}
