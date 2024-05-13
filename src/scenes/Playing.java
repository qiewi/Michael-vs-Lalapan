package scenes;

import java.awt.Graphics;

import helpz.LevelBuild;
import main.Game;
import managers.TileManager;
import objects.Tile;
import ui.BottomBar;
import ui.MyButton;

import static main.GameStates.*;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private TileManager tileManager;

	private BottomBar bottomBar;

	public Playing(Game game) {
		super(game);

		lvl = LevelBuild.getLevelData();
		tileManager = new TileManager();
		bottomBar = new BottomBar(0, 64 * 6, 64 * 9, 100, this);

	}

	@Override
	public void render(Graphics g) {

		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(tileManager.getSprite(id), x * 64, y * 64, null);
	
			}
		}
		
		bottomBar.draw(g);

	}

	public TileManager getTileManager () {
		return tileManager;
	}


	@Override
	public void mouseClicked(int x, int y) {
		if ( y >= 64 * 6) {
			bottomBar.mouseClicked(x, y);
		} 
	}

	@Override
	public void mouseMoved(int x, int y) {
		if ( y >= 64 * 6) {
			bottomBar.mouseMoved(x, y);
		} 
	}

	@Override
	public void mousePressed(int x, int y) {
		if ( y >= 64 * 6) {
			bottomBar.mousePressed(x, y);
		} 
	}

	@Override
	public void mouseReleased(int x, int y) {	
		bottomBar.mouseReleased(x, y);
	}

}