package entity.Zombies;

public class NormalZomB extends Zombie {

    public NormalZomB() {   //untuk instansiasi
        super("Normal Zombie", 125, 100, 1, false);
    }

    public NormalZomB(String name, int extra_health) {   //untuk subclass non-aquatic
        super(name, 100 + extra_health, 100, 1, false);
    }

    public NormalZomB(String name, int extra_health_aquatic, boolean is_aquatic) {   //untuk subclass aquatic
        super(name, 100 + extra_health_aquatic, 100, 1, true);
    }

    public  void attack() {
        
    }

    // public boolean getAquatic() {
    //     return is_aquatic;
    // }

    /*    public void takeDamage(Plant Tanaman) {

        } */

}