package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

import java.awt.*;
import java.util.Objects;

import static com.sun.javafx.event.EventUtil.fireEvent;

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

    TinyPacStateMachineObservable fsm;

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

    private Group placesGroup;

    public UI_Root(TinyPacStateMachineObservable fsm) {
        this.fsm = fsm;
        menus();
    }


    public void start(Stage primaryStage, char[][] board) {
        placesGroup = new Group();
        updateBoard(board);
        Scene scene = new Scene(placesGroup, WIDTH * BLOCK_SIZE, HEIGHT * BLOCK_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tinypac");

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            KeyPress keyPress = switch (keyCode) {
                case UP -> KeyPress.UP;
                case DOWN -> KeyPress.DOWN;
                case LEFT -> KeyPress.LEFT;
                case RIGHT -> KeyPress.RIGHT;
                case SPACE -> KeyPress.SPACE;
                case ENTER -> KeyPress.ENTER;
                default -> null;
            };

            if (keyPress != null) {
                mapKeyToAction(keyPress);
            }
        });

        primaryStage.show();
    }

    public void updateBoard(char[][] board) {
        placesGroup.getChildren().removeAll();
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
                placesGroup.getChildren().add(block);
            }
        }
    }

    public boolean mapKeyToAction(KeyPress keyPress) {
        return switch (keyPress) {
            case UP -> {
                fsm.registDirection(Direction.UP);
                yield true;
            }
            case DOWN -> {
                fsm.registDirection(Direction.DOWN);
                yield true;
            }
            case RIGHT -> {
                fsm.registDirection(Direction.RIGHT);
                yield true;
            }
            case LEFT -> {
                fsm.registDirection(Direction.LEFT);
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

    private void menus() {
        MenuBar menuBar = new MenuBar();
        //setTop(menuBar);

        // menu Jogo
        Menu jogoMenu = new Menu("_Jogo");  // underscore: abre com alt + j


        MenuItem interromperJogo = new MenuItem("Interromper Jogo");
        ///interromperJogo.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

        MenuItem sairAplicacao = new MenuItem("Sair");
        //sairAplicacao.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        jogoMenu.add(interromperJogo);
       /* SeparatorMenuItem sep = new SeparatorMenuItem();
        jogoMenu.add((MenuItem) sep);*/
        jogoMenu.add(sairAplicacao);

        interromperJogo.addActionListener((e)->{
            //fsm.interrompeJogo();
            Stage janela = (Stage) this.getScene().getWindow();
            fireEvent( new WindowEvent(janela, WindowEvent.WINDOW_CLOSE_REQUEST));
        });

        sairAplicacao.addActionListener((e)-> {
            Stage janela = (Stage) this.getScene().getWindow();
            fireEvent( new WindowEvent(janela, WindowEvent.WINDOW_CLOSE_REQUEST));
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
        menuBar.add(jogoMenu);
    }

}
