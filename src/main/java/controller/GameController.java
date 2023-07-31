package controller;

import entity.*;
import useCases.*;
import useCases.impactor.PositionImpactor;
import useCases.impactor.PropertyImpactor;
import useCases.impactor.StatusImpactor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameController {


    private final GameData data;
    public GameController(GameData data)
    {
        this.data = data;
    }


    /**
     * remove not playable players;
     **/
    public void updatePlayablePlayer()
    {   for(int i = 0; i < data.currentPlayers.size(); i++)
        {
            Player player = data.currentPlayers.get(i);
            if(!StatusChecker.isPlayable(player))
            {
                i--;
                data.playerNum -= 1;
                data.currentPlayers.remove(player);
                ArrayList<Property> properties = player.getProperties();
                for (Property property: properties) {
                    PropertyImpactor.changeOwner(null,property);
                    PropertyImpactor.downgradeToZero(property);
                }
            }
        }

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
        if(data.currentPlayers.size() <= 1)
        {
            return true;
        }
        if(getMaxMoneyPlayer().getMoney() >= data.MAXMONEY)
            return true;
        return data.gameRounds > data.MAXROUNDS;
    }
    public boolean isCurrentMovable()
    {

        return StatusChecker.isMovable(data.currentPlayer);
    }
    public void settleOneRound()
    {
        data.gameRounds += 1;
        StatusImpactor.changeStatus(data.currentPlayer);

        for(int i =1 ;i<= data.currentPlayers.size();i++)
        {
            data.currentPlayerIndex = (data.currentPlayerIndex + 1) % data.currentPlayers.size();
            data.currentPlayer = data.currentPlayers.get(data.currentPlayerIndex);
            if (StatusChecker.isPlayable(data.currentPlayer))
                break;
        }

        updatePlayablePlayer();
        if (data.currentPlayers.size() ==0) return;
        data.currentPlayerIndex = data.currentPlayers.indexOf(data.currentPlayer);



        // some summary for each round
        System.out.println("\n======================================");
        System.out.println("Round " + data.gameRounds);
        for (Player player: data.currentPlayers) {
            System.out.println("Player: " + player.getUserId() + " Tbucks: " + player.getMoney());
        }
        System.out.println("======================================\n");

    }
    public int randomDice()
    {
        Random random = new Random();


        int min = 1;
        int max = 6;
        return random.nextInt(max - min + 1) + min;
    }
    public boolean playerRelativeWalk()
    {

        boolean flag = PositionImpactor.relativeMove(data, randomDice());
        System.out.println("Player " + data.currentPlayer.getUserId() + " moved to " + data.currentPlayer.getPosition());
        return flag;
    }
    public void playerAbsoluteWalk(int BlockId)
    {

        PositionImpactor.absoluteMove(data, BlockId);
        System.out.println("Player " + data.currentPlayer.getUserId() + " moved to " + data.currentPlayer.getPosition());
    }


    public void finish() {
        if (data.currentPlayers.size() == 0) {
            System.out.println("All player has broken up, there is no winner!");
        }

        if (data.currentPlayers.size() == 1) {
            System.out.println("Game over! Winner is Player " + data.currentPlayers.get(0).getUserId());
            return;
        }

        Player winner = data.currentPlayers.get(0);
        for (int i = 0; i < data.currentPlayers.size(); i++) {
            if (data.currentPlayers.get(i).getMoney() > winner.getMoney()) {
                winner = data.currentPlayers.get(i);
            }
        }
        StringBuilder message = new StringBuilder("Game over! winner is ");
        for (Player player: data.currentPlayers) {
            if (player.getMoney() == winner.getMoney())
                message.append("Player ").append(player.getUserId()).append(" and ");
        }
        message.delete(message.length()-5, message.length());
        message.append("!");
        System.out.println(message);
    }

}
