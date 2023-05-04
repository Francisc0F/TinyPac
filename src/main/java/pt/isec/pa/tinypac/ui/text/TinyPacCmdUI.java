package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.utils.Direction;

public class TinyPacCmdUI {
    TinyPacStateMachine fsm;

    public TinyPacCmdUI(TinyPacStateMachine fsm) {
        this.fsm = fsm;

    }


    public void showStateUI() {
        switch (fsm.getState()) {
            case UPDATECURRENTGAMESTATE -> showBoardWithInfo();
            case INITGAMESTATE -> initGame();
            case LOSTGAMESTATE -> lostGame();
            case PAUSEGAMESTATE -> pauseGame();
            case PACMANPOWERFULLSTATE -> pacmanGodMode();
        }
    }

    private void pacmanGodMode() {
        System.out.println("GOD MODE");
        showBoard();
    }

    private void pauseGame() {
        System.out.println("PAUSE");
        System.out.println("W -> UP, S -> DOWN, A -> LEFT, D -> RIGHT");
        showBoard();
    }

    private void lostGame() {
        showBoardWithInfo();
    }

    private void initGame() {
        System.out.println("Press the keys to start");
        System.out.println("W -> UP, S -> DOWN, A -> LEFT, D -> RIGHT");
        showBoard();
    }

    private void showBoard() {
        char[][] env = fsm.getMap();
        for (int y = 0; y < env.length; y++) {
            for (int x = 0; x < env[0].length; x++)
                System.out.print(env[y][x]);
            System.out.println();
        }
    }

    public void showBoardWithInfo() {
        /*ModelLog.getInstance().getLog().forEach(System.out::println);
        ModelLog.getInstance().reset();*/

        System.out.println("Points:" + fsm.getTotalPoints() + " Lifes:" + fsm.getLifesRemaining());
        showBoard();
    }

    public boolean mapKeyToAction(char s) {
        return switch (s) {
            case 'w' -> {
                fsm.registDirection(Direction.UP);
                yield true;
            }
            case 's' -> {
                fsm.registDirection(Direction.DOWN);
                yield true;
            }
            case 'd' -> {
                fsm.registDirection(Direction.RIGHT);
                yield true;
            }
            case 'a' -> {
                fsm.registDirection(Direction.LEFT);
                yield true;
            }
            case 'p' -> {
                fsm.pause();
                yield true;
            }
            case 'l' -> {
                fsm.leave();
                yield false;
            }
            default -> throw new IllegalStateException("mapKeyToAction unexpected value: " + s);
        };
    }
}
