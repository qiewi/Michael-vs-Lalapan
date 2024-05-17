package entity.Zombies;

public class ZombieFactory {
    public static Zombie CreateZombie(String name, int x, int y) {
        Zombie zombie = null;

        if (name.equals("Normal")) {
            zombie = new NormalZomB(x, y);
        } 
            
        return zombie;
    
    }
}
