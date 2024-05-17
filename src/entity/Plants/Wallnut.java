package entity.Plants;

public class Wallnut extends Plant{
    public Wallnut(int x, int y) {
        super("WallNut", 50, 1000, 0, 0, 0, 20, false, x, y);
        this.setImage(getPlantImage("WallNut"));
    }
    
}
