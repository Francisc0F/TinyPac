package pt.isec.pa.tinypac.ui.gui.javafx.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;

public class HeaderScoreBarComponent extends HBox {

    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils;
    private Label currentScore;
    private Label highestScore;
    private Label state;

    public HeaderScoreBarComponent(TinyPacStateMachineObservable fsmObs, Utils utils) {
        this.fsmObs = fsmObs;
        this.utils = utils;
        view();
        registListeners();
    }

    private void registListeners() {
        fsmObs.addPropertyChangeListener(Events.updateBoard, evt -> {
            this.currentScore.setText("" + this.fsmObs.getScore());
            this.state.setText(this.fsmObs.getState().toString());
        });
    }

    private void view() {
        setAlignment(Pos.CENTER);
        state = new Label();
        state.setFont(utils.pixelfont);
        state.setStyle("-fx-padding: 0 10 0 0;");

        currentScore = new Label("0");
        currentScore.setFont(utils.pixelfont);
        currentScore.setStyle("-fx-padding: 0 10 0 0;");
        highestScore = new Label("0");
        highestScore.setFont(utils.pixelfont);


        getChildren().addAll(state, currentScore, highestScore);
    }
}
