package entity.Zombies;

public class ShieldZomB extends Zombie {

    private boolean shield_on = true;

    public ShieldZomB(String name, int extra_health, boolean shield_on, int x, int y) {
        super(name, 100 + extra_health, 100, 1, false, x, y);
        this.shield_on = shield_on;
    }

    public boolean getShield() {
        return shield_on;
    }

    public void setShield(boolean head) {
        this.shield_on = head;
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
