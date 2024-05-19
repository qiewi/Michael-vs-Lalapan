package managers;

import static main.GameStates.GAMEOVER;
import static main.GameStates.setGameState;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import entity.Plants.Plant;
import entity.Zombies.Buckethead;
import entity.Zombies.Conehead;
import entity.Zombies.Football;
import entity.Zombies.HeadwearType;
import entity.Zombies.Screendoor;
import entity.Zombies.Polevault;
import entity.Zombies.ShieldType;
import entity.Zombies.VaultingType;
import entity.Zombies.Zombie;
import entity.Zombies.ZombieFactory;
import objects.Sun;
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
					if (z instanceof VaultingType && ((VaultingType) z).getVault()) {
						z.action();
					} else {
						z.setAttacking(true);
						attacked = true;
						break;
					}
				} else {
					z.setAttacking(false);
				}
			}
	
			// Move the zombie if it hasn't attacked
			if (!attacked) {
				z.move(z.getSpeed(), 0);
				z.setAttacking(false);  // Stop attacking when moving
			} else {
				z.startAttacking();
			}

			if (z.getFrozenTick() != -1 && z.getFrozenTick() + 3 <= Sun.getTick()) {
				z.setSpeed(z.getBeforeSpeed());
				z.setFrozenTick(-1);
			}

			if (z.getHealth() <= 0) {
				zombieIterator.remove();
			}

			// Game Over
			if (z.getX() <= 90) {
				setGameState(GAMEOVER);
			}
		}
	}

	public void scheduleZombieGeneration() { // ambil tick dari sun 
		Runnable addZombieTask = () -> { // ubah
			if (Sun.getTick() >= 20 && Sun.getTick() <= 160) {
				if (zombies.size() >= INITIAL_ZOMBIE_COUNT) {
					return;
				} else {
					Random rand = new Random();
					int[] positions = new int[] {200, 290, 560, 650};
					int pos = rand.nextInt(positions.length);
					addZombie(990, positions[pos]);
				}
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

	public static Zombie checkZombiesInPos(int x, int y) {
		Zombie zombie = null;
		for (Zombie z : zombies) {
			if ((int) z.getX() == x && (int) z.getY() == y){
				zombie = z;
			}
		}
		return zombie;
	}

	public static void slowZombies(Zombie zombie) {
		for (Zombie z : zombies) {
			if (z.equals(zombie)) {
				System.out.println("Zombie Slowed");
				z.setFrozenTick(Sun.getTick());
				z.setSpeed(-0.05f);

				if (z instanceof VaultingType) {
					z.setBeforeSpeed(-0.3f);
				} else {
					z.setBeforeSpeed(-0.15f);
				}
			}
		}
	}

	public static void takeDamage(Zombie zombie, int damage) {
		for (Zombie z : zombies) {
			if (z.equals(zombie)) {
				z.takeDamage(damage);
				if (z instanceof HeadwearType && (z.getHealth() == ((HeadwearType) z).getDefHealth())) {
					if (z instanceof Buckethead) {
                        ((Buckethead) z).setHead(false);
						z.setImage(z.getZombieImage("Normal"));
                    } else if (z instanceof Conehead) {
                        ((Conehead) z).setHead(false);
						z.setImage(z.getZombieImage("Normal"));
                    } else if (z instanceof Football) {
                        ((Football) z).setHead(false);
						// z.setImage(z.getZombieImage("Normal"));
                    }
				} else if (z instanceof ShieldType && (z.getHealth() == ((ShieldType) z).getDefHealth())) {
					if (z instanceof Screendoor) {
                        ((Screendoor) z).setShield(false);
						z.setImage(z.getZombieImage("Normal"));
                    }
				}
			}
		}
	}

	public void addZombie(int x, int y) {
		Random random = new Random();
		String[] zombieTypes = {"Polevault"}; //flag belom // Pole Vault nnt aja tunggu fixed
		int zombieType = random.nextInt(zombieTypes.length);
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

	public static ArrayList<Zombie> getZombies(){
		return zombies;
	}
}