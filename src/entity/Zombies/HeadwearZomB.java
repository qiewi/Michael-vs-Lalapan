package entity.Zombies;

public class HeadwearZomB extends Zombie {

    private boolean head_on = true;

    public HeadwearZomB(String name, int extra_health, boolean head_on) {
        super(name, 100 + extra_health, 100, 1, false);
        this.head_on = head_on;
    }

    public void setHead(boolean head) {
        this.head_on = head;
    }

    public  void attack() {
        
    }

    // public boolean getAquatic() {
    //     return is_aquatic;
    // }

    /*    public void takeDamage(Plant Tanaman) {

        } */
}