package managers;

import static main.GameStates.GAMEOVER;
import static main.GameStates.MENU;
import static main.GameStates.setGameState;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import entity.Plants.Plant;
import entity.Zombies.Zombie;
import entity.Zombies.ZombieFactory;
import scenes.Playing;

public class ZombiesManager {
    private Playing playing;
	private static ArrayList<Zombie> zombies;
	private static ArrayList<Plant> plants;
	private ScheduledExecutorService scheduler;
	private static final int INITIAL_ZOMBIE_COUNT = 10;
	private static final int ZOMBIE_GENERATION_DELAY = 10;

	public ZombiesManager(Playing playing) {
		this.playing = playing;
		zombies = new ArrayList<>();
		plants = PlantsManager.getPlants();
		scheduler = Executors.newScheduledThreadPool(1);
	}

	public void update() {
		Iterator<Zombie> zombieIterator = zombies.iterator();
		while (zombieIterator.hasNext()) {
			Zombie z = zombieIterator.next();
			boolean attacked = false;
	
			// Iterate over the unharmed plants
			Iterator<Plant> plantIterator = plants.iterator();
			while (plantIterator.hasNext()) {
				Plant p = plantIterator.next();
	
				// Check if zombie and plant are at the same position
				if (((int) z.getX() == (int) p.getX()) && ((int) z.getY() == (int) p.getY())) {
					z.setAttacking(true);
					attacked = true;
					break;
				} else {
					z.setAttacking(false);
				}
			}
	
			// Move the zombie if it hasn't attacked
			if (!attacked) {
				z.move(-0.3f, 0);
				z.setAttacking(false);  // Stop attacking when moving
			} else {
				z.startAttacking();
			}

			// Game Over
			if (z.getX() <= 220) {
				setGameState(GAMEOVER);
			}
		}
	}

	public void scheduleZombieGeneration() {
		Runnable addZombieTask = () -> {
			Random rand = new Random();
			int[] positions = new int[] {200, 290, 380, 470, 560, 650};
			int pos = rand.nextInt(positions.length);
			addZombie(990, positions[pos]);

			if (zombies.size() >= INITIAL_ZOMBIE_COUNT) {
				scheduler.shutdown(); // Stop scheduling once the initial number of zombies are generated
			}
		};
		scheduler.scheduleAtFixedRate(addZombieTask, 0, ZOMBIE_GENERATION_DELAY, TimeUnit.SECONDS);
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
		Random random = new Random();
		int zombieType = random.nextInt(4);
		String[] zombieTypes = {"Normal", "Conehead", "Buckethead", "Football", "Flag"};
		zombies.add(ZombieFactory.CreateZombie(zombieTypes[zombieType], x, y));
	}

	public void clearZombie() {
		zombies.clear();
	}

	public void draw(Graphics g) {
		for (Zombie z : zombies)
			drawZombie(z, g);
	}

	private void drawZombie(Zombie z, Graphics g) {
		g.drawImage(z.getImage(), (int) z.getX(), (int) z.getY() - 75, null);
	}
}
