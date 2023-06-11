package pt.isec.pa.tinypac.ui.gui.javafx.views;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;
import pt.isec.pa.tinypac.model.SavedGame;
import pt.isec.pa.tinypac.model.TinyPac;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.tinypac.ui.gui.javafx.components.PacButtonComponent;
import pt.isec.pa.tinypac.ui.gui.javafx.views.states.*;

import java.util.ArrayList;

public class Top5Screen extends VBox {
    private final TinyPacStateMachineObservable fsmObs;
    private static final Utils utils = new Utils();
    private final TinyPac model;

    public Top5Screen(TinyPac model) {
        super();
        this.model = model;
        this.fsmObs = model.getFsmObs();
        buildView();
    }

    private void buildView() {
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(20));
        setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");
        Text label = new Text("Top 5");
        label.setFont(utils.pixelfont);

        ArrayList<SavedGame> items = fsmObs.getTop5();

        ListView<SavedGame> listView = new ListView<>(FXCollections.observableArrayList(items));
        listView.setPrefWidth(600);
        listView.setCellFactory(param -> new ScoreItemCell());

        Button backBtn = new PacButtonComponent("Voltar", utils, Color.LIGHTCORAL, Color.ORANGE);
        backBtn.setOnAction(event -> {
            ((BorderPane) getParent()).setCenter(new WelcomeScreen(model));
        });
        backBtn.setMinWidth(150);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(listView);
        hBox.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");
        getChildren().addAll(label, hBox, backBtn);
    }

    public static class ScoreItemCell extends TextFieldListCell<SavedGame> {

        @Override
        public void updateItem(SavedGame item, boolean empty) {
            super.updateItem(item, empty);
            setFont(utils.pixelfontSmall);

            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getUsername() + " - Score: " + item.getPoints());
            }
        }
    }

}
