package entity.Zombies;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import entity.Entity; 

public abstract class Zombie extends Entity {
    private boolean is_aquatic;
    private BufferedImage image;

    public Zombie(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic, int x, int y) {
        super(name, health, attack_damage, attack_speed, x, y);
        this.is_aquatic = is_aquatic;
    }

    public abstract void attack();

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