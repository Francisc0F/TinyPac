package pt.isec.pa.tinypac;

import javafx.application.Application;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.TinyPacStateMachineObservable;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.ui.gui.javafx.MainJFX;
import pt.isec.pa.tinypac.ui.text.TinyPacCmdUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        boolean gui = true;

        if(gui){
            Application.launch(MainJFX.class,args);
        }else{
            setupTextUI();
        }
    }


    public static void setupTextUI() {
        TinyPacStateMachine fsm = new TinyPacStateMachine();
        TinyPacStateMachineObservable fsmOBS = new TinyPacStateMachineObservable(fsm);
        TinyPacCmdUI ui = new TinyPacCmdUI(fsm);

        GameEngine gameEngine = new GameEngine();
        gameEngine.registerClient((g,t) -> {
            fsmOBS.update();
        });

        gameEngine.registerClient((g, t) -> ui.showStateUI());

        gameEngine.start(1000);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int c;
            while ((c = reader.read()) != -1) {
                if (c == '\n')
                    continue;
                if(!ui.mapKeyToAction((char) c)){
                    gameEngine.stop();
                    System.exit(0);
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Something went wrong");
        }
    }

}
