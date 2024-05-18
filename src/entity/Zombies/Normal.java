package entity.Zombies;


public class Normal extends Zombie {
    

    public Normal(int x, int y) {
        super("Normal", 125, 100, 1, false, x, y);
        this.setImage(getZombieImage("Normal"));
        
    }

    @Override
    public void action() {
    }

    @Override
    public void actionStop() {
    }
}
