package entity.Plants;

public class TangleKelp extends Plant {
    public TangleKelp(int x, int y) {
        super("TangleKelp", 25, 100, 5000, 0, 1, 20, false, x, y);
        this.setImage(getPlantImage("TangleKelp"));
    }
}
