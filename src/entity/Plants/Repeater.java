package entity.Plants;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

import managers.PeasManager;
import managers.ZombiesManager;

public class Repeater extends Plant {
    private Timer shootTimer, secondTimer;
    
    public Repeater(int x, int y) {
        super("Repeater", 200, 150, 25, 6, -1, 15, false, x, y);
        this.setImage(getPlantImage("Repeater"));
        action();
    }

    public void action() {
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            if (ZombiesManager.checkZombiesInLane((int) this.getY())) {
                PeasManager.addPeaInLane((int) this.getX() + 50, (int) this.getY(), "Normal"); // jadiin dua timer
                secondTimer = new Timer(50, (ActionEvent e2) -> {
                    PeasManager.addPeaInLane((int) this.getX() + 50, (int) this.getY(), "Normal");
                    secondTimer.stop(); // Stop the timer after shooting the second pea
                });
                secondTimer.setRepeats(false); // Set to execute only once
                secondTimer.start();
            }
        });
        shootTimer.start();
    }

    public void actionStop() { // mungkin bakal taro di interface
        if (shootTimer != null && shootTimer.isRunning()) {
            shootTimer.stop();
        } 
        if (secondTimer != null && secondTimer.isRunning()) {
            secondTimer.stop();
        } 
    }
}
