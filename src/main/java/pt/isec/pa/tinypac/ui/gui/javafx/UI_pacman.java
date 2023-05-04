package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class UI_pacman {

    private static final int BLOCK_SIZE = 20;
    private static final int WIDTH = 28;
    private static final int HEIGHT = 31;

    public void buildPacScene(Stage primaryStage){

    }

    public void start(Stage primaryStage, char[][] board) {
        Group group = new Group();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[x].length; x++) {
                Rectangle block = new Rectangle(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                switch (board[y][x]){
                    case 'x' -> block.setFill(Color.BLACK);
                    case 'o' -> block.setFill(Color.GREEN);
                    case 'y' -> block.setFill(Color.GREY);
                    case 'Y' -> block.setFill(Color.GREY);
                    case 'O' -> block.setFill(Color.DARKGREEN);
                    case 'W' -> block.setFill(Color.BLUE);
                    case '@' -> block.setFill(Color.GHOSTWHITE);
                    case '&' -> block.setFill(Color.GREENYELLOW);
                }
                group.getChildren().add(block);
            }
        }
        Scene scene = new Scene(group, WIDTH * BLOCK_SIZE, HEIGHT * BLOCK_SIZE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
