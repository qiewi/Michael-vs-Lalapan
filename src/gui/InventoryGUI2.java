package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.event.ComponentAdapter;
// import java.awt.event.ComponentEvent;

public class InventoryGUI2 extends JFrame {
    private Inventory inventory;
    private MyButton[] inventoryButtons;
    private MyButton[] deckButtons;
    private MyButton[] panelButtons;
    private boolean selectedClear = false;
    private boolean selectedSwap = false;

    // // Initialize inventory buttons
    // private static int startX = 30;
    // private static int startY = 150;
    // private static int buttonWidth = 70;
    // private static int buttonHeight = 70;
    // private static int spacing = 10;

    // // Initialize deck buttons
    // private static int startXDeck = 140;
    // private static int startYDeck = 10;

    // // Initialize panel buttons
    // private static int startXPanel = 30;
    // private static int startYPanel = 570;
    // private static int buttonWidthPanel = 220;
    // private static int buttonHeightPanel = 40;
    // private static int spacingPanel = 5;

    public InventoryGUI2() {
        inventory = new Inventory();

        setTitle("Plant Inventory");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1440, 1080); // Adjusted size to better accommodate the background image
        setMinimumSize(getSize());
        setLocationRelativeTo(null);

        // Main Panel with Background Image
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/gui/images/InventoryScene.png"));
                    g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    System.out.println("Gambar tidak ditemukan!");
                }
            }
        };
        mainPanel.setLayout(null);

        getContentPane().add(mainPanel);

        inventoryButtons = new MyButton[16];
        deckButtons = new MyButton[6];
        panelButtons = new MyButton[3];

        // Initialize inventory buttons
        int startX = 30;
        int startY = 150;
        int buttonWidth = 70;
        int buttonHeight = 70;
        int spacing = 10;
        // Initialize deck buttons
        int startXDeck = 140;
        int startYDeck = 10;
        // Initialize panel buttons
        int startXPanel = 30;
        int startYPanel = 570;
        int buttonWidthPanel = 220;
        int buttonHeightPanel = 40;
        int spacingPanel = 5;

        // Initialize inventory buttons
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                inventoryButtons[i*8+j] = new MyButton();
                int x = startX + (j % 8) * (buttonWidth + spacing);
                int y = startY + (j / 8) * (buttonHeight + spacing);
                inventoryButtons[i*8+j].setBounds(x, y, buttonWidth, buttonHeight);
                mainPanel.add(inventoryButtons[i*8+j]);
            }
            startX = 30;
            startY = 150 + buttonHeight + spacing;
        }

        // Initialize deck buttons
        for (int i = 0; i < 6; i++) {
            deckButtons[i] = new MyButton();
            int x = startXDeck + i * (buttonWidth + spacing);
            deckButtons[i].setBounds(x, startYDeck, buttonWidth, buttonHeight);
            mainPanel.add(deckButtons[i]);
        }

        // Initialize panel buttons
        panelButtons[0] = new MyButton("Clear");
        panelButtons[1] = new MyButton("Swap");
        panelButtons[2] = new MyButton("Start");
        for (int i = 0; i < 3; i++) {
            int x = startXPanel + i * (buttonWidthPanel + spacingPanel);
            panelButtons[i].setBounds(x, startYPanel, buttonWidthPanel, buttonHeightPanel);
            mainPanel.add(panelButtons[i]);
        }

        // Add action listeners to panel buttons
        panelButtons[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedClear = true;
                refreshInventoryAndDeck();
            }
        });

        panelButtons[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedSwap = !selectedSwap;
                refreshInventoryAndDeck();
            }
        });

        panelButtons[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code for start button
                JOptionPane.showMessageDialog(null, "Start button clicked!");
            }
        });

        // // Add ComponentListener to handle resizing
        // addComponentListener(new ComponentAdapter() {
        //     @Override
        //     public void componentResized(ComponentEvent e) {
        //         repositionComponents(mainPanel.getWidth(), mainPanel.getHeight());
        //     }
        // });

        // // Initial positioning
        // repositionComponents(getWidth(), getHeight());

        refreshInventoryAndDeck();
    }

    // private void repositionComponents(int panelWidth, int panelHeight) {
    //     int startX = panelWidth / 22;
    //     int startY = panelHeight / 4;
    //     int spacing = buttonWidth / 7;

    //     // Position inventory buttons
    //     for (int i = 0; i < 2; i++) {
    //         for (int j = 0; j < 5; j++) {
    //             int x = startX + (j % 5) * (buttonWidth + spacing);
    //             int y = startY + i * (buttonHeight + spacing);
    //             inventoryButtons[i * 5 + j].setBounds(x, y, buttonWidth, buttonHeight);
    //         }
    //     }

    //     int startXDeck = panelWidth / 10;
    //     int startYDeck = panelHeight / 48;

    //     // Position deck buttons
    //     for (int i = 0; i < 6; i++) {
    //         int x = startXDeck + i * (buttonWidth + spacing);
    //         deckButtons[i].setBounds(x, startYDeck, buttonWidth, buttonHeight);
    //     }

    //     int startXPanel = startX / 2;
    //     int startYPanel = panelHeight - (buttonHeight);
    //     int spacingPanel = spacing;

    //     // Position panel buttons
    //     for (int i = 0; i < 3; i++) {
    //         int x = startXPanel + i * (buttonWidthPanel + spacingPanel);
    //         panelButtons[i].setBounds(x, startYPanel, buttonWidthPanel, buttonHeightPanel);
    //     }
    // }

    private int firstIndexSwapInventory;
    private int firstIndexSwapDeck;

    private void refreshInventoryAndDeck() {
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);
        Border border2 = BorderFactory.createLineBorder(Color.RED, 3);
        Border borderDef = BorderFactory.createLineBorder(Color.GRAY, 1);
        Plant[] inventoryPlants = inventory.getPlants();
        Plant[] shadowPlants = inventory.getShadowPlants();
        Plant[] deckPlants = inventory.getDeck();
        if (!selectedSwap) {
            firstIndexSwapInventory = -1;
            firstIndexSwapDeck = -1;
        }

        for (int i = 0; i < 10; i++) {
            ActionListener[] actionListeners = inventoryButtons[i].getActionListeners();
            for (int j = 0; j < actionListeners.length; j++) {
                inventoryButtons[i].removeActionListener(actionListeners[j]);
            }
            if (inventoryPlants[i] != null) {
                inventoryButtons[i].setIcon(inventoryPlants[i].getImage());
                inventoryButtons[i].setEnabled(true);
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
                inventoryButtons[i].setIcon(shadowPlants[i].getImage());
                inventoryButtons[i].setEnabled(false);
                inventoryButtons[i].setBorder(borderDef);
            } else {
                inventoryButtons[i].setText("Empty");
                inventoryButtons[i].setEnabled(false);
            }
            final int index = i;
            inventoryButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
            });
        }

        // Update deck buttons
        for (int i = 0; i < 6; i++) {
            ActionListener[] actionListeners = deckButtons[i].getActionListeners();
            for (int j = 0; j < actionListeners.length; j++) {
                deckButtons[i].removeActionListener(actionListeners[j]);
            }
            if (deckPlants[i] != null) {
                deckButtons[i].setIcon(deckPlants[i].getImage());
                deckButtons[i].setEnabled(true);
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
                deckButtons[i].setIcon(null);
                deckButtons[i].setEnabled(false);
            }
            final int index = i;
            deckButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
            });
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

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InventoryGUI2().setVisible(true);
            }
        });
    }
}

