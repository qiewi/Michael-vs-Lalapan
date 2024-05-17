package entity.Plants;

import entity.Action;

public class Squash extends Plant{
    public Squash(int x, int y) {
        super("Squash", 50, 100, 5000, 0, 1, 20, false, x, y);
        this.setImage(getPlantImage("Squash"));
    }
    
    public void action() {
    }

    public void actionStop() {
    
    }
}
