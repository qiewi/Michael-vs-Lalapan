package managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import objects.Pea;
import scenes.Playing;

public class PeasManager {
    private Playing playing;
	private static ArrayList<Pea> peas = new ArrayList<>();
    private int peasSize = peas.size();

    public PeasManager(Playing playing) {
		this.playing = playing;
	}

	public void update() {
        Iterator<Pea> iterator = peas.iterator();
        while (iterator.hasNext()) {
            Pea pea = iterator.next();
            pea.move();
            // if (pea.getY() == ) {  // Add cara detect kena zombie
            //     iterator.remove();
            // }
        }
	}

    // For Game 
	public static void addPeaInLane(int x, int y) {
        peas.add(new Pea(x, y));
	}

	public void draw(Graphics g) {
		for (Pea p : peas)
			drawPea(p, g);
	}

	private void drawPea(Pea pea, Graphics g) {
		g.drawImage(pea.getImage(), pea.getX(), pea.getY(), null);
	}

    public int getPeasSize() {
        return peasSize;
    }
}
