package usecase;

import entity.*;
import usecase.impactor.MoneyImpactor;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent the use case when a player arrives at his property or an unowned property
 */
public class OwnerPropertyUseCase {
    private final Player owner;
    private final Property property;
    private final MoneyImpactor impactor;

    public OwnerPropertyUseCase(Player owner, Property property) {
        this.owner = owner;
        this.property = property;
        this.impactor = new MoneyImpactor();
    }

    public void run() {
        // ask for user input
        Scanner scanner = new Scanner(System.in);
        String verb = "upgrade";
        if (property.getLevel() == 0)
            verb = "buy";

        System.out.println("Do you want to " + verb + " the property? (Y/n)");
        String option = scanner.nextLine();
        if (!(option.equals("Y")))
            return;

        if (this.property.getLevel() == 3) {
            System.out.println("this property cannot be updated further, did nothing");
            return;
        }


        int price = this.property.getPrice();
        if (price > this.owner.getMoney()) {
            System.out.println("the player doesn't have enough money to upgrade, did nothing");
            return;
        }

        this.impactor.deduct(price, owner);

        if (this.property.getLevel() == 0) {
            this.property.setOwner(owner);
            verb = "bought";
            ArrayList<Property> properties = owner.getProperties();
            if (properties == null) {
                properties = new ArrayList<>();
            }
            properties.add(this.property);
            owner.setProperties(properties);
        } else {verb = "upgraded";}

        this.property.upgradeLevel();

        System.out.println("Player " + this.owner.getUserId() + " " +  verb + " property "
                + this.property.getId() + " Player money: " + this.owner.getMoney()
                + " Property level: " + this.property.getLevel());
    }
}
