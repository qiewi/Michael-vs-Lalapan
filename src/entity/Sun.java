package entity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Sun {  
    private int sun = 25;
    Timer timer = new Timer();

    public void addSunMorning() {
        int period = generateRandomPeriod(5, 10) * 1000;

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                sun += 25;
            }
        }, 0000, period);
    }

    private int generateRandomPeriod(int minSeconds, int maxSeconds) {
        Random random = new Random();
        return random.nextInt(maxSeconds - minSeconds + 1) + minSeconds;
    }

    public int getSun() {
        return sun;
    }
    
    public void setSun(int sun) {
        this.sun = sun;
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