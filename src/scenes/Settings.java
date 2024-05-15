package scenes;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import ui.MyButton;

import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods {

	private MyButton bMenu;

	public Settings(Game game) {
		super(game);
		initButtons();

	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 2, 100, 30);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 640, 640);

		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			setGameState(MENU);

	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseHover(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseHover(true);

	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();

	}

}
