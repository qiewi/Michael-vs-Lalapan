import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Sun {  
    private int sun = 25;
    Timer timer = new Timer();
    
    public abstract void addSunSF(); // nanti untuk di extend sama sunflower
    // ini codenya kalau mau
    // import java.util.Timer;
    // import java.util.TimerTask;

    // public class MySun extends Sun {
        
    //     public void addSunSF() {
    //         timer.scheduleAtFixedRate(new TimerTask() {
    //             public void run() {
    //                 setSun(getSun() + 25);
    //             }
    //         }, 0000, 10000);
    //     }
    // }

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
