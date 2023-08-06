package entities;

/**
 * Represents an interactor for a specific use case in the game.
 * Classes implementing this interface must provide the functionality to interact with a block in the game and modify the game data accordingly.
 */
public interface UseCaseInteractor {
    /**
     * Interacts with the provided block and modifies the game data accordingly.
     *
     * @param block The block to interact with in the game.
     * @param data  The data structure representing the current game state.
     */
    void interact(Block block, GameData data);
}
