package scenes;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import javax.swing.*;

import static main.GameStates.MENU;
import static main.GameStates.PLAYING;
import static main.GameStates.setGameState;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Image;

import main.Game;
import objects.Sun;
import ui.MyButton;

public class Preparation extends GameScene implements SceneMethods {
    private Inventory inventory;
    private Sun sun;

    // Initialize Buttons
    private MyButton[] inventoryButtons;
    private MyButton[] deckButtons;
    private MyButton[] panelButtons;
    private MyButton menuButton;
    private MyButton sunText;

    // Initialize Booleans for Inventory Swap
    private boolean selectedClear = false;
    private boolean selectedSwap = false;

    // Initialize Inventory
    InventoryPlant[] inventoryPlants;
    InventoryPlant[] shadowPlants;
    InventoryPlant[] deckPlants;

    private int firstIndexSwapInventory;
    private int firstIndexSwapDeck;

    // Initialize inventory buttons
    private int startX = 30;
    private int startY = 170;
    private int buttonWidth = 62;
    private int buttonHeight = 80;
    private int spacing = 5;

    // Initialize deck buttons
    private int startXDeck = 113;
    private int startYDeck = 15;

    // Initialize panel buttons
    private int startXPanel = 30;
    private int startYPanel = 670;
    private int buttonWidthPanel = 173;
    private int buttonHeightPanel = 50;
    private int spacingPanel = 5;

    // Initialize sun text
    private int startXSun = 38;
    private int startYSun = 77;
    private int SunWidth = 25;
    private int SunHeight = 25;

    public Preparation(Game game) {
        super(game);
        inventory = new Inventory();
        sun = new Sun();

        // Initialize Inventories
        inventoryPlants = inventory.getPlants();
        shadowPlants = inventory.getShadowPlants();
        deckPlants = inventory.getDeck();

        // Initialize Buttons
        initSunText();
        initMenuButton();
        initButtons();
    }

    @Override
	public void render(Graphics g) {
        drawMenuButton(g);
		drawBG(g);
        drawButtons(g);
        drawSunText(g);
	}

    private void drawBG(Graphics g) {
		BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream("resources/InventoryScene.png");
	
		if (is == null) {
			System.out.println("Stream is null. Check the file path.");
            System.exit(0);
		} else {
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			if (img == null) {
				System.out.println("Image is null. Check the file format and content.");
                System.exit(0);
			} else {
				g.drawImage(img, 0, 0, null);
			}
		}
	}

    public BufferedImage getPlantsImages(String name, int width, int height) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("resources/PlantCards/" + name + ".png");
    
        try {
            img = ImageIO.read(is);
            img = resize(img, width, height);
        } catch (IOException e) {
            e.printStackTrace();   
        }       
    
