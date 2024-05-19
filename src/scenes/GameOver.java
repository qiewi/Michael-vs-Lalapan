package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class GameOver extends GameScene implements SceneMethods {
    private MyButton bTryAgain, bExit;
    private Playing playing;

    public GameOver(Game game) {
        super(game);
        playing = game.getPlaying();
        initButtons();
    }

    private void initButtons() {
        int w = 180;
        int h = 40;
        int x = 275; 
        int y = 505;  
        int yOffset = 50;

        bTryAgain = new MyButton("Try Again", x, y - yOffset, w, h);
        bExit = new MyButton("Quit", 565, y - yOffset , w, h);

        bTryAgain.setBodyColor(new Color(84, 82, 121));
        bExit.setBodyColor(new Color(84, 82, 121));
    }

    @Override
    public void render(Graphics g) {
        drawGameOver(g);
        drawButtons(g);
    }

    private void drawGameOver(Graphics g) {
		BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream("resources/GameOver.png");
	
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
        bTryAgain.draw(g);
        bExit.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {

        if (bTryAgain.getBounds().contains(x, y)) {
            playing.clearAll();
            setGameState(PREPARATION); 
        } else if (bExit.getBounds().contains(x, y)) {
            playing.clearAll();
            setGameState(MENU); 
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bTryAgain.setMouseHover(bTryAgain.getBounds().contains(x, y));
        bExit.setMouseHover(bExit.getBounds().contains(x, y));
    }

    @Override
    public void mousePressed(int x, int y) {
        bTryAgain.setMousePressed(bTryAgain.getBounds().contains(x, y));
        bExit.setMousePressed(bExit.getBounds().contains(x, y));
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bTryAgain.resetBooleans();
        bExit.resetBooleans();
    }
}