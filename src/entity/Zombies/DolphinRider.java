package entity.Zombies;

public class DolphinRider extends VaultingType {

    public DolphinRider(int x, int y) {
        super("DolphinRider", 125, true, x, y);
        this.setImage(getZombieImage("DolphinRider"));
    }
}