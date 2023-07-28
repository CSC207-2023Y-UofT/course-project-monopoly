package Controller;

import entity.GameData;
import entity.Player;
import usecase.StatusChecker;
import usecase.impactor.PositionImpactor;
import usecase.impactor.StatusImpactor;

import java.util.Random;

public class GameController {


    private final GameData data;
    public GameController(GameData data)
    {
        this.data = data;
    }
    public void updatePlayablePlayer()
            /**
             * removev not playable players;
            * */
    {
        data.currentPlayers.removeIf(player -> !StatusChecker.isPlayable(player));
    }
    public Player getMaxMoneyPlayer()
    {
        Player MaxMoneyPlayer = data.currentPlayers.get(0);
        for(Player player : data.currentPlayers)
        {
            if (player.getMoney() >= MaxMoneyPlayer.getMoney())
            {
                MaxMoneyPlayer = player;
            }
        }
        return MaxMoneyPlayer;
    }
    public boolean isGameOver()
    {
        if(data.currentPlayers.size()==1)
        {
            return true;
        }
        if(getMaxMoneyPlayer().getMoney() >= data.MAXMONEY)
            return true;
        return data.gameRounds > data.MAXROUNDS;
    }
    public boolean isCurrentMovable()
    {
        Player player = findCunrrentPlayer();
        return StatusChecker.isMovable(player);
    }
    public void settleOneRound()
    {
        data.gameRounds += 1;
        StatusImpactor.changeStatus(findCunrrentPlayer());
        data.currentPlayerIndex = (data.currentPlayerIndex + 1) % data.playerNum;
    }
    public int randomDice()
    {
        Random random = new Random();


        int min = 1;
        int max = 6;
        return random.nextInt(max - min + 1) + min;
    }
    public void playerRelativeWalk()
    {
        Player player = findCunrrentPlayer();
        PositionImpactor.relativeMove(data, player, randomDice());
    }
    public void playerAbsoluteWalk(int BlockId)
    {
        Player player = findCunrrentPlayer();
        PositionImpactor.absoluteMove(data, player, BlockId);
    }
    public Player findCunrrentPlayer()
    {
        return data.currentPlayers.get(data.currentPlayerIndex);
    }
}
