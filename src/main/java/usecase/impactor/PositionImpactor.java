package usecase.impactor;
import entity.Block;
import entity.Player;
import entity.GameData;
import java.util.*;

/**
 * It is an impactor impacting the position of the player
 */
public class PositionImpactor {

    /**
     * moves the player by distance
     * @param data Game data
     * @param distance distance of move, positive front, negative back
     */
    public static boolean relativeMove(GameData data, int distance) {
        // delete player from the list
        boolean isPassStartingpoint = false;
        int oldPosId = data.currentPlayer.getPosition();
        int oldPos = data.getPositionFromId(oldPosId);
        if (oldPos == -1) {
            throw new RuntimeException("No such block");
        }
        data.playerAtPosition.get(oldPos).remove(data.currentPlayer);
        // find new block
        if (oldPos + distance >= data.blocks.size()) isPassStartingpoint = true;
        int newPos = Math.floorMod(oldPos + distance, data.blocks.size());
        int newBlockId = data.blocks.get(newPos).getId();
        // add player on the block
        data.playerAtPosition.get(newPos).add(data.currentPlayer);
        // change player's block
        data.currentPlayer.setPosition(newBlockId);
        return isPassStartingpoint;
    }


    /**
     * change the distance of the player directly
     * @param player player to move
     * @param blockId id of the new block
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
