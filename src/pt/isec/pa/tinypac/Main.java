package pt.isec.pa.tinypac;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.ui.text.TinyPacCmdUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        TinyPacStateMachine fsm = new TinyPacStateMachine();
        TinyPacCmdUI ui = new TinyPacCmdUI(fsm);

        setupGameEngine(fsm, ui);
    }


    public static void setupGameEngine(TinyPacStateMachine fsm, TinyPacCmdUI ui ) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.registerClient((g,t) -> {
            if (!fsm.evolve())
                g.stop();
        });

        gameEngine.registerClient((g, t) -> ui.showStateUI());

        gameEngine.start(1000);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int c;
            while ((c = reader.read()) != -1) {
                if (c == '\n')
                    continue;
                //System.out.println("read char: " + (char)c);
                if(!ui.mapKeyToAction((char) c)){
                    gameEngine.stop();
                    System.exit(0);
                    break;
                }
                //showStateUI();
            }
        } catch (IOException ex) {
            System.out.println("Something went wrong");
        }
    }
}
