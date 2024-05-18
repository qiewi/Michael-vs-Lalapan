package entity.Zombies;

import entity.Action;

public class ShieldZomB extends Zombie implements Action {

    private boolean shield_on = true;

    public ShieldZomB(int x, int y) {
        super("Shield Zombie", 100 + 200, 100, 1, false, x, y);
    }

    public boolean getShield() {
        return shield_on;
    }

    public void setShield(boolean shield_on) {
        this.shield_on = shield_on;
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
