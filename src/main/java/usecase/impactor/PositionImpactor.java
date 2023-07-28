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
     * @param player the player to move
     * @param distance distance of move, positive front, negative back
     */
    public static void relativeMove(GameData data, Player player, int distance) {
        // delete player from the list
        int oldPosId = player.getPosition();
        int oldPos = data.getPositionFromId(oldPosId);
        if (oldPos == -1) {
            throw new RuntimeException("No such block");
        }
        data.playerAtPosition.get(oldPos).remove(player);
        // find new block
        int newPos = Math.floorMod(oldPos + distance, data.blocks.size());
        int newBlockId = data.blocks.get(newPos).getId();
        // add player on the block
        data.playerAtPosition.get(newPos).add(player);
        // change player's block
        player.setPosition(newBlockId);
    }


    /**
     * change the distance of the player directly
     * @param player player to move
     * @param blockId id of the new block
     */
    public static void absoluteMove(GameData data, Player player, int blockId) {
        int oldBlockPos = data.getPositionFromId(player.getPosition());
        if (data.playerAtPosition.get(oldBlockPos) == null) {
            throw new RuntimeException("Block with id " + blockId + " does not exist");
        }
        data.playerAtPosition.get(oldBlockPos).remove(player);
        int newBlockPos = data.getPositionFromId(blockId);
        data.playerAtPosition.get(newBlockPos).add(player);
        player.setPosition(blockId);
    }
}
