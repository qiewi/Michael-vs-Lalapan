package entity;

public abstract class Entity {
    private String name;
    private int health;
    private int attack_damage;
    private int attack_speed;

    public Entity() {
        this.name = "";
        this.health = 0;
        this.attack_damage = 0;
        this.attack_speed = 0;
    }

    public String getName() { 
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getAttackDamage() {
        return this.attack_damage;
    }

    public int getAttackSpeed() {
        return this.attack_speed;
    }
}
