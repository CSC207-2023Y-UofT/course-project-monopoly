package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GameData {
    public final Block[] blocks;                           // all the blocks in order
    public final int MAXROUNDS = 100;
    public final int MAXMONEY = 10000;
    public final int playerNum;


    public HashMap<Integer, List<Player>> playerAtPosition; // map from each block id to a list of players currently on th block
    public int gameRounds;
    public int currentPlayerIndex;
    public ArrayList<Player> currentPlayers;    //players that are still playing the game
    public GameData(int num, Block[] blocks, Player[] players, HashMap<Integer, List<Player>> position)
    {
        this.playerNum = num;
        this.blocks = blocks;
        this.playerAtPosition = position;
        this.currentPlayerIndex = 0;
        this.gameRounds = 0;
        this.currentPlayers = new ArrayList<Player>();
        currentPlayers.addAll(Arrays.asList(players));
    }
    public int getPositionFromId(int id)
    {
        for(int i=0;  i<this.blocks.length;i++)
        {
            if(this.blocks[i].getId()==id)
            {
                return i;
            }
        }
        return -1;
    }

}
