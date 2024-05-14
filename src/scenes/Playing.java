package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import helpz.LevelBuild;
import main.Game;
import managers.CardManager;
import ui.TopBar;

import static main.GameStates.*;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private CardManager tileManager;

	private TopBar bottomBar;

	public Playing(Game game) {
		super(game);

		lvl = LevelBuild.getLevelData();
		tileManager = new CardManager();
		bottomBar = new TopBar(0, 0, 768, 100, this);

	}

	@Override
	public void render(Graphics g) {

		// for (int y = 0; y < lvl.length; y++) {
		// 	for (int x = 0; x < lvl[y].length; x++) {
		// 		int id = lvl[y][x];
		// 		g.drawImage(tileManager.getSprite(id), x * 64, y * 64, null);
	
		// 	}
		// }

		bottomBar.draw(g);
		drawMap(g);

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

	public CardManager getTileManager () {
		return tileManager;
	}


	@Override
	public void mouseClicked(int x, int y) {
		if ( y >= 0 && y <= 100) {
			bottomBar.mouseClicked(x, y);
		} 
	}

	@Override
	public void mouseMoved(int x, int y) {
		if ( y >= 0 && y <= 100) {
			bottomBar.mouseMoved(x, y);
		} 
	}

	@Override
	public void mousePressed(int x, int y) {
		if ( y >= 0 && y <= 100) {
			bottomBar.mousePressed(x, y);
		} 
	}

	@Override
	public void mouseReleased(int x, int y) {	
		bottomBar.mouseReleased(x, y);
	}

}