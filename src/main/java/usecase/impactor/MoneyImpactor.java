package usecase.impactor;

import entity.Player;

/**
 * It is an impactor impacting the money attribute of the player
 */
public class MoneyImpactor {

    /**
     * change the money of two players
     * @param price the amount of money to change
     * @param giver the player to give money
     */
    public void trade(int price, Player giver){
        giver.setMoney(giver.getMoney() - price);

        if (giver.getMoney() < 0) {
            StatusImpactor statusImpactor = new StatusImpactor();
            statusImpactor.changeStatus(giver, "playable", -8964);
        }
    }

    /**
     * change the money of two players
     * @param price the amount of money to change
     * @param giver the player to give money
     * @param receiver the player to receive money
     */
    public void trade(int price, Player giver, Player receiver) {
        this.trade(price, giver);
        receiver.setMoney(receiver.getMoney() + price);
    }
}
