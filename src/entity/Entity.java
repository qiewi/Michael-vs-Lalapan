package entity;

public abstract class Entity {
    private String name;
    private int health;
    private int attack_damage;
    private int attack_speed;
    private int x, y;


    public Entity(String name, int health, int attack_damage, int attack_speed, int x, int y) {
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed =  attack_speed;
        this.x = x;
        this.y = y;
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

    public void setName(String name) { 
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackDamage(int attack_damage) {
        this.attack_damage = attack_damage;
    }

    public void setAttackSpeed(int attack_speed) {
        this.attack_speed = attack_speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
