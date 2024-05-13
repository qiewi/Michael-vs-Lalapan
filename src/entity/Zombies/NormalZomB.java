package entity.Zombies;

public class NormalZomB extends Zombie {

    public NormalZomB() {
        super("Normal Zombie", 125, 100, 1, false);
    }

    public NormalZomB(int extra_health) {
        super(125 + extra_health, 100, 1, false);
    }

    public  void attack() {
        
    }

    // public boolean getAquatic() {
    //     return is_aquatic;
    // }

    /*    public void takeDamage(Plant Tanaman) {

        } */

}