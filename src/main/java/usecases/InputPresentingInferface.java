package usecases;

import entities.GameData;

public interface InputPresentingInferface {
    boolean ownerChooseAtProperty(String verb, String propName, int currPrice);
    int playerChooseBlock(GameData data);
}
