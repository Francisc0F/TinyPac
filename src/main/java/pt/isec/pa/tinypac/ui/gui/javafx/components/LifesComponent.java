package pt.isec.pa.tinypac.ui.gui.javafx.components;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;

public class LifesComponent extends HBox {

    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils;

    public LifesComponent(TinyPacStateMachineObservable fsmObs, Utils utils) {
        this.fsmObs = fsmObs;
        this.utils = utils;
        view();
        registListeners();
    }

    private void registListeners() {
        fsmObs.addPropertyChangeListener(Events.lifesUpdated, evt -> {
            Platform.runLater(this::update);
        });
    }

    private void view() {

        setStyle("-fx-padding: 10px;");
        setAlignment(Pos.CENTER);
        update();
    }

    private void update() {
        getChildren().clear();
        for (int i = 0; i < this.fsmObs.getLifes(); i++) {
            ImageView img = new ImageView(utils.pacmanopen);
            img.setFitWidth(Utils.BLOCK_SIZE);
            img.setFitHeight(Utils.BLOCK_SIZE);
            getChildren().add(img);
        }
    }
}
