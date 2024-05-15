package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class ZombiesList extends GameScene implements SceneMethods {

	private MyButton bQuit;
	private MyButton Normal, ConeHead, BucketHead, ScreenDoor;
	private MyButton Football, Newspaper, DuckyTube, Flag;
	private MyButton PoleVault, DolphinRider;

	private BufferedImage selectedZombie = null;
	private BufferedImage[] zombieImages = new BufferedImage[10];

	public ZombiesList(Game game) {
		super(game);
		initButtons();
		initImages();
	}

	private void initImages() {
		zombieImages[0] = getZombieImage("Normal");
        zombieImages[1] = getZombieImage("ConeHead");
        zombieImages[2] = getZombieImage("BucketHead");
        zombieImages[3] = getZombieImage("ScreenDoor");
        zombieImages[4] = getZombieImage("Football");
        zombieImages[5] = getZombieImage("Newspaper");
        zombieImages[6] = getZombieImage("DuckyTube");
        zombieImages[7] = getZombieImage("Flag");
        zombieImages[8] = getZombieImage("PoleVault");
        zombieImages[9] = getZombieImage("DolphinRider");
	}

	public BufferedImage getZombieImage(String name) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("resources/ZombiesList/" + name + ".png");

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

		Normal = new MyButton("1", x, y, w, h);
		ConeHead= new MyButton("2", x + xOffset, y, w, h);
		BucketHead = new MyButton("3", x + xOffset * 2, y, w, h);
		ScreenDoor = new MyButton("4", x + xOffset * 3, y, w, h);
		Football = new MyButton("5", x, y + yOffset, w, h);
		Newspaper = new MyButton("6", x + xOffset, y + yOffset, w, h);
		DuckyTube = new MyButton("7", x + xOffset * 2, y + yOffset, w, h);
		Flag = new MyButton("8", x + xOffset * 3, y + yOffset, w, h);
		PoleVault = new MyButton("9", x, y + yOffset * 2, w, h);
		DolphinRider = new MyButton("10", x + xOffset, y + yOffset * 2, w, h);

		bQuit = new MyButton("Quit", 870, 730, 110, 20);

	}

	@Override
	public void render(Graphics g) {
        drawButtons(g);
		drawAlmanac(g);
		drawSelectedZombie(g);
	}

	private void drawSelectedZombie(Graphics g) {
		if (selectedZombie != null) {
			g.drawImage(selectedZombie, 0, 0, null);
		}
	}

	private void drawAlmanac(Graphics g) {
		BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream("resources/ZombiesAlmanac.png");
	
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
		Normal.draw(g);
		ConeHead.draw(g);
		BucketHead.draw(g);
		ScreenDoor.draw(g);
		Football.draw(g);
		Newspaper.draw(g);
		DuckyTube.draw(g);
		Flag.draw(g);
		PoleVault.draw(g);
		DolphinRider.draw(g);
		bQuit.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {

		if (bQuit.getBounds().contains(x, y)) {
			setGameState(MENU);
		} else if (Normal.getBounds().contains(x,y)) {
			selectedZombie = zombieImages[0];
		} else if (ConeHead.getBounds().contains(x,y)) {
			selectedZombie = zombieImages[1];
		} else if (BucketHead.getBounds().contains(x,y)) {
			selectedZombie = zombieImages[2];
		} else if (ScreenDoor.getBounds().contains(x,y)) {
			selectedZombie = zombieImages[3];
		} else if (Football.getBounds().contains(x,y)) {
			selectedZombie = zombieImages[4];
		} else if (Newspaper.getBounds().contains(x,y)) {
			selectedZombie = zombieImages[5];
		} else if (DuckyTube.getBounds().contains(x,y)) {
			selectedZombie = zombieImages[6];
		} else if (Flag.getBounds().contains(x,y)) {
			selectedZombie = zombieImages[7];
		} else if (PoleVault.getBounds().contains(x,y)) {
			selectedZombie = zombieImages[8];
		} else if (DolphinRider.getBounds().contains(x,y)) {
			selectedZombie = zombieImages[9];
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