package pt.isec.pa.tinypac.ui.gui.javafx.views;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;

public class UpdateCurrentGameStateViewStack extends BorderPane {
    private Label scoreLabel;
    private TinyPacStateMachineObservable fsmObs;

    public UpdateCurrentGameStateViewStack(Label scoreLabel, Group board, TinyPacStateMachineObservable fsmObs) {
        super();
        this.fsmObs = fsmObs;
        buildView(scoreLabel, board);
        createObservables();
        setPanelVisible();
    }

    private void buildView(Label scoreLabel, Group board) {
        VBox centralCol = new VBox();
        HBox hgroup = new HBox();

        centralCol.setAlignment(Pos.CENTER);
        hgroup.setAlignment(Pos.CENTER);

        //hgroup.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");
        centralCol.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");


        Button pause = new Button("Pause");
        Button save = new Button("Save");
        save.setFocusTraversable(false);
        pause.setFocusTraversable(false);


        hgroup.getChildren().addAll(scoreLabel, pause, save);

        centralCol.getChildren().add(hgroup);

        Pane boardContainer = new Pane(board);
        board.layoutXProperty().bind(boardContainer.widthProperty().subtract(board.getBoundsInParent().getWidth()).divide(2));
        board.layoutYProperty().bind(boardContainer.heightProperty().subtract(board.getBoundsInParent().getHeight()).divide(2));


        HBox contentCenteredWrapper = new HBox(boardContainer);

        boardContainer.setStyle("" +
                "-fx-border-color: grey; " +
                "-fx-border-width: 5px; " +
                "-fx-border-style: solid;" +
                "-fx-border-radius: 3px;" +
                "-fx-background-color: lighgrey;" +
                "-fx-effect: dropshadow(gaussian, grey, 3.9, 0.3, 0.3, 0.3);"
        );

        contentCenteredWrapper.setStyle("" +
                "-fx-background-color: lighgrey;" +
                "-fx-effect: dropshadow(gaussian, grey, 3.9, 0.3, 0.3, 0.3);"
        );
        contentCenteredWrapper.setAlignment(Pos.CENTER);

        centralCol.getChildren().add(contentCenteredWrapper);
        this.getChildren().add(centralCol);
    }

    private void createObservables() {
        fsmObs.addPropertyChangeListener(Events.updateBoard, evt -> {
            setPanelVisible();
        });
    }

    private void setPanelVisible(){
        this.setVisible(this.fsmObs.getState() == TinyPacState.UPDATECURRENTGAMESTATE);
    }
}
