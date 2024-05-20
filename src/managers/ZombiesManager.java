package managers;

import static main.GameStates.GAMEOVER;
import static main.GameStates.VICTORY;
import static main.GameStates.setGameState;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import entity.Plants.*;
import entity.Zombies.*;
import objects.Sun;
import scenes.Playing;

public class ZombiesManager {
    private Playing playing;
	private static ArrayList<Zombie> zombies;
	private static ArrayList<Plant> plants;
	private ScheduledExecutorService scheduler;
	private static final int INITIAL_ZOMBIE_COUNT = 10;
	private static final int ZOMBIE_GENERATION_DELAY = 10;
	private static final int TOTAL_ZOMBIE_COUNT = 3;
	private static int zombieCount = 0;

	public ZombiesManager(Playing playing) {
		this.playing = playing;
		zombies = new ArrayList<>();
		plants = PlantsManager.getPlants();
		scheduler = Executors.newScheduledThreadPool(1);
	}

	public void update() {

		if (zombieCount >= TOTAL_ZOMBIE_COUNT && zombies.isEmpty()) {
			setGameState(VICTORY);
		}

		Iterator<Zombie> zombieIterator = zombies.iterator();
		while (zombieIterator.hasNext()) {
			Zombie z = zombieIterator.next();
			boolean attacked = false;
	
			// Iterate over the unharmed plants
			Iterator<Plant> plantIterator = plants.iterator();
			while (plantIterator.hasNext()) {
				Plant p = plantIterator.next();

				// Check if zombie and plant are at the same position
				if (((int) z.getX() <= (int) p.getX() && (int) z.getX() >= (int) p.getX() - 30) && ((int) z.getY() == (int) p.getY())) {
					if (z instanceof VaultingType && ((VaultingType) z).getVault()) {
						if (!(p instanceof Tallnut))
							z.action();
						else
							((VaultingType) z).setVault(false);
							z.setSpeed(-0.15f);
					} else {
						if (p instanceof Tanglekelp) {
							plantIterator.remove();
							zombieIterator.remove();
							break;
						} 

						if (!(p instanceof Squash)) {
							z.setAttacking(true);
							attacked = true;
						} 
						
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

			if (z instanceof Newspaper) {
				if (((Newspaper)z).getAngerTick() != -1 && ((Newspaper)z).getAngerTick() + 3 <= Sun.getTick()) {
					z.setSpeed(-0.4f);
					((Newspaper)z).setAngerTick(-1);
				}
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

	public void scheduleZombieGeneration() { 
		Runnable addZombieTask = () -> { 
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
			if (zombieCount >= TOTAL_ZOMBIE_COUNT) {
				scheduler.shutdown();
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

	public static int squashDamage(int x, int y) {
		int zomPos = -1;
		for (Zombie z : zombies)
			if (((int) z.getX() >= (int) x - 50 && (int) z.getX() <= (int) x + 100) && ((int) z.getY() == (int) y)) {
				// p.setX(p.getX() + 80);
				zomPos = (int) z.getX();
				for (Zombie z2 : zombies) {
					if ((int) z2.getX() > (int) x - 20 && (int) z2.getX() <= (int) x + 140 && (int) z2.getY() == (int) y) {
						z2.takeDamage(5000);
					}
				}
				// p.setHealth(0);
			}
		return zomPos + 15;
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
					((ShieldType) z).setShield(false);
					if (z instanceof Screendoor) {
						z.setImage(z.getZombieImage("Normal"));
                    } else if (z instanceof Newspaper) {
						((Newspaper) z).setAngerTick(Sun.getTick());
						z.setSpeed(0f);
					}
				}
			}
		}
	}

	public static void deleteZombieAt(int x, int y) {
		Iterator<Zombie> zombieIterator = zombies.iterator();
		while (zombieIterator.hasNext()) {
			Zombie z = zombieIterator.next();
			if ((int) z.getX() == x && (int) z.getY() == y) {
				zombieIterator.remove();
			}
		}
	}

	public void addZombie(int x, int y) {
		Random random = new Random();
		String[] zombieTypes = {"Normal", "Football", "Conehead", "Buckethead", "Flag", "Screendoor", "Polevault", "Newspaper"}; //flag belom // Pole Vault nnt aja tunggu fixed
		int zombieType = random.nextInt(zombieTypes.length);
		zombies.add(ZombieFactory.CreateZombie(zombieTypes[zombieType], x, y));
		zombieCount++;
	}

	public void clearZombie() {
		zombies.clear();
	}

	public void draw(Graphics g) {
		for (Zombie z : zombies)
			drawZombie(z, g);
	}

	private void drawZombie(Zombie z, Graphics g) {
		g.drawImage(z.getImage(), (int) z.getX(), (int) z.getY() - 90, null);
	}

	public static ArrayList<Zombie> getZombies(){
		return zombies;
	}
}