        return img;
    }
    
    private BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return resized;
    }

    private void initSunText() {
        sunText = new MyButton(String.valueOf(sun.getSun()), startXSun, startYSun, SunWidth, SunHeight, true);
    }
    
    private void initMenuButton() {
        menuButton = new MyButton(874, 2, 140, 40, null);
    }
    
    private void initButtons() {
        inventoryButtons = new MyButton[16];
        deckButtons = new MyButton[6];
        panelButtons = new MyButton[3];

        // Initialize inventory buttons
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                int x = startX + (j % 8) * (buttonWidth + spacing);
                int y = startY + (j / 8) * (buttonHeight + spacing);
                inventoryButtons[i*8+j] = new MyButton(x, y, buttonWidth, buttonHeight, null);
            }
            startY = startY + buttonHeight + spacing;
        }

        // Initialize deck buttons
        for (int i = 0; i < 6; i++) {
            int x = startXDeck + i * (buttonWidth + spacing);
            deckButtons[i] = new MyButton(x, startYDeck, buttonWidth, buttonHeight, null);
        }

        // Initialize panel buttons
        String[] panelButtonsTexts = {"CLEAR", "SWAP", "START"};
        for (int i = 0; i < 3; i++) {
            int x = startXPanel + i * (buttonWidthPanel + spacingPanel);
            panelButtons[i] = new MyButton(panelButtonsTexts[i], x, startYPanel, buttonWidthPanel, buttonHeightPanel);
            panelButtons[i].setEnabled(true);
        }

        refreshInventoryAndDeck();
    }

    private void drawSunText(Graphics g) {
        sunText.draw(g);
    }


    private void drawMenuButton(Graphics g) {
        menuButton.draw(g);
    }

    private void drawButtons(Graphics g) {
		for (MyButton button : inventoryButtons) {
            button.draw(g);
        }
        for (MyButton button : deckButtons) {
            button.draw(g);
        }
        for (MyButton button : panelButtons) {
            button.draw(g);
        }
	}

    public void refreshInventoryAndDeck() {
        if (!selectedSwap) {
            firstIndexSwapInventory = -1;
            firstIndexSwapDeck = -1;
        }

        for (int i = 0; i < inventoryPlants.length; i++) {
            if (inventoryPlants[i] != null) {
                BufferedImage img = getPlantsImages(inventoryPlants[i].getName(), buttonWidth, buttonHeight);
                inventoryButtons[i].setImage(img);
                inventoryButtons[i].setEnabled(true);
                if (selectedSwap) {
                    inventoryButtons[i].setPotentialSwap(true);
                    if (firstIndexSwapInventory != -1) {
                        inventoryButtons[firstIndexSwapInventory].setFirstSwap(true);
                    }
                } else {
                    for (MyButton button : inventoryButtons) {
                        button.setPotentialSwap(false);
                    }
                    inventoryButtons[i].setFirstSwap(false);
                }
            } else if (shadowPlants[i] != null) {
                BufferedImage img = getPlantsImages(shadowPlants[i].getName(), buttonWidth, buttonHeight);
                inventoryButtons[i].setImage(img);
            }
        }

        // Update deck buttons
        for (int i = 0; i < deckPlants.length; i++) {
            if (deckPlants[i] != null) {
                BufferedImage img = getPlantsImages(deckPlants[i].getName(), buttonWidth, buttonHeight);
                deckButtons[i].setImage(img);
                deckButtons[i].setEnabled(true);
                if (selectedSwap) {
                    deckButtons[i].setPotentialSwap(true);
                    if (firstIndexSwapDeck != -1) {
                        deckButtons[firstIndexSwapDeck].setFirstSwap(true);
                    }
                } else {
                    for (MyButton button : deckButtons) {
                        button.setPotentialSwap(false);
                    }
                    deckButtons[i].setFirstSwap(false);
                }
            } else {
                deckButtons[i].setImage(null);
                deckButtons[i].setEnabled(false);
            }
        }

        if (selectedClear == true) {
            for (int i = 0; i < deckPlants.length-1; i++) {
                if (deckPlants[i] != null) {
                    try {
                        inventory.removeDeck(deckPlants[i]);
                        refreshInventoryAndDeck();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
            selectedClear = false;
            refreshInventoryAndDeck();
        }

        if (selectedSwap == false) {
            panelButtons[1].setText("SWAP");
        } else {
            panelButtons[1].setText("CANCEL");
        }
    }

    public void setSelectedClear(boolean selectedClear) {
        this.selectedClear = selectedClear;
    }


    @Override
    public void mouseClicked(int x, int y) {

        if (menuButton.getBounds().contains(x, y)) {
            selectedClear = true;
            refreshInventoryAndDeck();
            setGameState(MENU);
        }
        if (panelButtons[0].getBounds().contains(x, y)) {
            selectedClear = true;
            refreshInventoryAndDeck();
        }

        if (panelButtons[1].getBounds().contains(x, y)) {
            selectedSwap = !selectedSwap;
            refreshInventoryAndDeck();
        }

        if (panelButtons[2].getBounds().contains(x, y)) {
            boolean deckFull = true;
            for (InventoryPlant DP : deckPlants) {
                if (DP == null) {
                    deckFull = false;
                }
            }
            try {
                if (deckFull) {
                    game.getPlaying().createPlantDeck(this.inventory.getPlantDeckNames());
                    game.getPlaying().getTopBar().updateButtons();
                    game.getPlaying().getZombiesManager().clearZombie();
                    game.getPlaying().getZombiesManager().scheduleZombieGeneration();
                    setGameState(PLAYING);
                    //nanti pindain ke playing
                    sun.startMorning();
                } else {
                    throw new Exception("Deck belum full!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        for (int i = 0; i < 10; i++) {
            final int index = i;
            if (inventoryButtons[i].getBounds().contains(x, y) && inventoryButtons[i].isEnabled()) {
                try {
                    if (selectedSwap) {
                        if (firstIndexSwapInventory == -1) {
                            if (firstIndexSwapDeck != -1) {
                                throw new Exception("Tidak bisa menukar tanaman antara deck dan inventory!");
                            } else {
                                firstIndexSwapInventory = index;
                            }
                        } else {
                            inventory.swapPlants(firstIndexSwapInventory, index, inventoryPlants);
                            inventory.swapPlants(firstIndexSwapInventory, index, shadowPlants);
                            selectedSwap = !selectedSwap;
                        }
                    } else {
                        inventory.addDeck(inventoryPlants[index]);
                        inventoryButtons[i].setEnabled(!inventoryButtons[i].isEnabled());
                    }
                    refreshInventoryAndDeck();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            final int index = i;
            if (deckButtons[i].getBounds().contains(x, y) && deckButtons[i].isEnabled()) {
                try {
                    if (deckPlants[index] != null) {
                        if (selectedSwap) {
                            if (firstIndexSwapDeck == -1) {
                                if (firstIndexSwapInventory != -1) {
                                    throw new Exception("Tidak bisa menukar tanaman antara deck dan inventory!");
                                } else {
                                    firstIndexSwapDeck = index;
                                }
                            } else {
                                inventory.swapPlants(firstIndexSwapDeck, index, deckPlants);
                                selectedSwap = !selectedSwap;
                            }
                        } else {
                            inventory.removeDeck(deckPlants[index]);
                        }
                        refreshInventoryAndDeck();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }

    public MyButton[] getPlantDeckButtons() {
        return deckButtons;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void mouseMoved(int x, int y) {
        // Handle mouse move events
        for (MyButton button : panelButtons) {
            button.setMouseHover(false);
            if (button.getBounds().contains(x, y))
                button.setMouseHover(true);
        }
        for (MyButton button : inventoryButtons) {
            button.setMouseHover(false);
            if (button.getBounds().contains(x, y))
                button.setMouseHover(true);
        }
        for (MyButton button : deckButtons) {
            button.setMouseHover(false);
            if (button.getBounds().contains(x, y))
                button.setMouseHover(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        for (MyButton button : panelButtons) {
            button.setMousePressed(false);
            if (button.getBounds().contains(x, y))
                button.setMousePressed(true);
        }
        for (MyButton button : inventoryButtons) {
            button.setMousePressed(false);
            if (button.getBounds().contains(x, y))
                button.setMousePressed(true);
        }
        for (MyButton button : deckButtons) {
            button.setMousePressed(false);
            if (button.getBounds().contains(x, y))
                button.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        // Handle mouse release events
        for (MyButton button : panelButtons) {
            button.resetBooleans();
        }
        for (MyButton button : inventoryButtons) {
            button.resetBooleans();
        }
        for (MyButton button : deckButtons) {
            button.resetBooleans();
        }
    }
}

class Inventory {
    private static InventoryPlant[] plants = new InventoryPlant[10];
    private static InventoryPlant[] shadowPlants = new InventoryPlant[10];
    private static InventoryPlant[] deck = new InventoryPlant[6];

    public Inventory() {
        plants[0] = new InventoryPlant("Sunflower");
        plants[1] = new InventoryPlant("Peashooter");
        plants[2] = new InventoryPlant("TangleKelp");
        plants[3] = new InventoryPlant("WallNut");
        plants[4] = new InventoryPlant("LilyPad");
        plants[5] = new InventoryPlant("SnowPea");
        plants[6] = new InventoryPlant("Repeater");
        plants[7] = new InventoryPlant("GatlingPea");
        plants[8] = new InventoryPlant("Squash");
        plants[9] = new InventoryPlant("TallNut");
    }

    public void addDeck(InventoryPlant plant) throws Exception {
        boolean added = false;
        for (int i = 0; i < deck.length; i++) {
            for (int j = 0; j < plants.length; j++) {
                if (deck[i] == null && plants[j] != null && plants[j].getName().equals(plant.getName())) {
                    deck[i] = plant;
                    shadowPlants[j] = plant;
                    plants[j] = null;
                    added = true;
                    break;
                }
            }
            if (added) {
                break;
            }
        }
        if (!added) {
            throw new Exception("Deck sudah penuh!");
        }
    }

    public void removeDeck(InventoryPlant plant) throws Exception {
        boolean removed = false;
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] != null && deck[i].getName().equals(plant.getName())) {
                for (int j = 0; j < shadowPlants.length; j++) {
                    if (shadowPlants[j] != null && shadowPlants[j].getName().equals(plant.getName())) {
                        plants[j] = plant;
                        shadowPlants[j] = null;
                        // deck[i] = null;
                        int idx = i+1;
                        while (idx < deck.length) {
                            if (deck[idx] != null) {
                                deck[i] = deck[idx];
                                deck[idx] = null;
                                removed = true;
                                i++;
                            }
                            idx++;
                        }
                        if (!removed) {
                            deck[i] = null;
                            removed = true;
                        }                        
                        break;
                    }
                }
            }
        }
        if (!removed) {
            throw new Exception("Tidak ada tanaman yang dipilih pada deck!");
        }
    }

    public void swapPlants(int idx1, int idx2, InventoryPlant[] array) throws Exception{
        if (idx1 == idx2) {
            throw new Exception("Tanaman tidak bisa ditukar dengan dirinya sendiri!");
        } else if (idx1 < 0 || idx1 >= array.length || idx2 < 0 || idx2 >= array.length) {
            throw new Exception("Indeks diluar batas!");
        } else if (array != shadowPlants && (array[idx1] == null || array[idx2] == null)) {
            throw new Exception("Tanaman tidak bisa ditukar dengan posisi kosong!");
        } 
        else {
            InventoryPlant temp = array[idx1];
            array[idx1] = array[idx2];
            array[idx2] = temp;
        }
    }

    public InventoryPlant[] getPlants() {
        return plants;
    }
    
    public InventoryPlant[] getShadowPlants() {
        return shadowPlants;
    }

    public InventoryPlant[] getDeck() {
        return deck;
    }

    public String[] getPlantDeckNames() {
        String[] names = new String[6];
        for (int i = 0; i < 6; i++) {
            if (deck[i].getName() != null) {
                names[i] = deck[i].getName();
            }
        }
        return names;
    }
}

class InventoryPlant {
    private String name;
    // private ImageIcon image;

    public InventoryPlant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}