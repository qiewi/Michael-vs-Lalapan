package objects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LandMower {
    private BufferedImage image;
    private Rectangle bounds;

    private int x, y;
    private int destructPos;

    public LandMower(int x, int y) {
        this.x = x;
        this.y = y;

        this.bounds = new Rectangle(x,y, 80, 81);
        this.destructPos = 990;
        this.image = setMowerImage();
    }

    public BufferedImage setMowerImage() {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("ObjectImages/LandMower.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    public void move() {
        if (x < destructPos) {
            x += 1;
            bounds.x += 1;
        } 
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getDestructPos() {
        return destructPos;
    }
}