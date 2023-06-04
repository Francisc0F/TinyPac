package pt.isec.pa.tinypac.ui.gui.javafx.views;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pt.isec.pa.tinypac.model.Events;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.tinypac.ui.gui.javafx.Utils;
import pt.isec.pa.utils.Direction;

public class InitGameStateViewStack extends VBox {
    private final Label scoreLabel;
    private final Group board;
    private final TinyPacStateMachineObservable fsmObs;
    private final Utils utils = new Utils();

    public InitGameStateViewStack(TinyPacStateMachineObservable fsmObs) {
        super();
        this.fsmObs = fsmObs;
        this.scoreLabel = new Label();
        scoreLabel.setFont(utils.pixelfont);
        this.board = new Group();
        buildView();
        createObservables();
        setPanelVisible();
    }

    private void buildView() {
        HBox hgroup = new HBox();
        setAlignment(Pos.CENTER);
        hgroup.setAlignment(Pos.CENTER);

        //hgroup.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");
        setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid;");


        Button pause = new Button("Pause");
        Button save = new Button("Save");
        save.setFocusTraversable(false);
        pause.setFocusTraversable(false);

        pause.setOnAction(event -> {
            fsmObs.pause();
        });


        hgroup.getChildren().addAll(scoreLabel, pause, save);

        getChildren().add(hgroup);

        Pane boardContainer = new Pane(board);
        HBox contentCenteredWrapper = new HBox(boardContainer);

        boardContainer.setStyle("" +
                "-fx-border-color: grey; " +
                "-fx-border-width: 5px; " +
                "-fx-border-style: solid;" +
                "-fx-border-radius: 3px;" +
                "-fx-background-color: lighgrey;" +
                "-fx-effect: dropshadow(gaussian, grey, 3.9, 0.3, 0.3, 0.3);"
        );

        contentCenteredWrapper.setStyle("" +
                "-fx-background-color: lighgrey;" +
                "-fx-effect: dropshadow(gaussian, grey, 3.9, 0.3, 0.3, 0.3);"
        );
        contentCenteredWrapper.setAlignment(Pos.CENTER);

        getChildren().add(contentCenteredWrapper);
    }

    private void createObservables() {
        fsmObs.addPropertyChangeListener(Events.updateBoard, evt -> {
            updateBoard();
            this.scoreLabel.setText("" + this.fsmObs.getScore());
            setPanelVisible();
        });
    }

    private void setPanelVisible() {
        setVisible(this.fsmObs.getState() == TinyPacState.INITGAMESTATE);
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

                    case ' ' -> image.setImage(utils.empty);
                    case 'x' -> image.setImage(utils.wall);
                    case 'o' -> {
                        shape = utils.buildCircle(x, y, Color.rgb(255, 204, 0), 3);
                    }
                    case 'M' -> {
                        image.setImage(utils.pacmanopen);
                        if (pacmanDirection != null) {
                            utils.setPacmanRotation(image, pacmanDirection);
                        }
                    }
                    case 'F' -> image.setImage(utils.fruit);
                    case 'O' -> {
                        shape = utils.buildCircle(x, y, Color.rgb(255, 230, 0), 5);
                    }
                    case 'W' -> image.setImage(utils.wrap);
                    case '%' -> {
                        shape = utils.buildCircle(x, y, Color.PINK, 10);
                    }
                    case '@' -> {
                        shape = utils.buildCircle(x, y, Color.BLUE, 10);
                    }
                    case '&' -> {
                        shape = utils.buildCircle(x, y, Color.RED, 10);
                    }
                    case '#' -> {
                        shape = utils.buildCircle(x, y, Color.GREEN, 10);
                    }
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

}
