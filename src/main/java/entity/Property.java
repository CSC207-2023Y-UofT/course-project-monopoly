package entity;


import java.util.ArrayList;

/**
 * Represents the property blocks
 */
public class Property extends Block {

    String name;
    int level;
    Player owner;
    ArrayList<Integer> price;
    ArrayList<Integer> tax;

    public Property(int ID, String name, int level, ArrayList<Integer> price, ArrayList<Integer> tax){
        super(ID);
        this.name = name;
        this.level = level;
        this.price = price;
        this.tax = tax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void upgradeLevel() {
        this.level ++ ;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public ArrayList<Integer> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<Integer> price) {
        this.price = price;
    }

    public ArrayList<Integer> getTax() {
        return tax;
    }

    public void setTax(ArrayList<Integer> tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", owner=" + owner +
                ", price=" + price +
                ", tax=" + tax +
                ", id=" + id +
                '}';
    }
}
