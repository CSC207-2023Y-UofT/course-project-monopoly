package useCases;
import entity.*;
import useCases.impactor.MoneyImpactor;
/**
 * Represent the use case when a player arrives at a property
 * owned by others.
 */
public class PasserbyUseCase {




    public static Object[] PasserbyArrival(Player passer, Property property, Player owner) {
        /**
         * Proceeds the event where a player arrives at the property owned by others.
         * @param  Player passer: the player who passes the block.
         *                property: the property on this block
         *                owner: the player who owns this property
         * @return an informational Object array with 5 elements.
         *         - index 0: Player, passerby player
         *         - index 1: int, tokens transferred
         *         - index 2: Player, owner of property
         *         - index 3: Property, the property
         *         - index 4: boolean, whether the passerby player is playable after transaction.
         */
        int tax = property.getTax();

        MoneyImpactor.trade(tax, passer, owner);

        return new Object[]{passer, tax, owner,
                property, passer.getMoney() >= 0};
        // Might be useful, but not necessary.
    }
}


