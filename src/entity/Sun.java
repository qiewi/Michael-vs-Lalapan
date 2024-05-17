package entity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Sun {  
    private static int sun = 25;
    Timer timer = new Timer();
    boolean morning = false;

    // Nanti pindain method ke playing
    public void startMorning() {

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                morning = !morning;
                addSunMorning();
            }
        }, 0000, 100000);
    }

    public void addSunMorning() {
        int period = generateRandomPeriod(5, 10) * 1000;

        if (morning) {
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    sun += 25;
                }
            }, 1000, period);
        }
        
    }

    private int generateRandomPeriod(int minSeconds, int maxSeconds) {
        Random random = new Random();
        return random.nextInt(maxSeconds - minSeconds + 1) + minSeconds;
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