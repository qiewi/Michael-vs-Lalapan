package scenes;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Image;

import main.Game;
import ui.MyButton;

public class Preparation extends GameScene implements SceneMethods {
    private Inventory inventory;
    private MyButton[] inventoryButtons;
    private MyButton[] deckButtons;
    private MyButton[] panelButtons;
    private boolean selectedClear = false;
    private boolean selectedSwap = false;
    Plant[] inventoryPlants;
    Plant[] shadowPlants;
    Plant[] deckPlants;
    private BufferedImage selectedPlant = null;
	private BufferedImage[] plantsImages = new BufferedImage[10];

    public Preparation(Game game) {
        super(game);
        inventory = new Inventory();
        inventoryPlants = inventory.getPlants();
        shadowPlants = inventory.getShadowPlants();
        deckPlants = inventory.getDeck();
        initButtons();
    }

    @Override
	public void render(Graphics g) {

		drawMap(g);
        drawButtons(g);

	}

    private void drawMap(Graphics g) {
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
    
    private void initButtons() {
        inventoryButtons = new MyButton[12];
        deckButtons = new MyButton[6];
        panelButtons = new MyButton[3];

        // Initialize inventory buttons
        int startX = 30;
        int startY = 160;
        int buttonWidth = 70;
        int buttonHeight = 70;
        int spacing = 10;
        // Initialize deck buttons
        int startXDeck = 110;
        int startYDeck = 15;
        // Initialize panel buttons
        int startXPanel = 30;
        int startYPanel = 570;
        int buttonWidthPanel = 160;
        int buttonHeightPanel = 40;
        int spacingPanel = 5;

        // Initialize inventory buttons
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                int x = startX + (j % 6) * (buttonWidth + spacing);
                int y = startY + (j / 6) * (buttonHeight + spacing);
                inventoryButtons[i*6+j] = new MyButton(x, y, buttonWidth, buttonHeight, null);
            }
            startX = 30;
            startY = startY + buttonHeight + spacing;
        }

        // Initialize deck buttons
        for (int i = 0; i < 6; i++) {
            int x = startXDeck + i * (buttonWidth + spacing);
            deckButtons[i] = new MyButton(x, startYDeck, buttonWidth, buttonHeight, null);
        }

        // Initialize panel buttons
        String[] panelButtonsTexts = {"Clear", "Swap", "Start"};
        for (int i = 0; i < 3; i++) {
            int x = startXPanel + i * (buttonWidthPanel + spacingPanel);
            panelButtons[i] = new MyButton(panelButtonsTexts[i], x, startYPanel, buttonWidthPanel, buttonHeightPanel);
        }

        refreshInventoryAndDeck();
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

    private int firstIndexSwapInventory;
    private int firstIndexSwapDeck;

