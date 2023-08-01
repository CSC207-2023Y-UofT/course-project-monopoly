package useCases;
import entity.*;
import presenters.OutputPresenter;
import useCases.impactor.MoneyImpactor;
/**
 * Represent the use case when a player arrives at a property
 * owned by others.
 */
public class PasserbyUseCase {

    /**
     * Proceeds the event where a player arrives at the property owned by others.
     * @param  passer the player who passes the block.
     * @param  property the property on this block
     * @param  owner: the player who owns this property
     */
    public static void passerbyArrival(Player passer, Property property, Player owner) {
        int passerId = passer.getUserId();
        int ownerId = owner.getUserId();

        String propName = property.getName();
        int tax = property.getTax();

        MoneyImpactor.trade(tax, passer, owner);

        OutputPresenter.notifyPasserbyPaid(passerId, tax, ownerId, propName);

    }
}


