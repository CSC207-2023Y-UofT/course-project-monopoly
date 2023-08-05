package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the player of the game
 */
public class Player {

    /**
     *
     */
    private int userId;
    private int money;
    private int position; // the id of the block that this player stands on
    private ArrayList<Property> properties;
    private HashMap<String, Integer> status;

    public Player(int userId, int money){
        this.userId = userId;
        this.money = money;
        this.position = 0;
        this.properties = new ArrayList<>();
        this.status = new HashMap<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public HashMap<String, Integer> getStatus() {
        return status;
    }

    public void setStatus(HashMap<String, Integer> status){this.status = status;}


    public boolean equals(Player player){
        return this.userId == player.getUserId();
    }

    @Override
    public String toString() {
        return "Player{" +
                "userId=" + userId +
                ", money=" + money +
                ", properties=" + properties +
                ", status=" + status +
                '}';
    }
}
