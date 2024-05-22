package scenes;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
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
import managers.VictoryNoteManager;
import managers.ZombiesManager;
import objects.Sun;
import ui.FlagBar;
import ui.MyButton;
import ui.TopBar;

public class Playing extends GameScene implements SceneMethods {

    private int xArrow, yArrow;
    private PlantsManager plantsManager;
    private ZombiesManager zombiesManager;
    private SunDropManager sunDropManager;
    private PeasManager peasManager;
    private VictoryNoteManager victoryNoteManager;
    private String[] plantDeck;

    private TopBar topBar;
    private FlagBar flagBar;

    private Sun sun;
    private MyButton sunText;

    private BufferedImage dayImage, nightImage;
    private static float alpha = 1.0f; 
    private static boolean isTransitioning = false; 
    private static boolean hasTransitionedToNight = false; 

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
        victoryNoteManager = new VictoryNoteManager(this);

        // Initialize Bar
        topBar = new TopBar(0, 0, 768, 100, this);
        flagBar = new FlagBar(795, 65, this);

        // Initialize etc
        sun = new Sun();
        initSunText();
        loadImages();
    }

    private void loadImages() {
        try {
            InputStream isDay = getClass().getResourceAsStream("resources/PoolDay.png");
            InputStream isNight = getClass().getResourceAsStream("resources/PoolNight.png");
            dayImage = ImageIO.read(isDay);
            nightImage = ImageIO.read(isNight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g) {
        // Draw Map
        drawMap(g);
        drawSunText(g);

        // Draw Managers
        plantsManager.draw(g);
        zombiesManager.draw(g);
        sunDropManager.draw(g);
        peasManager.draw(g);
        victoryNoteManager.draw(g);

        // Draw Bar
        topBar.draw(g);
        flagBar.draw(g);

        // Draw Arrow and the Selected Tile
        drawSelectedTile(g);
    }

    public void update() {
        transitionUpdate();

        plantsManager.update();
        zombiesManager.update();
        sunDropManager.update();
        peasManager.update();
        victoryNoteManager.update();

        sunText.setText(String.valueOf(sun.getSun()));
    }

    private void transitionUpdate() {
        if (isTransitioning) {
            alpha -= 0.01f; 
            if (alpha <= 0) {
                alpha = 0;
                isTransitioning = false; 
                hasTransitionedToNight = !Sun.getMorning();
            }
        } else if (!Sun.getMorning() && !hasTransitionedToNight) {
            
            isTransitioning = true;
            alpha = 1.0f;
        }
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
                
                g.drawImage(img, xArrow, yArrow - 30, null);
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
        Graphics2D g2d = (Graphics2D) g;

        if (Sun.getMorning()) {
            g2d.drawImage(dayImage, 0, 0, null);
        } else {
            if (!isTransitioning) {
                g2d.drawImage(nightImage, 0, 0, null);
            } else {
                
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2d.drawImage(dayImage, 0, 0, null);

                
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - alpha));
                g2d.drawImage(nightImage, 0, 0, null);

                
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            }
        }
    }

    public static void resetTransition() {
        alpha = 1.0f;
        isTransitioning = false;
        hasTransitionedToNight = !Sun.getMorning();
    }

	@Override
	public void mouseClicked(int x, int y) {
		if ( y >= 2 && y <= 42 && x >= 874 && x <= 1004) {
			topBar.mouseClicked(x, y);
		} else {
			victoryNoteManager.mouseClicked(x, y);
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
		switch (e.getKeyCode()) { // TopBarCoolDown pindah ke add plant ajah
			
			case KeyEvent.VK_1:
				if (!topBar.getPlantCardsButton(0).isOnCooldown()) {
					plantsManager.addPlant(topBar.getPlantCardsButton(0).getName(), xArrow, yArrow, 0);
				}
				break;

			case KeyEvent.VK_2:
				if (!topBar.getPlantCardsButton(1).isOnCooldown()) {
					plantsManager.addPlant(topBar.getPlantCardsButton(1).getName(), xArrow, yArrow, 1);
				}
				break;
			
			case KeyEvent.VK_3:
				if (!topBar.getPlantCardsButton(2).isOnCooldown()) {
					plantsManager.addPlant(topBar.getPlantCardsButton(2).getName(), xArrow, yArrow, 2);
				}
				break;

			case KeyEvent.VK_4:
				if (!topBar.getPlantCardsButton(3).isOnCooldown()) {
					plantsManager.addPlant(topBar.getPlantCardsButton(3).getName(), xArrow, yArrow, 3);
				}
				break;
			
			case KeyEvent.VK_5:
				if (!topBar.getPlantCardsButton(4).isOnCooldown()) {
					plantsManager.addPlant(topBar.getPlantCardsButton(4).getName(), xArrow, yArrow, 4);
				}
				break;
			
			case KeyEvent.VK_6:
				if (!topBar.getPlantCardsButton(5).isOnCooldown()) {
					plantsManager.addPlant(topBar.getPlantCardsButton(5).getName(), xArrow, yArrow, 5);
				}
				break;

			case KeyEvent.VK_D:
				PlantsManager.deletePlantsAt(xArrow, yArrow);;
				break;
		}
		
    }

	public void clearAll() {
		sun.resetTick();
		plantsManager.clearPlants();
		zombiesManager.clearZombie();
		sunDropManager.clearSun();
		peasManager.clearPeas();
		xArrow = 270;
		yArrow = 200;
	}

	public String[] getPlantDeckNames () {
		return plantDeck;
	}

	public PlantsManager getPlantsManager() {
		return plantsManager;
	}

	public ZombiesManager getZombiesManager() {
		return zombiesManager;
	}

	public TopBar getTopBar() {
		return topBar;
	}

	public Sun getPlayingSun() {
		return sun;
	}
}