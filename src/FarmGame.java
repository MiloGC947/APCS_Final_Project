import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

class Achievement {
    private String name;
    private String description;
    private boolean achieved;

    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
        this.achieved = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void achieve() {
        this.achieved = true;
    }
}

public class FarmGame implements ActionListener {

    private JFrame frame;
    private JTabbedPane tabbedPane, tabbedUpgradePane;
    private JPanel plantPanel, buyLandPanel, upgradePanel, cropSpeedPanel, cropYieldPanel, landEfficiencyPanel,
            autoFarmingPanel;
    private JLabel moneyLabel, landLabel, humorLabel;
    private JButton plantWheat, plantCorn, plantCarrots, plantTomatoes, plantPotatoes, plantApples, plantGrapes,
            plantCoffee, plantCacao, plantVanilla, plantSaffron, plantTruffles, plantMagicBeans, growthSpeed1,
            growthSpeed2, growthSpeed3, growthSpeed4, growthSpeed5, growthSpeed6, growthSpeed7, growthSpeed8,
            growthSpeed9, yieldBoost1, yieldBoost2, yieldBoost3, yieldBoost4, yieldBoost5, yieldBoost6, yieldBoost7,
            yieldBoost8, yieldBoost9, yieldBoost10, landEfficiency1, landEfficiency2, landEfficiency3, landEfficiency4,
            landEfficiency5, autoFarm1, autoFarm2, autoFarm3, buy1AcreButton, buy500AcresButton, buy250KAcresButton, buy200MAcresButton,
            buy75BAcresButton, buy50TAcresButton, buy20QaAcresButton, buy8QiAcresButton, buy4SxAcresButton,
            buy2SpAcresButton, buy1OcAcresButton, buy1NoAcresButton, buy1DcAcresButton, prestigeButton;
    private java.util.List<Crop> crops = new ArrayList<>();
    private java.util.List<Achievement> achievements = new ArrayList<>();
    private List<Timer> autoFarmTimers = new ArrayList<>();
    private Set<String> plantedCrops = new HashSet<>();
    private BigInteger money = new BigInteger("1000000");
    private BigInteger land = new BigInteger("1");
    private Timer timer;
    private double growthSpeedMultiplier = 1.0;
    private double yieldMultiplier = 1.0;
    private int prestigeLevel = 0;
    private double prestigeMultiplier = 1.0;
    private boolean hasPrestigeBonus = false;
    private BigInteger prestigeRequirement = new BigInteger("1000000");
    private JLabel prestigeRequirementLabel;
    private int cropsHarvested = 0;

