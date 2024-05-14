import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

// Define the Crop class
class Crop {
    private String name;
    private int growthTime; // Seconds
    private BigInteger yield; // Use BigInteger for large numbers
    private long plantedTime; // Time when the crop was planted (milliseconds)
    private BigInteger acres; // Use BigInteger for large numbers

    public Crop(String name, int growthTime, BigInteger yield, BigInteger acres) {
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

    public BigInteger getYield() {
        return yield;
    }

    public long getPlantedTime() {
        return plantedTime;
    }

    public BigInteger getAcres() {
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
    private JButton buy1AcreButton, buy500AcresButton, buy250KAcresButton, buy200MAcresButton, buy75BAcresButton, buy50TAcresButton, buy20QaAcresButton, buy8QiAcresButton, buy4SxAcresButton, buy2SpAcresButton, buy1OcAcresButton, buy1NoAcresButton, buy1DcAcresButton;
    private List<Crop> crops = new ArrayList<>();
    private BigInteger money = BigInteger.ZERO;
    private BigInteger land = BigInteger.ONE;
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
        moneyLabel = new JLabel("Money: $" + money.toString());
        landLabel = new JLabel("Land: " + land.toString() + " acres");

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
        buy500AcresButton = new JButton  ("  Buy 500 Acres ($25K)  ");
        buy500AcresButton.addActionListener(this);
        buy250KAcresButton = new JButton(" Buy 250K Acres ($12.5M) ");
        buy250KAcresButton.addActionListener(this);
        buy200MAcresButton = new JButton ("  Buy 200M Acres ($10B)  ");
        buy200MAcresButton.addActionListener(this);
        buy75BAcresButton = new JButton(" Buy 75B Acres ($3.75T) ");
        buy75BAcresButton.addActionListener(this);
        buy50TAcresButton = new JButton (" Buy 50T Acres ($2.5Qa) ");
        buy50TAcresButton.addActionListener(this);
        buy20QaAcresButton = new JButton("  Buy 20Qa Acres ($1Qi)  ");
        buy20QaAcresButton.addActionListener(this);
        buy8QiAcresButton = new JButton(" Buy 8Qi Acres ($400Qi) ");
        buy8QiAcresButton.addActionListener(this);
        buy4SxAcresButton = new JButton(" Buy 4Sx Acres ($200Sx) ");
        buy4SxAcresButton.addActionListener(this);
        buy2SpAcresButton = new JButton(" Buy 2Sp Acres ($100Sp) ");
        buy2SpAcresButton.addActionListener(this);
        buy1OcAcresButton = new JButton("  Buy 1Oc Acres ($50Oc)  ");
        buy1OcAcresButton.addActionListener(this);
        buy1NoAcresButton = new JButton("  Buy 1No Acres ($50No)  ");
        buy1NoAcresButton.addActionListener(this);
        buy1DcAcresButton = new JButton("  Buy 1Dc Acres ($50Dc)  ");
        buy1DcAcresButton.addActionListener(this);

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
        buyLandPanel.add(buy500AcresButton);
        buyLandPanel.add(buy250KAcresButton);
        buyLandPanel.add(buy200MAcresButton);
        buyLandPanel.add(buy75BAcresButton);
        buyLandPanel.add(buy50TAcresButton);
        buyLandPanel.add(buy20QaAcresButton);
        buyLandPanel.add(buy8QiAcresButton);
        buyLandPanel.add(buy4SxAcresButton);
        buyLandPanel.add(buy2SpAcresButton);
        buyLandPanel.add(buy1OcAcresButton);
        buyLandPanel.add(buy1NoAcresButton);
        buyLandPanel.add(buy1DcAcresButton);
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
            BigInteger acresNeeded = BigInteger.ONE;
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Wheat", 3, new BigInteger("10"), acresNeeded)); // Plant wheat with 3s growth, $10 yield, 1 acre
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantCorn) {
            BigInteger acresNeeded = new BigInteger("500");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Corn", 4, new BigInteger("5000"), acresNeeded)); // Plant corn with 4s growth, $5000 yield, 500 acres
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantCarrots) {
            BigInteger acresNeeded = new BigInteger("250000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Carrot", 5, new BigInteger("25000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantTomatoes) {
            BigInteger acresNeeded = new BigInteger("200000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Tomatoes", 6, new BigInteger("1500000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantPotatoes) {
            BigInteger acresNeeded = new BigInteger("75000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Potatoes", 7, new BigInteger("750000000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantApples) {
            BigInteger acresNeeded = new BigInteger("50000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Apples", 8, new BigInteger("250000000000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantGrapes) {
            BigInteger acresNeeded = new BigInteger("20000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Grapes", 9, new BigInteger("150000000000000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantCoffee) {
            BigInteger acresNeeded = new BigInteger("8000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Coffee", 10, new BigInteger("75000000000000000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantCacao) {
            BigInteger acresNeeded = new BigInteger("4000000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Cacao", 12, new BigInteger("40000000000000000000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantVanilla) {
            BigInteger acresNeeded = new BigInteger("2000000000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Vanilla", 14, new BigInteger("20000000000000000000000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantSaffron) {
            BigInteger acresNeeded = new BigInteger("1000000000000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Saffron", 16, new BigInteger("10000000000000000000000000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantTruffles) {
            BigInteger acresNeeded = new BigInteger("1000000000000000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Truffles", 18, new BigInteger("10000000000000000000000000000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantMagicBeans) {
            BigInteger acresNeeded = new BigInteger("1000000000000000000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Magic Beans", 20, new BigInteger("10000000000000000000000000000000000"), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == buy1AcreButton) {
            BigInteger cost = new BigInteger("50");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(BigInteger.ONE);
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy500AcresButton) {
            BigInteger cost = new BigInteger("250000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("500"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy250KAcresButton) {
            BigInteger cost = new BigInteger("12500000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("250000"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy200MAcresButton) {
            BigInteger cost = new BigInteger("10000000000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("200000000"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy75BAcresButton) {
            BigInteger cost = new BigInteger("3750000000000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("75000000000"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy50TAcresButton) {
            BigInteger cost = new BigInteger("2500000000000000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("50000000000000"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy20QaAcresButton) {
            BigInteger cost = new BigInteger("1000000000000000000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("20000000000000000"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy8QiAcresButton) {
            BigInteger cost = new BigInteger("400000000000000000000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("8000000000000000000"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy4SxAcresButton) {
            BigInteger cost = new BigInteger("200000000000000000000000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("4000000000000000000000"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy2SpAcresButton) {
            BigInteger cost = new BigInteger("100000000000000000000000000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("2000000000000000000000000"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy1OcAcresButton) {
            BigInteger cost = new BigInteger("50000000000000000000000000000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("1000000000000000000000000000"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy1NoAcresButton) {
            BigInteger cost = new BigInteger("50000000000000000000000000000000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("1000000000000000000000000000000"));
                updateMoneyLabel();
                updateLandLabel();
            }
        } else if (e.getSource() == buy1DcAcresButton) {
            BigInteger cost = new BigInteger("50000000000000000000000000000000000");
            if (money.compareTo(cost) >= 0) {
                money = money.subtract(cost);
                land = land.add(new BigInteger("1000000000000000000000000000000000"));
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
                    money = money.add(crop.getYield());
                    land = land.add(crop.getAcres()); // Increase land by the acres used for planting
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
        moneyLabel.setText("Money: $" + money.toString());
        // Update money label on all tabs
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component tabComponent = tabbedPane.getComponentAt(i);
            if (tabComponent instanceof JPanel) {
                JPanel tabPanel = (JPanel) tabComponent;
                for (Component component : tabPanel.getComponents()) {
                    if (component instanceof JLabel && ((JLabel) component).getText().startsWith("Money")) {
                        ((JLabel) component).setText("Money: $" + money.toString());
                    }
                }
            }
        }
    }

    // Helper method to update the land label
    private void updateLandLabel() {
        landLabel.setText("Land: " + land.toString() + " acres");
        // Update land label on all tabs
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component tabComponent = tabbedPane.getComponentAt(i);
            if (tabComponent instanceof JPanel) {
                JPanel tabPanel = (JPanel) tabComponent;
                for (Component component : tabPanel.getComponents()) {
                    if (component instanceof JLabel && ((JLabel) component).getText().startsWith("Land")) {
                        ((JLabel) component).setText("Land: " + land.toString() + " acres");
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