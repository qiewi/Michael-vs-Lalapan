package managers;

import java.awt.Graphics;
import java.util.ArrayList;

import objects.LandMower;
import scenes.Playing;
import java.util.Iterator;

import entity.Zombies.Zombie;

public class LandMowersManager implements ManagersUI {
    private Playing playing;
    private static boolean landMowerAdded = false;
    private static ArrayList<LandMower> landMowers = new ArrayList<>();
    private static ArrayList<Zombie> zombies = new ArrayList<>();

	public LandMowersManager(Playing playing) {
		this.playing = playing;
        zombies = ZombiesManager.getZombies();
	}

    public void update() {
        Iterator<LandMower> iterator = landMowers.iterator();
        
        while (iterator.hasNext()) {
            LandMower l = iterator.next();

            if (l != null) {
                l.move();

                Iterator<Zombie> zombieIterator = zombies.iterator();
                while (zombieIterator.hasNext()) {
                    Zombie z = zombieIterator.next();

                    if (checkCollision(l, z)) {
                        ZombiesManager.takeDamage(z, z.getHealth()); // Deal lethal damage
                    }
                }

                if (l.getX() >= l.getDestructPos()) {
                    iterator.remove();
                }
            }
        }
    }

    private boolean checkCollision(LandMower l, Zombie z) {
        int mowerX = (int) l.getX();
        int mowerY = (int) l.getY();
        int mowerWidth = 50;
        int mowerHeight = 50;

        int zombieX = (int) z.getX();
        int zombieY = (int) z.getY();
        int zombieWidth = 50;
        int zombieHeight = 50;

        return (mowerX < zombieX + zombieWidth &&
                mowerX + mowerWidth > zombieX &&
                mowerY < zombieY + zombieHeight &&
                mowerY + mowerHeight > zombieY);
    }
	

    // For Game 
	public static void initiateMower() {
        int yOffset = 90;
        for (int i = 0; i < 2; i++) {
            landMowers.add(new LandMower(200, 200 + i * yOffset, "Land")); // 200, 290
        }

        for (int i = 2; i < 4; i++) {
            landMowers.add(new LandMower(200, 380 + (i-2) * yOffset, "Pool")); // 380, 470
        }


        for (int i = 2; i < 4; i++) {
            landMowers.add(new LandMower(200, 560 + (i-2) * yOffset, "Land")); // 560, 650
        }
	}

	public void draw(Graphics g) {
        for (LandMower l: landMowers) {
            if (l != null) {
                if (l.getLane().equals("Pool")) {
                    if (l.getX() <= 920 && l.getX() >= 220) {
                        l.setMowerImage("Pool");
                    } else {
                        l.setMowerImage("PoolOnLand");
                    }
                    g.drawImage(l.getImage(), (int) l.getX(), (int) l.getY(), null);
                } else {
                    g.drawImage(l.getImage(), (int) l.getX(), (int) l.getY(), null);
                }
            }
        }
	}

    public static void clearMower() {
        landMowers.clear();
    }

    public ArrayList<LandMower> getLandMowers() {
        return landMowers;
    }

    public static void setLandMowerAdded(boolean landMowerAdded) {
        LandMowersManager.landMowerAdded = landMowerAdded;
    }

    public static boolean isLandMowerAdded() {
        return landMowerAdded;
    }
}

