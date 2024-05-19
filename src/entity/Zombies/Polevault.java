package entity.Zombies;

public class Polevault extends VaultingType {

    public Polevault(int x, int y) {
        super("Polevault", 125, true, x, y);
        this.setImage(getZombieImage("Polevault"));
    }

    
}
