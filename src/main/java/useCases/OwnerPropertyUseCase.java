package useCases;

import entity.Player;
import entity.Property;
import presenters.GameMapPanel;
import presenters.InputPresenter;
import presenters.OutputPresenter;
import useCases.impactor.MoneyImpactor;

import java.util.ArrayList;

/**
 * Represents the use case when a player arrives at his/her own property
 * or an unoccupied property.
 */
public class OwnerPropertyUseCase {

    public static void ownerUpgrade(Player owner, Property currProperty) {
        int playerId = owner.getUserId();
        int currSaving = owner.getMoney();

        String propName = currProperty.getName();
        int currLevel = currProperty.getLevel();
        int currPrice = currProperty.getPrice();

        String verb = "Upgrade";
        if (currLevel == 0) { verb = "Invest"; }

        // If the property has reached maximum level, nothing can be done.
        if (currLevel == 5) {
            OutputPresenter.notifyMaxLevel(propName);
            OutputPresenter.notifyOwnerIgnored(playerId, propName);
            return;
        }

        // The player need to input 'Y' for property buy/upgrade, or 'N' for ignoring.
        boolean choice = InputPresenter.ownerChooseAtProperty(verb, propName, currPrice);

        if (choice) {  // Inputted 'Y'.
            if (currSaving < currPrice) {  // Fail to proceed due to insufficient fund.
                OutputPresenter.notifyInsufficientFund();
                OutputPresenter.notifyOwnerIgnored(playerId, propName);
                return;
            }

            // Invest / upgrade proceeds
            MoneyImpactor.deduct(currPrice, owner);
            if (currProperty.getLevel() == 0) {
                verb = "bought";
                currProperty.setOwner(owner);
                ArrayList<Property> properties = owner.getProperties();
                if (properties == null) {
                    properties = new ArrayList<>();
                }
                properties.add(currProperty);
                owner.setProperties(properties);

            } else {
                verb = "upgraded";
            }

            currProperty.upgradeLevel();
            OutputPresenter.notifyOwnerUpgraded(playerId, verb,
                    propName, currPrice, currProperty.getLevel());
            GameMapPanel.GameMapModifier();

        } else {  // Inputted 'N' or an invalid String.
            OutputPresenter.notifyOwnerIgnored(playerId, propName);
        }

    }
}