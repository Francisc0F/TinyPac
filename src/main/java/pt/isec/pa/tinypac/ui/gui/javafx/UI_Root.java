package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.isec.pa.tinypac.model.TinyPac;
import pt.isec.pa.tinypac.ui.gui.javafx.views.InitGameStateViewStack;
import pt.isec.pa.tinypac.ui.gui.javafx.views.LostLifeStateViewStack;
import pt.isec.pa.tinypac.ui.gui.javafx.views.PacmanPowefullStateViewStack;
import pt.isec.pa.tinypac.ui.gui.javafx.views.UpdateCurrentGameStateViewStack;
import pt.isec.pa.utils.Direction;

public class UI_Root extends BorderPane {

    Utils utils = new Utils();
    TinyPac model;

    public enum KeyPress {
        UP(KeyCode.UP),
        DOWN(KeyCode.DOWN),
        LEFT(KeyCode.LEFT),
        RIGHT(KeyCode.RIGHT),
        SPACE(KeyCode.SPACE),
        ENTER(KeyCode.ENTER);

        private final KeyCode keyCode;

        KeyPress(KeyCode keyCode) {
            this.keyCode = keyCode;
        }
    }

    public UI_Root(TinyPac model) {
        this.model = model;
        buildInitialScreen();

    }

    private void buildInitialScreen() {

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        root.setSpacing(10);
        root.setPadding(new Insets(20));

        ImageView logo = new ImageView(utils.logoIsec);
        logo.setFitWidth(200);
        logo.setPreserveRatio(true);

        Text label = new Text("Welcome to Button Screen");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Create the label
        Text subtitle = new Text("TinyPac 2023 LEI-PL DEIS-ISEC-IPC");
        Text subtitle2 = new Text("Francisco Ferreira");
        Text subtitle3 = new Text("2019113494");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Create the buttons
        Button startGame = new Button("Iniciar Jogo");
        Button button2 = new Button("Consultar Top 5");
        Button button3 = new Button("Sair");

        startGame.setOnAction(event -> {
            BuildStateView();
        });
        startGame.setMinWidth(150);
        button2.setMinWidth(150);
        button3.setMinWidth(150);
        // Add the elements to the VBox
        root.getChildren().addAll(logo, label, subtitle, subtitle2, subtitle3, startGame, button2, button3);
        setWidth(700);
        setCenter(root);
    }

    private void BuildStateView() {
        InitGameStateViewStack start = new InitGameStateViewStack(this.model.getFsmObs());
        UpdateCurrentGameStateViewStack updateCurrentGameStateViewStack = new UpdateCurrentGameStateViewStack(this.model.getFsmObs());
        LostLifeStateViewStack lostLifeStateViewStack = new LostLifeStateViewStack(this.model.getFsmObs());
        PacmanPowefullStateViewStack pacmanPowefullStateViewStack = new PacmanPowefullStateViewStack(this.model.getFsmObs());

        StackPane stackPane = new StackPane(
                start,
                updateCurrentGameStateViewStack,
                lostLifeStateViewStack,
                pacmanPowefullStateViewStack
        );
        stackPane.setAlignment(Pos.CENTER);
        setCenter(stackPane);
    }


    public boolean mapKeyToAction(KeyPress keyPress) {
        return switch (keyPress) {
            case UP -> {
                this.model.getFsmObs().registDirection(Direction.UP);
                yield true;
            }
            case DOWN -> {
                this.model.getFsmObs().registDirection(Direction.DOWN);
                yield true;
            }
            case RIGHT -> {
                this.model.getFsmObs().registDirection(Direction.RIGHT);
                yield true;
            }
            case LEFT -> {
                this.model.getFsmObs().registDirection(Direction.LEFT);
                yield true;
            }
            case ENTER -> {
                //fsm.pause();
                yield true;
            }
            case SPACE -> {
                //fsm.leave();
                yield false;
            }
        };
    }

    private MenuBar menus() {
        MenuBar menuBar = new MenuBar();

        // menu Jogo
        Menu jogoMenu = new Menu("_Jogo");  // underscore: abre com alt + j


        MenuItem interromperJogo = new MenuItem("Interromper Jogo");
        ///interromperJogo.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

        MenuItem sairAplicacao = new MenuItem("Sair");
        //sairAplicacao.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        jogoMenu.getItems().addAll(interromperJogo, new SeparatorMenuItem(), sairAplicacao);

        interromperJogo.setOnAction((e) -> {
            //fsm.interrompeJogo();
            Stage janela = (Stage) this.getScene().getWindow();
            fireEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSE_REQUEST));
        });

        sairAplicacao.setOnAction((e) -> {
            Stage janela = (Stage) this.getScene().getWindow();
            fireEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSE_REQUEST));
        });

/*
        // menu ajuda
        Menu ajudaMenu = new Menu("_Ajuda");

        MenuItem comoJogarMI = new MenuItem("Como jogar");
        comoJogarMI.setAccelerator(new KeyCodeCombination(KeyCode.J, KeyCombination.CONTROL_DOWN));

        MenuItem acercaMI = new MenuItem("Acerca");
        acercaMI.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));

        ajudaMenu.getItems().addAll(comoJogarMI, acercaMI);

        comoJogarMI.setOnAction(new AjudaListener());
        acercaMI.setOnAction(new AcercaListener());
*/
        menuBar.getMenus().addAll(jogoMenu);
        return menuBar;
    }
}
