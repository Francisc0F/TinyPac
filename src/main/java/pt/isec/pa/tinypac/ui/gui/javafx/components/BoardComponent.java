package pt.isec.pa.tinypac.ui.gui.javafx.components;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.data.Pacman;
import pt.isec.pa.tinypac.model.data.Wrap;
import pt.isec.pa.tinypac.model.data.food.Food;
import pt.isec.pa.tinypac.model.data.food.Fruit;
import pt.isec.pa.tinypac.model.data.food.PowerfullFood;
import pt.isec.pa.tinypac.model.data.ghosts.*;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.utils.Direction;
import pt.isec.pa.utils.MusicPlayer;

public class BoardComponent extends HBox {

    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils;
    private final Group board;

    public BoardComponent(TinyPacStateMachineObservable fsmObs, Utils utils) {
        this.fsmObs = fsmObs;
        this.utils = utils;
        board = new Group();
        view();
        registListeners();
    }

    private void registListeners() {
        fsmObs.addPropertyChangeListener(Events.changedState, evt -> {
            Platform.runLater(this::updateBoard);
        });

        fsmObs.addPropertyChangeListener(Events.levelUpdated, evt -> {
            Platform.runLater(this::updateBoard);
        });
        fsmObs.addPropertyChangeListener(Events.updateBoard, evt -> {
            Platform.runLater(this::updateBoard);
        });

        fsmObs.addPropertyChangeListener(Events.foodEated, evt -> {
            MusicPlayer.playMusic(MusicPlayer.pacman_chomp);
        });

        fsmObs.addPropertyChangeListener(Events.ghostEated, evt -> {
            MusicPlayer.playMusic(MusicPlayer.pacman_eatghost);
        });

        fsmObs.addPropertyChangeListener(Events.fruitEated, evt -> {
            MusicPlayer.playMusic(MusicPlayer.pacman_eatfruit);
        });
    }

    public void updateBoard() {
        char board[][] = this.fsmObs.getMap();
        this.board.getChildren().clear();
        Direction pacmanDirection = this.fsmObs.getDirection();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[x].length; x++) {
                ImageView image = new ImageView();
                image.setX(x * Utils.BLOCK_SIZE);
                image.setY(y * Utils.BLOCK_SIZE);
                image.setPreserveRatio(true);
                image.setFitWidth(Utils.BLOCK_SIZE);
                // image is suffering from antialiasing
                Circle shape = null;
                switch (board[y][x]) {

                    case ' ' -> image.setImage(utils.wall);
                    case 'x' -> image.setImage(utils.wall);


                    case Food.SYMBOL -> {
                        shape = utils.buildCircle(x, y, Color.rgb(255, 204, 0), 3);
                    }
                    case Pacman.SYMBOL -> {
                        image.setImage(utils.pacmanopen);
                        if (pacmanDirection != null) {
                            utils.setPacmanRotation(image, pacmanDirection);
                        }
                    }
                    case Fruit.SYMBOL -> image.setImage(utils.fruit);
                    case PowerfullFood.SYMBOL -> {
                        shape = utils.buildCircle(x, y, Color.rgb(255, 230, 0), 5);
                    }
                    case Wrap.SYMBOL -> image.setImage(utils.wrap);
                    case Ghost.VULNERABLE -> image.setImage(utils.ghost);
                    case Clyde.SYMBOL -> image.setImage(utils.clyde);
                    case Blinky.SYMBOL -> image.setImage(utils.blinky);
                    case Pinky.SYMBOL -> image.setImage(utils.pinky);
                    case Inky.SYMBOL -> image.setImage(utils.inky);
                }
                if (shape != null) {
                   /* shape.setStroke(Color.RED);
                    shape.setStrokeWidth(1);*/
                }

                Pane container = new Pane(shape != null ? shape : image);
                container.setPrefSize(Utils.BLOCK_SIZE, Utils.BLOCK_SIZE);

                //container.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");
                this.board.getChildren().add(container);
            }
        }
    }

    private void view() {
        Pane boardContainer = new Pane(board);
        getChildren().add(boardContainer);
        boardContainer.setStyle("" +
                "-fx-border-color: grey; " +
                "-fx-border-width: 5px; " +
                "-fx-border-style: solid;" +
                "-fx-border-radius: 3px;" +
                "-fx-background-color: lighgrey;" +
                "-fx-effect: dropshadow(gaussian, grey, 3.9, 0.3, 0.3, 0.3);"
        );

        setStyle("" +
                "-fx-background-color: lighgrey;" +
                "-fx-effect: dropshadow(gaussian, grey, 3.9, 0.3, 0.3, 0.3);"
        );
        setAlignment(Pos.CENTER);
    }
}
