package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class InventoryGUI extends JFrame {
    private Inventory inventory;
    private JButton[] inventoryButtons;
    private JButton[] deckButtons;
    // private JButton[] shadowButtons;
    private JButton[] panelButtons;
    private Plant selectedPlant = null;    

    public InventoryGUI() {
        inventory = new Inventory();

        setTitle("Plant Inventory");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 300);
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
        inventoryPanel.setBorder(BorderFactory.createTitledBorder("Inventory"));

        // Deck Panel
        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(new GridLayout(3, 2));
        deckPanel.setBorder(BorderFactory.createTitledBorder("Deck"));

        // // Shadow Panel
        // JPanel shadowPanel = new JPanel();
        // shadowPanel.setLayout(new GridLayout(5, 2));
        // shadowPanel.setBorder(BorderFactory.createTitledBorder("Shadow"));

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
        panelPanel.setLayout(new GridLayout(1, 4));
        panelPanel.setBorder(BorderFactory.createTitledBorder("Panel"));

        bottomPanel.add(panelPanel);
        gbc.weighty = 0.2;
        gbc.gridy = 1;
        mainPanel.add(bottomPanel, gbc);

        getContentPane().add(mainPanel);

        inventoryButtons = new JButton[10];
        deckButtons = new JButton[10];
        // shadowButtons = new JButton[10];
        panelButtons = new JButton[4];

        // Initialize inventory buttons
        for (int i = 0; i < 10; i++) {
            inventoryButtons[i] = new JButton();
            inventoryPanel.add(inventoryButtons[i]);
        }

        // Initialize deck buttons
        for (int i = 0; i < 6; i++) {
            deckButtons[i] = new JButton();
            deckPanel.add(deckButtons[i]);
        }

        // Initialize shadow buttons
        // for (int i = 0; i < 10; i++) {
        //     shadowButtons[i] = new JButton();
        //     shadowPanel.add(shadowButtons[i]);
        // }

        // Initialize panel buttons
        panelButtons[0] = new JButton("Clear");
        panelButtons[1] = new JButton("Select");
        panelButtons[2] = new JButton("Swap");
        panelButtons[3] = new JButton("Start");
        for (int i = 0; i < 4; i++) {
            panelPanel.add(panelButtons[i]);
        }

        // Add action listeners to panel buttons
        panelButtons[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i < deckButtons.length; i++) {
                        if (deckButtons[i] != null) {
                            inventory.removeDeck(inventory.getDeck()[i]);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                refreshInventoryAndDeck();
            }
        });

        panelButtons[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code for select button
                if (selectedPlant != null) {
                    try {
                        inventory.addDeck(selectedPlant);
                        refreshInventoryAndDeck();
                        selectedPlant = null; // Reset selected plant
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No plant selected!");
                }
            }
        });

        panelButtons[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code for swap button
                JOptionPane.showMessageDialog(null, "Swap button clicked!");
            }
        });

        panelButtons[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code for start button
                JOptionPane.showMessageDialog(null, "Start button clicked!");
            }
        });

        refreshInventoryAndDeck();
    }

    private void refreshInventoryAndDeck() {
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 2);
        Border borderDef = BorderFactory.createLineBorder(Color.GRAY, 1);
        Plant[] inventoryPlants = inventory.getPlants();
        Plant[] shadowPlants = inventory.getShadowPlants();
        Plant[] deckPlants = inventory.getDeck();

        // Update inventory buttons
        for (int i = 0; i < 10; i++) {
            if (inventoryPlants[i] != null) {
                inventoryButtons[i].setText(inventoryPlants[i].getName());
                inventoryButtons[i].setEnabled(true);
            } else if (shadowPlants[i] != null) {
                inventoryButtons[i].setText(shadowPlants[i].getName());
                inventoryButtons[i].setEnabled(false);
                inventoryButtons[i].setBorder(borderDef);
            } else {
                inventoryButtons[i].setText("Empty");
                inventoryButtons[i].setEnabled(false);
            }
            final int index = i;
            inventoryButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (selectedPlant != null) {
                        for (int i = 0; i < inventoryButtons.length; i++) {
                            if (inventoryButtons[i].getText().equals(selectedPlant.getName())) {
                                inventoryButtons[i].setBorder(borderDef);
                                break;
                            }
                        }
                    }
                    inventoryButtons[index].setBorder(border);
                    // Set selected plant
                    selectedPlant = inventoryPlants[index];
                }
            });
        }

        // Update deck buttons
        for (int i = 0; i < 6; i++) {
            if (deckPlants[i] != null) {
                deckButtons[i].setText(deckPlants[i].getName());
                deckButtons[i].setEnabled(true);
            } else {
                deckButtons[i].setText("Empty");
                deckButtons[i].setEnabled(false);
            }
            final int index = i;
            deckButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (deckPlants[index] != null) { // Check if deckPlants[index] is not null
                            inventory.removeDeck(deckPlants[index]);
                            refreshInventoryAndDeck();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            });
        }

        // Update shadow buttons
        // for (int i = 0; i < 10; i++) {
        //     if (shadowPlants[i] != null) {
        //         shadowButtons[i].setText(shadowPlants[i].getName());
        //         shadowButtons[i].setEnabled(true);
        //     } else {
        //         shadowButtons[i].setText("Empty");
        //         shadowButtons[i].setEnabled(false);
        //     }
        // }

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
        plants[0] = new Plant("Sunflower");
        plants[1] = new Plant("Peashooter");
        plants[2] = new Plant("Cherry Bomb");
        plants[3] = new Plant("Wall-nut");
        plants[4] = new Plant("Potato Mine");
        plants[5] = new Plant("Snow Pea");
        plants[6] = new Plant("Repeater");
        plants[7] = new Plant("Gatling Gun");
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
            throw new Exception("Deck penuh atau tidak ada tanaman yang dipilih pada inventory!");
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
        } else if (array[idx1] == null || array[idx2] == null) {
            throw new Exception("Tanaman tidak bisa ditukar dengan posisi kosong!");
        } else {
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

    public Plant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
