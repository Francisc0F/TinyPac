import org.junit.jupiter.api.Test;
import pt.isec.pa.tinypac.model.data.MazeElement;
import pt.isec.pa.tinypac.model.data.TinyPac;

import static org.junit.jupiter.api.Assertions.*;

class TinyPacTest {

    @Test
    void testReadMaze() {
        TinyPac game = new TinyPac();

        try {
            game.loadMap("level01.txt");
            for (int i = 0; i < game.getMaze().length; i++) {
                for (int j = 0; j < game.getMaze()[i].length ; j++) {
                        System.out.print(game.getMaze()[i][j]);
                }
                System.out.print("\n");
            }

            MazeElement item = game.getMazeItem(3, 4);
            System.out.print('\n');
            System.out.print(item.getSymbol());


           /* item = game.getMazeItem(3, 5);
            assertTrue(item.isPowerFullFood());*/
        } catch (Exception ignored) {

        }
    }
}