package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Help extends GameScene implements SceneMethods{
    private Game game;
    private MyButton bBack;

    public Help(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {

		int w = 85;
		int h = 50;
		int x = 35;
		int y = 40;

		bBack = new MyButton("Back", x, y, w, h);
	}

    @Override
    public void render(Graphics g) {
        bBack.draw(g);
        drawScene(g);
    }

    private void drawScene(Graphics g) {
        BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream("resources/Instruction.png");
	
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

    @Override
    public void mouseClicked(int x, int y) {
        if (bBack.getBounds().contains(x, y)) {
			setGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }
    
}
