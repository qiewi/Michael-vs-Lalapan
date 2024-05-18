package entity.Plants;

import java.util.Timer;
import java.util.TimerTask;

import managers.SunDropManager;
import objects.Sun;

public class Sunflower extends Plant {
    private Sun sun;
    private TimerTask sunTask;
    private int sunX, sunY;

    public Sunflower(int x, int y) {
        super("Sunflower", 50, 100, 0, 0, 0, 10, false, x, y);
        this.setImage(getPlantImage("Sunflower"));
        this.sunX = x;
        this.sunY = y;

        // Inisiasi untuk penambahan sun
        sun = new Sun();
        action();
    }

    Timer timer = new Timer();
    

    public void action() {
        timer = new Timer();
        sunTask = new TimerTask() {
            public void run() {
                SunDropManager.addSunDrop(sunX, sunY);
                // sun.addSun(25);
            }
        };
        timer.scheduleAtFixedRate(sunTask, 0, 3000);
    }

    public void actionStop() {
        if (timer != null) {
            sunTask.cancel();
            timer.cancel();
            timer.purge();
        }
    }
}