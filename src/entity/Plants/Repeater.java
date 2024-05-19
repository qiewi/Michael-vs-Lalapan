package entity.Plants;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

import managers.PeasManager;
import managers.ZombiesManager;

public class Repeater extends Plant {
    private Timer shootTimer;
    
    public Repeater(int x, int y) {
        super("Repeater", 200, 150, 25, 6, -1, 15, false, x, y);
        this.setImage(getPlantImage("Repeater"));
        action();
    }

    public void action() {
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            if (ZombiesManager.checkZombiesInLane((int) this.getY())) {
                PeasManager.addPeaInLane((int) this.getX() + 50, (int) this.getY(), "Normal"); // jadiin dua timer
                PeasManager.addPeaInLane((int) this.getX() + 80, (int) this.getY(), "Normal");
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
