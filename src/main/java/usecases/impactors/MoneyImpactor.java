package usecases.impactors;

import entities.Player;
import presenters.InputPresenter;
import presenters.OutputPresenter;
import presenters.PlayerInfoPanel;

/**
 * Represents an impactor that affects the money attribute of a player in the game.
 */
public class MoneyImpactor {

    /**
     * Deducts the specified amount of money from the player.
     * Updates the player's money and the player info panel with the resultant savings.
     *
     * @param price The amount of money to deduct. Positive value represents deduction, negative value represents increase.
     * @param giver The player from whom the money is deducted.
     */
    public static void deduct(int price, Player giver){
        giver.setMoney(giver.getMoney() - price);

        PlayerInfoPanel.updatePanel(giver.getUserId(), giver.getMoney());
        if (giver.getMoney() < 0) {
            StatusImpactor.changeStatus(giver, "playable", -1);
        }
    }

    /**
     * Trades the specified amount of money between two players.
     * Deducts the money from the giver and adds it to the receiver.
     * Updates the player info panels of both players with the resultant savings.
     *
     * @param price    The amount of money to trade.
     * @param giver    The player from whom the money is deducted.
     * @param receiver The player who receives the money.
     */
    public static void trade(int price, Player giver, Player receiver) {
        MoneyImpactor.deduct(price, giver);
        receiver.setMoney(receiver.getMoney() + price);
        PlayerInfoPanel.updatePanel(receiver.getUserId(), receiver.getMoney());
    }
}
