package objects;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import managers.SunDropManager;

public class Pea { // TO DO LIST: Pisah sama frozen pea
    public BufferedImage image;
    private int x, y;

    Timer timer = new Timer();

    public Pea(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = setPeaImage();
    }
    
    // public void startPea() {
    //     timer.scheduleAtFixedRate(new TimerTask() {
    //         public void run() {
    //             generatePea();
    //         }
    //     }, 0000, 100000);
    // }

    // public void generatePea() {
    //     int period = 3000;

    //     timer.scheduleAtFixedRate(new TimerTask() {
    //         public void run() {
    //             SunDropManager.addSunDrop();
    //         }
    //     }, 1000, period);
    // }

    public BufferedImage setPeaImage() {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("NormalPea.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    public void move() {
        x += 5;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }
}
