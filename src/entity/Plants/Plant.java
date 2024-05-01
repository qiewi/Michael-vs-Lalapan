package entity.Plants;

import entity.Action;
import entity.Entity;

public abstract class Plant extends Entity implements Action {
    private int cost;
    private int range;
    private int cooldown;
    private boolean aqua_only;

    public Plant(String name, int cost, int health, int attack_damage, int attack_speed, int range, int cooldown, boolean aqua_only) {
        super(name, health, attack_damage, attack_speed);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.aqua_only = aqua_only;
    }

    public void takeDamage(Zombie Zombies) {

    }

    public void action() {

    }

    public int getCost() {
        return this.cost;
    }

    public int getRange() {
        return this.range;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public boolean getAquaStatus() {
        return this.aqua_only;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setAquaStatus(boolean aqua_only) {
        this.aqua_only = aqua_only;
    }
}