class Inventory {
    private static Plant[] plants = new Plant[10];
    private static Plant[] shadowPlants = new Plant[10];
    private static Plant[] deck = new Plant[6];

    public Inventory() {
        plants[0] = new Plant("Sunflower", "Sunflower.png");
        plants[1] = new Plant("Peashooter", "Peashooter.png");
        plants[2] = new Plant("Cherry Bomb", "Cherry_Bomb.png");
        plants[3] = new Plant("Wall-nut", "Wall-nut.png");
        plants[4] = new Plant("Potato Mine", "Potato_Mine.png");
        plants[5] = new Plant("Snow Pea", "Snow_Pea.png");
        plants[6] = new Plant("Repeater", "Repeater.png");
        plants[7] = new Plant("Gatling Pea", "Gatling_Pea.png");
        plants[8] = new Plant("Squash", "Squash.png");
        plants[9] = new Plant("Tall-nut", "Tall-nut.png");
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
                        deck[i] = null;
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
    private ImageIcon image;

    public Plant(String name, String imageName) {
        this.name = name;
        try {
            String imagePath = "/gui/images/" + imageName;
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            this.image = new ImageIcon(resizedImage);
        } catch (Exception e) {
            System.out.println("Gambar tidak tersedia!");
        }
    }

    public String getName() {
        return name;
    }

    public ImageIcon getImage() {
        return image;
    }
}