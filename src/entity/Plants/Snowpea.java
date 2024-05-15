package entity.Plants;

public class Snowpea extends Plant{
    public Snowpea(int x, int y) {
        super("Snowpea", 175, 100, 25, 4, -1, 10, false, x, y);
        this.setImage(getPlantImage("Snowpea"));
    }

    public void action() {
    }
}
