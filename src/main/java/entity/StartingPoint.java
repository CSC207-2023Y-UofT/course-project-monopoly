package entity;

/**
 *  Represents the starting point block
 */
public class StartingPoint extends Blocks{
    int bonus;

    public StartingPoint(int ID, int bonus) {
        super(ID);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
