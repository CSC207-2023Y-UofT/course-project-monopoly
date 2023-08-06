package usecases.impactors;

import entities.Player;
import presenters.PlayerInfoPanel;

/**
 * It is an impactor impacting the money attribute of the player
 */
public class MoneyImpactor {

    /**
     * change the money of a player
     * (Update panel) Print the resultant savings of the player.
     * @param price the amount of money to change, positive if it is deduction,
     *              negative if it is increase
     * @param giver the player to give money
     */
    public static void deduct(int price, Player giver){
        giver.setMoney(giver.getMoney() - price);

        PlayerInfoPanel.updatePanel(giver.getUserId(), giver.getMoney());
        if (giver.getMoney() < 0) {
            StatusImpactor.changeStatus(giver, "playable", -1);
        }
    }

    /**
     * change the money of two players
     * (Update panel) Print the resultant savings of both players.
     * @param price the amount of money to change
     * @param giver the player to give money
     * @param receiver the player to receive money
     */
    public static void trade(int price, Player giver, Player receiver) {
        MoneyImpactor.deduct(price, giver);
        receiver.setMoney(receiver.getMoney() + price);
        PlayerInfoPanel.updatePanel(receiver.getUserId(), receiver.getMoney());
    }
}
