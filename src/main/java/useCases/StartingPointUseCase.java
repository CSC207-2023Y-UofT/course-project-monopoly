package useCases;

import entity.Player;
import entity.StartingPoint;
import useCases.impactor.MoneyImpactor;

/**
 * Represent the use case when the player passing through the starting point
 */
public class StartingPointUseCase {
    public static void giveBonus(Player player){
        /**
         * give bonus to the player when he/she passes the starting point
         * @param  Player player
         */
        int bonus = StartingPoint.getBonus();
        MoneyImpactor.deduct(bonus * -1, player);
    }


}
