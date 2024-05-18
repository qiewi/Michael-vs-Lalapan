package entity.Plants;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

import managers.PeasManager;
import managers.ZombiesManager;

public class SnowPea extends Plant{
    private Timer shootTimer;

    public SnowPea(int x, int y) {
        super("SnowPea", 175, 100, 25, 4, -1, 10, false, x, y);
        this.setImage(getPlantImage("SnowPea"));
        action();
    }

    public void action() {
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            if (ZombiesManager.checkZombiesInLane((int) this.getY())) {
                PeasManager.addPeaInLane((int) this.getX() + 50, (int) this.getY(), "Frozen");
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
