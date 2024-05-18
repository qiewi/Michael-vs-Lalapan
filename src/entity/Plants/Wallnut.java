package entity.Plants;

public class WallNut extends Plant{
    public WallNut(int x, int y) {
        super("WallNut", 50, 1000, 0, 0, 0, 20, false, x, y);
        this.setImage(getPlantImage("WallNut"));
    }

    @Override
    public void action() {
    }

    public void actionStop() {
    
    }
    
}
