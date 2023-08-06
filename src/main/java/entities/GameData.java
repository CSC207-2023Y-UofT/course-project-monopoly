package entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the game data that stores the current state of the game.
 */
public class GameData {
    public final int MAX_ROUNDS = 100; // Maximum number of game rounds allowed.
    public final int MAX_MONEY = 10000; // Maximum amount of money a player can have.

    public int playerNum;   // Number of players in the game.

    public final ArrayList<Block> blocks; // List of all the blocks in order.

    public HashMap<Integer, ArrayList<Player>> playerAtPosition; // Mapping from each block ID to a list of players currently on that block.

    public int gameRounds;  // The number of game rounds played.
    public int currentPlayerIndex;  // The index of the current player in the `currentPlayers` list.
    public Player currentPlayer;    // The player whose turn it is currently.

    public ArrayList<Player> currentPlayers;    // List of players who are still playing the game.

    /**
     * Class constructor to initialize the game data.
     *
     * @param num      Number of players in the game.
     * @param blocks   List of all the blocks in order.
     * @param players  List of players participating in the game.
     * @param position Mapping of players to their current positions on the game map.
     */
    public GameData(int num, ArrayList<Block> blocks, ArrayList<Player> players, HashMap<Integer, ArrayList<Player>> position)
    {
        this.playerNum = num;
        this.blocks = blocks;
        this.playerAtPosition = position;
        this.currentPlayerIndex = 0;
        this.gameRounds = 0;
        this.currentPlayers = players;
        this.currentPlayer = currentPlayers.get(currentPlayerIndex);
    }

    /**
     * Gets the position index of a block based on its ID.
     *
     * @param id The ID of the block to find the position index for.
     * @return The position index of the block, or -1 if the block with the given ID is not found.
     */
    public int getPositionFromId(int id)
    {
        for(int i=0;  i<this.blocks.size();i++)
        {
            if(this.blocks.get(i).getId()==id)
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the block object based on its ID.
     *
     * @param id The ID of the block to retrieve.
     * @return The Block object with the given ID, or null if the block is not found.
     */
    public Block getBlockFromId(int id)
    {
        for (Block block: this.blocks)
        {
            if (block.getId() == id)
                return block;
        }
        return null;
    }

    /**
     * Sets the current player to the player whose turn it is based on the `currentPlayerIndex`.
     */
    public void setCurrentPlayer()
    {
        this.currentPlayer = this.currentPlayers.get(currentPlayerIndex);
    }

}
