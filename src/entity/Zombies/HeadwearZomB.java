package entity.Zombies;

import entity.Action;

public class HeadwearZomB extends Zombie implements Action {

    private boolean head_on = true;

    public HeadwearZomB(String name, int extra_health, int x, int y) {
        super(name, 100 + extra_health, 100, 1, false, x, y);
    }

    public boolean setHead() {
        return head_on;
    }

    public void setHead(boolean head) {
        this.head_on = head;
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