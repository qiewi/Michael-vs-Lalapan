package scenes;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
    private static Clip soundClip;
    
    public static void playSound(String soundName) {
        try {
            File soundFile = new File("src/scenes/resources/Music/" + soundName + ".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            // Stop the currently playing sound clip before playing a new one
            stopSound();

            soundClip = AudioSystem.getClip();
            soundClip.open(audioIn);
            
            // Add a listener to restart the clip when it ends
            soundClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    soundClip.setFramePosition(0);
                    soundClip.start();
                }
            });

            soundClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    // Method to stop the currently playing sound clip
    public static void stopSound() {
        if (soundClip != null && soundClip.isRunning()) {
            soundClip.stop();
            soundClip.close();
        }
    }
}
