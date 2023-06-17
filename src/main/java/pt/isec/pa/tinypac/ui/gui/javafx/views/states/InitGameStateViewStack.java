package pt.isec.pa.tinypac.ui.gui.javafx.views.states;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
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

public class InitGameStateViewStack extends VBox {
    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils = new Utils();

    public InitGameStateViewStack(Manager model) {
        super();
        this.fsmObs = model.getFsmObs();
        buildView();
        registerListeners();
        setPanelVisible();
    }

    private void buildView() {
        setAlignment(Pos.CENTER);
        setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");
        getChildren().add(new HeaderScoreBarComponent(fsmObs, utils));
        getChildren().add(new BoardComponent(fsmObs, utils));
        getChildren().add(new LifesComponent(fsmObs, utils));
        getChildren().add(new LowerMenuComponent(fsmObs, utils));
        MusicPlayer.playMusic(MusicPlayer.pacman_beginning);
    }

    private void registerListeners() {
        fsmObs.addPropertyChangeListener(Events.updateBoard, update());
        fsmObs.addPropertyChangeListener(Events.changedState, update());
    }
    private PropertyChangeListener update() {
        return evt -> Platform.runLater(this::setPanelVisible);
    }
    private void setPanelVisible(){
        boolean isVisible = fsmObs.getState() == TinyPacState.INITGAMESTATE;
        setVisible(isVisible);
    }
}
