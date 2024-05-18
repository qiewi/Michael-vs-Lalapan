package entity.Zombies;

public class Conehead extends HeadwearType {

    public Conehead(int x, int y) {
        super("Conehead", 125, true, x, y);
        this.setImage(getZombieImage("Conehead"));
    }

    
}
