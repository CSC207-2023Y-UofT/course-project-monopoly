package entity;


import controller.PPTInteractor;

import java.util.ArrayList;

/**
 * Represents the property blocks
 */
public class Property extends Block {

    private String name;
    private int level;
    private Player owner;
    private int price;
    private int tax;
    private ArrayList<Integer> priceList;
    private ArrayList<Integer> taxList;


    public Property(int ID, String name, ArrayList<Integer> priceList, ArrayList<Integer> taxList) {
        super(ID);
        this.name = name;
        this.level = 0;
        this.price = priceList.get(0);
        this.tax = 0;
        this.priceList = priceList; // priceList sample: [15, 30, 45, 0]
        this.taxList = taxList; // taxList sample: [0, 10, 20, 30]
    }
    public String getBlockName()
    {
        return "property";
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
        this.price = this.priceList.get(this.level);
        this.tax = this.taxList.get(this.level);
    }

    public void downgradeToZero() {
        this.level = 0;
        this.price = this.priceList.get(this.level);
        this.tax = this.taxList.get(this.level);
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getPrice() { return price; }

    public int getTax() { return tax; }

    public ArrayList<Integer> getPriceList() {
        return priceList;
    }

    public void setPriceList(ArrayList<Integer> priceList) {
        this.priceList = priceList;
    }

    public ArrayList<Integer> getTaxList() {
        return taxList;
    }

    public void setTaxList(ArrayList<Integer> taxList) {
        this.taxList = taxList;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", owner=" + (owner == null ? null: owner.getUserId()) +
                ", price=" + price +
                ", tax=" + tax +
                ", priceList=" + priceList +
                ", taxList=" + taxList +
                ", id=" + id +
                '}';
    }

    @Override
    public void run(GameData data) {
        UseCaseInteractor interactor = new PPTInteractor();
        interactor.interact(this, data);
    }
}
