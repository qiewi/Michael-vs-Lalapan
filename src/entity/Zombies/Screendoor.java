package entity.Zombies;

public class Screendoor extends ShieldType {
    public Screendoor(int x, int y) {
        super("Screendoor", 200, true, x, y);
        this.setImage(getZombieImage("Conehead"));
    }
}
