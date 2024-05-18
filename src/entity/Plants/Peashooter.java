package entity.Plants;

import java.awt.event.ActionEvent;
import managers.PeasManager;
import managers.ZombiesManager;

import javax.swing.Timer;

public class Peashooter extends Plant {
    private Timer shootTimer;
    
    public Peashooter(int x, int y) {
        super("Peashooter", 100, 100, 25, 4, -1, 10, false, x, y);
        this.setImage(getPlantImage("Peashooter"));
        action();
    }

    public void action() {
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            if (ZombiesManager.checkZombiesInLane((int) this.getY())) {
                PeasManager.addPeaInLane((int) this.getX() + 50, (int) this.getY());
            }
        });
        shootTimer.setInitialDelay(0);
        shootTimer.start();
    }

    public void actionStop() { // mungkin bakal taro di interface
        if (shootTimer != null && shootTimer.isRunning()) {
            shootTimer.stop();
        }
    }
}
