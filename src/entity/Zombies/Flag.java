package entity.Zombies;

public class Flag extends ShieldType {

    public Flag(int x, int y) {
        super("Flag", 50, true, x, y);
        this.setImage(getZombieImage("Flag"));
    }

}