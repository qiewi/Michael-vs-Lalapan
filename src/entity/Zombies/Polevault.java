package entity.Zombies;

public class Polevault extends VaultingType {

    public Polevault(int x, int y) {
        super("Polevault", 75, true, false, x, y);
        this.setImage(getZombieImage("Polevault"));
    }
}
