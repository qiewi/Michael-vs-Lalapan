package entity.Zombies;

import entity.Action;
import entity.Plants.Plant;

public class NormalZomB extends Zombie implements Action {

    public NormalZomB(int x, int y) {
        super("Normal Zombie", 125, 100, 1, false, x, y);
        this.setImage(getZombieImage("Normal"));
    }
    
    public void action() {

    }

    public void actionStop() {
        
    }

    // public boolean getAquatic() {
    //     return is_aquatic;
    // }

    /*    public void takeDamage(Plant Tanaman) {

        } */

}