package entity.Zombies;


public class Duckytube extends Zombie {
    

    public Duckytube(int x, int y) {
        super("Duckytube", 100, 100, 1, true, x, y);
        this.setImage(getZombieImage("Duckytube"));
    }

    @Override
    public void action() {
    }

    @Override
    public void actionStop() {
    }


}
