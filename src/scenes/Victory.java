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

public class Victory extends GameScene implements SceneMethods {
    private MyButton bBackmenu;

    public Victory(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 180;
        int h = 40;
        int x = 430; 
        int y = 695;  
        int yOffset = 0;

        bBackmenu = new MyButton("Back to Menu", x, y - yOffset, w, h);

        bBackmenu.setBodyColor(new Color(84, 82, 121));
    }

    @Override
    public void render(Graphics g) {
        drawVictory(g);
        drawButtons(g);
    }

    private void drawVictory(Graphics g) {
		BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream("resources/Victory.png");
	
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
        bBackmenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {

        game.getPreparation().setSelectedClear(true);
        game.getPreparation().refreshInventoryAndDeck();

        if (bBackmenu.getBounds().contains(x, y)) {
            setGameState(MENU); 
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bBackmenu.setMouseHover(bBackmenu.getBounds().contains(x, y));
    }

    @Override
    public void mousePressed(int x, int y) {
        bBackmenu.setMousePressed(bBackmenu.getBounds().contains(x, y));
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bBackmenu.resetBooleans();
    }
}