/*
todo check this lanterna

package pt.isec.pa.organisms.ui;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import pt.isec.pa.organisms.gameengine.IGameEngine;
import pt.isec.pa.organisms.gameengine.IGameEngineEvolve;
import pt.isec.pa.organisms.model.EnvironmentManager;
import pt.isec.pa.organisms.model.data.Evolver;
import pt.isec.pa.organisms.model.data.Virus;

import java.io.IOException;

public class OrganismsLanternaUI implements IGameEngineEvolve {
    EnvironmentManager environment;
    Screen screen;

    public OrganismsLanternaUI(EnvironmentManager environment) throws IOException {
        this.environment = environment;
        screen = new DefaultTerminalFactory().createScreen();
        screen.setCursorPosition(null);
        show();
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        try {
            show();
            KeyStroke key = screen.pollInput();
            if (environment.onlyOneSpecies() ||
                    ( key != null &&
                       (key.getKeyType() == KeyType.Escape ||
                       (key.getKeyType() == KeyType.Character &&
                               key.getCharacter().equals('q'))))
            ){
                gameEngine.stop();
                screen.close();
            }
        } catch (IOException e) { }
    }

    private void show() throws IOException {
        char[][] env = environment.getEnvironment();
        screen.startScreen();
        for (int y = 0; y < env.length; y++) {
            for (int x = 0; x < env[0].length; x++) {
                TextColor tc = switch(env[y][x]) {
                    case Virus.SYMBOL -> TextColor.ANSI.WHITE;
                    case Evolver.SYMBOL -> TextColor.ANSI.YELLOW;
                    default -> TextColor.ANSI.BLACK;
                };
                TextColor bc = switch(env[y][x]) {
                    case Virus.SYMBOL -> TextColor.ANSI.RED;
                    case Evolver.SYMBOL -> TextColor.ANSI.BLUE;
                    default -> TextColor.ANSI.WHITE;
                };
                screen.setCharacter(x,y, TextCharacter.fromCharacter(env[y][x],tc,bc)[0]);
            }
        }
        screen.refresh();
    }
}
 */

import org.junit.jupiter.api.Test;
import pt.isec.pa.tinypac.model.data.MazeElement;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.utils.Direction;

import static org.junit.jupiter.api.Assertions.*;

class TinyPacTest {

    @Test
    void testReadMaze() {
        MapController game = new MapController();
        game.setCurrentPacmanDirection(Direction.RIGHT);

        game.evolve();


    }
}