package entity.Zombies;

public class Football extends HeadwearType {

    public Football(int x, int y) {
        super("Football", 125, true, x, y);
        this.setImage(getZombieImage("Football"));
    }

}
