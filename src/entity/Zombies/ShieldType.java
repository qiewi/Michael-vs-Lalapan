package entity.Zombies;

public class ShieldType extends Zombie {

    private boolean shield_on = true;
    private static final int DEFAULT_HEALTH = 100;

    public ShieldType(String name, int extra_health, boolean shield_on, int x, int y) {
        super(name, DEFAULT_HEALTH + extra_health, 100, 1, false, x, y);
        this.shield_on = shield_on;
    }

    public boolean getShield() {
        return shield_on;
    }

    public void setShield(boolean head) {
        this.shield_on = head;
    }

    public int getDefHealth() {
        return DEFAULT_HEALTH;
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
