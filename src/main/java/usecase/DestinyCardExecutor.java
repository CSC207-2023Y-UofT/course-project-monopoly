package usecase;

import entity.DestinyCard;
import entity.GameData;
import entity.Player;
import usecase.impactor.MoneyImpactor;
import usecase.impactor.PositionImpactor;
import usecase.impactor.StatusImpactor;

import java.util.ArrayList;

/**
 * It is an executor executing the actions of a destiny card
 */
public class DestinyCardExecutor {
    public static String executeCard(GameData data, Player player, DestinyCard card){
        ArrayList<Object> actions = card.getActions();

        MoneyImpactor.deduct(-((int)actions.get(0)), player);

        int position = (int)actions.get(1);

        if (position > 100){
            PositionImpactor.absoluteMove(data, player, position);
        } else {
            PositionImpactor.relativeMove(data, player, position);
        }

        StatusImpactor.changeStatus(player, "movable", (int)actions.get(2));

        return card.getMessage();
    }
}
