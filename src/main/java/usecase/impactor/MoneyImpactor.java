package usecase.impactor;

import entity.Player;

/**
 * It is an impactor impacting the money attribute of the player
 */
public class MoneyImpactor {

    /**
     * Impact the money of a player
     * @param player player whose money should be changed
     * @param value the value of money should be change. If it is a deduction, the value is negative
     */
    public void trading(Player player, int value){
        int now = player.getMoney();
        player.setMoney(now + value);
    }
}
