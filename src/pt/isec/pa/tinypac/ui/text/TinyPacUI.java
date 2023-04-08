package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.utils.PAInput;

public class TinyPacUI {
    TinyPacContext fsm;

    public TinyPacUI(TinyPacContext fsm) {
        this.fsm = fsm;
    }

    /* a) ii
    public void start() {
        int op;
        do {
            System.out.println("Elevator current floor: "+fsm.getCurrentFloor());
            op = PAInput.chooseOption("Elevator","Up","Down","Quit");
            switch (op) {
                case 1 -> fsm.up();
                case 2 -> fsm.down();
            }
        } while (op!=3);
    } */

    // a) iii
    boolean finish = false;

    public void start() {
        int op;
        while (!finish) {
            switch (fsm.getState()) {
                case NEGATIVEFLOOR -> negativeFloorUI();
                case GROUND_FLOOR -> groundFloorUI();
                case FIRST_FLOOR -> firstFloorUI();
                case MENU -> secondFloorUI();
                case THIRD_FLOOR -> thirdFloorUI();
            }
        }
    }

    public void groundFloorUI() {
        System.out.println("Ground Floor");
        switch (PAInput.chooseOption("Elevator", "Up", "Down", "Quit")) {
            case 1 -> fsm.up();
            case 2 -> fsm.down();
            default -> finish = true;
        }
    }

    public void firstFloorUI() {
        System.out.println("1st Floor");
        switch (PAInput.chooseOption("Elevator", "Up", "Down", "Quit")) {
            case 1 -> fsm.up();
            case 2 -> fsm.down();
            default -> finish = true;
        }
    }

    public void secondFloorUI() {
        System.out.println("2nd Floor");

        switch (PAInput.chooseOption("Elevator", "Up", "Down", "Quit")) {
            case 1 -> fsm.up();
            case 2 -> fsm.down();
            default -> finish = true;
        }
    }

    public void thirdFloorUI() {
        System.out.println("3nd Floor");
        if (PAInput.chooseOption("Elevator", "Down", "Quit") == 1)
            fsm.down();
        else
            finish = true;

    }

    public void negativeFloorUI() {
        System.out.println("Negative Floor");
        if (PAInput.chooseOption("Elevator", "Up", "Quit") == 1)
            fsm.up();
        else
            finish = true;

    }
}
