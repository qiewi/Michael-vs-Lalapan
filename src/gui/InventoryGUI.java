package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class InventoryGUI extends JFrame {
    private Inventory inventory;
    private JButton[] inventoryButtons;
    private JButton[] deckButtons;
    private JButton[] panelButtons;   
    private boolean selectedSelect = false;
    private boolean selectedClear = false;
    private boolean selectedSwap = false;

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
                selectedClear = true;
                refreshInventoryAndDeck();
            }
        });

        panelButtons[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedSelect = !selectedSelect;
                refreshInventoryAndDeck();
            }
        });

        panelButtons[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedSwap = !selectedSwap;
                refreshInventoryAndDeck();
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

    private int firstIndexSwap = -1;

    private void refreshInventoryAndDeck() {
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);
        Border border2 = BorderFactory.createLineBorder(Color.RED, 3);
        Border borderDef = BorderFactory.createLineBorder(Color.GRAY, 1);
        Plant[] inventoryPlants = inventory.getPlants();
        Plant[] shadowPlants = inventory.getShadowPlants();
        Plant[] deckPlants = inventory.getDeck();

        for (int i = 0; i < 10; i++) {
            if (inventoryPlants[i] != null) {
                inventoryButtons[i].setText(inventoryPlants[i].getName());
                inventoryButtons[i].setEnabled(true);
                if (selectedSelect || selectedSwap) {
                    inventoryButtons[i].setBorder(border);
                    if (firstIndexSwap != -1) {
                        inventoryButtons[firstIndexSwap].setBorder(border2);
                    }
                } else {
                    inventoryButtons[i].setBorder(borderDef);
                }
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
                    try {
                        if (selectedSelect && inventoryPlants[index] != null) {
                            inventory.addDeck(inventoryPlants[index]);
                            refreshInventoryAndDeck();
                        } else if (selectedSwap && inventoryPlants[index] != null) {
                            if (firstIndexSwap == -1) {
                                firstIndexSwap = index;
                            } if (index != firstIndexSwap && inventoryPlants[firstIndexSwap] != null && inventoryPlants[index] != null) {
                                System.out.println(firstIndexSwap);
                                System.out.println(index);
                                System.out.println(inventoryPlants[firstIndexSwap].getName() + ", " + inventoryPlants[index].getName());
                                inventory.swapPlants(firstIndexSwap, index, inventoryPlants);
                                inventory.swapPlants(firstIndexSwap, index, shadowPlants);
                            }
                            refreshInventoryAndDeck();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
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

        if (selectedSelect == false) {
            panelButtons[1].setText("Select");
        } else {
            panelButtons[1].setText("Unselect");
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
            panelButtons[2].setText("Swap");
        } else {
            panelButtons[2].setText("Cancel");
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
