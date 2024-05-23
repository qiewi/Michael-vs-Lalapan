package entity;

public abstract class Entity implements Action {
    protected String name;
    protected int health;
    protected int attack_damage;
    protected int attack_speed;
    protected float x, y;


    public Entity(String name, int health, int attack_damage, int attack_speed, float x, float y) {
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed =  attack_speed;
        this.x = x;
        this.y = y;
    }

    public void move(float x, float y) {
		this.x += x;
		this.y += y;
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

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }
}
