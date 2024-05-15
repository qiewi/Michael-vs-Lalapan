package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class PlantsList extends GameScene implements SceneMethods {

	private MyButton bQuit;
	private MyButton Sunflower, Peashooter, Repeater, GatlingPea;
	private MyButton WallNut, TallNut, Squash, SnowPea;
	private MyButton TangleKelp, LilyPad;

	private BufferedImage selectedPlant = null;
	private BufferedImage[] plantImages = new BufferedImage[10];

	public PlantsList(Game game) {
		super(game);
		initButtons();
		initImages();
	}

	private void initImages() {
		plantImages[0] = getPlantImage("Sunflower");
		plantImages[1] = getPlantImage("Peashooter");
		plantImages[2] = getPlantImage("Repeater");
		plantImages[3] = getPlantImage("GatlingPea");
		plantImages[4] = getPlantImage("WallNut");
		plantImages[5] = getPlantImage("TallNut");
		plantImages[6] = getPlantImage("Squash");
		plantImages[7] = getPlantImage("SnowPea");
		plantImages[8] = getPlantImage("TangleKelp");
		plantImages[9] = getPlantImage("LilyPad");
	}

	public BufferedImage getPlantImage(String name) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("resources/PlantsList/" + name + ".png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

	private void initButtons() {

		int w = 95;
		int h = 95;
		int x = 70;
		int y = 155;
		int xOffset = 120;
		int yOffset = 120;

		Sunflower = new MyButton("1", x, y, w, h);
		Peashooter = new MyButton("2", x + xOffset, y, w, h);
		Repeater = new MyButton("3", x + xOffset * 2, y, w, h);
		GatlingPea = new MyButton("4", x + xOffset * 3, y, w, h);
		WallNut = new MyButton("5", x, y + yOffset, w, h);
		TallNut = new MyButton("6", x + xOffset, y + yOffset, w, h);
		Squash = new MyButton("7", x + xOffset * 2, y + yOffset, w, h);
		SnowPea = new MyButton("8", x + xOffset * 3, y + yOffset, w, h);
		TangleKelp = new MyButton("9", x, y + yOffset * 2, w, h);
		LilyPad = new MyButton("10", x + xOffset, y + yOffset * 2, w, h);

		bQuit = new MyButton("Quit", 870, 730, 110, 20);

	}

	@Override
	public void render(Graphics g) {
        drawButtons(g);
		drawAlmanac(g);
		drawSelectedPlant(g);
	}

	private void drawSelectedPlant(Graphics g) {
		if (selectedPlant != null) {
			g.drawImage(selectedPlant, 0, 0, null);
		}
	}

	private void drawAlmanac(Graphics g) {
		BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream("resources/PlantsAlmanac.png");
	
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

	private void drawButtons(Graphics g) {
		Sunflower.draw(g);
		Peashooter.draw(g);
		Repeater.draw(g);
		GatlingPea.draw(g);
		WallNut.draw(g);
		TallNut.draw(g);
		Squash.draw(g);
		SnowPea.draw(g);
		TangleKelp.draw(g);
		LilyPad.draw(g);
		bQuit.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {

		if (bQuit.getBounds().contains(x, y)) {
			setGameState(MENU);
		} else if (Sunflower.getBounds().contains(x,y)) {
			selectedPlant = plantImages[0];
		} else if (Peashooter.getBounds().contains(x,y)) {
			selectedPlant = plantImages[1];
		} else if (Repeater.getBounds().contains(x,y)) {
			selectedPlant = plantImages[2];
		} else if (GatlingPea.getBounds().contains(x,y)) {
			selectedPlant = plantImages[3];
		} else if (WallNut.getBounds().contains(x,y)) {
			selectedPlant = plantImages[4];
		} else if (TallNut.getBounds().contains(x,y)) {
			selectedPlant = plantImages[5];
		} else if (Squash.getBounds().contains(x,y)) {
			selectedPlant = plantImages[6];
		} else if (SnowPea.getBounds().contains(x,y)) {
			selectedPlant = plantImages[7];
		} else if (TangleKelp.getBounds().contains(x,y)) {
			selectedPlant = plantImages[8];
		} else if (LilyPad.getBounds().contains(x,y)) {
			selectedPlant = plantImages[9];
		} 
	}

	@Override
	public void mouseMoved(int x, int y) {
		bQuit.setMouseHover(false);

		if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseHover(true);
		} 

	}

	@Override
	public void mousePressed(int x, int y) {

	}

	@Override
	public void mouseReleased(int x, int y) {
        resetButtons();
	}

	private void resetButtons() {
		bQuit.resetBooleans();
	}

}