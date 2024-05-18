package entity.Zombies;

public class Buckethead extends HeadwearType {
    public Buckethead(int x, int y) {
        super("Buckethead", 175, true, x, y);
        this.setImage(getZombieImage("Buckethead"));
    }
}
