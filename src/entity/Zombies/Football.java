package entity.Zombies;

public class Football extends HeadwearType {

    public Football(int x, int y) {
        super("Football", 200, true, x, y);
        this.setImage(getZombieImage("Football"));
        this.setSpeed(-0.3f);
    }

}
