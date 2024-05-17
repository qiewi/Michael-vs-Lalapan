package entity.Plants;

public class Tanglekelp extends Plant {
    public Tanglekelp(int x, int y) {
        super("TangleKelp", 25, 100, 5000, 0, 1, 20, true, x, y);
        this.setImage(getPlantImage("TangleKelp"));
    }
}
