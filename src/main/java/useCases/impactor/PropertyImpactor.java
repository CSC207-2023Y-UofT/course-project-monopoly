package useCases.impactor;

import entity.Player;
import entity.Property;

/**
 *  It is an impactor impacting the attributes of a property
 */
public class PropertyImpactor {
    /**
     * Change the owner of a property
     * @param player the new owner of the property
     * @param property the property whose owner should be change
     */
    public static void changeOwner(Player player, Property property){
        property.setOwner(player);
    }

    /**
     * Downgrade a property's level to zero, remove its owner
     * @param property the property to downgrade
     */
    public static void downgradeToZero(Property property){property.downgradeToZero();}
}
