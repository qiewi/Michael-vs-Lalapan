package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Plants.Plant;
import entity.Plants.PlantFactory;
import entity.Plants.Sunflower;
import main.Game;
import managers.ZombiesManager;
import ui.MyButton;
import ui.TopBar;


public class Playing extends GameScene implements SceneMethods {

	private int xArrow, yArrow;
	private ZombiesManager zombiesManager;
	private String[] plantDeck;

	private static ArrayList<Plant> PlantsList = new ArrayList<Plant>();

	private TopBar topBar;

	public Playing(Game game) {
		super(game);
		xArrow = 270;
		yArrow = 200;

		zombiesManager = new ZombiesManager(this);
		topBar = new TopBar(0, 0, 768, 100, this);
	}

	@Override
	public void render(Graphics g) {

		drawMap(g);
		topBar.draw(g);
		drawPlants(g);
		zombiesManager.draw(g);
		drawSelectedTile(g);

	}

	public void update() {
		updateTick();
		zombiesManager.update();
	}

	public void createPlantDeck(String[] plantDeck) {
		this.plantDeck = plantDeck;
	}

	private void drawPlants(Graphics g) {
		for (Plant plant : PlantsList) {
			g.drawImage(plant.getImage(), (int) plant.getX() - 15, (int) plant.getY(), null);
		}
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

	private boolean checkPool(Plant plant) {
		boolean plantable = true;
		
		if (yArrow >= 380 && yArrow <= 470) {
			if (!plant.getAquaStatus()) {
				for (Plant p : PlantsList) {
					if (p.getX() == xArrow && p.getY() == yArrow) {
						if (p.getName().equals("LilyPad") == false) {
							plantable = false;
						}
					}
				}
			}
		}

		return plantable;
	}


	@Override
	public void mouseClicked(int x, int y) {
		if ( y >= 0 && y <= 100) {
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
		// Set
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

		// Create Plants (Harus disesuain lg)
		Plant plantCreated;

		switch (e.getKeyCode()) {
			
			case KeyEvent.VK_1:
				if (checkPlants()) {
					break;
				} else {
					plantCreated = PlantFactory.CreatePlant(topBar.getPlantCardsButton(0).getName(), xArrow, yArrow);
					if (checkPool(plantCreated)) 
						PlantsList.add(plantCreated);
				}
				break;

			case KeyEvent.VK_2:
				if (checkPlants()) {
					break;
				} else {
					plantCreated = PlantFactory.CreatePlant(topBar.getPlantCardsButton(1).getName(), xArrow, yArrow);
					if (checkPool(plantCreated)) 
						PlantsList.add(plantCreated);
				}
				break;
			
			case KeyEvent.VK_3:
				if (checkPlants()) {
					break;
				} else {
					plantCreated = PlantFactory.CreatePlant(topBar.getPlantCardsButton(2).getName(), xArrow, yArrow);
					if (checkPool(plantCreated)) 
						PlantsList.add(plantCreated);
				}
				break;

			case KeyEvent.VK_4:
				if (checkPlants()) {
					break;
				} else {
					plantCreated = PlantFactory.CreatePlant(topBar.getPlantCardsButton(3).getName(), xArrow, yArrow);
					if (checkPool(plantCreated)) 
						PlantsList.add(plantCreated);
				}
				break;
			
			case KeyEvent.VK_5:
				if (checkPlants()) {
					break;
				} else {
					plantCreated = PlantFactory.CreatePlant(topBar.getPlantCardsButton(4).getName(), xArrow, yArrow);
					if (checkPool(plantCreated)) 
						PlantsList.add(plantCreated);
				}
				break;
			
			case KeyEvent.VK_6:
				if (checkPlants()) {
					break;
				} else {
					plantCreated = PlantFactory.CreatePlant(topBar.getPlantCardsButton(5).getName(), xArrow, yArrow);
					if (checkPool(plantCreated)) 
						PlantsList.add(plantCreated);
				}
				break;
		}
		
    }

	public void clearPlants() {
		PlantsList.clear();
	}

	private boolean checkPlants() {
		boolean isExist = false;
		for (Plant plant : PlantsList) {
			if (plant.getX() == xArrow && plant.getY() == yArrow) {
				isExist = true;
			}
		}
		return isExist;
	}

	public String[] getPlantDeckNames () {
		return plantDeck;
	}

	public TopBar getTopBar() {
		return topBar;
	}
}