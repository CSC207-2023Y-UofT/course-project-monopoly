package usecases.impactors;

import entities.Player;
import entities.Property;

/**
 * The PropertyImpactor class represents an use case that impacts the attributes of a property.
 * It provides methods to change the owner of a property and to downgrade a property's level to zero
 * and remove its owner.
 */
public class PropertyImpactor {
    /**
     * Changes the owner of a property to the specified player.
     *
     * @param player   The new owner of the property.
     * @param property The property whose owner should be changed.
     */
    public static void changeOwner(Player player, Property property){
        property.setOwner(player);
    }

    /**
     * Downgrades a property's level to zero and removes its owner.
     *
     * @param property The property to be downgraded.
     */
    public static void downgradeToZero(Property property){property.downgradeToZero();}
}
