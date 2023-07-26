package usecase;
import entity.*;
import usecase.impactor.MoneyImpactor;
/**
 * Represent the use case when a player arrives at a property
 * owned by others.
 */
public class PasserbyUseCase {
    private final Player passer;
    private final Property property;
    private final Player owner;

    public PasserbyUseCase(Player passer, Property property) {
        this.passer = passer;
        this.property = property;
        this.owner = property.getOwner();
    }

    public Object[] PasserbyArrival(PasserbyUseCase passerbyUseCase) {
        /**
         * Proceeds the event where a player arrives at the property owned by others.
         * @param passerbyUseCase The event.
         * @return an informational Object array with 5 elements.
         *         - index 0: Player, passerby player
         *         - index 1: int, tokens transferred
         *         - index 2: Player, owner of property
         *         - index 3: Property, the property
         *         - index 4: boolean, whether the passerby player is playable after transaction.
         */
        int tax = passerbyUseCase.property.getTax();
        MoneyImpactor impactor = new MoneyImpactor();
        impactor.trade(tax, passerbyUseCase.passer, passerbyUseCase.owner);

        return new Object[]{passerbyUseCase.passer, tax, passerbyUseCase.owner,
                passerbyUseCase.property, passerbyUseCase.passer.getMoney() >= 0};
        // Might be useful, but not necessary.
    }
}


