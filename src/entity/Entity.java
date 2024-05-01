package entity;

public abstract class Entity {
    private String name;
    private int health;
    private int attack_damage;
    private int attack_speed;

    public Entity(String name, int health, int attack_damage, int attack_speed) {
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
    }
    public Entity(int health, int attack_damage, int attack_speed) {
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
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
