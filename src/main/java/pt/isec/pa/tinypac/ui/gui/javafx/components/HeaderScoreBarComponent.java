package pt.isec.pa.tinypac.ui.gui.javafx.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;

public class HeaderScoreBarComponent extends HBox {

    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils;
    private Label currentScore;
    private Label highestScoreLabel;
    private Label state;
    private int highestScore;

    public HeaderScoreBarComponent(TinyPacStateMachineObservable fsmObs, Utils utils) {
        this.fsmObs = fsmObs;
        this.utils = utils;
        view();
        highestScore = this.fsmObs.getHighestScore();
        this.highestScoreLabel.setText("" + highestScore);
        registListeners();
    }

    private void registListeners() {
        fsmObs.addPropertyChangeListener(Events.updateBoard, evt -> {
            this.currentScore.setText("" + this.fsmObs.getScore());
            //this.state.setText(this.fsmObs.getState().toString());

        });
    }

    private void view() {
        setStyle("-fx-padding: 20 0 16 0;");
        setAlignment(Pos.CENTER);
        state = new Label();
        state.setFont(utils.pixelfont);
        state.setStyle("-fx-padding: 0 10 0 0;");

        currentScore = new Label("0");
        currentScore.setFont(utils.pixelfont);
        currentScore.setStyle("-fx-padding: 0 10 0 0;");
        highestScoreLabel = new Label("0");
        highestScoreLabel.setFont(utils.pixelfont);

        VBox hightScoreWrapper = new VBox();
        Label highL= new Label("HIGH SCORE");
        highL.setFont(utils.pixelfontSmall);

        hightScoreWrapper.getChildren().add(highL);
        hightScoreWrapper.getChildren().add(highestScoreLabel);

        VBox score = new VBox();
        Label scoreL = new Label("SCORE");
        scoreL.setFont(utils.pixelfontSmall);
        score.getChildren().add(scoreL);
        score.getChildren().add(currentScore);
        score.setStyle("-fx-padding: 0 60 0 0;");

        getChildren().addAll( score, hightScoreWrapper);
    }
}
