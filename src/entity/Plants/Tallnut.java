package entity.Plants;

public class TallNut extends Plant {
    public TallNut(int x, int y) {
        super("TallNut", 100, 2000, 0, 0, 0, 40, false, x, y);
        this.setImage(getPlantImage("TallNut"));
    }
}
