package entity.Plants;

import java.util.Timer;
import java.util.TimerTask;


public class Sunflower extends Plant {

    public Sunflower(int x, int y) {
        super("Sunflower", 50, 100, 0, 0, 0, 10, false, x, y);
        this.setImage(getPlantImage("Sunflower"));
    }

    private int sun = 25;
    Timer timer = new Timer();

    public void addSunSF() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                setSun(getSun() + 25);
            }
        }, 0000, 10000);
    }

    public int getSun() {
        return sun;
    }
    
    public void setSun(int sun) {
        this.sun = sun;
    }

    public void action() {
    }
}
