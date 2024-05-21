import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.math.BigDecimal;

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

    public void setGrowthTime(int growthTime) {
        this.growthTime = growthTime;
    }

    public void setYield(BigInteger yield) {
        this.yield = yield;
    }

    public void setAcres(BigInteger acres) {
        this.acres = acres;
    }
}

// Define the FarmGame class which implements the ActionListener interface
public class FarmGame implements ActionListener {

    // Instance variables declaration
    private JFrame frame;
    private JTabbedPane tabbedPane, tabbedUpgradePane;
    private JPanel plantPanel, buyLandPanel, upgradePanel, cropSpeedPanel, cropYieldPanel, landEfficiencyPanel,
            incomeBoostPanel, autoFarmingPanel, specialUpgradesPanel;
    private JLabel moneyLabel, landLabel;
    private JButton plantWheat, plantCorn, plantCarrots, plantTomatoes, plantPotatoes, plantApples, plantGrapes,
            plantCoffee, plantCacao, plantVanilla, plantSaffron, plantTruffles, plantMagicBeans, growthSpeed1,
            growthSpeed2, growthSpeed3, growthSpeed4, growthSpeed5, growthSpeed6, growthSpeed7, growthSpeed8,
            growthSpeed9, yieldBoost1, yieldBoost2, yieldBoost3, yieldBoost4, yieldBoost5, yieldBoost6, yieldBoost7,
            yieldBoost8, yieldBoost9, yieldBoost10, landEfficiency1, landEfficiency2, landEfficiency3, landEfficiency4,
            landEfficiency5, autoFarm1, autoFarm2, autoFarm3, marketExpansion1, marketExpansion2, marketExpansion3,
            marketExpansion4, marketExpansion5, marketExpansion6, marketExpansion7, marketExpansion8, marketExpansion9,
            marketExpansion10, buy1AcreButton, buy500AcresButton, buy250KAcresButton, buy200MAcresButton,
            buy75BAcresButton, buy50TAcresButton, buy20QaAcresButton, buy8QiAcresButton, buy4SxAcresButton,
            buy2SpAcresButton, buy1OcAcresButton, buy1NoAcresButton, buy1DcAcresButton;
    private List<Crop> crops = new ArrayList<>();
    private BigInteger money = new BigInteger("500000000000000000000000000");
    private BigInteger land = new BigInteger("1");
    private Timer timer;
    private double growthSpeedMultiplier = 1.0;
    private double yieldMultiplier = 1.0;

