package objects;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import managers.SunDropManager;

public class Sun {  
    private static int sun = 50;
    private static int tick;
    public BufferedImage image = setSunImage();
    public static Timer timer;
    public static Timer tickTimer;
    private static boolean morning = true;
    
    // Nanti pindain method ke playing
    public void startMorning() {   // bikin tick untuk si zombie
        tick = 20;
        sun = 50;
        timer = new Timer();
        tickTimer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                increaseTick();
                addSunMorning();
            }
        }, 0000, 200000); // bikin timer baru
    }

    public void addSunMorning() {
        int period = generateRandomPeriod(5, 10) * 1000;

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (morning)
                    SunDropManager.addSunDrop();
            }
        }, 1000, period);        
    }

    public void increaseTick() {
        tickTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                tick++;
                if (tick >= 100) {
                    morning = false;
                }
            }
        }, 1000, 1000);
    }

    private int generateRandomPeriod(int minSeconds, int maxSeconds) {
        Random random = new Random();
        return random.nextInt(maxSeconds - minSeconds + 1) + minSeconds;
    }

    public BufferedImage setSunImage() {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("ObjectImages/Sun.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    public int getSun() {
        return sun;
    }
    
    public void setSun(int sun) {
        Sun.sun = sun;
    }

    public void addSun(int add) {
        Sun.sun += add;
    }

    public void reduceSun(int min) {
        Sun.sun -= min;
    }

    public BufferedImage getImage() {
        return image;
    }

    public static int getTick() {
        return tick;
    }

    public static boolean getMorning() {
        return morning;
    }

    public void resetTick() {
        timer.cancel();
        tickTimer.cancel();

        timer.purge();
        tickTimer.purge();
        tick = 0;
    }
}

// ini main nya

// import java.util.Random;
// import java.util.Timer;
// import java.util.TimerTask;

// public class Main extends MySun {
    
//     public static void main(String[] args) {
//         Timer timer = new Timer();
//         // Create an instance of MySun
//         MySun mySun = new MySun();

//         System.out.println(mySun.getSun());

//         // Call addSunMorning method
//         mySun.addSunMorning();
        
//         timer.scheduleAtFixedRate(new TimerTask() {
//             public void run() {
//                 System.out.println(mySun.getSun());
//             }
//         }, 0000, 1000);
//     }
// }