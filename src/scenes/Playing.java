package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import managers.PeasManager;
import managers.PlantsManager;
import managers.SunDropManager;
import managers.ZombiesManager;
import objects.Sun;
import ui.MyButton;
import ui.TopBar;


public class Playing extends GameScene implements SceneMethods {

	private int xArrow, yArrow;
	private PlantsManager plantsManager;
	private ZombiesManager zombiesManager;
	private SunDropManager sunDropManager;
	private PeasManager peasManager;
	private String[] plantDeck;

	private TopBar topBar;

	private Sun sun;
	private MyButton sunText;

	// Initialize sun text
    private int startXSun = 38;
    private int startYSun = 75;
    private int SunWidth = 25;
    private int SunHeight = 25;

	public Playing(Game game) {
		super(game);

		// Default Cursor Position
		xArrow = 270;
		yArrow = 200;

		// Initialize Managers
		plantsManager = new PlantsManager(this);
		zombiesManager = new ZombiesManager(this);
		sunDropManager = new SunDropManager(this);
		peasManager = new PeasManager(this);

		topBar = new TopBar(0, 0, 768, 100, this);

		sun = new Sun();
        initSunText();
	}

	@Override
	public void render(Graphics g) {

		// Draw Map and Bar
		drawMap(g);
		topBar.draw(g);
		drawSunText(g);

		// Draw Managers
		plantsManager.draw(g);
		zombiesManager.draw(g);
		sunDropManager.draw(g);
		peasManager.draw(g);

		// Draw Arrow and the Selected Tile
		drawSelectedTile(g);
	}

	public void update() {
		updateTick();

		plantsManager.update();
		zombiesManager.update();
		sunDropManager.update();
		peasManager.update();
		
		sunText.setText(String.valueOf(sun.getSun()));
	}

	public void createPlantDeck(String[] plantDeck) {
		this.plantDeck = plantDeck;
	}

	private void drawSelectedTile(Graphics g) {
		BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream("resources/Arrow.png");

		if (is == null) {
			System.out.println("Stream is null. Check the file path.");
		} else {
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			if (img == null) {
				System.out.println("Image is null. Check the file format and content.");
			} else {
				// Per Tiles 80 x 90
				g.drawImage(img, xArrow, yArrow, null);
			}
		}
	}

	private void initSunText() {
        sunText = new MyButton(String.valueOf(sun.getSun()), startXSun, startYSun, SunWidth, SunHeight, true);
    }

	private void drawSunText(Graphics g) {
        sunText.draw(g);
    }

	private void drawMap(Graphics g) {
		BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream("resources/PoolDay.png");
	
		if (is == null) {
			System.out.println("Stream is null. Check the file path.");
		} else {
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			if (img == null) {
				System.out.println("Image is null. Check the file format and content.");
			} else {
				g.drawImage(img, 0, 0, null);
			}
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if ( y >= 2 && y <= 42 && x >= 874 && x <= 1004) {
			topBar.mouseClicked(x, y);
		} 
	}

	@Override
	public void mouseMoved(int x, int y) {
		if ( y >= 0 && y <= 100) {
			topBar.mouseMoved(x, y);
		} 
	}

	@Override
	public void mousePressed(int x, int y) {
		if ( y >= 0 && y <= 100) {
			topBar.mousePressed(x, y);
		} 
	}

	@Override
	public void mouseReleased(int x, int y) {	
		topBar.mouseReleased(x, y);
	}


	public void keyPressed(KeyEvent e) {
		// Posisi Cursor
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (xArrow + 80 > 910) {
				xArrow = 270;
			} else {
				xArrow += 80;
			}
			Game.update();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (xArrow - 80 < 270) {
				xArrow = 910;
			} else {
				xArrow -= 80;
			}
			Game.update();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (yArrow - 90 < 200) {
				yArrow = 650;
			} else {
				yArrow -= 90;
			}
			Game.update();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (yArrow + 90 > 650) {
				yArrow = 200;
			} else {
				yArrow += 90;
			}
			Game.update();
		}

		// Create Plants 
		switch (e.getKeyCode()) {
			
			case KeyEvent.VK_1:
				plantsManager.addPlant(topBar.getPlantCardsButton(0).getName(), xArrow, yArrow);
				break;

			case KeyEvent.VK_2:
				plantsManager.addPlant(topBar.getPlantCardsButton(1).getName(), xArrow, yArrow);
				break;
			
			case KeyEvent.VK_3:
				plantsManager.addPlant(topBar.getPlantCardsButton(2).getName(), xArrow, yArrow);
				break;

			case KeyEvent.VK_4:
				plantsManager.addPlant(topBar.getPlantCardsButton(3).getName(), xArrow, yArrow);
				break;
			
			case KeyEvent.VK_5:
				plantsManager.addPlant(topBar.getPlantCardsButton(4).getName(), xArrow, yArrow);
				break;
			
			case KeyEvent.VK_6:
				plantsManager.addPlant(topBar.getPlantCardsButton(5).getName(), xArrow, yArrow);
				break;

			case KeyEvent.VK_D:
				plantsManager.deletePlantsAt(xArrow, yArrow);;
				break;
		}
		
    }

	public void clearPlants() {
		plantsManager.clearPlants();
	}

	public String[] getPlantDeckNames () {
		return plantDeck;
	}

	public PlantsManager getPlantsManager() {
		return plantsManager;
	}

	public TopBar getTopBar() {
		return topBar;
	}

	public Sun getPlayingSun() {
		return sun;
	}
}