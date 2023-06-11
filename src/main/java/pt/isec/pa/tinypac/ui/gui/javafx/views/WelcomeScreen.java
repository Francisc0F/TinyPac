package pt.isec.pa.tinypac.ui.gui.javafx.views;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.SavedGame;
import pt.isec.pa.tinypac.model.TinyPac;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.tinypac.ui.gui.javafx.components.*;
import pt.isec.pa.tinypac.ui.gui.javafx.views.states.*;

public class WelcomeScreen extends VBox {
    private final Utils utils = new Utils();
    private final TinyPac model;

    public WelcomeScreen(TinyPac model) {
        super();
        this.model = model;
        buildView();
    }

    private void buildView() {
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(20));

        ImageView logo = new ImageView(utils.logoIsec);
        logo.setFitWidth(200);
        logo.setPreserveRatio(true);

        Text label = new Text("Welcome TinyPac");
        label.setFont(utils.pixelfont);

        // Create the label
        Text subtitle = new Text("TinyPac 2023 LEI-PL DEIS-ISEC-IPC");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        Text subtitle2 = new Text("Francisco Ferreira");
        subtitle2.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        Text subtitle3 = new Text("2019113494");
        subtitle3.setFont(utils.pixelfont);


        // Create the buttons
        Button startGame = new PacButtonComponent("Iniciar Jogo", utils, Color.GREENYELLOW, Color.ORANGE);
        Button button2 =new PacButtonComponent("Consultar Top 5", utils, Color.BLANCHEDALMOND, Color.ORANGE);
        Button button3 = new PacButtonComponent("Sair", utils, Color.LIGHTCORAL, Color.ORANGE);

        startGame.setOnAction(event -> {
            SavedGame savedGame= this.model.getFsmObs().getSavedGame();
            if(savedGame != null){
                showDialog(savedGame);
                return;
            }
            BuildStateMachineViews();
        });

        button2.setOnAction(event -> {
            BuildTop5Screen();
        });

        button3.setOnAction(event -> {
            Window window = button3.getScene().getWindow();
            if (window instanceof Stage) {
                WindowEvent closeRequest = new WindowEvent((Stage) window, WindowEvent.WINDOW_CLOSE_REQUEST);
                window.fireEvent(closeRequest);
            }
        });
        startGame.setMinWidth(150);
        button2.setMinWidth(150);
        button3.setMinWidth(150);

        getChildren().addAll(logo, label, subtitle, subtitle2, subtitle3, startGame, button2, button3);
    }

    private void BuildTop5Screen() {
        ((BorderPane)getParent()).setCenter(new Top5Screen(model));
    }

    private void BuildStateMachineViews() {
        InitGameStateViewStack start = new InitGameStateViewStack(model);
        UpdateCurrentGameStateViewStack updateCurrentGameStateViewStack = new UpdateCurrentGameStateViewStack(model);
        LostLifeStateViewStack lostLifeStateViewStack = new LostLifeStateViewStack(model);
        PacmanPowefullStateViewStack pacmanPowefullStateViewStack = new PacmanPowefullStateViewStack(model);
        PauseGameStateViewStack pauseGameStateViewStack = new PauseGameStateViewStack(model);
        LostGameStateViewStack lostGameStateViewStack = new LostGameStateViewStack(model);
        NewLevelStateViewStack newLevelStateViewStack = new NewLevelStateViewStack(model);

        StackPane stackPane = new StackPane(
                start,
                updateCurrentGameStateViewStack,
                lostLifeStateViewStack,
                pacmanPowefullStateViewStack,
                pauseGameStateViewStack,
                lostGameStateViewStack,
                newLevelStateViewStack
        );
        stackPane.setAlignment(Pos.CENTER);

        ((BorderPane)getParent()).setCenter(stackPane);
    }


    private void showDialog(SavedGame savedGame) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Saved Game");
        alert.setHeaderText("We have detected and game saved.");
        alert.setContentText("You want to load the saved game?");

        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("No, start new game", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        EventHandler<ActionEvent> okHandler = event -> {
            this.model.setSavedGame(savedGame.getData());
            BuildStateMachineViews();
        };

        EventHandler<ActionEvent> cancelHandler = event -> {
            BuildStateMachineViews();
        };

        alert.setOnHidden(event -> {
            ButtonType result = alert.getResult();
            if (result == okButton) {
                okHandler.handle(null);
            } else if (result == cancelButton) {
                cancelHandler.handle(null);
            }
        });

        alert.showAndWait();
    }
}
