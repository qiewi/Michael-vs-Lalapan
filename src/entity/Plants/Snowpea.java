package entity.Plants;

import entity.Action;

public class SnowPea extends Plant{
    public SnowPea(int x, int y) {
        super("SnowPea", 175, 100, 25, 4, -1, 10, false, x, y);
        this.setImage(getPlantImage("SnowPea"));
    }

    public void action() {
    }

    public void actionStop() {
    
    }
}
