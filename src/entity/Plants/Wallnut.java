package entity.Plants;

public class Wallnut extends Plant{
    public Wallnut(int x, int y) {
        super("WallNut", 50, 1000, 0, 0, 0, 10, false, x, y);
        this.setImage(getPlantImage("WallNut"));
    }

    public void action() {
    }

    public void actionStop() {
    
    }
    
}
