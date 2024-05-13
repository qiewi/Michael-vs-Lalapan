package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class InventoryGUI extends JFrame {
    private Inventory inventory;
    private MyButton[] inventoryButtons;
    private MyButton[] deckButtons;
    private JButton[] panelButtons;
    private boolean selectedClear = false;
    private boolean selectedSwap = false;

    public InventoryGUI() {
        inventory = new Inventory();

        setTitle("Plant Inventory");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;

        // Top Panel
        JPanel topPanel = new JPanel(new GridLayout(1, 2));

        // Inventory Panel
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(5, 2));
        inventoryPanel.setBorder(BorderFactory.createTitledBorder("CHOOSE YOUR PLANTS!"));

        // Deck Panel
        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(new GridLayout(3, 2));
        deckPanel.setBorder(BorderFactory.createTitledBorder("Deck"));

        // topPanel.add(shadowPanel);
        topPanel.add(deckPanel);
        topPanel.add(inventoryPanel);
        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridy = 0;
        mainPanel.add(topPanel, gbc);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridLayout(1, 1));

        // panel Panel
        JPanel panelPanel = new JPanel();
        panelPanel.setLayout(new GridLayout(1, 3));
        panelPanel.setBorder(BorderFactory.createTitledBorder("Panel"));

        bottomPanel.add(panelPanel);
        gbc.weighty = 0.2;
        gbc.gridy = 1;
        mainPanel.add(bottomPanel, gbc);

        getContentPane().add(mainPanel);

        inventoryButtons = new MyButton[10];
        deckButtons = new MyButton[6];
        panelButtons = new JButton[3];

        // Initialize inventory buttons
        for (int i = 0; i < 10; i++) {
            inventoryButtons[i] = new MyButton();
            inventoryPanel.add(inventoryButtons[i]);
        }

        // Initialize deck buttons
        for (int i = 0; i < 6; i++) {
            deckButtons[i] = new MyButton();
            deckPanel.add(deckButtons[i]);
        }

        // Initialize panel buttons
        panelButtons[0] = new JButton("Clear");
        panelButtons[1] = new JButton("Swap");
        panelButtons[2] = new JButton("Start");
        for (int i = 0; i < 3; i++) {
            panelPanel.add(panelButtons[i]);
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

        refreshInventoryAndDeck();
    }

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
                    inventoryButtons[i].setBorder1(true);
                    if (firstIndexSwapInventory != -1) {
                        inventoryButtons[firstIndexSwapInventory].setBorder2(true);
                    }
                } else {
                    inventoryButtons[i].setBorder1(false);
                    inventoryButtons[i].setBorder2(false);
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
                    deckButtons[i].setBorder1(true);
                    if (firstIndexSwapDeck != -1) {
                        deckButtons[firstIndexSwapDeck].setBorder2(true);
                    }
                } else {
                    deckButtons[i].setBorder1(false);
                    deckButtons[i].setBorder2(false);
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
                new InventoryGUI().setVisible(true);
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
            Image resizedImage = originalImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
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