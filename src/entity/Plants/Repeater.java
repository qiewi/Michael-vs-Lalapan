package entity.Plants;

public class Repeater extends Plant {
    public Repeater(int x, int y) {
        super("Repeater", 200, 150, 25, 6, -1, 15, false, x, y);
        this.setImage(getPlantImage("Repeater"));
    }

    public void action() {
    }
}
