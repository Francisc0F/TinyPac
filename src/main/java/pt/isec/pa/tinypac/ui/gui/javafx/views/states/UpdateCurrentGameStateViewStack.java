package pt.isec.pa.tinypac.ui.gui.javafx.views.states;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.TinyPac;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.tinypac.ui.gui.javafx.components.BoardComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.HeaderScoreBarComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.LifesComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.LowerMenuComponent;

public class UpdateCurrentGameStateViewStack extends VBox {
    private final TinyPac model;
    private final Utils utils = new Utils();

    public UpdateCurrentGameStateViewStack(TinyPac model) {
        super();
        this.model = model;
        buildView();
        createObservables();
        setPanelVisible();
    }

    private void buildView() {
        setAlignment(Pos.CENTER);
        //hgroup.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");
        setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");
        getChildren().add(new HeaderScoreBarComponent(model.getFsmObs(), utils));
        getChildren().add(new BoardComponent(model.getFsmObs(), utils));
        getChildren().add(new LifesComponent(model.getFsmObs(), utils));
        getChildren().add(new LowerMenuComponent(model.getFsmObs(), utils));
    }


    private void createObservables() {
        model.getFsmObs().addPropertyChangeListener(Events.updateBoard, evt -> {
            setPanelVisible();
        });
    }

    private void setPanelVisible() {
        setVisible(model.getFsmObs().getState() == TinyPacState.UPDATECURRENTGAMESTATE);
    }

}
