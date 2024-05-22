package entity.Zombies;

public class Dolphin extends VaultingType {

    public Dolphin(int x, int y) {
        super("Dolphin0", 75, true, true, x, y);
        this.setImage(getZombieImage("Dolphin0"));
    }
}