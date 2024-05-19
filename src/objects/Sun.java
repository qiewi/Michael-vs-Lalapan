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
    private static int sun = 25;
    private static int tick = 0;
    public BufferedImage image = setSunImage();
    Timer timer = new Timer();
    Timer tickTimer = new Timer();
    private static boolean morning = true;

    // Nanti pindain method ke playing
    public void startMorning() {   // bikin tick untuk si zombie
        sun = 50;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                increaseTick();
                addSunMorning();
            }
        }, 0000, 200000); // bikin timer baru
    }

    public void addSunMorning() {
        int period = generateRandomPeriod(5, 10) * 1000;

        if (morning = true) {
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if (tick < 100)
                        SunDropManager.addSunDrop();
                }
            }, 1000, period);
        }
        
    }

    public void increaseTick() {
        if (morning) {
            tickTimer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    tick++;
                    if (tick >= 100) {
                        morning = false;
                    }
                }
            }, 1000, 1000);
        }
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

    public int getTick() {
        return tick;
    }

    public boolean getMorning() {
        return morning;
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