package entity.Zombies;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Action; 
import entity.Plants.Plant; 

public abstract class Zombie extends Entity implements Action {
    private boolean is_aquatic;
    private BufferedImage image;
    private ScheduledExecutorService scheduler;

    public Zombie(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic, int x, int y) {
        super(name, health, attack_damage, attack_speed, x, y);
        this.is_aquatic = is_aquatic;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startAttacking(final Plant plant) {
        scheduler.scheduleAtFixedRate(() -> attack(plant), 0, 2, TimeUnit.SECONDS);
    }

    public void attack(Plant plant) {
        if (plant.getHealth() > 0) {
            plant.setHealth(plant.getHealth() - getAttackDamage());
        } else {
            stopAttacking();
        }
    }

    public void stopAttacking() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    public BufferedImage getZombieImage(String name) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("ZombiesImage/" + name + ".png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    public boolean getAquatic() {
        return is_aquatic;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    //public abstract void takeDamage(Plant Tanaman);
}