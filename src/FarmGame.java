// Import necessary packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Define the Crop class
class Crop {
    private String name;
    private int growthTime; // Seconds
    private int yield;
    private long plantedTime; // Time when the crop was planted (milliseconds)

    public Crop(String name, int growthTime, int yield) {
        this.name = name;
        this.growthTime = growthTime;
        this.yield = yield;
        plantedTime = System.currentTimeMillis(); // Set planted time when created
    }

    public String getName() {
        return name;
    }

    public int getGrowthTime() {
        return growthTime;
    }

    public int getYield() {
        return yield;
    }

    public long getPlantedTime() {
        return plantedTime;
    }
}

// Define the FarmGame class which implements the ActionListener interface
public class FarmGame implements ActionListener {

    // Instance variables declaration
    private JFrame frame;
    private JPanel infoPanel, buttonPanel, cropListPanel;
    private JLabel moneyLabel, landLabel;
    private JButton plantButton, buyLandButton, upgradeButton;
    private List<Crop> crops = new ArrayList<>();
    private int money = 0;
    private int land = 1; // Starting land area
    private Timer timer;

    // Constructor for the IdleFarm class
    public FarmGame() {
        // Set up the JFrame
        frame = new JFrame("Idle Farm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panels
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        cropListPanel = new JPanel();
        cropListPanel.setLayout(new FlowLayout());

        // Create labels
        moneyLabel = new JLabel("Money: $" + money);
        landLabel = new JLabel("Land: " + land + " acres");

        // Create buttons
        plantButton = new JButton("Plant Wheat");
        plantButton.addActionListener(this);
        buyLandButton = new JButton("Buy Land ($50)");
        buyLandButton.addActionListener(this);
        upgradeButton = new JButton("Upgrade (Depends)");
        upgradeButton.addActionListener(this);

        // Add components to panels and frame
        infoPanel.add(moneyLabel);
        infoPanel.add(landLabel);
        buttonPanel.add(plantButton);
        buttonPanel.add(buyLandButton);
        buttonPanel.add(upgradeButton);
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(cropListPanel, BorderLayout.SOUTH);

        // Start timer
        timer = new Timer(1000, this);
        timer.start();

        // Pack the frame and set it visible
        frame.pack();
        frame.setVisible(true);
    }

    // ActionListener implementation
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == plantButton) {
            if (land > 0) {
                crops.add(new Crop("Wheat", 3, 10)); // Plant wheat with 10s growth and $5 yield
                land--;
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == buyLandButton) {
            if (money >= 50) {
                money -= 50;
                land++;
                updateMoneyLabel();
                updateLandLabel();
            }


    // Main method to start the game
    public static void main(String[] args) {
        new FarmGame();
    }
}