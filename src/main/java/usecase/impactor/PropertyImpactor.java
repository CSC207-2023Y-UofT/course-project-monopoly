package usecase.impactor;

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
     * Upgrade the level of a property
     * @param property the property whose level should be upgraded
     */
    public static void upgrade(Property property){
        property.upgradeLevel();
    }
    public static void downgradeToZero(Property property){property.downgradeToZero();}
}
