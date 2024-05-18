package managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import entity.Zombies.Zombie;
import entity.Zombies.ZombieFactory;
import scenes.Playing;

import entity.Plants.Plant;

public class ZombiesManager {
    private Playing playing;
    private static ArrayList<Zombie> zombies;
    private static ArrayList<Zombie> zombiesWalk;
    private static ArrayList<Zombie> zombiesEat;

    private PlantsManager PM;
    private ArrayList<Plant> plants;
    private static ArrayList<Plant> plantsUnharmed;
    private static ArrayList<Plant> plantsAttacked;

    public ZombiesManager(Playing playing) {
        this.playing = playing;

		// ditaro di inisialiasi
        zombies = new ArrayList<>();
        zombiesWalk = new ArrayList<>();
        zombiesEat = new ArrayList<>();
        plantsUnharmed = new ArrayList<>();
        plantsAttacked = new ArrayList<>();
        initializeZombie();

        PM = new PlantsManager();
    }

    public void update() {
        plants = PM.getPlants();
        plantsUnharmed = new ArrayList<>(plants); // Create a copy of the plants list

        List<Zombie> zombiesToWalk = new ArrayList<>();
        List<Zombie> zombiesToEat = new ArrayList<>();
        List<Plant> plantsToUnharm = new ArrayList<>();
        List<Plant> plantsToAttack = new ArrayList<>();

        // Iterate over the zombies that are walking
        Iterator<Zombie> zombieIterator = zombiesWalk.iterator();
        while (zombieIterator.hasNext()) {
            Zombie z = zombieIterator.next();
            boolean attacked = false;

            // Iterate over the unharmed plants
            Iterator<Plant> plantIterator = plantsUnharmed.iterator();
            while (plantIterator.hasNext()) {
                Plant p = plantIterator.next();

                // Check if zombie and plant are at the same position
                if (((int) z.getX() == (int) p.getX()) && ((int) z.getY() == (int) p.getY())) {
                    zombieIterator.remove();
                    plantIterator.remove();
                    plantsToAttack.add(p);
                    attacked = true;
                    break;
                } else {
                    plantsToUnharm.add(p);
                }
            }

            // Move the zombie if it hasn't attacked
            if (!attacked) {
                z.move(-0.3f, 0);
                zombiesToWalk.add(z);
            } else {
                zombiesToEat.add(z);
            }
        }

        // Update the lists
        zombiesWalk = new ArrayList<>(zombiesToWalk);
        zombiesEat = new ArrayList<>(zombiesToEat);
        plantsUnharmed = new ArrayList<>(plantsToUnharm);
        plantsAttacked = new ArrayList<>(plantsToAttack);

        // Check the health of attacked plants and handle zombies accordingly
        Iterator<Zombie> eatingZombieIterator = zombiesEat.iterator();
        Iterator<Plant> attackedPlantIterator = plantsAttacked.iterator();
        while (eatingZombieIterator.hasNext() && attackedPlantIterator.hasNext()) {
            Zombie z = eatingZombieIterator.next();
            Plant p = attackedPlantIterator.next();

            System.out.println("test " + p.getHealth());
            z.startAttacking(p);
            System.out.println("test " + p.getHealth());
            if (p.getHealth() <= 0) {
				System.out.println("test " + p.getHealth());
                eatingZombieIterator.remove();
                attackedPlantIterator.remove();
				zombiesWalk.add(z);
                zombiesEat.remove(z);
				plantsUnharmed.add(p);
                plantsAttacked.add(p);
				System.out.println(p.getHealth());
            }
        }
    }

    private void initializeZombie() {
        int[] position = new int[]{200, 290, 380, 470, 560, 650};

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
        Zombie newZombie = ZombieFactory.CreateZombie("Normal", x, y);
        zombies.add(newZombie);
        zombiesWalk.add(newZombie);
    }

    public void draw(Graphics g) {
        for (Zombie z : zombies)
            drawZombie(z, g);
    }

    private void drawZombie(Zombie z, Graphics g) {
        g.drawImage(z.getImage(), (int) z.getX(), (int) z.getY() - 35, null);
    }
}
