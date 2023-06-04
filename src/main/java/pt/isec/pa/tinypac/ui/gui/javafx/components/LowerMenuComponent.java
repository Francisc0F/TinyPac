package pt.isec.pa.tinypac.ui.gui.javafx.components;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;

public class LowerMenuComponent extends HBox {

    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils;

    public LowerMenuComponent(TinyPacStateMachineObservable fsmObs, Utils utils){
        this.fsmObs = fsmObs;
        this.utils = utils;
        view();
    }

    private void view(){
        setAlignment(Pos.CENTER);
        PacButtonComponent menuBtn = new PacButtonComponent("Menu", utils, Color.YELLOW, Color.ORANGE);
        menuBtn.setOnAction(event -> {
            fsmObs.pause();
        });

        getChildren().add(menuBtn);
    }
}
