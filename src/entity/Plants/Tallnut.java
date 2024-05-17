package entity.Plants;

public class Tallnut extends Plant {
    public Tallnut(int x, int y) {
        super("TallNut", 100, 2000, 0, 0, 0, 40, false, x, y);
        this.setImage(getPlantImage("TallNut"));
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'action'");
    }

    public void actionStop() {
    
    }
}
