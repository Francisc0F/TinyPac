package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TinyPacCmdUI {
    TinyPacStateMachine fsm;

    public TinyPacCmdUI(TinyPacStateMachine fsm) {
        this.fsm = fsm;
        this.setupGameEngine();

    }

    boolean finish = false;

    public void showStateUI() {
        int op;
        switch (fsm.getState()) {
            case UPDATECURRENTGAMESTATE -> show();
            case INITGAMESTATE -> initGame();
        }
    }

    private void initGame() {
        show();
    }

    public void show() {
        /*ModelLog.getInstance().getLog().forEach(System.out::println);
        ModelLog.getInstance().reset();*/

        char[][] env = fsm.getMap();
        System.out.println();
        for (int y = 0; y < env.length; y++) {
            for (int x = 0; x < env[0].length; x++)
                System.out.print(env[y][x]);
            System.out.println();
        }
    }


    public void setupGameEngine() {
        this.fsm.getGameEngine().registerClient((g,t) -> {
            if (!fsm.evolve())
                g.stop();
        });

        this.fsm.getGameEngine().registerClient((g, t) -> show());
        this.fsm.getGameEngine().start(1000);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int c;
            while ((c = reader.read()) != -1) {
                if (c == '\n')
                    continue;
                //System.out.println("read char: " + (char)c);
                mapKeyToAction((char) c);
                //showStateUI();
            }
        } catch (IOException ex) {
            System.out.println("Something went wrong");
        }
    }

    public void mapKeyToAction(char s) {
        switch (s) {
            case 'w' -> fsm.registDirection(Direction.UP);
            case 's' -> fsm.registDirection(Direction.DOWN);
            case 'd' -> fsm.registDirection(Direction.RIGHT);
            case 'a' -> fsm.registDirection(Direction.LEFT);
        }
    }
}
