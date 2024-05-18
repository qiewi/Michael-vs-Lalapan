package entity.Plants;

import java.awt.event.ActionEvent;

import javax.swing.Timer;


import managers.PeasManager;
import managers.ZombiesManager;

public class GatlingpPea extends Plant {
    private Timer shootTimer;
    
    public GatlingpPea(int x, int y) {
        super("GatlingPea", 250, 100, 25, 8, -1, 10, false, x, y);
        this.setImage(getPlantImage("GatlingPea"));
        action();
    }

    public void action() {
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            if (ZombiesManager.checkZombiesInLane((int) this.getY())) {
                PeasManager.addPeaInLane((int) this.getX() + 50, (int) this.getY(), "Normal");
                PeasManager.addPeaInLane((int) this.getX() + 80, (int) this.getY(), "Normal");
                PeasManager.addPeaInLane((int) this.getX() + 110, (int) this.getY(), "Normal");
                PeasManager.addPeaInLane((int) this.getX() + 140, (int) this.getY(), "Normal");
            }
        });

        shootTimer.start();
    }

    public void actionStop() { // mungkin bakal taro di interface
        if (shootTimer != null && shootTimer.isRunning()) {
            shootTimer.stop();
        } 
    }

}
