package usecases.impactors;
import entities.GameData;

/**
 * Represents an impactor that affects the position of a player in the game.
 */
public class PositionImpactor {

    /**
     * Moves the player by the specified distance.
     *
     * @param data     The game data containing the current state of the game.
     * @param distance The distance of the move. Positive distance moves the player forward, negative distance moves the player backward.
     * @return true if the player passes the starting point during the move, false otherwise.
     * @throws RuntimeException if there is no block corresponding to the player's old position.
     */
    public static boolean relativeMove(GameData data, int distance) {
        // Delete player from the old block
        boolean isPassStartingpoint = false;
        int oldPosId = data.currentPlayer.getPosition();
        int oldPos = data.getPositionFromId(oldPosId);
        if (oldPos == -1) {
            throw new RuntimeException("No such block");
        }
        data.playerAtPosition.get(oldPos).remove(data.currentPlayer);

        // Find new block position
        if (oldPos + distance >= data.blocks.size()) isPassStartingpoint = true;
        int newPos = Math.floorMod(oldPos + distance, data.blocks.size());
        int newBlockId = data.blocks.get(newPos).getId();

        // Add player to the new block
        data.playerAtPosition.get(newPos).add(data.currentPlayer);

        // Update player's block position
        data.currentPlayer.setPosition(newBlockId);

        return isPassStartingpoint;
    }


    /**
     * Changes the player's position directly to the specified block.
     *
     * @param data    The game data containing the current state of the game.
     * @param blockId The id of the new block to which the player's position is changed.
     * @throws RuntimeException if the block with the specified id does not exist.
     */
    public static void absoluteMove(GameData data, int blockId) {
        int oldBlockPos = data.getPositionFromId(data.currentPlayer.getPosition());
        if (data.playerAtPosition.get(oldBlockPos) == null) {
            throw new RuntimeException("Block with id " + blockId + " does not exist");
        }
        data.playerAtPosition.get(oldBlockPos).remove(data.currentPlayer);
        int newBlockPos = data.getPositionFromId(blockId);
        data.playerAtPosition.get(newBlockPos).add(data.currentPlayer);
        data.currentPlayer.setPosition(blockId);
    }
}
