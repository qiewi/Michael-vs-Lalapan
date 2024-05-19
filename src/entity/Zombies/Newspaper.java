package entity.Zombies;

public class Newspaper extends ShieldType {
    private int angerTick = -1;

    public Newspaper(int x, int y) {
        super("Newspaper", 125, true, x, y);
        this.setImage(getZombieImage("Newspaper"));
    }

    public void setAngerTick(int angerTick) {
        this.angerTick = angerTick;
    }

    public int getAngerTick() {
        return angerTick;
    }
}
