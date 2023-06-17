package pt.isec.pa.tinypac.ui.gui.javafx.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;

public class NewRecordFormComponent extends VBox {

    private final Utils utils;
    private final TinyPacStateMachineObservable fsmObs;
    public NewRecordFormComponent(Utils utils, TinyPacStateMachineObservable fsmObs) {
        this.utils = utils;
        this.fsmObs = fsmObs;
        view();
    }

    private void view() {
        Label topLabel = new Label("The Game has Ended. You have reached the top 5");
        topLabel.setFont(utils.pixelfont);
        topLabel.setStyle("-fx-text-fill: white; -fx-padding: 0 0 20 0");
        TextField inputField = new TextField();
        inputField.setFont(utils.pixelfont);
        PacButtonComponent submitButton = new PacButtonComponent("Guardar", utils, Color.BLANCHEDALMOND, Color.ORANGE);
        submitButton.setOnAction(event -> {
            String userInput = inputField.getText();
            fsmObs.saveGame(userInput);
        });

        HBox submitWrapper = new HBox();
        submitWrapper.setAlignment(Pos.CENTER);
        submitWrapper.getChildren().add(submitButton);
        submitWrapper.setPadding(new Insets(10, 0, 0, 0));

        getChildren().addAll(topLabel, inputField, submitWrapper);
    }
}
