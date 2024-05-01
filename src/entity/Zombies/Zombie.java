package entity.Zombies;

import entity.Entity; 

public abstract class Zombie extends Entity {
    private boolean is_aquatic;

    public Zombie(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic) {
        super(name, health, attack_damage, attack_speed);
        this.is_aquatic = is_aquatic;
    }

    public abstract void attack();

    public boolean getAquatic() {
        return is_aquatic;
    }

    //public abstract void takeDamage(Plant Tanaman);
}