package usecase;

import entity.Player;
import entity.Property;

/**
 * It is an identifies to check whether the player is the owner of a property
 */
public class OwnerIdentifier {
    public static boolean isOwner(Player player, Property property){
        return player.equals(property.getOwner());
    }
}
