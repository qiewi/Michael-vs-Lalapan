package entity.Zombies;

public class ShieldZomB extends Zombie {

    private boolean shield_on = true;

    public ShieldZomB(String name, int extra_health, boolean shield_on) {
        super(name, 100 + extra_health, 100, 1, false);
        this.shield_on = shield_on;
    }

    public void setHead(boolean head) {
        this.shield_on = head;
    }

    public  void attack() {
        
    }

    // public boolean getAquatic() {
    //     return is_aquatic;
    // }

    /*    public void takeDamage(Plant Tanaman) {

        } */       
}
