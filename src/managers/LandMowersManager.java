package managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import objects.LandMower;
import scenes.Playing;
import java.util.Iterator;

public class LandMowersManager {
    private Playing playing;
    private static ArrayList<LandMower> landMowers = new ArrayList<>();

	public LandMowersManager(Playing playing) {
		this.playing = playing;
	}

    public void update() {
        Iterator<LandMower> iterator = landMowers.iterator();
        
        while (iterator.hasNext()) {
            LandMower l = iterator.next();

            if (l != null)
                l.move();
                
                if (l.getX() >= l.getDestructPos()) {
                    iterator.remove();
                }
        }
    }
	

    // For Game 
	public static void initiateMower() {
        int yOffset = 90;
        for (int i = 0; i < 2; i++) {
            landMowers.add(new LandMower(200, 200 + i * yOffset));
        }


        for (int i = 2; i < 4; i++) {
            landMowers.add(new LandMower(200, 570 + (i-2) * yOffset));
        }
	}

	public void draw(Graphics g) {
        for (LandMower l: landMowers) {
            if (l != null) {
                g.drawImage(l.getImage(), (int) l.getX(), (int) l.getY(), null);
            }
        }
	}

    public static void clearMower() {
        landMowers.clear();
    }

    public ArrayList<LandMower> getLandMowers() {
        return landMowers;
    }
}

