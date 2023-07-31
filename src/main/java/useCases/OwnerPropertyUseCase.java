package useCases;

import entity.*;
import useCases.impactor.MoneyImpactor;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent the use case when a player arrives at his property or an unowned property
 */
public class OwnerPropertyUseCase {

    public static void run(Player owner, Property property) {
        /**
         * when player step on a property block. (terminal version)
         * @param  Player owner
         * @param  Property property
         */

        // ask for user input
        Scanner scanner = new Scanner(System.in);
        String verb = "upgrade";
        if (property.getLevel() == 0)
            verb = "buy";

        System.out.println("Do you want to " + verb + " the property? (Y/n)");
        String option = scanner.nextLine();
        if (!(option.equals("Y")))
            return;

        if (property.getLevel() == 3) {
            System.out.println("this property cannot be updated further, did nothing");
            return;
        }


        int price = property.getPrice();
        if (price > owner.getMoney()) {
            System.out.println("the player doesn't have enough money to upgrade, did nothing");
            return;
        }

        MoneyImpactor.deduct(price, owner);

        if (property.getLevel() == 0) {
            property.setOwner(owner);
            verb = "bought";
            ArrayList<Property> properties = owner.getProperties();
            if (properties == null) {
                properties = new ArrayList<>();
            }
            properties.add(property);
            owner.setProperties(properties);
        } else {verb = "upgraded";}

        property.upgradeLevel();

        System.out.println("Player " + owner.getUserId() + " " +  verb + " property "
                + property.getId() + " Player money: " + owner.getMoney()
                + " Property level: " + property.getLevel());
    }
}
