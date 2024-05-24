package managers;

import static main.GameStates.GAMEOVER;
import static main.GameStates.setGameState;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import entity.Plants.*;
import entity.Zombies.*;
import objects.Sun;
import scenes.Music;
import scenes.Playing;

public class ZombiesManager implements ManagersUI {
    private Playing playing;
	private static ArrayList<Zombie> zombies;
	private static ArrayList<Plant> plants;
	private static ScheduledExecutorService scheduler;
	private static final int TOTAL_ZOMBIE_COUNT = 70;

	private static final int[] positions = new int[] {200, 290, 380, 470, 560, 650};
	private static final int[] flagPositions = new int[] {200, 290, 560, 650};

	private static int zombieAtOneTime;
	private static int zombieDelay;
	private static int zombieCount;
	private static boolean flag = false;
	private static boolean victory = false;

	private static Zombie lastZombie;

	public ZombiesManager(Playing playing) {
		this.playing = playing;
		zombies = new ArrayList<>();
		plants = PlantsManager.getPlants();

		zombieCount = 0;
		zombieDelay = 5;
		zombieAtOneTime = 10;
	}

	public static void initScheduler() {
		flag = false;
		victory = false;
		zombieCount = 0;
		scheduler = Executors.newScheduledThreadPool(1);
	}

	public static void shutScheduler() {
		scheduler.shutdown();
	}

