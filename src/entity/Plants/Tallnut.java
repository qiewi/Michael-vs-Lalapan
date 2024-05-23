package entity.Plants;

public class Tallnut extends Plant {
    public Tallnut(int x, int y) {
        super("TallNut", 100, 2000, 0, 0, 0, 20, false, x, y);
        this.setImage(getPlantImage("TallNut"));
    }

    @Override
    public void action() {
    }

    public void actionStop() {
    
    }
}
