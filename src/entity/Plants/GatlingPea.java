package entity.Plants;

import java.awt.event.ActionEvent;

import javax.swing.Timer;


import managers.PeasManager;
import managers.ZombiesManager;

public class GatlingPea extends Plant {
    private Timer shootTimer, secondTimer, thirdTimer, fourthTimer;
    
    public GatlingPea(int x, int y) {
        super("GatlingPea", 250, 100, 25, 8, -1, 15, false, x, y);
        this.setImage(getPlantImage("GatlingPea"));
        action();
    }

    public void action() {
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            if (ZombiesManager.checkZombiesInLane((int) this.getY())) {
                PeasManager.addPeaInLane((int) this.getX() + 50, (int) this.getY(), "Normal");
                
                secondTimer = new Timer(50, (ActionEvent e2) -> {
                    if (ZombiesManager.checkZombiesInLane((int) this.getY())) {
                        PeasManager.addPeaInLane((int) this.getX() + 50, (int) this.getY(), "Normal");
                        
                        thirdTimer = new Timer(50, (ActionEvent e3) -> {
                            if (ZombiesManager.checkZombiesInLane((int) this.getY())) {
                                PeasManager.addPeaInLane((int) this.getX() + 50, (int) this.getY(), "Normal");
                                
                                fourthTimer = new Timer(50, (ActionEvent e4) -> {
                                    if (ZombiesManager.checkZombiesInLane((int) this.getY())) {
                                        PeasManager.addPeaInLane((int) this.getX() + 50, (int) this.getY(), "Normal");
                                    }
                                });
                                fourthTimer.setRepeats(false);
                                fourthTimer.start();
                            }
                        });
                        thirdTimer.setRepeats(false);
                        thirdTimer.start();
                    }
                });
                secondTimer.setRepeats(false);
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
        if (thirdTimer != null && thirdTimer.isRunning()) {
            thirdTimer.stop();
        }
        if (fourthTimer != null && fourthTimer.isRunning()) {
            fourthTimer.stop();
        }
    }

}