	public void update() {
		if (zombieCount >= TOTAL_ZOMBIE_COUNT && zombies.size() == 1 && !victory) {
			// System.out.println("Victory");
			lastZombie = zombies.get(0);
			setVictory();
		}

		Iterator<Zombie> zombieIterator = zombies.iterator();
		while (zombieIterator.hasNext()) {
			Zombie z = zombieIterator.next();
			boolean attacked = false;
			List<Plant> plantsToRemove = new ArrayList<>();

			// Iterate over the unharmed plants
			Iterator<Plant> plantIterator = plants.iterator();
			while (plantIterator.hasNext()) {
				Plant p = plantIterator.next();

				// Check if zombie and plant are at the same position
				if (((int) z.getX() <= (int) p.getX() && (int) z.getX() >= (int) p.getX() - 30) && ((int) z.getY() == (int) p.getY())) {
					if (z instanceof VaultingType && ((VaultingType) z).getVault() && !(p instanceof Tanglekelp) && !(p instanceof Squash)) {
						if (p instanceof Tallnut) {
							((VaultingType) z).setVault(false);
							z.setSpeed(-0.15f);
							if (z instanceof Polevault) {
								z.setImage(z.getZombieImage("Polevault2"));
							} 
							z.setAttacking(true);
							attacked = true;
							break;
						} else if (p instanceof Lilypad) {
							boolean withLily = false;
							for (Plant p2 : plants) {
								if (p2.getX() == p.getX() && p2.getY() == p.getY() && !(p2 instanceof Lilypad)) {
									withLily = true;
									if (p2 instanceof Tallnut) {
										((VaultingType) z).setVault(false);
										z.setSpeed(-0.15f);
									} else if (p2 instanceof Squash) {
										p2.actionStop();
										plantsToRemove.add(p2);
										zombieIterator.remove();
										break;
									} else {
										z.action();
										p2.actionStop();
										plantsToRemove.add(p2);
									}
								}
							}
							if (!withLily) {
								z.action();
								p.actionStop();
								plantsToRemove.add(p);
							}
							if (z instanceof Dolphin) {
								z.setImage(z.getZombieImage("Dolphin2"));
							}
						} else if (p instanceof Tanglekelp) {
							p.actionStop();
							plantsToRemove.add(p);
							zombieIterator.remove();
							break;
						} else {
							z.action();
							p.actionStop();
							plantsToRemove.add(p);
							if (z instanceof Polevault) {
								z.setImage(z.getZombieImage("Polevault2"));
							}
						}
					} else {
						if (p instanceof Tanglekelp) {
							p.actionStop();
							plantsToRemove.add(p);
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

			// Remove plants after iteration
			plants.removeAll(plantsToRemove);

			// Move the zombie
			if (!attacked) {
				z.move(z.getSpeed(), 0);
				z.setAttacking(false);  // Stop attacking when moving
			} else {
				z.startAttacking();
			}

			if (z.getFrozenTick() != -1 && z.getFrozenTick() + 3 <= Sun.getTick()) {
				z.setSpeed(z.getBeforeSpeed());
				zombieReturnToNormalImage(z);
				z.setFrozenTick(-1);
			}

			if (z instanceof Newspaper) {
				if (((Newspaper)z).getAngerTick() != -1 && ((Newspaper)z).getAngerTick() + 3 <= Sun.getTick()) {
					z.setSpeed(-0.4f);
					((Newspaper)z).setAngerTick(-1);
				}
			}

			if (z.getHealth() <= 0) {
				if (z.equals(lastZombie))
					VictoryNoteManager.addNoteDrop((int) z.getX(), (int) z.getY());
				zombieIterator.remove();
			}

			// Game Over
			if (z.getX() <= 100) {
				Music.playSound("GameOver", false);
				setGameState(GAMEOVER);
			}
    	}
	}

	private static void zombieReturnToNormalImage(Zombie z) { // habis frozen set image lg
		if (z instanceof HeadwearType) {
			if (((HeadwearType) z).getHead()) {
				if (z instanceof Buckethead) {
					z.setImage(z.getZombieImage("Buckethead"));
				} else if (z instanceof Conehead) {
					z.setImage(z.getZombieImage("Conehead"));
				} else if (z instanceof Football) {
					z.setImage(z.getZombieImage("Football"));
				}
			} else {
				if (z instanceof Football) {
					z.setImage(z.getZombieImage("Football2"));
				} else {
					z.setImage(z.getZombieImage("Normal"));
				}
			}
		} else if (z instanceof ShieldType) {
			if (((ShieldType) z).getShield()) {
				if (z instanceof Screendoor) {
					z.setImage(z.getZombieImage("Screendoor"));
				} else if (z instanceof Newspaper) {
					z.setImage(z.getZombieImage("Newspaper"));
				}
			} else {
				if (z instanceof Newspaper) {
					z.setImage(z.getZombieImage("Newspaper2"));
				} else {
					z.setImage(z.getZombieImage("Normal"));
				}
			}
		} else if (z instanceof VaultingType) {
			if (((VaultingType) z).getVault()) {
				if (z instanceof Polevault)
					z.setImage(z.getZombieImage("Polevault"));
				else if (z instanceof Dolphin)
					z.setImage(z.getZombieImage("Dolphin"));
			} else {
				if (z instanceof Polevault)
					z.setImage(z.getZombieImage("Polevault2"));
				else if (z instanceof Dolphin)
					z.setImage(z.getZombieImage("Dolphin2"));
			}
		} else if (z instanceof Duckytube) {
			z.setImage(z.getZombieImage("Duckytube2"));
		} else {
			z.setImage(z.getZombieImage("Normal"));
		}
	}

	public void scheduleZombieGeneration() { 
		if (Sun.getTick() == 175) {
			zombieDelay = 1;
			zombieAtOneTime = 25;
		}
		Runnable addZombieTask = () -> { 
			int tick = Sun.getTick();
			if (tick >= 20 && tick <= 210) {
				if (zombies.size() >= zombieAtOneTime) {
					return;
				} else {
					Random rand = new Random();
					int pos;
					if (tick >= 165 && tick <= 170 && !flag) {
						pos = rand.nextInt(flagPositions.length);
						addZombie(990, flagPositions[pos]);
					} else if (zombieCount == TOTAL_ZOMBIE_COUNT - 1) {
						pos = rand.nextInt(flagPositions.length);
						addZombie(990, flagPositions[pos]);
					} else {
						pos = rand.nextInt(positions.length);
						addZombie(990, positions[pos]);
					}
					
				}
			}
			if (zombieCount >= TOTAL_ZOMBIE_COUNT || Sun.getTick() > 210) {
				scheduler.shutdown();
			}
		};
		scheduler.scheduleAtFixedRate(addZombieTask, 0, zombieDelay, TimeUnit.SECONDS);
	}

	public static boolean checkZombiesInLane(int y) {
		for (Zombie z : zombies) {
			if (z.getY() == y && z.getX() <= 870) {
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
			if (((int) z.getX() >= (int) x - 60 && (int) z.getX() <= (int) x + 100) && ((int) z.getY() == (int) y)) {
				// p.setX(p.getX() + 80);
				zomPos = (int) z.getX();
				for (Zombie z2 : zombies) {
					if ((int) z2.getX() > (int) x - 20 && (int) z2.getX() <= (int) x + 140 && (int) z2.getY() == (int) y) {
						z2.takeDamage(5000);
						zomPos = zomPos + 5;
					}
				}
			}
		return zomPos;
	}

	public static void slowZombies(Zombie zombie) {
		for (Zombie z : zombies) {
			if (z.equals(zombie)) {
				System.out.println("Zombie Slowed");

				z.setFrozenTick(Sun.getTick());
				z.setSpeed(-0.05f);
				zombieSetToFrozenImage(z);

				if (z instanceof VaultingType || z instanceof Football) {
					z.setBeforeSpeed(-0.3f);
				} else {
					z.setBeforeSpeed(-0.15f);
				}
	
			}
		}
	}

	private static void zombieSetToFrozenImage(Zombie z) { // set zombies ke Frozen
		if (z instanceof HeadwearType) {
			if (((HeadwearType) z).getHead()) {
				if (z instanceof Buckethead) {
					z.setImage(z.getFrozenZombieImage("Buckethead"));
				} else if (z instanceof Conehead) {
					z.setImage(z.getFrozenZombieImage("Conehead"));
				} else if (z instanceof Football) {
					z.setImage(z.getFrozenZombieImage("Football"));
				}
			} else {
				if (z instanceof Football) {
					z.setImage(z.getFrozenZombieImage("Football2"));
				} else {
					z.setImage(z.getFrozenZombieImage("Normal"));
				}
			}
		} else if (z instanceof ShieldType) {
			if (((ShieldType) z).getShield()) {
				if (z instanceof Screendoor) {
					z.setImage(z.getFrozenZombieImage("Screendoor"));
				} else if (z instanceof Newspaper) {
					z.setImage(z.getFrozenZombieImage("Newspaper"));
				}
			} else {
				if (z instanceof Newspaper) {
					z.setImage(z.getFrozenZombieImage("Newspaper2"));
				} else {
					z.setImage(z.getFrozenZombieImage("Normal"));
				}
			}
		} else if (z instanceof VaultingType) {
			if (((VaultingType) z).getVault()) {
				if (z instanceof Polevault)
					z.setImage(z.getFrozenZombieImage("Polevault"));
				else if (z instanceof Dolphin)
					z.setImage(z.getFrozenZombieImage("Dolphin"));
			} else {
				if (z instanceof Polevault)
					z.setImage(z.getFrozenZombieImage("Polevault2"));
				else if (z instanceof Dolphin)
					z.setImage(z.getFrozenZombieImage("Dolphin2"));
			}
		} else if (z instanceof Duckytube) {
			z.setImage(z.getFrozenZombieImage("Duckytube2"));
		} else {
			z.setImage(z.getFrozenZombieImage("Normal"));
		}
	}

	public static void takeDamage(Zombie zombie, int damage) {
		for (Zombie z : zombies) {
			if (z.equals(zombie)) {
				z.takeDamage(damage);
				if (z instanceof HeadwearType && (z.getHealth() == ((HeadwearType) z).getDefHealthHead())) {
					if (z instanceof Buckethead) {
                        ((Buckethead) z).setHead(false);
						z.setImage(z.getZombieImage("Normal"));
                    } else if (z instanceof Conehead) {
                        ((Conehead) z).setHead(false);
						z.setImage(z.getZombieImage("Normal"));
                    } else if (z instanceof Football) {
                        ((Football) z).setHead(false);
						z.setImage(z.getZombieImage("Football2"));
                    }
				} else if (z instanceof ShieldType && (z.getHealth() == ((ShieldType) z).getDefHealth())) {
					((ShieldType) z).setShield(false);
					if (z instanceof Screendoor) {
						z.setImage(z.getZombieImage("Normal"));
                    } else if (z instanceof Newspaper) {
						z.setImage(z.getZombieImage("Newspaper2"));
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
		String[] zombieTypes = {"Normal", "Football", "Conehead", "Buckethead", "Screendoor", "Polevault", "Newspaper", "Duckytube", "Dolphin"};
		// String[] zombieTypes = {"Polevault", "Duckytube", "Dolphin"};
		int zombieType = random.nextInt(zombieTypes.length);
		Zombie zom = ZombieFactory.CreateZombie(zombieTypes[zombieType], x, y);
		
		if (Sun.getTick() >= 165 && Sun.getTick() <= 170 && !flag) {
			zom = ZombieFactory.CreateZombie("Flag", x, y);
			flag = true;
		} else {
			while ((y == 380 || y == 470) && !zom.getAquatic()) {
				zombieType = random.nextInt(zombieTypes.length);
				zom = ZombieFactory.CreateZombie(zombieTypes[zombieType], x, y);
			}
	
			while ((y == 200 || y == 290 || y == 560 || y == 650) && zom.getAquatic()) {
				zombieType = random.nextInt(zombieTypes.length);
				zom = ZombieFactory.CreateZombie(zombieTypes[zombieType], x, y);
			}
		}

		zombies.add(zom);
		zombieCount++;

		System.out.println("Zombie generated at " + y);
	}

	public void clearZombie() {
		zombies.clear();
	}

	public void draw(Graphics g) {
		for (Zombie z : zombies)
			drawZombie(z, g);
	}

	private void drawZombie(Zombie z, Graphics g) {
		if (z.getAquatic()) {
			if (z instanceof Dolphin) {
				if (z.getX() <= 870 && z.getX() >= 150 && ((Dolphin) z).getVault()) {
					if (z.getFrozenTick() == -1) {
						z.setImage(z.getZombieImage("Dolphin"));
					}
					g.drawImage(z.getImage(), (int) z.getX() + 50, (int) z.getY(), null);
				} else if (z.getX() <= 870 && z.getX() >= 150 && !((Dolphin) z).getVault()) {
					if (z.getFrozenTick() == -1) {
						z.setImage(z.getZombieImage("Dolphin2"));
					}
					g.drawImage(z.getImage(), (int) z.getX(), (int) z.getY(), null);
				} else if (z.getX() < 150) {
					if (z.getFrozenTick() == -1) {
						z.setImage(z.getZombieImage("Dolphin3"));
					}
					g.drawImage(z.getImage(), (int) z.getX() + 30, (int) z.getY() - 90, null);
				} else {
					if (z.getFrozenTick() == -1) {
						z.setImage(z.getZombieImage("Dolphin0"));
					}
					g.drawImage(z.getImage(), (int) z.getX() + 50, (int) z.getY() - 30, null);
				}
			} else if (z instanceof Duckytube) {
				if (z.getX() <= 910 && z.getX() >= 220) {
					if (z.getFrozenTick() == -1) {
						z.setImage(z.getZombieImage("Duckytube2"));
					}
					g.drawImage(z.getImage(), (int) z.getX(), (int) z.getY(), null);
				} else {
					if (z.getFrozenTick() == -1) {
						z.setImage(z.getZombieImage("Duckytube"));
					}
					g.drawImage(z.getImage(), (int) z.getX()-30, (int) z.getY()-30, null);
				}
			}
		} else {
			g.drawImage(z.getImage(), (int) z.getX(), (int) z.getY() - 90, null);
		}
		
	}

	public static ArrayList<Zombie> getZombies(){
		return zombies;
	}

	public static void setVictory() {
		victory = true;
	}

	public static void resetVictory() {
		victory = false;
	}

	public static boolean isVictory() {
		return victory;
	}
}