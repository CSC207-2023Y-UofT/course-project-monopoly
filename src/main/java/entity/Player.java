package entity;

import java.util.ArrayList;

/**
 * Represents the player of the game
 */
public class Player {

    int userId;
    int money;
    ArrayList<Property> properties;
    ArrayList<String> status;

    public Player(int userId, int money){
        this.userId = userId;
        this.money = money;
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

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public ArrayList<String> getStatus() {
        return status;
    }

    public void addStatus(String status) {this.status.add(status);}

    public void removeStatus(String status) {this.status.remove(status);}

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
