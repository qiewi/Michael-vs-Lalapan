package src.entity;

public class Inventory {
    private static Plant[] plants = new Plant[10];
    private static Plant[] shadowPlants = new Plant[10]; // menjaga posisi null plants + buat gui, plant yg di idx shadowPlants jadi abu2
    private static Plant[] deck = new Plant[10];

    public Inventory() {

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

    public void displayInventory() {
        System.out.println("Inventory:");
        for (int i = 0; i < plants.length; i++) {
            if (plants[i] != null) {
                System.out.println((i + 1) + ". " + plants[i].getName());
            } else {
                System.out.println((i + 1) + ". null");
            }
            
        }
    }

    public void displayDeck() {
        System.out.println("Deck:");
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] != null) {
                System.out.println((i + 1) + ". " + deck[i].getName());
            } else {
                System.out.println((i + 1) + ". null");
            }
        }
    }

    // public static void main(String[] args) {
    //     Inventory inventory = new Inventory();
        
    //     // Adding plants to inventory
    //     inventory.plants[0] = new Plant("Sunflower");
    //     inventory.plants[1] = new Plant("Peashooter");
    //     inventory.plants[2] = new Plant("Cherry Bomb");
    //     inventory.plants[3] = new Plant("Wall-nut");
    //     inventory.plants[4] = new Plant("Potato Mine");
    //     inventory.plants[5] = new Plant("Snow Pea");

    //     // Displaying inventory
    //     inventory.displayInventory();
    //     inventory.displayDeck();
    //     System.out.println();
        
    //     // Adding plants to deck
    //     try {
    //         inventory.addDeck(inventory.plants[3]);
    //         inventory.addDeck(inventory.plants[1]);
    //         inventory.addDeck(inventory.plants[5]);
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
        
    //     // // Displaying inventory & deck
    //     inventory.displayInventory();
    //     inventory.displayDeck();
    //     System.out.println();
        
    //     // Swapping plants in deck
    //     try {
    //         inventory.swapPlants(1, 2, inventory.deck);
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    //     System.out.println("After swapping:");
    //     inventory.displayInventory();
    //     inventory.displayDeck();
    //     System.out.println();
        
    //     // Removing a plant from deck
    //     try {
    //         inventory.removeDeck(inventory.deck[1]);
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    //     System.out.println("After removing:");
    //     inventory.displayInventory();
    //     inventory.displayDeck();
    //     System.out.println();
    // }
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
