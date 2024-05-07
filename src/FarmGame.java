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
    private JTabbedPane tabbedPane;
    private JPanel plantPanel, buyLandPanel, upgradePanel;
    private JLabel moneyLabel, landLabel;
    private JButton plantButton, upgradeButton;
    private JButton buy1AcreButton, buy100AcresButton, buy10000AcresButton, buy1000000AcresButton, buy50000000AcresButton, buy1000000000AcresButton, buy50000000000AcresButton, buy1000000000000AcresButton, buy50000000000000AcresButton, buy1000000000000000AcresButton;
    private List<Crop> crops = new ArrayList<>();
    private int money = 0;
    private int land = 1; // Starting land area
    private Timer timer;

    // Constructor for the FarmGame class
    public FarmGame() {
        // Set up the JFrame
        frame = new JFrame("Idle Farm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();

        // Create panels for each tab
        plantPanel = new JPanel();
        plantPanel.setLayout(new FlowLayout());
        buyLandPanel = new JPanel();
        buyLandPanel.setLayout(new FlowLayout());
        upgradePanel = new JPanel();
        upgradePanel.setLayout(new FlowLayout());

        // Add tabs to the tabbed pane
        tabbedPane.addTab("Plant Crops", plantPanel);
        tabbedPane.addTab("Buy Land", buyLandPanel);
        tabbedPane.addTab("Upgrades", upgradePanel);

        // Create labels for money and land
        moneyLabel = new JLabel("Money: $" + money);
        landLabel = new JLabel("Land: " + land + " acres");

        // Create buttons for each tab
        plantButton = new JButton("Plant Wheat (1 acre, 3 sec, $10)");
        plantButton.addActionListener(this);
        upgradeButton = new JButton("Upgrade (TBA)");
        upgradeButton.addActionListener(this);

        // Create buttons for each tab
        plantButton = new JButton("Plant Wheat (1 acre, 3 sec, $10)");
        plantButton.addActionListener(this);
        upgradeButton = new JButton("Upgrade (TBA)");
        upgradeButton.addActionListener(this);

        // Initialize buy land buttons
        buy1AcreButton = new JButton("Buy 1 Acre ($50)");
        buy1AcreButton.addActionListener(this);
        buy100AcresButton = new JButton("Buy 100 Acres ($5000)");
        buy100AcresButton.addActionListener(this);
        buy10000AcresButton = new JButton("Buy 10,000 Acres ($500,000)");
        buy10000AcresButton.addActionListener(this);
        buy1000000AcresButton = new JButton("Buy 1,000,000 Acres ($50,000,000)");
        buy1000000AcresButton.addActionListener(this);
        buy50000000AcresButton = new JButton("Buy 50,000,000 Acres ($2,500,000,000)");
        buy50000000AcresButton.addActionListener(this);
        buy1000000000AcresButton = new JButton("Buy 1,000,000,000 Acres ($50,000,000,000)");
        buy1000000000AcresButton.addActionListener(this);
        buy50000000000AcresButton = new JButton("Buy 50,000,000,000 Acres ($2,500,000,000,000)");
        buy50000000000AcresButton.addActionListener(this);
        buy1000000000000AcresButton = new JButton("Buy 1,000,000,000,000 Acres ($50,000,000,000,000)");
        buy1000000000000AcresButton.addActionListener(this);
        buy1000000000000000AcresButton = new JButton("Buy 1,000,000,000,000,000 Acres ($50,000,000,000,000,000)");
        buy1000000000000000AcresButton.addActionListener(this);

        // Add components to panels for each tab
        plantPanel.add(moneyLabel);
        plantPanel.add(landLabel);
        plantPanel.add(plantButton);
        buyLandPanel.add(new JLabel("Money: $" + money));
        buyLandPanel.add(new JLabel("Land: " + land + " acres"));
        buyLandPanel.add(buy1AcreButton);
        buyLandPanel.add(buy100AcresButton);
        buyLandPanel.add(buy10000AcresButton);
        buyLandPanel.add(buy1000000AcresButton);
        buyLandPanel.add(buy50000000AcresButton);
        buyLandPanel.add(buy1000000000AcresButton);
        buyLandPanel.add(buy50000000000AcresButton);
        buyLandPanel.add(buy1000000000000AcresButton);
        buyLandPanel.add(buy1000000000000000AcresButton);
        upgradePanel.add(new JLabel("Money: $" + money));
        upgradePanel.add(new JLabel("Land: " + land + " acres"));
        upgradePanel.add(upgradeButton);

        // Add the tabbed pane to the frame
        frame.add(tabbedPane);

        // Start timer
        timer = new Timer(1000, this);
        timer.start();

        // Pack the frame and set it visible
        frame.setSize(500, 750);
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
        } if (e.getSource() == buy1AcreButton) {
            if (money >= 50) {
                money -= 50;
                land += 1;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy100AcresButton) {
            if (money >= 5000) {
                money -= 5000;
                land += 100;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy10000AcresButton) {
            if (money >= 500000) {
                money -= 500000;
                land += 10000;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy1000000AcresButton) {
            if (money >= 50000000) {
                money -= 50000000;
                land += 1000000;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy50000000AcresButton) {
            if (money >= 2500000000L) {
                money -= 2500000000L;
                land += 50000000;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy1000000000AcresButton) {
            if (money >= 50000000000L) {
                money -= 50000000000L;
                land += 1000000000;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy50000000000AcresButton) {
            if (money >= 2500000000000L) {
                money -= 2500000000000L;
                land += 50000000000L;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy1000000000000AcresButton) {
            if (money >= 50000000000000L) {
                money -= 50000000000000L;
                land += 1000000000000L;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy1000000000000000AcresButton) {
            if (money >= 50000000000000000L) {
                money -= 50000000000000000L;
                land += 1000000000000000L;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == upgradeButton) {
            // Implement upgrade logic (e.g., faster growth, higher yield)
        } else if (e.getSource() == timer) {
            // Check for crop maturity and harvest
            for (int i = crops.size() - 1; i >= 0; i--) {
                Crop crop = crops.get(i);
                int elapsedTime = (int) (System.currentTimeMillis() - crop.getPlantedTime()) / 1000;
                if (elapsedTime >= crop.getGrowthTime()) {
                    money += crop.getYield();
                    land++;
                    crops.remove(i);
                }
            }
        }

        // Update labels
        updateMoneyLabel();
        updateLandLabel();
    }

    // Helper method to update the money label
    private void updateMoneyLabel() {
        moneyLabel.setText("Money: $" + money);
        // Update money label on all tabs
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component tabComponent = tabbedPane.getComponentAt(i);
            if (tabComponent instanceof JPanel) {
                JPanel tabPanel = (JPanel) tabComponent;
                for (Component component : tabPanel.getComponents()) {
                    if (component instanceof JLabel && ((JLabel) component).getText().startsWith("Money")) {
                        ((JLabel) component).setText("Money: $" + money);
                    }
                }
            }
        }
    }

    // Helper method to update the land label
    private void updateLandLabel() {
        landLabel.setText("Land: " + land + " acres");
        // Update land label on all tabs
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component tabComponent = tabbedPane.getComponentAt(i);
            if (tabComponent instanceof JPanel) {
                JPanel tabPanel = (JPanel) tabComponent;
                for (Component component : tabPanel.getComponents()) {
                    if (component instanceof JLabel && ((JLabel) component).getText().startsWith("Land")) {
                        ((JLabel) component).setText("Land: " + land + " acres");
                    }
                }
            }
        }
    }

    // Helper method to update the crop list panel
    private void updateCropListPanel() {
        // Update crop list panel if needed
    }

    // Main method to start the game
    public static void main(String[] args) {
        new FarmGame();
    }
}