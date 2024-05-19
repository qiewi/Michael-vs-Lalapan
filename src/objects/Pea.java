package objects;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;

import javax.imageio.ImageIO;

public class Pea { // TO DO LIST: Pisah sama frozen pea
    public BufferedImage image;
    private float x, y;

    Timer timer = new Timer();

    public Pea(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.image = setPeaImage(name);
    }

    public BufferedImage setPeaImage(String name) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("ObjectImages/" + name + "Pea.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    public void move() {
        x += 5f;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }
}
