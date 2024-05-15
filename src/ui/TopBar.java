package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import managers.PlantCard;
import scenes.Playing;

import static main.GameStates.*;

public class TopBar {
    
    private int x, y, width, height;
    private Playing playing;
    private MyButton bMenu;

    private ArrayList<MyButton> plantCards = new ArrayList<MyButton>();

    public TopBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;

        initButtons();
    }

    private void drawButtons(Graphics g) {
		// bMenu.draw(g);

        drawplantCards(g);
	}

    private void drawplantCards(Graphics g) {
        for (MyButton b: plantCards) {
            g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);
        }
    }

    public BufferedImage getButtImg(int id) {
        return playing.getPlantManager().getSprite(id);
    }

    private void initButtons() {
		bMenu = new MyButton("Menu", 1024 - 150, 2, 130, 40);

        int w = 60;
        int h = 80;
        int xStart = 120;
        int yStart = 15;
        int xOffset = (int) (w * 1.2f);

        int i = 0;
        for (PlantCard plant : playing.getPlantManager().cards) {
            plantCards.add(new MyButton(plant.getName(), xStart + xOffset * i, yStart, w, h, i));
            i++;
        }

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
            playing.clearPlants();

	}

	
	public void mouseMoved(int x, int y) {
		bMenu.setMouseHover(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseHover(true);

	}

	
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
        for (MyButton b: plantCards) {
            if (b.getBounds().contains(x, y)) {
			    b.setMouseHover(true);
            }
        }

	}

	
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
        for (MyButton b: plantCards) {
            if (b.getBounds().contains(x, y)) {
			    b.resetBooleans();
            }
        }
	}

}
