package entity.Zombies;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import entity.Action;
import entity.Entity;
import entity.Plants.Plant;
import managers.PlantsManager; 

public abstract class Zombie extends Entity implements Action {
    private boolean is_aquatic;
    private BufferedImage image;
    private boolean moving = true;
    private boolean attacking = false;

    private Timer attackTimer;
    private boolean isTimerRunning;

    public Zombie(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic, int x, int y) {
        super(name, health, attack_damage, attack_speed, x, y);
        this.is_aquatic = is_aquatic;
        this.isTimerRunning = false;
        createAttackTimer();
    }

    private void createAttackTimer() {
        attackTimer = new Timer(1000, (ActionEvent e) -> {
            if (this.getAttacking()) {
                Plant plantAttacked = PlantsManager.checkPlantsInPos((int) this.getX(), (int) this.getY());
                PlantsManager.takeDamage(plantAttacked, this);
                System.out.println("Attacking!");
            }
        });
        attackTimer.setInitialDelay(0);
    }

    public void startAttacking() {
        if (!isTimerRunning) {
            attackTimer.start();
            isTimerRunning = true;
        }
    }

    public void stopAttacking() {
        if (isTimerRunning) {
            attackTimer.stop();
            isTimerRunning = false;
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

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean getAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    //public abstract void takeDamage(Plant Tanaman);
}