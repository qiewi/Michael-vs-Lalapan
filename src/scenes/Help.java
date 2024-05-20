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
    private int imageShown = 0;
    private MyButton bBack, bNext;
    private BufferedImage[] helpImages = new BufferedImage[4];

    public Help(Game game) {
        super(game);
        initButtons();
        initImages();
    }

    private void initButtons() {

		int w = 100;
		int h = 90;
		int x = 30;
		int y = 660;

		bBack = new MyButton("Back", x, y, w, h);
        bNext= new MyButton("Next", x + 870, y, w, h);
	}

    private void initImages() {
		helpImages[0] = getHelpImage("0");
		helpImages[1] = getHelpImage("1");
		helpImages[2] = getHelpImage("2");
		helpImages[3] = getHelpImage("3");
    }

    public BufferedImage getHelpImage(String name) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("resources/Help/" + name + ".png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    @Override
    public void render(Graphics g) {
        drawScene(g, imageShown);
        // bBack.draw(g);
        // bNext.draw(g);
    }

    private void drawScene(Graphics g, int imageShown) {
		g.drawImage(helpImages[imageShown], 0, 0, null);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (imageShown == 0) {
            if (bBack.getBounds().contains(x, y)) {
                setGameState(MENU);
            } else if (bNext.getBounds().contains(x, y)) {
                imageShown = 1;
            }
        } else if (imageShown == 1) {
            if (bNext.getBounds().contains(x, y)) {
                imageShown = 2;
            }
        } else if (imageShown == 2) {
            if (bNext.getBounds().contains(x, y)) {
                imageShown = 3;
            }
        } else if (imageShown == 3) {
            if (bNext.getBounds().contains(x, y)) {
                imageShown = 0;
            }
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
