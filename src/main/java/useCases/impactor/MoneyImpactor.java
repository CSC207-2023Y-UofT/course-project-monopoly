package useCases.impactor;

import entity.Player;

/**
 * It is an impactor impacting the money attribute of the player
 */
public class MoneyImpactor {

    /**
     * change the money of a player
     * @param price the amount of money to change, positive if it is deduction,
     *              negative if it is increase
     * @param giver the player to give money
     */
    public static void deduct(int price, Player giver){
        giver.setMoney(giver.getMoney() - price);

        if (giver.getMoney() < 0) {
            StatusImpactor.changeStatus(giver, "playable", -1);
        }
    }

    /**
     * change the money of two players
     * @param price the amount of money to change
     * @param giver the player to give money
     * @param receiver the player to receive money
     */
    public static void trade(int price, Player giver, Player receiver) {
        MoneyImpactor.deduct(price, giver);
        receiver.setMoney(receiver.getMoney() + price);
    }
}
