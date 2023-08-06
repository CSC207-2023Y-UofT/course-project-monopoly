package entities;


import usecases.interactors.PPTInteractor;

import java.util.ArrayList;

/**
 * Represents a property block on the game map.
 */
public class Property extends Block {

    private String name;
    private int level;
    private Player owner;
    private int price;  // Current price of the property.
    private int tax;    // Current tax amount for the property.
    private ArrayList<Integer> priceList;
    private ArrayList<Integer> taxList;

    /**
     * Class constructor to create a new Property block with the specified ID, name, price list, and tax list.
     *
     * @param ID        The unique identifier for the Property block.
     * @param name      The name of the property.
     * @param priceList The list of prices at different levels for the property.
     * @param taxList   The list of taxes at different levels for the property.
     */
    public Property(int ID, String name, ArrayList<Integer> priceList, ArrayList<Integer> taxList) {
        super(ID);
        this.name = name;
        this.level = 0;
        this.price = priceList.get(0);
        this.tax = 0;
        this.priceList = priceList; // priceList sample: [15, 30, 45, 55, 70, 0]
        this.taxList = taxList; // taxList sample: [0, 10, 20, 30, 40, 50]
        this.owner = null;
    }

    /**
     * Retrieves the name of the block, which is "property" for this type.
     *
     * @return The name of this type of block.
     */
    public String getBlockName()
    {
        return "property";
    }

    /**
     * Gets the name of the property.
     *
     * @return The name of the property.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the property.
     *
     * @param name The name to set for the property.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current level of the property.
     *
     * @return The current level of the property.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Upgrades the level of the property to the next level, adjusting the price and tax accordingly.
     */
    public void upgradeLevel() {
        this.level++;
        this.price = this.priceList.get(this.level);
        this.tax = this.taxList.get(this.level);
    }

    /**
     * Downgrades the property level to zero, resetting the price and tax to the initial values.
     */
    public void downgradeToZero() {
        this.level = 0;
        this.price = this.priceList.get(this.level);
        this.tax = this.taxList.get(this.level);
    }

    /**
     * Gets the player who owns the property.
     *
     * @return The owner of the property (null if unowned).
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the player who owns the property.
     *
     * @param owner The player to set as the owner of the property.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Gets the current price of the property.
     *
     * @return The current price of the property.
     */
    public int getPrice() { return price; }

    /**
     * Gets the current tax amount for the property.
     *
     * @return The current tax amount for the property.
     */
    public int getTax() { return tax; }

    /**
     * Gets the list of prices at different levels for the property.
     *
     * @return The list of prices for the property.
     */
    public ArrayList<Integer> getPriceList() {
        return priceList;
    }

    /**
     * Sets the list of prices at different levels for the property.
     *
     * @param priceList The list of prices to set for the property.
     */
    public void setPriceList(ArrayList<Integer> priceList) {
        this.priceList = priceList;
    }

    /**
     * Gets the list of taxes at different levels for the property.
     *
     * @return The list of taxes for the property.
     */
    public ArrayList<Integer> getTaxList() {
        return taxList;
    }

    /**
     * Sets the list of taxes at different levels for the property.
     *
     * @param taxList The list of taxes to set for the property.
     */
    public void setTaxList(ArrayList<Integer> taxList) {
        this.taxList = taxList;
    }

    /**
     * Returns a string representation of the property, showing its name, level, owner, price, tax, and block ID.
     *
     * @return A string representation of the property.
     */
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

    /**
     * Executes the actions associated with the Property block.
     * This method interacts with the `PPTInteractor` to perform specific actions for the Property block type.
     *
     * @param data The data structure representing the game state.
     */
    @Override
    public void run(GameData data) {
        UseCaseInteractor interactor = new PPTInteractor();
        interactor.interact(this, data);
    }
}
