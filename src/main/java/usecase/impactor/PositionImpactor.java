package usecase.impactor;
import entity.Block;
import entity.Player;
import java.util.*;

/**
 * It is an impactor impacting the position of the player
 */
public class PositionImpactor {
    private final Block[] blocks;                           // all the blocks in order
    private final HashMap<Integer, Integer> idToBlockPos;   // map from ID to block positions in <blocks>
    private final HashMap<Integer, List<Player>> position;  // map from each block id to a list of players currently on th block

    /**
     * Constructor of PositionImpactor, creates the position. The position
     * is a loop, the order is the same as the order in `newBlocks`, initially
     * all the players will be in the zeroth block.
     *
     * @param newBlocks A list of all the blocks with correct order.
     * @param players All the players of this game.
     */
    public PositionImpactor(Block[] newBlocks, Player[] players) {
        this.blocks = new Block[newBlocks.length];
        this.idToBlockPos = new HashMap<>();
        this.position = new HashMap<>();
        int id;
        int i = 0;
        for(Block block: newBlocks) {
            this.blocks[i] = block;
            id = block.getId();
            idToBlockPos.put(id, i++);
            position.put(id, new ArrayList<>());
        }

        int originId = this.blocks[0].getId();
        for (Player player: players) {
            player.setPosition(originId);
            this.position.get(originId).add(player);
        }
    }


    /**
     * given ID get the block with that ID
     * @param blockId id of the block
     */
    public Block getBlockFromId(int blockId) {
        return this.blocks[this.idToBlockPos.get(blockId)];
    }


    /**
     * get a list of players that are currently on this block
     */
    public List<Player> getPlayersOnBlock(Block block) {
        return this.position.get(block.getId());
    }


    /**
     * get a list of players that are currently on the block with this blockId
     */
    public List<Player> getPlayersOnBlock(int blockId) {
        return this.position.get(blockId);
    }

    /**
     * moves the player by distance
     * @param player the player to move
     * @param distance distance of move, positive front, negative back
     */
    public void relativeMove(Player player, int distance) {
        // delete player from the list
        int oldPosId = player.getPosition();
        this.position.get(oldPosId).remove(player);
        // find new block
        int oldPos = idToBlockPos.get(oldPosId);
        int newPos = Math.floorMod(oldPos + distance, this.blocks.length);
        int newBlockId = blocks[newPos].getId();
        // add player on the block
        this.position.get(newBlockId).add(player);
        // change player's block
        player.setPosition(newBlockId);
    }


    /**
     * change the distance of the player directly
     * @param player player to move
     * @param blockId id of the new block
     */
    public void absoluteMove(Player player, int blockId) {
        this.position.get(player.getPosition()).remove(player);
        this.position.get(blockId).add(player);
        player.setPosition(blockId);
    }
}
