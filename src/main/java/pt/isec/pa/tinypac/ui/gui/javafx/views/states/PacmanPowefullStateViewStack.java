package pt.isec.pa.tinypac.ui.gui.javafx.views.states;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.TinyPac;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.tinypac.ui.gui.javafx.components.BoardComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.HeaderScoreBarComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.LifesComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.components.LowerMenuComponent;

public class PacmanPowefullStateViewStack extends VBox {
    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils = new Utils();

    public PacmanPowefullStateViewStack(TinyPac model) {
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

        getChildren().add(new HeaderScoreBarComponent(fsmObs, utils));
        getChildren().add(new BoardComponent(fsmObs, utils));
        getChildren().add(new LifesComponent(fsmObs, utils));
        getChildren().add(new LowerMenuComponent(fsmObs, utils));
    }

    private void createObservables() {
        fsmObs.addPropertyChangeListener(Events.updateBoard, evt -> {
            setPanelVisible();
        });
    }

    private void setPanelVisible(){
        setVisible(this.fsmObs.getState() == TinyPacState.PACMANPOWERFULLSTATE);
    }
}
