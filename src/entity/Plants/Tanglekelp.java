package entity.Plants;

import entity.Action;

public class TangleKelp extends Plant implements Action {
    public TangleKelp(int x, int y) {
        super("TangleKelp", 25, 100, 5000, 0, 1, 20, true, x, y);
        this.setImage(getPlantImage("TangleKelp"));
    }

    @Override
    public void action() {
    }

    public void actionStop() {
    
    }
}
