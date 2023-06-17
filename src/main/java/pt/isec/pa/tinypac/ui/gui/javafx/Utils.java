package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import pt.isec.pa.utils.Direction;

import java.io.*;
import java.util.Objects;

public class Utils {
    public Image empty = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/empty.png")));
    public Image wall = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/wall.png")));
    public Image food = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/food.png")));
    public Image pacmanopen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/pacman-open.png")));
    public Image wrap = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/warp.png")));
    public Image fruit = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/fruit.png")));
    public Image powerfullFruit = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/powerfull-food.png")));
    public Image inky = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/inky.png")));
    public Image ghost = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/ghost.png")));
    public Image clyde = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/clyde.png")));
    public Image pinky = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/pinky.png")));
    public Image blinky = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/blinky.png")));
    public Image logoIsec = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logoisec.png")));
    public Font pixelfont = Font.loadFont(getClass().getResourceAsStream("/fonts/Pixelation.ttf"), 24);
    public Font pixelfontSmall = Font.loadFont(getClass().getResourceAsStream("/fonts/Pixelation.ttf"), 16);
    public static final int BLOCK_SIZE = 20;

    public static final String GAMEFILE = "games.bin";
    public static final String SAVEDGAMEONPAUSE = "saved.bin";


    public Circle buildCircle(int x, int y, Color color, double radius) {
        Circle shape = null;
        shape = new Circle(x * Utils.BLOCK_SIZE + 10, y * Utils.BLOCK_SIZE + 10, radius);
        shape.setFill(color);
        return shape;
    }

    public void setPacmanRotation(ImageView imageView, Direction direction) {
        imageView.setRotate(direction.getAngle());
    }

    public static Object clone(Object objectToClone) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(objectToClone);
        oos.flush();

        ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);

        Object clonedObject = ois.readObject();

        oos.close();
        ois.close();

        return clonedObject;
    }

    public static void saveObject(Object object, String fileName)  {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(object);
            System.out.println("Object saved successfully.");
        } catch (Exception ex) {
            System.out.println("Exception ex. " + ex);
        }
    }

    public static Object readObject(String fileName)  {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        } catch (Exception ex) {
            System.out.println(" readObject Exception: " + ex);
            return null;
        }
    }
}
