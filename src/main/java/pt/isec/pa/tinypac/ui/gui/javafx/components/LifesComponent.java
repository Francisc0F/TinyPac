package pt.isec.pa.tinypac.ui.gui.javafx.components;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;

public class LifesComponent extends HBox {

    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils;

    public LifesComponent(TinyPacStateMachineObservable fsmObs, Utils utils){
        this.fsmObs = fsmObs;
        this.utils = utils;
        view();
    }

    private void view(){

        setStyle("-fx-padding: 10px;");
        setAlignment(Pos.CENTER);
        for (int i = 0; i < this.fsmObs.getLifes(); i++) {
            ImageView img = new ImageView(utils.pacmanopen);
            img.setFitWidth(Utils.BLOCK_SIZE);
            img.setFitHeight(Utils.BLOCK_SIZE);
            getChildren().add(img);
        }
    }
}
