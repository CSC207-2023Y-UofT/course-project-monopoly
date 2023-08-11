package controllers;

import entities.*;
import presenters.InputPresenter;
import presenters.OutputPresenter;
import usecases.*;
import usecases.impactors.PositionImpactor;
import usecases.impactors.PropertyImpactor;
import usecases.impactors.StatusImpactor;

import java.util.ArrayList;
import java.util.Random;

/**
 * GameController class handles the game logic and manages the game state.
 */
public class GameController {


    private final GameData data;


    /**
     * Constructor to initialize the GameController with the given GameData.
     *
     * @param data The GameData instance containing game-related information.
     */
    public GameController(GameData data)
    {
        this.data = data;
    }

    /**
     * Get the current GameData instance.
     *
     * @return The GameData instance.
     */
    public GameData getData() {
        return data;
    }

    /**
     * Removes not playable players from the game.
     */
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

    /**
     * Get the player with the maximum amount of money.
     *
     * @return The Player with the maximum money.
     */
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

    /**
     * Check if the game is over based on certain conditions.
     *
     * @return True if the game is over, otherwise false.
     */
    public boolean isGameOver()
    {
        if(data.currentPlayers.size() <= 1)
        {
            return true;
        }
        if(getMaxMoneyPlayer().getMoney() >= data.MAX_MONEY)
            return true;
        return data.gameRounds > data.MAX_ROUNDS;
    }

    /**
     * Check if the current player can make a move.
     *
     * @return True if the current player is movable, otherwise false.
     */
    public boolean isCurrentMovable()
    {

        return StatusChecker.isMovable(data.currentPlayer);
    }

    /**
     * Progress the game by settling one round of player turns.
     */
    public void settleOneRound()
    {
        data.gameRounds += 1;
        StatusImpactor.changeStatus(data.currentPlayer);

        for(int i = 1; i <= data.currentPlayers.size(); i++)
        {
            data.currentPlayerIndex = (data.currentPlayerIndex + 1) % data.currentPlayers.size();
            data.currentPlayer = data.currentPlayers.get(data.currentPlayerIndex);
            if (StatusChecker.isPlayable(data.currentPlayer))
                break;
        }

        updatePlayablePlayer();
        if (data.currentPlayers.size() == 0) return;
        data.currentPlayerIndex = data.currentPlayers.indexOf(data.currentPlayer);



        // some summary for each round
        System.out.println("\n======================================");
        System.out.println("Round " + data.gameRounds);
        for (Player player: data.currentPlayers) {
            System.out.println("Player: " + player.getUserId() + " Tbucks: " + player.getMoney());
        }
        System.out.println("======================================\n");

    }

    /**
     * Simulate rolling a dice and return the result.
     *
     * @return A random integer representing the result of the dice roll.
     */
    public int randomDice()
    {
        Random random = new Random();


        int min = 1;
        int max = 6;
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Move the current player relative to their current position based on a dice roll.
     *
     * @return True if the movement was successful, otherwise false.
     */
    public boolean playerRelativeWalk()
    {
        int points = randomDice();
        boolean flag = PositionImpactor.relativeMove(data, points);
        String message = "Player " + data.currentPlayer.getUserId() + " moved to " + data.currentPlayer.getPosition();
        OutputPresenter.notifyRandomDice(points);

//        System.out.println("Player " + data.currentPlayer.getUserId() + " moved to " + data.currentPlayer.getPosition());
        return flag;
    }


    /**
     * Finish the game and display the winner or a message if no winner is found.
     */
    public Player finish() {
        if (data.currentPlayers.size() == 0) {
            return null;
//            System.out.println("All player has broken up, there is no winner!");
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
