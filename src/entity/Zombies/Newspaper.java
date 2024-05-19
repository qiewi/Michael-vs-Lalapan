package entity.Zombies;

public class Newspaper extends ShieldType {
    public Newspaper(int x, int y) {
        super("Newspaper", 120, true, x, y);
        this.setImage(getZombieImage("Newspaper"));
    }
}
