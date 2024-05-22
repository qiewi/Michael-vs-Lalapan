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
    private static Clip soundClip, clickClip;
    private static boolean isLooping;

    public static void playSound(String soundName, boolean loop) {
        try {
            File soundFile = new File("src/scenes/resources/Music/" + soundName + ".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            // Stop the currently playing sound clip before playing a new one
            stopSound();

            soundClip = AudioSystem.getClip();
            soundClip.open(audioIn);
            isLooping = loop;

            if (loop) {
                // Add a listener to restart the clip when it ends
                soundClip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP && isLooping) {
                        soundClip.setFramePosition(0);
                        soundClip.start();
                    }
                });
            }

            soundClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public static void playClickSound() {
        try {
            File soundFile = new File("src/scenes/resources/Music/Click.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            clickClip = AudioSystem.getClip();
            clickClip.open(audioIn);

            clickClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    // Method to stop the currently playing sound clip
    private static void stopSound() {
        if (soundClip != null) {
            soundClip.stop();
            soundClip.close();
            isLooping = false;  // Reset the looping flag
        }
    }
}
