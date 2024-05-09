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
    private int acres; // Acres required to plant the crop

    public Crop(String name, int growthTime, int yield, int acres) {
        this.name = name;
        this.growthTime = growthTime;
        this.yield = yield;
        this.acres = acres;
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

    public int getAcres() {
        return acres;
    }
}

// Define the FarmGame class which implements the ActionListener interface
public class FarmGame implements ActionListener {

    // Instance variables declaration
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel plantPanel, buyLandPanel, upgradePanel;
    private JLabel moneyLabel, landLabel;
    private JButton plantWheat, plantCorn, plantCarrots, plantTomatoes, plantPotatoes, plantApples, plantGrapes, plantCoffee, plantCacao, plantVanilla, plantSaffron, plantTruffles, plantMagicBeans;
    private JButton upgradeButton;
    private JButton buy1AcreButton, buy100AcresButton, buy10000AcresButton, buy1milAcresButton, buy50milAcresButton, buy1bilAcresButton, buy50bilAcresButton, buy1trilAcresButton, buy50000000000000AcresButton, buy1quadAcresButton;
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
        plantWheat = new JButton("Plant Wheat (1 acre, 3 sec, $10)");
        plantWheat.addActionListener(this);
        plantCorn = new JButton("Plant Corn (500 acres, 4 sec, $5K)");
        plantCorn.addActionListener(this);
        plantCarrots = new JButton("Plant Carrots (250K acres, 5 sec, $2.5M)");
        plantCarrots.addActionListener(this);
        plantTomatoes = new JButton("Plant Tomatoes (200M acres, 6 sec, $1.5B)");
        plantTomatoes.addActionListener(this);
        plantPotatoes = new JButton("Plant Potatoes (75B acre, 7 sec, $750B)");
        plantPotatoes.addActionListener(this);
        plantApples = new JButton("Plant Apples (50T acres, 8 sec, $250T)");
        plantApples.addActionListener(this);
        plantGrapes = new JButton("Plant Grapes (20Qa acres, 9 sec, $150Qa)");
        plantGrapes.addActionListener(this);
        plantCoffee = new JButton("Plant Coffee (8Qi acres, 10 sec, $75Qi)");
        plantCoffee.addActionListener(this);
        plantCacao = new JButton("Plant Cacao (4Sx acre, 12 sec, $40Sx)");
        plantCacao.addActionListener(this);
        plantVanilla = new JButton("Plant Vanilla (2Sp acres, 14 sec, $20Sp)");
        plantVanilla.addActionListener(this);
        plantSaffron = new JButton("Plant Saffron (1Oc acres, 16 sec, $10Oc)");
        plantSaffron.addActionListener(this);
        plantTruffles = new JButton("Plant Truffles (1No acres, 18 sec, $10No)");
        plantTruffles.addActionListener(this);
        plantMagicBeans = new JButton("Plant Magic Beans (1Dc acres, 20 sec, $10Dc)");
        plantMagicBeans.addActionListener(this);
        upgradeButton = new JButton("Upgrade (TBA)");
        upgradeButton.addActionListener(this);

        // Initialize buy land buttons
        buy1AcreButton = new JButton     ("     Buy 1 Acre ($50)     ");
        buy1AcreButton.addActionListener(this);
        buy100AcresButton = new JButton  ("    Buy 100 Acres ($5k)    ");
        buy100AcresButton.addActionListener(this);
        buy10000AcresButton = new JButton("   Buy 10k Acres ($500k)   ");
        buy10000AcresButton.addActionListener(this);
        buy1milAcresButton = new JButton ("  Buy 1mil Acres ($50mil)  ");
        buy1milAcresButton.addActionListener(this);
        buy50milAcresButton = new JButton(" Buy 50mil Acres ($2.5bil) ");
        buy50milAcresButton.addActionListener(this);
        buy1bilAcresButton = new JButton ("  Buy 1bil Acres ($50bil)  ");
        buy1bilAcresButton.addActionListener(this);
        buy50bilAcresButton = new JButton(" Buy 50bil Acres ($2.5tri) ");
        buy50bilAcresButton.addActionListener(this);
        buy1trilAcresButton = new JButton("  Buy 1tri Acres ($50tri)  ");
        buy1trilAcresButton.addActionListener(this);
        buy1quadAcresButton = new JButton(" Buy 1quad Acres ($50quad) ");
        buy1quadAcresButton.addActionListener(this);

        // Add components to panels for each tab
        plantPanel.add(moneyLabel);
        plantPanel.add(landLabel);
        plantPanel.add(plantWheat);
        plantPanel.add(plantCorn);
        plantPanel.add(plantCarrots);
        plantPanel.add(plantTomatoes);
        plantPanel.add(plantPotatoes);
        plantPanel.add(plantApples);
        plantPanel.add(plantGrapes);
        plantPanel.add(plantCoffee);
        plantPanel.add(plantCacao);
        plantPanel.add(plantVanilla);
        plantPanel.add(plantSaffron );
        plantPanel.add(plantTruffles);
        plantPanel.add(plantMagicBeans);
        buyLandPanel.add(new JLabel("Money: $" + money));
        buyLandPanel.add(new JLabel("Land: " + land + " acres"));
        buyLandPanel.add(buy1AcreButton);
        buyLandPanel.add(buy100AcresButton);
        buyLandPanel.add(buy10000AcresButton);
        buyLandPanel.add(buy1milAcresButton);
        buyLandPanel.add(buy50milAcresButton);
        buyLandPanel.add(buy1bilAcresButton);
        buyLandPanel.add(buy50bilAcresButton);
        buyLandPanel.add(buy1trilAcresButton);
        buyLandPanel.add(buy1quadAcresButton);
        upgradePanel.add(new JLabel("Money: $" + money));
        upgradePanel.add(new JLabel("Land: " + land + " acres"));
        upgradePanel.add(upgradeButton);

        // Add the tabbed pane to the frame
        frame.add(tabbedPane);

        // Start timer
        timer = new Timer(1000, this);
        timer.start();

        // Pack the frame and set it visible
        frame.setSize(350, 540);
        frame.setVisible(true);
    }

    // ActionListener implementation
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == plantWheat) {
            if (land >= 1) {
                crops.add(new Crop("Wheat", 3, 10, 1)); // Plant wheat with 3s growth and $10 yield, 1 acre
                land -= 1;
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantCorn) {
            if (land >= 500) {
                crops.add(new Crop("Corn", 4, 5000, 500)); // Plant corn with 4s growth and $5000 yield, 500 acres
                land -= 500;
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantCarrots) {
            if (land >= 250000) {
                crops.add(new Crop("Carrot", 5, 25000000, 250000)); // Plant corn with 4s growth and $5000 yield, 500 acres
                land -= 250000;
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
        } else if (e.getSource() == buy1milAcresButton) {
            if (money >= 50000000) {
                money -= 50000000;
                land += 1000000;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy50milAcresButton) {
            if (money >= 2500000000L) {
                money -= 2500000000L;
                land += 50000000;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy1bilAcresButton) {
            if (money >= 50000000000L) {
                money -= 50000000000L;
                land += 1000000000;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy50bilAcresButton) {
            if (money >= 2500000000000L) {
                money -= 2500000000000L;
                land += 50000000000L;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy1trilAcresButton) {
            if (money >= 50000000000000L) {
                money -= 50000000000000L;
                land += 1000000000000L;
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy1quadAcresButton) {
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
                    land += crop.getAcres(); // Increase land by the acres used for planting
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