    // Constructor for the FarmGame class
    public FarmGame() {
        // Set up the JFrame
        frame = new JFrame("Idle Farm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedUpgradePane = new JTabbedPane();

        // Create panels for each tab
        plantPanel = new JPanel();
        plantPanel.setLayout(new FlowLayout());
        buyLandPanel = new JPanel();
        buyLandPanel.setLayout(new FlowLayout());
        upgradePanel = new JPanel();
        upgradePanel.setLayout(new FlowLayout());
        cropSpeedPanel = new JPanel();
        cropSpeedPanel.setLayout(new BoxLayout(cropSpeedPanel, BoxLayout.Y_AXIS));
        cropYieldPanel = new JPanel();
        cropYieldPanel.setLayout(new BoxLayout(cropYieldPanel, BoxLayout.Y_AXIS));
        landEfficiencyPanel = new JPanel();
        landEfficiencyPanel.setLayout(new BoxLayout(landEfficiencyPanel, BoxLayout.Y_AXIS));
        incomeBoostPanel = new JPanel();
        incomeBoostPanel.setLayout(new BoxLayout(incomeBoostPanel, BoxLayout.Y_AXIS));
        autoFarmingPanel = new JPanel();
        autoFarmingPanel.setLayout(new BoxLayout(autoFarmingPanel, BoxLayout.Y_AXIS));
        specialUpgradesPanel = new JPanel();
        specialUpgradesPanel.setLayout(new BoxLayout(specialUpgradesPanel, BoxLayout.Y_AXIS));

        // Add tabs to the tabbed pane
        tabbedPane.addTab("Plant Crops", plantPanel);
        tabbedPane.addTab("Buy Land", buyLandPanel);
        tabbedPane.addTab("Upgrades", upgradePanel);

        tabbedUpgradePane.addTab("Crop Speed", cropSpeedPanel);
        tabbedUpgradePane.addTab("Crop Yield", cropYieldPanel);
        tabbedUpgradePane.addTab("Land Efficiency", landEfficiencyPanel);
        tabbedUpgradePane.addTab("Income Boost", incomeBoostPanel);
        tabbedUpgradePane.addTab("Auto Farming", autoFarmingPanel);
        tabbedUpgradePane.addTab("Special", specialUpgradesPanel);

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

        // Initialize buy land buttons
        buy1AcreButton = new JButton("     Buy 1 Acre ($50)     ");
        buy1AcreButton.addActionListener(this);
        buy500AcresButton = new JButton("  Buy 500 Acres ($25K)  ");
        buy500AcresButton.addActionListener(this);
        buy250KAcresButton = new JButton(" Buy 250K Acres ($12.5M) ");
        buy250KAcresButton.addActionListener(this);
        buy200MAcresButton = new JButton("  Buy 200M Acres ($10B)  ");
        buy200MAcresButton.addActionListener(this);
        buy75BAcresButton = new JButton(" Buy 75B Acres ($3.75T) ");
        buy75BAcresButton.addActionListener(this);
        buy50TAcresButton = new JButton(" Buy 50T Acres ($2.5Qa) ");
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

        // Initialize growth speed boost buttons with updated prices
        growthSpeed1 = new JButton("Growth Speed Boost 1 (100B)");
        growthSpeed1.addActionListener(this);
        growthSpeed2 = new JButton("Growth Speed Boost 2 (100T)");
        growthSpeed2.addActionListener(this);
        growthSpeed3 = new JButton("Growth Speed Boost 3 (100Qa)");
        growthSpeed3.addActionListener(this);
        growthSpeed4 = new JButton("Growth Speed Boost 4 (100Qi)");
        growthSpeed4.addActionListener(this);
        growthSpeed5 = new JButton("Growth Speed Boost 5 (100Sx)");
        growthSpeed5.addActionListener(this);
        growthSpeed6 = new JButton("Growth Speed Boost 6 (100Sp)");
        growthSpeed6.addActionListener(this);
        growthSpeed7 = new JButton("Growth Speed Boost 7 (100Oc)");
        growthSpeed7.addActionListener(this);
        growthSpeed8 = new JButton("Growth Speed Boost 8 (100No)");
        growthSpeed8.addActionListener(this);
        growthSpeed9 = new JButton("Growth Speed Boost 9 (100Dc)");
        growthSpeed9.addActionListener(this);

        // Initialize yield boost buttons
        yieldBoost1 = new JButton("Yield Boost 1 (1M)");
        yieldBoost1.addActionListener(this);
        yieldBoost2 = new JButton("Yield Boost 2 (1B)");
        yieldBoost2.addActionListener(this);
        yieldBoost3 = new JButton("Yield Boost 3 (1T)");
        yieldBoost3.addActionListener(this);
        yieldBoost4 = new JButton("Yield Boost 4 (1Qa)");
        yieldBoost4.addActionListener(this);
        yieldBoost5 = new JButton("Yield Boost 5 (1Qi)");
        yieldBoost5.addActionListener(this);
        yieldBoost6 = new JButton("Yield Boost 6 (1Sx)");
        yieldBoost6.addActionListener(this);
        yieldBoost7 = new JButton("Yield Boost 7 (1Sp)");
        yieldBoost7.addActionListener(this);
        yieldBoost8 = new JButton("Yield Boost 8 (1Oc)");
        yieldBoost8.addActionListener(this);
        yieldBoost9 = new JButton("Yield Boost 9 (1No)");
        yieldBoost9.addActionListener(this);
        yieldBoost10 = new JButton("Yield Boost 10 (1Dc)");
        yieldBoost10.addActionListener(this);

        // Initialize land efficiency buttons
        landEfficiency1 = new JButton("Efficient Land Use 1 (1K)");
        landEfficiency1.addActionListener(this);
        landEfficiency2 = new JButton("Efficient Land Use 2 (500M)");
        landEfficiency2.addActionListener(this);
        landEfficiency3 = new JButton("Efficient Land Use 3 (500B)");
        landEfficiency3.addActionListener(this);
        landEfficiency4 = new JButton("Efficient Land Use 4 (500Qa)");
        landEfficiency4.addActionListener(this);
        landEfficiency5 = new JButton("Efficient Land Use 5 (500Sp)");
        landEfficiency5.addActionListener(this);

        // Initialize auto farming buttons
        autoFarm1 = new JButton("Automated Harvest 1 (100B)");
        autoFarm1.addActionListener(this);
        autoFarm2 = new JButton("Automated Harvest 2 (500Qi)");
        autoFarm2.addActionListener(this);
        autoFarm3 = new JButton("Automated Harvest 3 (50No)");
        autoFarm3.addActionListener(this);

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
        plantPanel.add(plantSaffron);
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
        cropSpeedPanel.add(growthSpeed1);
        cropSpeedPanel.add(growthSpeed2);
        cropSpeedPanel.add(growthSpeed3);
        cropSpeedPanel.add(growthSpeed4);
        cropSpeedPanel.add(growthSpeed5);
        cropSpeedPanel.add(growthSpeed6);
        cropSpeedPanel.add(growthSpeed7);
        cropSpeedPanel.add(growthSpeed8);
        cropSpeedPanel.add(growthSpeed9);
        cropYieldPanel.add(yieldBoost1);
        cropYieldPanel.add(yieldBoost2);
        cropYieldPanel.add(yieldBoost3);
        cropYieldPanel.add(yieldBoost4);
        cropYieldPanel.add(yieldBoost5);
        cropYieldPanel.add(yieldBoost6);
        cropYieldPanel.add(yieldBoost7);
        cropYieldPanel.add(yieldBoost8);
        cropYieldPanel.add(yieldBoost9);
        cropYieldPanel.add(yieldBoost10);
        landEfficiencyPanel.add(landEfficiency1);
        landEfficiencyPanel.add(landEfficiency2);
        landEfficiencyPanel.add(landEfficiency3);
        landEfficiencyPanel.add(landEfficiency4);
        landEfficiencyPanel.add(landEfficiency5);
        autoFarmingPanel.add(autoFarm1);
        autoFarmingPanel.add(autoFarm2);
        autoFarmingPanel.add(autoFarm3);
        upgradePanel.add(tabbedUpgradePane);

        // Add the tabbed pane to the frame
        frame.add(tabbedPane);
        upgradePanel.add(tabbedUpgradePane);

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
                crops.add(new Crop("Wheat", (int) (3 * growthSpeedMultiplier), calculateYield(new BigInteger("10")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantCorn) {
            BigInteger acresNeeded = new BigInteger("500");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Corn", (int) (4 * growthSpeedMultiplier), calculateYield(new BigInteger("5000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantCarrots) {
            BigInteger acresNeeded = new BigInteger("250000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Carrot", (int) (5 * growthSpeedMultiplier), calculateYield(new BigInteger("2500000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantTomatoes) {
            BigInteger acresNeeded = new BigInteger("200000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Tomatoes", (int) (6 * growthSpeedMultiplier), calculateYield(new BigInteger("1500000000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantPotatoes) {
            BigInteger acresNeeded = new BigInteger("75000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Potatoes", (int) (7 * growthSpeedMultiplier), calculateYield(new BigInteger("750000000000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantApples) {
            BigInteger             acresNeeded = new BigInteger("50000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Apples", (int) (8 * growthSpeedMultiplier), calculateYield(new BigInteger("250000000000000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantGrapes) {
            BigInteger acresNeeded = new BigInteger("20000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Grapes", (int) (9 * growthSpeedMultiplier), calculateYield(new BigInteger("150000000000000000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantCoffee) {
            BigInteger acresNeeded = new BigInteger("8000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Coffee", (int) (10 * growthSpeedMultiplier), calculateYield(new BigInteger("75000000000000000000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantCacao) {
            BigInteger acresNeeded = new BigInteger("4000000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Cacao", (int) (12 * growthSpeedMultiplier), calculateYield(new BigInteger("40000000000000000000000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantVanilla) {
            BigInteger acresNeeded = new BigInteger("2000000000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Vanilla", (int) (14 * growthSpeedMultiplier), calculateYield(new BigInteger("20000000000000000000000000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantSaffron) {
            BigInteger acresNeeded = new BigInteger("1000000000000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Saffron", (int) (16 * growthSpeedMultiplier), calculateYield(new BigInteger("10000000000000000000000000000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantTruffles) {
            BigInteger acresNeeded = new BigInteger("1000000000000000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Truffles", (int) (18 * growthSpeedMultiplier), calculateYield(new BigInteger("10000000000000000000000000000000")), acresNeeded));
                land = land.subtract(acresNeeded);
                updateLandLabel();
                updateCropListPanel();
            }
        } else if (e.getSource() == plantMagicBeans) {
            BigInteger acresNeeded = new BigInteger("1000000000000000000000000000000000");
            if (land.compareTo(acresNeeded) >= 0) {
                crops.add(new Crop("Magic Beans", (int) (20 * growthSpeedMultiplier), calculateYield(new BigInteger("10000000000000000000000000000000000")), acresNeeded));
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
            BigInteger cost = new BigInteger("25000");
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
        } else if (e.getSource() == growthSpeed1) {
            applyGrowthSpeedBoost(new BigInteger("100000000000"), growthSpeed1);
        } else if (e.getSource() == growthSpeed2) {
            applyGrowthSpeedBoost(new BigInteger("100000000000000"), growthSpeed2);
        } else if (e.getSource() == growthSpeed3) {
            applyGrowthSpeedBoost(new BigInteger("100000000000000000"), growthSpeed3);
        } else if (e.getSource() == growthSpeed4) {
            applyGrowthSpeedBoost(new BigInteger("100000000000000000000"), growthSpeed4);
        } else if (e.getSource() == growthSpeed5) {
            applyGrowthSpeedBoost(new BigInteger("100000000000000000000000"), growthSpeed5);
        } else if (e.getSource() == growthSpeed6) {
            applyGrowthSpeedBoost(new BigInteger("100000000000000000000000000"), growthSpeed6);
        } else if (e.getSource() == growthSpeed7) {
            applyGrowthSpeedBoost(new BigInteger("100000000000000000000000000000"), growthSpeed7);
        } else if (e.getSource() == growthSpeed8) {
            applyGrowthSpeedBoost(new BigInteger("100000000000000000000000000000000"), growthSpeed8);
        } else if (e.getSource() == growthSpeed9) {
            applyGrowthSpeedBoost(new BigInteger("100000000000000000000000000000000000"), growthSpeed9);
        } else if (e.getSource() == yieldBoost1) {
            applyYieldBoost(new BigInteger("1000000"), 1.1, yieldBoost1);
        } else if (e.getSource() == yieldBoost2) {
            applyYieldBoost(new BigInteger("1000000000"), 1.2, yieldBoost2);
        } else if (e.getSource() == yieldBoost3) {
            applyYieldBoost(new BigInteger("1000000000000"), 1.3, yieldBoost3);
        } else if (e.getSource() == yieldBoost4) {
            applyYieldBoost(new BigInteger("1000000000000000"), 1.4, yieldBoost4);
        } else if (e.getSource() == yieldBoost5) {
            applyYieldBoost(new BigInteger("1000000000000000000"), 1.5, yieldBoost5);
        } else if (e.getSource() == yieldBoost6) {
            applyYieldBoost(new BigInteger("1000000000000000000000"), 1.6, yieldBoost6);
        } else if (e.getSource() == yieldBoost7) {
            applyYieldBoost(new BigInteger("1000000000000000000000000"), 1.7, yieldBoost7);
        } else if (e.getSource() == yieldBoost8) {
            applyYieldBoost(new BigInteger("1000000000000000000000000000"), 1.8, yieldBoost8);
        } else if (e.getSource() == yieldBoost9) {
            applyYieldBoost(new BigInteger("1000000000000000000000000000000"), 1.9, yieldBoost9);
        } else if (e.getSource() == yieldBoost10) {
            applyYieldBoost(new BigInteger("1000000000000000000000000000000000"), 2.0, yieldBoost10);
        } else if (e.getSource() == landEfficiency1) {
            applyLandEfficiencyBoost(new BigInteger("1000"), 0.95, landEfficiency1);
        } else if (e.getSource() == landEfficiency2) {
            applyLandEfficiencyBoost(new BigInteger("500000000"), 0.90, landEfficiency2);
        } else if (e.getSource() == landEfficiency3) {
            applyLandEfficiencyBoost(new BigInteger("500000000000"), 0.85, landEfficiency3);
        } else if (e.getSource() == landEfficiency4) {
            applyLandEfficiencyBoost(new BigInteger("500000000000000"), 0.80, landEfficiency4);
        } else if (e.getSource() == landEfficiency5) {
            applyLandEfficiencyBoost(new BigInteger("500000000000000000"), 0.75, landEfficiency5);
        } else if (e.getSource() == autoFarm1) {
            applyAutoFarm(new BigInteger("100000000000"), 10, autoFarm1);
        } else if (e.getSource() == autoFarm2) {
            applyAutoFarm(new BigInteger("500000000000000"), 5, autoFarm2);
        } else if (e.getSource() == autoFarm3) {
            applyAutoFarm(new BigInteger("50000000000000000000"), 1, autoFarm3);
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
            updateMoneyLabel();
            updateLandLabel();
        }
    }

    private void applyGrowthSpeedBoost(BigInteger cost, JButton button) {
        if (money.compareTo(cost) >= 0) {
            money = money.subtract(cost);
            growthSpeedMultiplier -= 0.1;
            button.setVisible(false); // Make the button disappear
            updateMoneyLabel();
        }
    }

    private void applyYieldBoost(BigInteger cost, double multiplier, JButton button) {
        if (money.compareTo(cost) >= 0) {
            money = money.subtract(cost);
            yieldMultiplier *= multiplier;
            button.setVisible(false); // Make the button disappear
            updateMoneyLabel();
        }
    }

    private void applyLandEfficiencyBoost(BigInteger cost, double multiplier, JButton button) {
        if (money.compareTo(cost) >= 0) {
            money = money.subtract(cost);
            for (Crop crop : crops) {
                BigInteger newAcres = new BigDecimal(crop.getAcres()).multiply(BigDecimal.valueOf(multiplier)).toBigInteger();
                crop.setAcres(newAcres);
            }
            button.setVisible(false); // Make the button disappear
            updateMoneyLabel();
        }
    }

    private void applyAutoFarm(BigInteger cost, int interval, JButton button) {
        if (money.compareTo(cost) >= 0) {
            money = money.subtract(cost);
            Timer autoFarmTimer = new Timer(interval * 1000, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    money = money.add(land.multiply(BigInteger.valueOf(15)));
                    updateMoneyLabel();
                    updateLandLabel();
                }
            });
            autoFarmTimer.start();
            button.setVisible(false); // Make the button disappear
            updateMoneyLabel();
        }
    }

    // Helper method to update the money label
    private void updateMoneyLabel() {
        moneyLabel.setText("Money: $" + formatBigInteger(money));
        // Update money label on all tabs
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component tabComponent = tabbedPane.getComponentAt(i);
            if (tabComponent instanceof JPanel) {
                JPanel tabPanel = (JPanel) tabComponent;
                for (Component component : tabPanel.getComponents()) {
                    if (component instanceof JLabel && ((JLabel) component).getText().startsWith("Money")) {
                        ((JLabel) component).setText("Money: $" + formatBigInteger(money));
                    }
                }
            }
        }
    }

    // Helper method to update the land label
    private void updateLandLabel() {
        landLabel.setText("Land: " + formatBigInteger(land) + " acres");
        // Update land label on all tabs
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component tabComponent = tabbedPane.getComponentAt(i);
            if (tabComponent instanceof JPanel) {
                JPanel tabPanel = (JPanel) tabComponent;
                for (Component component : tabPanel.getComponents()) {
                    if (component instanceof JLabel && ((JLabel) component).getText().startsWith("Land")) {
                        ((JLabel) component).setText("Land: " + formatBigInteger(land) + " acres");
                    }
                }
            }
        }
    }

    private String formatBigInteger(BigInteger value) {
        BigDecimal decimalValue = new BigDecimal(value);
        String[] units = {"", "K", "M", "B", "T", "Qa", "Qi", "Sx", "Sp", "Oc", "No", "Dc"};
        int unitIndex = 0;
        BigDecimal thousand = new BigDecimal(1000);

        while (decimalValue.compareTo(thousand) >= 0 && unitIndex < units.length - 1) {
            decimalValue = decimalValue.divide(thousand);
            unitIndex++;
        }

        return String.format("%.2f%s", decimalValue, units[unitIndex]);
    }

    // Helper method to update the crop list panel
    private void updateCropListPanel() {
        // Update crop list panel if needed
    }

    // Helper method to calculate the yield based on the current multiplier
    private BigInteger calculateYield(BigInteger baseYield) {
        return baseYield.multiply(BigDecimal.valueOf(yieldMultiplier).toBigInteger());
    }

    // Main method to start the game
    public static void main(String[] args) {
        new FarmGame();
    }
}
