package entity.Plants;

import java.util.Timer;
import java.util.TimerTask;

import entity.Sun;

public class Sunflower extends Plant {
    private Sun sun;

    public Sunflower(int x, int y) {
        super("Sunflower", 50, 100, 0, 0, 0, 10, false, x, y);
        this.setImage(getPlantImage("Sunflower"));

        // Inisiasi untuk penambahan sun
        sun = new Sun();
        addSunSF();
    }

    Timer timer = new Timer();

    public void addSunSF() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                sun.addSun(25);
            }
        }, 0000, 3000);
    }

    public void action() {
    }
}
