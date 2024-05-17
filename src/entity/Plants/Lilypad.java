package entity.Plants;

public class Lilypad extends Plant {
    public Lilypad(int x, int y) {
        super("LilyPad", 25, 100, 0, 0, 0, 10, true, x, y);
        this.setImage(getPlantImage("LilyPad"));
    }
}
