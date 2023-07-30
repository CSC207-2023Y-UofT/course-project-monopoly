package useCases;

import entity.Player;
import entity.Property;

/**
 * It is an identifies to check whether the player is the owner of a property
 */
public class OwnerIdentifier {
    public static boolean isOwner(Player player, Property property){
        /**
         * identity whether this property belongs to this player
         * @param  Player player
         * @param  Property property
         * @return a boolean value represent the result
         */

        return property.getOwner() == null || player.equals(property.getOwner());
    }
}