    private void refreshInventoryAndDeck() {
        if (!selectedSwap) {
            firstIndexSwapInventory = -1;
            firstIndexSwapDeck = -1;
        }

        for (int i = 0; i < 10; i++) {
            if (inventoryPlants[i] != null) {
                BufferedImage img = getPlantsImages(inventoryPlants[i].getName(), 70, 70);
                inventoryButtons[i].setImage(img);
                // inventoryButtons[i].setEnabled(true);
                if (selectedSwap) {
                    // inventoryButtons[i].setBorder1(true);
                    if (firstIndexSwapInventory != -1) {
                        // inventoryButtons[firstIndexSwapInventory].setBorder2(true);
                    }
                } else {
                    // inventoryButtons[i].setBorder1(false);
                    // inventoryButtons[i].setBorder2(false);
                }
            } else if (shadowPlants[i] != null) {
                BufferedImage img = getPlantsImages(shadowPlants[i].getName(), 80, 80);
                inventoryButtons[i].setImage(img);
                // inventoryButtons[i].setIcon(shadowPlants[i].getImage());
                // inventoryButtons[i].setEnabled(false);
                // inventoryButtons[i].setBorder(borderDef);
                
            } else {
                // inventoryButtons[i].setText("Empty");
                // inventoryButtons[i].setEnabled(false);
            }
        }

        // Update deck buttons
        for (int i = 0; i < 6; i++) {
            if (deckPlants[i] != null) {
                BufferedImage img = getPlantsImages(deckPlants[i].getName(), 80, 80);
                deckButtons[i].setImage(img);
                // deckButtons[i].setIcon(deckPlants[i].getImage());
                // deckButtons[i].setEnabled(true);
                if (selectedSwap) {
                    // deckButtons[i].setBorder1(true);
                    if (firstIndexSwapDeck != -1) {
                        // deckButtons[firstIndexSwapDeck].setBorder2(true);
                    }
                } else {
                    // deckButtons[i].setBorder1(false);
                    // deckButtons[i].setBorder2(false);
                }
            } else {
                deckButtons[i].setImage(null);
                // deckButtons[i].setIcon(null);
                // deckButtons[i].setEnabled(false);
            }
        }

        if (selectedClear == true) {
            for (int i = 0; i < deckPlants.length; i++) {
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
            panelButtons[1].setText("Swap");
        } else {
            panelButtons[1].setText("Cancel");
        }

        // Game.refresh();
    }

    @Override
    public void mouseClicked(int x, int y) {
        // Handle mouse click events
        // Add action listeners to panel buttons
        if (panelButtons[0].getBounds().contains(x, y)) {
            // panelButtons[0].setEnabled(!panelButtons[0].isEnabled());
            System.out.println("test");
            selectedClear = true;
            refreshInventoryAndDeck();
        }

        if (panelButtons[1].getBounds().contains(x, y)) {
            // panelButtons[1].setEnabled(!panelButtons[1].isEnabled());
            selectedSwap = !selectedSwap;
            refreshInventoryAndDeck();
        }

        if (panelButtons[2].getBounds().contains(x, y)) {
            // panelButtons[2].setEnabled(!panelButtons[2].isEnabled());
            JOptionPane.showMessageDialog(null, "Start button clicked!");
        }

        for (int i = 0; i < 10; i++) {
            final int index = i;
            if (inventoryButtons[i].getBounds().contains(x, y)) {
                // inventoryButtons[i].setEnabled(!inventoryButtons[i].isEnabled());
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
                    }
                    refreshInventoryAndDeck();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            final int index = i;
            if (deckButtons[i].getBounds().contains(x, y)) {
                // deckButtons[i].setEnabled(!deckButtons[i].isEnabled());
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
    private static Plant[] plants = new Plant[10];
    private static Plant[] shadowPlants = new Plant[10];
    private static Plant[] deck = new Plant[6];

    public Inventory() {
        plants[0] = new Plant("Sunflower");
        plants[1] = new Plant("Peashooter");
        plants[2] = new Plant("Cherry_Bomb");
        plants[3] = new Plant("Wall-nut");
        plants[4] = new Plant("Potato_Mine");
        plants[5] = new Plant("Snow_Pea");
        plants[6] = new Plant("Repeater");
        plants[7] = new Plant("Gatling_Pea");
        plants[8] = new Plant("Squash");
        plants[9] = new Plant("Tall-nut");
    }

    public void addDeck(Plant plant) throws Exception {
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

    public void removeDeck(Plant plant) throws Exception {
        boolean removed = false;
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] != null && deck[i].getName().equals(plant.getName())) {
                for (int j = 0; j < shadowPlants.length; j++) {
                    if (shadowPlants[j] != null && shadowPlants[j].getName().equals(plant.getName())) {
                        plants[j] = plant;
                        shadowPlants[j] = null;
                        // deck[i] = null;
                        int index = i;
                        while (i+1 < deck.length) {
                            if (deck[i] != null) {
                                deck[index] = deck[i+1];
                                index++;
                            }
                            i++;
                        }
                        removed = true;
                        break;
                    }
                }
                if (removed) {
                    break;
                }
            }
        }
        if (!removed) {
            throw new Exception("Tidak ada tanaman yang dipilih pada deck!");
        }
    }

    public void swapPlants(int idx1, int idx2, Plant[] array) throws Exception{
        if (idx1 == idx2) {
            throw new Exception("Tanaman tidak bisa ditukar dengan dirinya sendiri!");
        } else if (idx1 < 0 || idx1 >= array.length || idx2 < 0 || idx2 >= array.length) {
            throw new Exception("Indeks diluar batas!");
        } else if (array != shadowPlants && (array[idx1] == null || array[idx2] == null)) {
            throw new Exception("Tanaman tidak bisa ditukar dengan posisi kosong!");
        } 
        else {
            Plant temp = array[idx1];
            array[idx1] = array[idx2];
            array[idx2] = temp;
        }
    }

    public Plant[] getPlants() {
        return plants;
    }
    
    public Plant[] getShadowPlants() {
        return shadowPlants;
    }

    public Plant[] getDeck() {
        return deck;
    }
}

class Plant {
    private String name;
    // private ImageIcon image;

    public Plant(String name) {
        this.name = name;
        // try {
        //     String imagePath = "/gui/images/" + imageName;
        //     ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        //     Image originalImage = originalIcon.getImage();
        //     Image resizedImage = originalImage.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        //     this.image = new ImageIcon(resizedImage);
        // } catch (Exception e) {
        //     System.out.println("Gambar tidak tersedia!");
        // }
    }

    public String getName() {
        return name;
    }

    // public ImageIcon getImage() {
    //     return image;
    // }
}