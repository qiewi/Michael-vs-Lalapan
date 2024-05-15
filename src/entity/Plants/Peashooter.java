package entity.Plants;

public class Peashooter extends Plant {
    public Peashooter(int x, int y) {
        super("Peashooter", 100, 100, 25, 4, -1, 10, false, x, y);
        this.setImage(getPlantImage("Peashooter"));
    }

    public void action() {
    }
}
