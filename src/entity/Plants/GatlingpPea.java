package entity.Plants;

import entity.Action;

public class GatlingpPea extends Plant {
    public GatlingpPea(int x, int y) {
        super("GatlingPea", 250, 100, 25, 8, -1, 10, false, x, y);
        this.setImage(getPlantImage("GatlingPea"));
    }

    public void action() {    
    }

    public void actionStop() {
    
    }
}
