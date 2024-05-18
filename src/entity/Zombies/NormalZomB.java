package entity.Zombies;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

import entity.Plants.Plant;
import managers.PlantsManager;

public class NormalZomB extends Zombie {
    

    public NormalZomB(int x, int y) {
        super("Normal Zombie", 125, 100, 1, false, x, y);
        this.setImage(getZombieImage("Normal"));
    }

    public  void attack() {
        
    }

    

    @Override
    public void action() {
    }

    @Override
    public void actionStop() {
    }
}
