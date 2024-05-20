package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;
import main.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {

	private Game game;

	public MyMouseListener(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case PREPARATION:
			game.getPreparation().mouseMoved(e.getX(), e.getY());
			break;
		case GAMEOVER:
			game.getGameOver().mouseMoved(e.getX(), e.getY());
			break;
		case VICTORY:
			game.getVictory().mouseMoved(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlaying().mouseMoved(e.getX(), e.getY());
			break;
		default:
			break;

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {

			switch (GameStates.gameState) {
			case MENU:
				game.getMenu().mouseClicked(e.getX(), e.getY());
				break;
			case HELP:
				game.getHelp().mouseClicked(e.getX(), e.getY());
				break;
			case PREPARATION:
				game.getPreparation().mouseClicked(e.getX(), e.getY());
				break;
			case PLAYING:
				game.getPlaying().mouseClicked(e.getX(), e.getY());
				break;
			case GAMEOVER:
				game.getGameOver().mouseClicked(e.getX(), e.getY());
				break;
			case VICTORY:
				game.getVictory().mouseClicked(e.getX(), e.getY());
				break;
			case PLANTSLIST:
				game.getPlantsList().mouseClicked(e.getX(), e.getY());
				break;
			case ZOMBIESLIST:
				game.getZombiesList().mouseClicked(e.getX(), e.getY());
				break;
			default:
				break;

			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().mousePressed(e.getX(), e.getY());
			break;
		case PREPARATION:
			game.getPreparation().mousePressed(e.getX(), e.getY());
			break;
		case GAMEOVER:
			game.getGameOver().mousePressed(e.getX(), e.getY());
			break;
		case VICTORY:
			game.getVictory().mousePressed(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlaying().mousePressed(e.getX(), e.getY());
			break;
		default:
			break;

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().mouseReleased(e.getX(), e.getY());
			break;
		case PREPARATION:
			game.getPreparation().mouseReleased(e.getX(), e.getY());
			break;
		case GAMEOVER:
			game.getGameOver().mouseReleased(e.getX(), e.getY());
			break;
		case VICTORY:
			game.getVictory().mouseReleased(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlaying().mouseReleased(e.getX(), e.getY());
			break;
		default:
			break;

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}