package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import pt.isec.pa.utils.Direction;

import java.util.Objects;

public class Utils {
    public Image empty = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/empty.png")));
    public Image white = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/white.png")));
    public Image wall = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/wall.png")));
    public Image food = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/food.png")));
    public Image pacmanopen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/pacman-open.png")));
    public Image wrap = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/warp.png")));
    public Image fruit = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/fruit.png")));
    public Image powerfullFruit = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/powerfull-food.png")));
    public Image ghost = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/ghost.png")));
    public Image logoIsec = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logoisec.png")));
    public Font pixelfont = Font.loadFont(getClass().getResourceAsStream("/fonts/Pixelation.ttf"), 24);



    public static final int BLOCK_SIZE = 20;
    public static final int WIDTH = 29;
    public static final int HEIGHT = 31;

    public Circle buildCircle(int x, int y, Color color, double radius) {
        Circle shape = null;
        shape = new Circle(x * Utils.BLOCK_SIZE + 10, y * Utils.BLOCK_SIZE + 10, radius);
        shape.setFill(color);
        return shape;
    }

    public void setPacmanRotation(ImageView imageView, Direction direction) {
        imageView.setRotate(direction.getAngle());
    }


}
