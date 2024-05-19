package entity.Plants;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import entity.Action;
import entity.Entity;
import entity.Zombies.Zombie;

public abstract class Plant extends Entity implements Action {
    private int cost;
    private int range;
    private int cooldown;
    private boolean aqua_only;
    private BufferedImage image;

    public Plant(String name, int cost, int health, int attack_damage, int attack_speed, int range, int cooldown, boolean aqua_only, int x, int y) {
        super(name, health, attack_damage, attack_speed, x, y);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.aqua_only = aqua_only;
    }

    public BufferedImage getPlantImage(String name) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("PlantsImage/" + name + ".png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    public BufferedImage getPlantGif(String name) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("PlantsGif/" + name + ".gif");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    public void takeDamage(Zombie Zombies) {
        this.setHealth(this.getHealth() - Zombies.getAttackDamage());
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

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
}
