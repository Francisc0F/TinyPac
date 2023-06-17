package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.EvolveEvent;
import pt.isec.pa.utils.Direction;

/**
 * The ITinyPacState interface represents possible actions in the in each respective state game.
 * It provides methods to control and manage the game state.
 */
public interface ITinyPacState {

    /**
     * Evolves the game state by one step.
     *
     * @return The EvolveEvent representing the changes in the game state after evolution.
     */
    EvolveEvent evolve();

    /**
     * Pauses the game.
     *
     * @return The EvolveEvent representing the changes in the game state after pausing.
     */
    EvolveEvent pause();

    /**
     * Saves the current game state with the specified name for the top 5 list.
     *
     * @param name The name to be given to the saved game state.
     * @return True if the game state is successfully saved, false otherwise.
     */
    boolean save(String name);

    /**
     * Resumes the paused game.
     * can only be executed in the pause state
     */
    void resume();

    /**
     * Saves the current game state.
     */
    void saveCurrentGame();

    /**
     * Retrieves the current game state.
     *
     * @return The TinyPacState representing the current game state.
     */
    TinyPacState getState();

    /**
     * Registers the specified direction for the player's movement.
     * Required in for init game state to start a movement and for new level state.
     *
     * @param direction The direction to be registered.
     * @return The EvolveEvent representing the changes in the game state after registering the direction.
     */
    EvolveEvent registerDirection(Direction direction);

}