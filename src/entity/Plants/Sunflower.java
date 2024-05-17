package entity.Plants;

import java.util.Timer;
import java.util.TimerTask;

import entity.Sun;

public class Sunflower extends Plant {
    private Sun sun;
    private TimerTask sunTask;

    public Sunflower(int x, int y) {
        super("Sunflower", 50, 100, 0, 0, 0, 10, false, x, y);
        this.setImage(getPlantImage("Sunflower"));

        // Inisiasi untuk penambahan sun
        sun = new Sun();
        addSunSF();
    }

    Timer timer = new Timer();
    

    public void addSunSF() {
        timer = new Timer();
        sunTask = new TimerTask() {
            public void run() {
                sun.addSun(25);
            }
        };
        timer.scheduleAtFixedRate(sunTask, 0, 3000);
    }

    public void stopSunSF() {
        if (timer != null) {
            sunTask.cancel();
            timer.cancel();
            timer.purge();
        }
    }

    public void action() {
    }
}