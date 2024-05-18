package entity.Zombies;

public class HeadwearType extends Zombie {

    private boolean head_on;
    private static final int DEFAULT_HEALTH = 125;

    public HeadwearType(String name, int extra_health, boolean head_on, int x, int y) {
        super(name, DEFAULT_HEALTH + extra_health, 100, 1, false, x, y);
        this.head_on = head_on;
    }

    public boolean getHead() {
        return head_on;
    }

    public void setHead(boolean head) {
        this.head_on = head;
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