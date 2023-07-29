package entity;

/**
 *  Represents the starting point block
 */
public class StartingPoint extends Block {
    private static int bonus;

    public StartingPoint(int ID, int bonus) {
        super(ID);
        StartingPoint.bonus = bonus;
    }
    public String getBlockName()
    {
        return "startingpoint";
    }


    public static int getBonus() {
        return bonus;
    }

    public static void setBonus(int bonus) {
        StartingPoint.bonus = bonus;
    }
}
