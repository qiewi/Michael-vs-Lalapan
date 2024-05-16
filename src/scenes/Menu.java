package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

	private MyButton bPlaying, bPlantsList, bZombiesList, bHelp, bQuit;

	public Menu(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {

		int w = 230;
		int h = 40;
		int x = 1024 / 2 - w / 2 + 250;
		int y = 728 - 290;
		int yOffset = 70;

		bPlaying = new MyButton("Play", x, y, w, h);
		bPlantsList = new MyButton("Plants List", x, y + yOffset, w, h);
		bZombiesList = new MyButton("Zombies List", x, y + yOffset * 2, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);

		bHelp = new MyButton("Help", 930, 685, 70, 50);

	}

	@Override
	public void render(Graphics g) {
		drawButtons(g);
		drawMenu(g);
	}

	private void drawMenu(Graphics g) {
		BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream("resources/MainMenu.png");
	
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
		bPlaying.draw(g);
		bPlantsList.draw(g);
		bZombiesList.draw(g);
		bHelp.draw(g);
		bQuit.draw(g);

	}

	@Override
	public void mouseClicked(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)) {
			setGameState(PREPARATION);
		} else if (bPlantsList.getBounds().contains(x, y)) {
			setGameState(PLANTSLIST);
		} else if (bZombiesList.getBounds().contains(x, y)) {
			setGameState(ZOMBIESLIST);
		} else if (bQuit.getBounds().contains(x, y)) {
			System.exit(0);
		} else if (bHelp.getBounds().contains(x, y)) {
			setGameState(HELP);
		}
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseHover(false);
		bPlantsList.setMouseHover(false);
		bQuit.setMouseHover(false);

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseHover(true);
		} else if (bPlantsList.getBounds().contains(x, y)) {
			bPlantsList.setMouseHover(true);
		} else if (bZombiesList.getBounds().contains(x, y)) {
			bZombiesList.setMouseHover(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseHover(true);
		} else if (bHelp.getBounds().contains(x, y)) {
			bHelp.setMouseHover(true);
		}

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		} else if (bPlantsList.getBounds().contains(x, y)) {
			bPlantsList.setMousePressed(true);
		} else if (bZombiesList.getBounds().contains(x, y)) {
			bZombiesList.setMousePressed(true);
		}  else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bPlantsList.resetBooleans();
		bQuit.resetBooleans();
		bHelp.resetBooleans();
	}

}