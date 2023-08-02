package UI;

import javax.swing.*;
import java.awt.*;
import entity.Player;

public class UserInformationBlock extends JPanel {
    private String userName;
    private int totalMoney;
    private Color squareColor;

    public UserInformationBlock(String userName, int totalMoney, Color squareColor) {
        this.userName = userName;
        this.totalMoney = totalMoney;
        this.squareColor = squareColor;

        // Set the preferred size to control the block's height
        setPreferredSize(new Dimension(250, 100));

        // Set a border for the block
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the colored square on the left side
        g.setColor(squareColor);
        g.fillRect(10, 10, 80, 80); // Adjust the size and position as needed

        // Draw the user's name and total money on the right side
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Name: " + userName, 100, 30); // Adjust the position as needed
        g.drawString("Total Money: $" + totalMoney, 100, 60); // Adjust the position as needed
    }

    // Setter methods in case you want to update the user information dynamically
    public void setUserName(String userName) {
        this.userName = userName;
        repaint(); // Redraw the panel after updating the name
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
        repaint(); // Redraw the panel after updating the total money
    }

    public void setSquareColor(Color squareColor) {
        this.squareColor = squareColor;
        repaint(); // Redraw the panel after updating the square color
    }
}
