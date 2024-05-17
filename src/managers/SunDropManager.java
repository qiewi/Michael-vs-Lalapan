package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import objects.Sun;
import scenes.Playing;

public class SunDropManager {
    private Playing playing;
	private static ArrayList<SunDrop> activeSuns = new ArrayList<>();

	public SunDropManager(Playing playing) {
		this.playing = playing;
	}

	public void update() {
        Iterator<SunDrop> iterator = activeSuns.iterator();
        while (iterator.hasNext()) {
            SunDrop s = iterator.next();
            s.move();
            if (s.getY() == s.getDestructPos()) {
                iterator.remove();
                playing.getPlayingSun().addSun(25);
            }
        }
	}

    // For Game 
	public static void addSunDrop() {
		Random ranX = new Random();
        Random ranDestructY = new Random();

        int[] xPositions = { 310, 390, 480, 570, 660, 750, 840 };
        int[] destructYPositions = { 200, 290, 380, 470, 560, 650 };

        int x = xPositions[ranX.nextInt(xPositions.length)];
        int destructY = destructYPositions[ranDestructY.nextInt(destructYPositions.length)];

        activeSuns.add(new SunDrop(x, 100, destructY));
	}

    // For Sunflower
    public static void addSunDrop(int x, int y) {
        activeSuns.add(new SunDrop(x, y, y + 40));
	}

	public void draw(Graphics g) {
		for (SunDrop s : activeSuns)
			drawSunDrop(s, g);
	}

	private void drawSunDrop(SunDrop s, Graphics g) {
		g.drawImage(s.getImage(), (int) s.getX(), (int) s.getY(), null);
	}
}

class SunDrop {
    private Sun sun = new Sun();
    private BufferedImage image;

    private int x, y;
    private int destructPos;

    public SunDrop(int x, int y, int destructPos) {
        this.x = x;
        this.y = y;

        this.destructPos = destructPos;
        this.image = sun.getImage();
    }

    public void move() {
        if (y < destructPos) {
            y += 1;
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDestructPos() {
        return destructPos;
    }
}
