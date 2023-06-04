package pt.isec.pa.tinypac.ui.gui.javafx.components;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;


public class PacButtonComponent extends Button {
    public PacButtonComponent(String text, Utils utils, Color color, Color hover){
        super(text);
        setFocusTraversable(false);
        setFont(utils.pixelfont);

        BackgroundFill backgroundFill = new BackgroundFill(color, new CornerRadii(10), javafx.geometry.Insets.EMPTY);
        setBackground(new Background(backgroundFill));
        String btnStyle = "-fx-border-color: orange; -fx-border-radius:10px;  -fx-border-width: 1px; -fx-border-style: solid;";
        setStyle(btnStyle);

        // Add hover effect using CSS
        setOnMouseEntered(event -> {
            setBackground(new Background(new BackgroundFill(hover, new CornerRadii(10), Insets.EMPTY)));
            setStyle("-fx-border-color: black; -fx-border-radius:10px;  -fx-border-width: 1px; -fx-border-style: solid;");
        });

        setOnMouseExited(event -> {
            setBackground(new Background(backgroundFill));
            setStyle(btnStyle);
        });

        // Set mouse pointer cursor
        setCursor(Cursor.HAND);

        // Set button text color
        setTextFill(Color.BLACK);
    }
}
