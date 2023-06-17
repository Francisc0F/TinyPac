import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.isec.pa.tinypac.model.data.EvolveEvent;
import pt.isec.pa.tinypac.model.data.MazeElement;
import pt.isec.pa.tinypac.model.data.MapController;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateMachine;
import pt.isec.pa.tinypac.model.fsm.states.TinyPacState;
import pt.isec.pa.utils.Direction;

import static org.junit.jupiter.api.Assertions.*;

public class TinyPacStateMachineTest {

    @Test
    void testInitialState() {
        TinyPacStateMachine stateMachine = new TinyPacStateMachine();
        TinyPacState initialState = stateMachine.getState();
        Assertions.assertEquals(TinyPacState.INITGAMESTATE, initialState);
    }

    @Test
    void testDirectionRegistration() {
        TinyPacStateMachine stateMachine = new TinyPacStateMachine();
        stateMachine.registDirection(Direction.UP);
        Direction registeredDirection = stateMachine.getDirection();
        Assertions.assertEquals(Direction.UP, registeredDirection);
    }

    @Test
    void testPointsCalculation() {
        TinyPacStateMachine stateMachine = new TinyPacStateMachine();
        int totalPoints = stateMachine.getTotalPoints();
        Assertions.assertTrue(totalPoints >= 0);
    }

    @Test
    void testEvolveEventNotNull() {
        TinyPacStateMachine stateMachine = new TinyPacStateMachine();
        stateMachine.registDirection(Direction.UP);
        EvolveEvent evolveEvent = stateMachine.evolve();
        Assertions.assertNotNull(evolveEvent);
    }
}