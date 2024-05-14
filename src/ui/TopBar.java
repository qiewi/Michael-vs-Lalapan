package ui;

import static main.GameStates.setGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.PlantCard;
import scenes.Playing;

import static main.GameStates.*;

public class TopBar {
    
    private int x, y, width, height;
    private Playing playing;
    private MyButton bMenu;

    private ArrayList<MyButton> tileButtons = new ArrayList<MyButton>();

    public TopBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;

        initButtons();
    }

    private void drawButtons(Graphics g) {
		bMenu.draw(g);

        // drawTileButtons(g);
	}

    // private void drawTileButtons(Graphics g) {
    //     for (MyButton b: tileButtons) {
    //         g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);
    //     }
    // }

    public BufferedImage getButtImg(int id) {
        return playing.getTileManager().getSprite(id);
    }

    private void initButtons() {
		bMenu = new MyButton("Menu", 1024 - 150, 2, 130, 40);

        int w = 40;
        int h = 40;
        int xStart = 80;
        int yStart = 64 * 6 + 10;
        int xOffset = (int) (w * 1.1f);

        // int i = 0;
        // for (Tile tile : playing.getTileManager().tiles) {
        //     tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
        //     i++;
        // }

	}

    public void draw(Graphics g) {
        
        // Background Color
        // g.setColor(new Color(92, 64, 51));
        // g.fillRect(x, y, width, height);

        // Buttons
        drawButtons(g);
        
    }

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			setGameState(MENU);

	}

	
	public void mouseMoved(int x, int y) {
		bMenu.setMouseHover(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseHover(true);

	}

	
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);

	}

	
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();

	}

}
