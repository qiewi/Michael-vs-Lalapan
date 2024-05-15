package entity.Plants;

public class Lilypad extends Plant {
    public Lilypad(int x, int y) {
        super("Lilypad", 25, 100, 0, 0, 0, 10, true, x, y);
        this.setImage(getPlantImage("Lilypad"));
    }
}
