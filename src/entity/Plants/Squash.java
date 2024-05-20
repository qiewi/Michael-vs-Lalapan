package entity.Plants;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

import managers.ZombiesManager;

public class Squash extends Plant{
    private Timer actionTimer, actionTimer2;
    private boolean smashed = false;

    public Squash(int x, int y) {
        super("Squash", 50, 100, 5000, 0, 1, 20, false, x, y);
        this.setImage(getPlantImage("Squash"));
        action();
    }
    
    public void action() {
        actionTimer = new Timer(0, (ActionEvent e) -> {
            if (ZombiesManager.squashDamage((int) this.getX(), (int) this.getY()) != -1) {
                if (!smashed) {
                    this.setX(ZombiesManager.squashDamage((int) this.getX(), (int) this.getY()));
                } 
                this.alreadySmashed();
                actionTimer2 = new Timer(200, (ActionEvent e2) -> {
                    this.setHealth(0);
                    actionTimer2.stop();
                });
                actionTimer2.setRepeats(false); // Set to execute only once
                actionTimer2.start();
            }
        });
        actionTimer.start();
    }

    public void actionStop() {
        if (actionTimer != null && actionTimer.isRunning()) {
            actionTimer.stop();
        } 
        if (actionTimer2 != null && actionTimer2.isRunning()) {
            actionTimer2.stop();
        } 
    }

    public void alreadySmashed() {
        smashed = true;
    }
}
