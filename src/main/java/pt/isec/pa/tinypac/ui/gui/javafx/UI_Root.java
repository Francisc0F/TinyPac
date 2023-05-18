package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.utils.Direction;

import java.util.Objects;


public class UI_Root extends BorderPane {

    Image empty = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/empty.png")));
    Image white = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/white.png")));
    Image wall = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/wall.png")));
    Image food = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/food.png")));
    Image pacmanopen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/pacman-open.png")));
    Image wrap = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/warp.png")));
    Image fruit = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/fruit.png")));
    Image powerfullFruit = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/powerfull-food.png")));
    Image ghost = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/ghost.png")));

    TinyPacStateMachineObservable fsmObservable;

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

    private static final int BLOCK_SIZE = 20;
    private static final int WIDTH = 29;
    private static final int HEIGHT = 31;

    private Group board = new Group();
    private Label scoreLabel = new Label("Normal Food Score: 0");

    public UI_Root(TinyPacStateMachineObservable fsmObservable) {
        this.fsmObservable = fsmObservable;
        buildView();

        fsmObservable.addPropertyChangeListener(Events.updateBoard, evt -> {
            updateBoard();
            this.scoreLabel.setText("Normal Food Score: " + this.fsmObservable.getScore());
        });

        fsmObservable.addPropertyChangeListener(Events.updateScore, evt -> {
        });
    }

    private void buildView() {
        VBox centralCol = new VBox();
        centralCol.setMinWidth(700);
        centralCol.setMinHeight(700);
        HBox hgroup = new HBox();
        Button pause = new Button("Pause");
        Button save =  new Button("Save");


        hgroup.getChildren().addAll(this.scoreLabel);
        centralCol.getChildren().add(hgroup);
        centralCol.getChildren().add(this.board);
        setCenter(centralCol);
    }

    public void updateBoard() {
        char board[][] = this.fsmObservable.getMap();
        this.board.getChildren().removeAll();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[x].length; x++) {
                ImageView block = new ImageView();
                block.setX(x * BLOCK_SIZE);
                block.setY(y * BLOCK_SIZE);
                block.setFitWidth(BLOCK_SIZE);
                block.setFitHeight(BLOCK_SIZE);
                block.setImage(white);
                switch (board[y][x]) {
                    case '?' -> block.setImage(white);
                    case ' ' -> block.setImage(empty);
                    case 'x' -> block.setImage(wall);
                    case 'o' -> block.setImage(food);
                    case 'M' -> block.setImage(pacmanopen);
                    case 'F' -> block.setImage(fruit);
                    case 'O' -> block.setImage(powerfullFruit);
                    case 'W' -> block.setImage(wrap);
                    case '@', '&', '#' -> block.setImage(ghost);
                }
                this.board.getChildren().add(block);
            }
        }
    }

    public boolean mapKeyToAction(KeyPress keyPress) {
        return switch (keyPress) {
            case UP -> {
                fsmObservable.registDirection(Direction.UP);
                yield true;
            }
            case DOWN -> {
                fsmObservable.registDirection(Direction.DOWN);
                yield true;
            }
            case RIGHT -> {
                fsmObservable.registDirection(Direction.RIGHT);
                yield true;
            }
            case LEFT -> {
                fsmObservable.registDirection(Direction.LEFT);
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