    public FarmGame() {
        frame = new JFrame("Idle Farm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        tabbedUpgradePane = new JTabbedPane();

        plantPanel = new JPanel(new FlowLayout());
        buyLandPanel = new JPanel(new FlowLayout());
        upgradePanel = new JPanel(new FlowLayout());
        cropSpeedPanel = new JPanel();
        cropSpeedPanel.setLayout(new BoxLayout(cropSpeedPanel, BoxLayout.Y_AXIS));
        cropYieldPanel = new JPanel();
        cropYieldPanel.setLayout(new BoxLayout(cropYieldPanel, BoxLayout.Y_AXIS));
        landEfficiencyPanel = new JPanel();
        landEfficiencyPanel.setLayout(new BoxLayout(landEfficiencyPanel, BoxLayout.Y_AXIS));
        autoFarmingPanel = new JPanel();
        autoFarmingPanel.setLayout(new BoxLayout(autoFarmingPanel, BoxLayout.Y_AXIS));

        tabbedPane.addTab("Plant Crops", plantPanel);
        tabbedPane.addTab("Buy Land", buyLandPanel);
        tabbedPane.addTab("Upgrades", upgradePanel);

        tabbedUpgradePane.addTab("Crop Speed", cropSpeedPanel);
        tabbedUpgradePane.addTab("Crop Yield", cropYieldPanel);
        tabbedUpgradePane.addTab("Land Efficiency", landEfficiencyPanel);
        tabbedUpgradePane.addTab("Auto Farming", autoFarmingPanel);

        moneyLabel = new JLabel("Money: $" + money.toString());
        landLabel = new JLabel("Land: " + land.toString() + " acres");
        humorLabel = new JLabel(getHumorText(money));
        prestigeRequirementLabel = new JLabel("Prestige Requirement: " + formatBigInteger(prestigeRequirement));

        initializeButtons();
        initializeAchievements();

        plantPanel.add(moneyLabel);
        plantPanel.add(landLabel);
        plantPanel.add(humorLabel);
        addPlantButtons(plantPanel);

        buyLandPanel.add(new JLabel("Money: $" + money));
        buyLandPanel.add(new JLabel("Land: " + land.toString() + " acres"));
        addBuyLandButtons(buyLandPanel);

        upgradePanel.add(new JLabel("Money: $" + money));
        upgradePanel.add(new JLabel("Land: " + land.toString() + " acres"));
        addUpgradeButtons();

        // Add the prestige button and label to the upgrade panel
        upgradePanel.add(prestigeButton);
        upgradePanel.add(prestigeRequirementLabel);

        // Now add the tabbed upgrade pane
        upgradePanel.add(tabbedUpgradePane);

        updatePrestigeRequirementLabel();

        frame.add(tabbedPane);

        timer = new Timer(1000, this);
        timer.start();

        frame.setSize(400, 600);
        frame.setVisible(true);
    }

    private void initializeButtons() {
        plantWheat = createPlantButton("Plant Wheat (1 acre, 3 sec, $10)", BigInteger.ONE, 3, new BigInteger("10"), "Wheat");
        plantCorn = createPlantButton("Plant Corn (500 acres, 4 sec, $5K)", new BigInteger("500"), 4, new BigInteger("5000"), "Corn");
        plantCarrots = createPlantButton("Plant Carrots (250K acres, 5 sec, $2.5M)", new BigInteger("250000"), 5, new BigInteger("2500000"), "Carrots");
        plantTomatoes = createPlantButton("Plant Tomatoes (200M acres, 6 sec, $1.5B)", new BigInteger("200000000"), 6, new BigInteger("1500000000"), "Tomatoes");
        plantPotatoes = createPlantButton("Plant Potatoes (75B acre, 7 sec, $750B)", new BigInteger("75000000000"), 7, new BigInteger("750000000000"), "Potatoes");
        plantApples = createPlantButton("Plant Apples (50T acres, 8 sec, $250T)", new BigInteger("50000000000000"), 8, new BigInteger("250000000000000"), "Apples");
        plantGrapes = createPlantButton("Plant Grapes (20Qa acres, 9 sec, $150Qa)", new BigInteger("20000000000000000"), 9, new BigInteger("150000000000000000"), "Grapes");
        plantCoffee = createPlantButton("Plant Coffee (8Qi acres, 10 sec, $75Qi)", new BigInteger("8000000000000000000"), 10, new BigInteger("75000000000000000000"), "Coffee");
        plantCacao = createPlantButton("Plant Cacao (4Sx acre, 12 sec, $40Sx)", new BigInteger("4000000000000000000000"), 12, new BigInteger("40000000000000000000000"), "Cacao");
        plantVanilla = createPlantButton("Plant Vanilla (2Sp acres, 14 sec, $20Sp)", new BigInteger("2000000000000000000000000"), 14, new BigInteger("20000000000000000000000000"), "Vanilla");
        plantSaffron = createPlantButton("Plant Saffron (1Oc acres, 16 sec, $10Oc)", new BigInteger("1000000000000000000000000000"), 16, new BigInteger("10000000000000000000000000000"), "Saffron");
        plantTruffles = createPlantButton("Plant Truffles (1No acres, 18 sec, $10No)", new BigInteger("1000000000000000000000000000000"), 18, new BigInteger("10000000000000000000000000000000"), "Truffles");
        plantMagicBeans = createPlantButton("Plant Magic Beans (1Dc acres, 20 sec, $10Dc)", new BigInteger("1000000000000000000000000000000000"), 20, new BigInteger("10000000000000000000000000000000000"), "Magic Beans");

        buy1AcreButton = createBuyLandButton("Buy 1 Acre ($50)", new BigInteger("50"), BigInteger.ONE);
        buy500AcresButton = createBuyLandButton("Buy 500 Acres ($25K)", new BigInteger("25000"), new BigInteger("500"));
        buy250KAcresButton = createBuyLandButton("Buy 250K Acres ($12.5M)", new BigInteger("12500000"), new BigInteger("250000"));
        buy200MAcresButton = createBuyLandButton("Buy 200M Acres ($10B)", new BigInteger("10000000000"), new BigInteger("200000000"));
        buy75BAcresButton = createBuyLandButton("Buy 75B Acres ($3.75T)", new BigInteger("3750000000000"), new BigInteger("75000000000"));
        buy50TAcresButton = createBuyLandButton("Buy 50T Acres ($2.5Qa)", new BigInteger("2500000000000000"), new BigInteger("50000000000000"));
        buy20QaAcresButton = createBuyLandButton("Buy 20Qa Acres ($1Qi)", new BigInteger("1000000000000000000"), new BigInteger("20000000000000000"));
        buy8QiAcresButton = createBuyLandButton("Buy 8Qi Acres ($400Qi)", new BigInteger("400000000000000000000"), new BigInteger("8000000000000000000"));
        buy4SxAcresButton = createBuyLandButton("Buy 4Sx Acres ($200Sx)", new BigInteger("200000000000000000000000"), new BigInteger("4000000000000000000000"));
        buy2SpAcresButton = createBuyLandButton("Buy 2Sp Acres ($100Sp)", new BigInteger("100000000000000000000000000"), new BigInteger("2000000000000000000000000"));
        buy1OcAcresButton = createBuyLandButton("Buy 1Oc Acres ($50Oc)", new BigInteger("50000000000000000000000000000"), new BigInteger("1000000000000000000000000000"));
        buy1NoAcresButton = createBuyLandButton("Buy 1No Acres ($50No)", new BigInteger("50000000000000000000000000000000"), new BigInteger("1000000000000000000000000000000"));
        buy1DcAcresButton = createBuyLandButton("Buy 1Dc Acres ($50Dc)", new BigInteger("50000000000000000000000000000000000"), new BigInteger("1000000000000000000000000000000000"));

        growthSpeed1 = createUpgradeButton("Growth Speed Boost 1 (100B)", new BigInteger("100000000000"), e -> applyGrowthSpeedBoost(new BigInteger("100000000000"), growthSpeed1));
        growthSpeed2 = createUpgradeButton("Growth Speed Boost 2 (100T)", new BigInteger("100000000000000"), e -> applyGrowthSpeedBoost(new BigInteger("100000000000000"), growthSpeed2));
        growthSpeed3 = createUpgradeButton("Growth Speed Boost 3 (100Qa)", new BigInteger("100000000000000000"), e -> applyGrowthSpeedBoost(new BigInteger("100000000000000000"), growthSpeed3));
        growthSpeed4 = createUpgradeButton("Growth Speed Boost 4 (100Qi)", new BigInteger("100000000000000000000"), e -> applyGrowthSpeedBoost(new BigInteger("100000000000000000000"), growthSpeed4));
        growthSpeed5 = createUpgradeButton("Growth Speed Boost 5 (100Sx)", new BigInteger("100000000000000000000000"), e -> applyGrowthSpeedBoost(new BigInteger("100000000000000000000000"), growthSpeed5));
        growthSpeed6 = createUpgradeButton("Growth Speed Boost 6 (100Sp)", new BigInteger("100000000000000000000000000"), e -> applyGrowthSpeedBoost(new BigInteger("100000000000000000000000000"), growthSpeed6));
        growthSpeed7 = createUpgradeButton("Growth Speed Boost 7 (100Oc)", new BigInteger("100000000000000000000000000000"), e -> applyGrowthSpeedBoost(new BigInteger("100000000000000000000000000000"), growthSpeed7));
        growthSpeed8 = createUpgradeButton("Growth Speed Boost 8 (100No)", new BigInteger("100000000000000000000000000000000"), e -> applyGrowthSpeedBoost(new BigInteger("100000000000000000000000000000000"), growthSpeed8));
        growthSpeed9 = createUpgradeButton("Growth Speed Boost 9 (100Dc)", new BigInteger("100000000000000000000000000000000000"), e -> applyGrowthSpeedBoost(new BigInteger("100000000000000000000000000000000000"), growthSpeed9));

        yieldBoost1 = createUpgradeButton("Yield Boost 1 (1M)", new BigInteger("1000000"), e -> applyYieldBoost(new BigInteger("1000000"), 1.1, yieldBoost1));
        yieldBoost2 = createUpgradeButton("Yield Boost 2 (1B)", new BigInteger("1000000000"), e -> applyYieldBoost(new BigInteger("1000000000"), 1.2, yieldBoost2));
        yieldBoost3 = createUpgradeButton("Yield Boost 3 (1T)", new BigInteger("1000000000000"), e -> applyYieldBoost(new BigInteger("1000000000000"), 1.3, yieldBoost3));
        yieldBoost4 = createUpgradeButton("Yield Boost 4 (1Qa)", new BigInteger("1000000000000000"), e -> applyYieldBoost(new BigInteger("1000000000000000"), 1.4, yieldBoost4));
        yieldBoost5 = createUpgradeButton("Yield Boost 5 (1Qi)", new BigInteger("1000000000000000000"), e -> applyYieldBoost(new BigInteger("1000000000000000000"), 1.5, yieldBoost5));
        yieldBoost6 = createUpgradeButton("Yield Boost 6 (1Sx)", new BigInteger("1000000000000000000000"), e -> applyYieldBoost(new BigInteger("1000000000000000000000"), 1.6, yieldBoost6));
        yieldBoost7 = createUpgradeButton("Yield Boost 7 (1Sp)", new BigInteger("1000000000000000000000000"), e -> applyYieldBoost(new BigInteger("1000000000000000000000000"), 1.7, yieldBoost7));
        yieldBoost8 = createUpgradeButton("Yield Boost 8 (1Oc)", new BigInteger("1000000000000000000000000000"), e -> applyYieldBoost(new BigInteger("1000000000000000000000000000"), 1.8, yieldBoost8));
        yieldBoost9 = createUpgradeButton("Yield Boost 9 (1No)", new BigInteger("1000000000000000000000000000000"), e -> applyYieldBoost(new BigInteger("1000000000000000000000000000000"), 1.9, yieldBoost9));
        yieldBoost10 = createUpgradeButton("Yield Boost 10 (1Dc)", new BigInteger("1000000000000000000000000000000000"), e -> applyYieldBoost(new BigInteger("1000000000000000000000000000000000"), 2.0, yieldBoost10));

        landEfficiency1 = createUpgradeButton("Efficient Land Use 1 (1K)", new BigInteger("1000"), e -> applyLandEfficiencyBoost(new BigInteger("1000"), 0.95, landEfficiency1));
        landEfficiency2 = createUpgradeButton("Efficient Land Use 2 (500M)", new BigInteger("500000000"), e -> applyLandEfficiencyBoost(new BigInteger("500000000"), 0.90, landEfficiency2));
        landEfficiency3 = createUpgradeButton("Efficient Land Use 3 (500B)", new BigInteger("500000000000"), e -> applyLandEfficiencyBoost(new BigInteger("500000000000"), 0.85, landEfficiency3));
        landEfficiency4 = createUpgradeButton("Efficient Land Use 4 (500Qa)", new BigInteger("500000000000000000"), e -> applyLandEfficiencyBoost(new BigInteger("500000000000000000"), 0.80, landEfficiency4));
        landEfficiency5 = createUpgradeButton("Efficient Land Use 5 (500Sp)", new BigInteger("500000000000000000000000000"), e -> applyLandEfficiencyBoost(new BigInteger("500000000000000000000000000"), 0.75, landEfficiency5));

        autoFarm1 = createUpgradeButton("Automated Harvest 1 (100B)", new BigInteger("100000000000"), e -> applyAutoFarm(new BigInteger("100000000000"), 10, autoFarm1));
        autoFarm2 = createUpgradeButton("Automated Harvest 2 (500Qi)", new BigInteger("500000000000000000000"), e -> applyAutoFarm(new BigInteger("500000000000000000000"), 5, autoFarm2));
        autoFarm3 = createUpgradeButton("Automated Harvest 3 (50No)", new BigInteger("50000000000000000000000000000000"), e -> applyAutoFarm(new BigInteger("50000000000000000000000000000000"), 1, autoFarm3));
        prestigeButton = new JButton("Prestige (Reset for Permanent Bonus)");
        prestigeButton.addActionListener(e -> applyPrestige());
        upgradePanel.add(prestigeRequirementLabel);
    }

    private JButton createPlantButton(String text, BigInteger acresNeeded, int growthTime, BigInteger yield, String cropName) {
        JButton button = new JButton(text);
        button.addActionListener(e -> plantCrop(acresNeeded, growthTime, yield, cropName));
        return button;
    }

    private JButton createBuyLandButton(String text, BigInteger cost, BigInteger acres) {
        JButton button = new JButton(text);
        button.addActionListener(e -> buyLand(cost, acres));
        return button;
    }

    private JButton createUpgradeButton(String text, BigInteger cost, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    private void addPlantButtons(JPanel panel) {
        panel.add(plantWheat);
        panel.add(plantCorn);
        panel.add(plantCarrots);
        panel.add(plantTomatoes);
        panel.add(plantPotatoes);
        panel.add(plantApples);
        panel.add(plantGrapes);
        panel.add(plantCoffee);
        panel.add(plantCacao);
        panel.add(plantVanilla);
        panel.add(plantSaffron);
        panel.add(plantTruffles);
        panel.add(plantMagicBeans);
    }

    private void addBuyLandButtons(JPanel panel) {
        panel.add(buy1AcreButton);
        panel.add(buy500AcresButton);
        panel.add(buy250KAcresButton);
        panel.add(buy200MAcresButton);
        panel.add(buy75BAcresButton);
        panel.add(buy50TAcresButton);
        panel.add(buy20QaAcresButton);
        panel.add(buy8QiAcresButton);
        panel.add(buy4SxAcresButton);
        panel.add(buy2SpAcresButton);
        panel.add(buy1OcAcresButton);
        panel.add(buy1NoAcresButton);
        panel.add(buy1DcAcresButton);
    }

    private void addUpgradeButtons() {
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

        upgradePanel.add(prestigeButton);
    }

    private void initializeAchievements() {
        achievements.add(new Achievement("First Harvest", "Harvest your first crop. Was it tasty?"));
        achievements.add(new Achievement("Millionaire", "Reach $1,000,000. Who needs a million dollars anyway?"));
        achievements.add(new Achievement("Landowner", "Own 1,000 acres of land. Do you have a permit for that?"));
        achievements.add(new Achievement("Farmer", "Plant 100 crops. Hope you like dirt under your nails!"));
        achievements.add(new Achievement("Tycoon", "Reach $1,000,000,000,000. Now you're just showing off."));
        achievements.add(new Achievement("Prestigious", "Prestige for the first time. Welcome to the fancy club."));
        achievements.add(new Achievement("Billionaire Farmer", "Reach $1,000,000,000. Rolling in green, literally!"));
        achievements.add(new Achievement("Crop Connoisseur", "Plant every type of crop. Your farm is a buffet."));
        achievements.add(new Achievement("Speedy Harvest", "Reduce growth time by 50%. Time is money!"));
        achievements.add(new Achievement("Yield Master", "Increase yield by 50%. More bang for your dirt!"));
        achievements.add(new Achievement("Land Baron", "Own 1,000,000 acres of land. You could start a country!"));
        achievements.add(new Achievement("Auto Farmer", "Activate Auto Farming. Sit back and relax!"));
        achievements.add(new Achievement("Money Tree", "Reach $1,000,000,000,000,000. Money does grow on trees!"));
        achievements.add(new Achievement("Land Hoarder", "Own 1,000,000,000 acres of land. Land for days!"));
        achievements.add(new Achievement("Crop King", "Harvest 10,000 crops. All hail the crop king!"));
        achievements.add(new Achievement("Infinite Wealth", "Reach $1,000,000,000,000,000,000. You're unstoppable!"));
        achievements.add(new Achievement("Ultimate Farmer", "Prestige 10 times. You've conquered farming!"));
    }

    private void checkAchievements() {
        for (Achievement achievement : achievements) {
            if (!achievement.isAchieved()) {
                if (achievement.getName().equals("First Harvest") && cropsHarvested >= 1) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Millionaire") && money.compareTo(new BigInteger("1000000")) >= 0) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Landowner") && land.compareTo(new BigInteger("1000")) >= 0) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Farmer") && crops.size() >= 100) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Tycoon") && money.compareTo(new BigInteger("1000000000000")) >= 0) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Prestigious") && hasPrestigeBonus) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Billionaire Farmer") && money.compareTo(new BigInteger("1000000000")) >= 0) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Crop Connoisseur") && allCropsPlanted()) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Speedy Harvest") && growthSpeedMultiplier <= 0.5) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Yield Master") && yieldMultiplier >= 1.5) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Land Baron") && land.compareTo(new BigInteger("1000000")) >= 0) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Auto Farmer") && autoFarmTimers.size() > 0) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Money Tree") && money.compareTo(new BigInteger("1000000000000000")) >= 0) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Land Hoarder") && land.compareTo(new BigInteger("1000000000")) >= 0) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Crop King") && cropsHarvested >= 10000) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Infinite Wealth") && money.compareTo(new BigInteger("1000000000000000000")) >= 0) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                } else if (achievement.getName().equals("Ultimate Farmer") && prestigeLevel >= 10) {
                    achievement.achieve();
                    JOptionPane.showMessageDialog(frame, "Achievement Unlocked: " + achievement.getDescription());
                }
            }
        }
    }

    private void plantCrop(BigInteger acresNeeded, int growthTime, BigInteger yield, String cropName) {
        if (land.compareTo(acresNeeded) >= 0) {
            crops.add(new Crop(cropName, (int) (growthTime * growthSpeedMultiplier), calculateYield(yield), acresNeeded));
            plantedCrops.add(cropName); // Track the crop type
            land = land.subtract(acresNeeded);
            updateLandLabel();
            updateCropListPanel();
        }
    }

    private void buyLand(BigInteger cost, BigInteger acres) {
        if (money.compareTo(cost) >= 0) {
            money = money.subtract(cost);
            land = land.add(acres);
            updateMoneyLabel();
            updateLandLabel();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            for (int i = crops.size() - 1; i >= 0; i--) {
                Crop crop = crops.get(i);
                int elapsedTime = (int) (System.currentTimeMillis() - crop.getPlantedTime()) / 1000;
                if (elapsedTime >= crop.getGrowthTime()) {
                    money = money.add(crop.getYield());
                    land = land.add(crop.getAcres());
                    crops.remove(i);
                    cropsHarvested++; // Increment crops harvested counter
                    checkAchievements();
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
            button.setVisible(false);
            updateMoneyLabel();
        }
    }

    private void applyYieldBoost(BigInteger cost, double multiplier, JButton button) {
        if (money.compareTo(cost) >= 0) {
            money = money.subtract(cost);
            yieldMultiplier *= multiplier;
            button.setVisible(false);
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
            button.setVisible(false);
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
            autoFarmTimers.add(autoFarmTimer); // Add the timer to the list
            button.setVisible(false);
            updateMoneyLabel();
        }
    }

    private void applyPrestige() {
        if (money.compareTo(prestigeRequirement) >= 0) {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to prestige? This will reset your progress but grant you a permanent bonus.", "Prestige", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                prestigeLevel++;
                prestigeMultiplier += 0.01;
                hasPrestigeBonus = true;
                money = BigInteger.ZERO;
                land = BigInteger.ONE;
                crops.clear();
                growthSpeedMultiplier = 1.0;
                yieldMultiplier = 1.0;
                prestigeRequirement = prestigeRequirement.multiply(new BigInteger("1000"));
                resetUpgradeButtons();
                updateMoneyLabel();
                updateLandLabel();
                updatePrestigeRequirementLabel();
                checkAchievements();
                // Stop all auto farm timers and clear the list
                for (Timer autoFarmTimer : autoFarmTimers) {
                    autoFarmTimer.stop();
                }
                autoFarmTimers.clear();
                JOptionPane.showMessageDialog(frame, "You have prestiged! Your prestige level is now " + prestigeLevel + " and you have a " + prestigeMultiplier + "x multiplier.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "You need " + formatBigInteger(prestigeRequirement) + " to prestige.");
        }
    }

    private boolean allCropsPlanted() {
        // List of all crop types
        String[] allCrops = {"Wheat", "Corn", "Carrots", "Tomatoes", "Potatoes", "Apples", "Grapes", "Coffee", "Cacao", "Vanilla", "Saffron", "Truffles", "Magic Beans"};
        for (String crop : allCrops) {
            if (!plantedCrops.contains(crop)) {
                return false;
            }
        }
        return true;
    }

    private void resetUpgradeButtons() {
        growthSpeed1.setVisible(true);
        growthSpeed2.setVisible(true);
        growthSpeed3.setVisible(true);
        growthSpeed4.setVisible(true);
        growthSpeed5.setVisible(true);
        growthSpeed6.setVisible(true);
        growthSpeed7.setVisible(true);
        growthSpeed8.setVisible(true);
        growthSpeed9.setVisible(true);

        yieldBoost1.setVisible(true);
        yieldBoost2.setVisible(true);
        yieldBoost3.setVisible(true);
        yieldBoost4.setVisible(true);
        yieldBoost5.setVisible(true);
        yieldBoost6.setVisible(true);
        yieldBoost7.setVisible(true);
        yieldBoost8.setVisible(true);
        yieldBoost9.setVisible(true);
        yieldBoost10.setVisible(true);

        landEfficiency1.setVisible(true);
        landEfficiency2.setVisible(true);
        landEfficiency3.setVisible(true);
        landEfficiency4.setVisible(true);
        landEfficiency5.setVisible(true);

        autoFarm1.setVisible(true);
        autoFarm2.setVisible(true);
        autoFarm3.setVisible(true);
    }

    private void updatePrestigeRequirementLabel() {
        prestigeRequirementLabel.setText("Prestige Requirement: " + formatBigInteger(prestigeRequirement));
    }

    private void updateMoneyLabel() {
        System.out.println(money);
        moneyLabel.setText("Money: $" + formatBigInteger(money));
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
        humorLabel.setText(getHumorText(money));
    }

    private void updateLandLabel() {
        landLabel.setText("Land: " + formatBigInteger(land) + " acres");
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

    private String getHumorText(BigInteger money) {
        String[] humorTexts = {
                "Welcome to Idle Farm! Your journey begins now...",
                "You've got thousands! Feeling rich yet?",
                "Millions in the bank. Time for a yacht?",
                "Billions! Don't forget about taxes.",
                "Trillions! Are you even human?",
                "Quadrillions! Buy a planet.",
                "Quintillions! What's next? The universe?",
                "Sextillions! Elon Musk is envious.",
                "Septillions! Reality might be broken.",
                "Octillions! Money really does grow on trees.",
                "Nonillions! You've transcended economics.",
                "Decillions! Infinity and beyond!"
        };

        BigDecimal decimalValue = new BigDecimal(money);
        int unitIndex = 0;
        BigDecimal thousand = new BigDecimal(1000);

        while (decimalValue.compareTo(thousand) >= 0 && unitIndex < humorTexts.length - 1) {
            decimalValue = decimalValue.divide(thousand);
            unitIndex++;
        }

        return humorTexts[unitIndex];
    }

    private void updateCropListPanel() {
        // Update crop list panel if needed
    }

    private BigInteger calculateYield(BigInteger baseYield) {
        return baseYield.multiply(BigDecimal.valueOf(yieldMultiplier).multiply(BigDecimal.valueOf(prestigeMultiplier)).toBigInteger());
    }

    public static void main(String[] args) {
        new FarmGame();
    }
}
