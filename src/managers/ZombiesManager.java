package managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import entity.Zombies.Zombie;
import entity.Zombies.ZombieFactory;
import scenes.Playing;

public class ZombiesManager {
    private Playing playing;
	private static ArrayList<Zombie> zombies;

	public ZombiesManager(Playing playing) {
		this.playing = playing;
		zombies = new ArrayList<>(); // ditaro di inisialiasi
		initializeZombie();
	}

	public void update() {
		for (Zombie z : zombies)
			z.move(-0.3f, 0);
	}

    private void initializeZombie () {
        int [] position = new int [] {200, 290, 380, 470, 560, 650};

        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int pos = rand.nextInt(5) + 1;
            addZombie(990, position[pos]);
        }
    }

	public static boolean checkZombiesInLane(int y) {
		for (Zombie z : zombies) {
			if (z.getY() == y) {
				return true;
			}
		}
		return false;
	}

	public void addZombie(int x, int y) {
		zombies.add(ZombieFactory.CreateZombie("Normal", x, y));
	}

	public void draw(Graphics g) {
		for (Zombie z : zombies)
			drawZombie(z, g);
	}

	private void drawZombie(Zombie z, Graphics g) {
		g.drawImage(z.getImage(), (int) z.getX(), (int) z.getY() - 35, null);
	}
}
