package objects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Note {
    private BufferedImage image;
    private Rectangle bounds;

    private int x, y;
    private int destructPos;

    public Note(int x, int y, int destructPos) {
        this.x = x;
        this.y = y;

        this.bounds = new Rectangle(x,y, 80, 81);
        this.destructPos = destructPos;
        this.image = setNoteImage();
    }

    public BufferedImage setNoteImage() {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("ObjectImages/Note.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    public void move() {
        if (y < destructPos) {
            y += 1;
            bounds.y += 1;
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