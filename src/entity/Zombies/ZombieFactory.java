package entity.Zombies;

public class ZombieFactory {
    public static Zombie CreateZombie(String name, int x, int y) {
        Zombie zombie = null;

        if (name.equals("Normal")) {
            zombie = new Normal(x, y);
        } else if (name.equals("Conehead")) {
            zombie = new Conehead(x, y);
        } else if (name.equals("Buckethead")) {
            zombie = new Buckethead(x, y);
        } else if (name.equals("Football")) {
            zombie = new Football(x, y);
        } else if (name.equals("Flag")) {
            zombie = new Flag(x, y);
        } else if (name.equals("Newspaper")) {
            zombie = new Newspaper(x, y);
        } else if (name.equals("Screendoor")) {
            zombie = new Screendoor(x, y);
        } else if (name.equals("Polevault")) {
            zombie = new Polevault(x, y);
        } else if (name.equals("Duckytube")) {
            zombie = new Duckytube(x, y);
        } else if (name.equals("Dolphin")) {
            zombie = new Dolphin(x, y);
        } 
            
        return zombie;
    
    }
}
