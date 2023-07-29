package Controller;

import entity.*;
import usecase.*;
import usecase.impactor.PositionImpactor;
import usecase.impactor.PropertyImpactor;
import usecase.impactor.StatusImpactor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameController {


    private final GameData data;
    public GameController(GameData data)
    {
        this.data = data;
    }
    public void updatePlayablePlayer()
            /**
             * remove not playable players;
            * */
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
        if (data.gameRounds % data.currentPlayers.size() == 0) {
            System.out.println("\n=====================================" +
                    "\nRound " + data.gameRounds / 4);
            for (Player player: data.currentPlayers) {
                System.out.println("Player: " + player.getUserId() + " Tbucks: " + player.getMoney());
            }
            System.out.println();
        }
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


    public void interactWithPPT(Property currentBlock)
    {

        // player is owner
        if (OwnerIdentifier.isOwner(data.currentPlayer, (Property) currentBlock)) {
            OwnerPropertyUseCase.run(data.currentPlayer, (Property) currentBlock);
        }
        // player is not owner
        else {
            PasserbyUseCase.PasserbyArrival(data.currentPlayer, (Property) currentBlock, ((Property) currentBlock).getOwner());
            System.out.println("Player" + data.currentPlayer.getUserId() + " passed by property "
                    + currentBlock.getId() + " and paid tax, current tbucks " + data.currentPlayer.getMoney());
        }
    }
    public  void  interactWithEC(ExamCenter currentBlock)
    {
        // player will be stopped for 2 rounds
        StatusImpactor.changeStatus(data.currentPlayer,"movable",-2);
        System.out.println("Player " + data.currentPlayer.getUserId() + " is in the exam center");
        //UI: update current player movable status
    }
    public  void  interactWithTTC(TTCStation currentBlock)
    {
        System.out.println("Enter a block id: ");
        Scanner scanner = new Scanner(System.in);
        int blockID;
        while (true) {
            try {
                blockID = scanner.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid id, try again: ");
                continue;
            }
            if (data.getBlockFromId(blockID).getBlockName().equals("ttcstation")) {
                System.out.println("Invalid move, try again: ");
                continue;
            }
            break;
        }

        PositionImpactor.absoluteMove(data,blockID);
        //UI: absoluteMove
        System.out.println("Player " + data.currentPlayer.getUserId() + " moved to " + data.currentPlayer.getPosition());
        Block newBlock = data.getBlockFromId(blockID);
        switch (newBlock.getBlockName()) {
            case "property":
                interactWithPPT((Property) newBlock);
                break;
            case "destiny":
                interactWithDestiny((Destiny) newBlock);
                break;
            case "examcenter":
                interactWithEC((ExamCenter) newBlock);
                break;
        }
    }
    public void interactWithDestiny(Destiny currentBlock)
    {
        DestinyCard card = DestinyCardChooser.chooseCard(currentBlock);
        System.out.println(DestinyCardExecutor.executeCard(data, data.currentPlayer, card));
        // UI: display message
    }

    public void finish() {
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
