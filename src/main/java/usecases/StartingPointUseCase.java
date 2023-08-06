package usecases;

import entities.Player;
import entities.StartingPoint;
import usecases.impactors.MoneyImpactor;

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
