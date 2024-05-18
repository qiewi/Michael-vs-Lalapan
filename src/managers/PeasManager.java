package managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import entity.Plants.Plant;
import entity.Zombies.Zombie;
import objects.Pea;
import scenes.Playing;

public class PeasManager {
    private Playing playing;
	private static ArrayList<Pea> peas = new ArrayList<>();
    private static ArrayList<Zombie> zombies = new ArrayList<>();
    private int peasSize = peas.size();

    public PeasManager(Playing playing) {
		this.playing = playing;
        this.zombies = ZombiesManager.getZombies();
	}

	public void update() {
        Iterator<Pea> peasIterator = peas.iterator();
        while (peasIterator.hasNext()) {
            Pea p = peasIterator.next();
            boolean collide = false;

			Iterator<Zombie> zombieIterator = zombies.iterator();
			while (zombieIterator.hasNext()) {
				Zombie z = zombieIterator.next();
	
				if (((int) z.getX() >= (int) p.getX() && (int) z.getX() <= (int) p.getX() + 30) && ((int) z.getY() == (int) p.getY())) {
                    System.out.println("Pea hit zombie at " + (int) p.getX() + ", " + (int) p.getY());
                    ZombiesManager.takeDamage(z, 25);
					collide = true;
					break;
				} 

			}
	
			if (!collide) {
				p.move();
			} else {
				peasIterator.remove();
			}
        }
	}

    // For Game 
	public static void addPeaInLane(int x, int y, String name) {
        switch (name) {
            case "Normal":
                peas.add(new Pea(x, y, "Normal"));
                break;
            case "Frozen":
                peas.add(new Pea(x, y, "Frozen"));
                break;
            default:
                break;
        };
	}

	public void draw(Graphics g) {
		for (Pea p : peas)
			drawPea(p, g);
	}

	private void drawPea(Pea pea, Graphics g) {
		g.drawImage(pea.getImage(), (int) pea.getX(), (int) pea.getY(), null);
	}

    public int getPeasSize() {
        return peasSize;
    }
}
