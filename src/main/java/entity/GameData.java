package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GameData {
    public final int MAXROUNDS = 100;
    public final int MAXMONEY = 10000;
    public int playerNum;

    public final ArrayList<Block> blocks;                           // all the blocks in order

    public HashMap<Integer, ArrayList<Player>> playerAtPosition; // map from each block id to a list of players currently on th block
    public int gameRounds;
    public int currentPlayerIndex;
    public Player currentPlayer;
    public ArrayList<Player> currentPlayers;    //players that are still playing the game
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

    public Block getBlockFromId(int id)
    {
        for (Block block: this.blocks)
        {
            if (block.getId() == id)
                return block;
        }
        return null;
    }
    public void setCurrentPlayer()
    {
        this.currentPlayer = this.currentPlayers.get(currentPlayerIndex);
    }

}
