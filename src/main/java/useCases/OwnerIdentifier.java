package useCases;

import entity.Player;
import entity.Property;

/**
 * The OwnerIdentifier class is responsible for identifying whether a player is the owner of a property.
 */
public class OwnerIdentifier {

    /**
     * Checks whether the given player is the owner of the specified property.
     *
     * @param player   The player to check if they are the owner.
     * @param property The property to check for ownership.
     * @return true if the player is the owner of the property, false otherwise.
     */
    public static boolean isOwner(Player player, Property property){

        return property.getOwner() == null || player.equals(property.getOwner());
    }
}
