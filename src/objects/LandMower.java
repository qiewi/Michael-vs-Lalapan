package objects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LandMower {
    private BufferedImage image;
    private Rectangle bounds;
    private String lane;

    private int x, y;
    private int destructPos;

    public LandMower(int x, int y, String lane) {
        this.x = x;
        this.y = y;
        this.lane = lane;

        this.bounds = new Rectangle(x,y, 80, 81);
        this.destructPos = 990;
        setMowerImage(lane);
    }

    public void setMowerImage(String lane) {
        BufferedImage img = null;
        InputStream is;

        if (lane.equals("Land"))
            is = getClass().getResourceAsStream("ObjectImages/LandMower.png");
        else if (lane.equals("PoolOnLand"))
            is = getClass().getResourceAsStream("ObjectImages/PoolCleanerOnLand.png");
        else 
            is = getClass().getResourceAsStream("ObjectImages/PoolCleaner.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        this.image = img;
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

    public String getLane() {
        return lane;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getDestructPos() {
        return destructPos;
    }
}