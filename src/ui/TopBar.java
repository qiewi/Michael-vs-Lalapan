package ui;

import java.awt.Graphics;
import java.util.ArrayList;

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
            g.drawImage(b.getImage(), b.x, b.y, b.width, b.height, null);
        }
    }

    // public BufferedImage getButtImg(int id) {
    //     return playing.getPlantManager().getSprite(id);
    // }

    private void initButtons() {
		bMenu = new MyButton("Menu", 1024 - 150, 2, 130, 40);

        int w = 62;
        int h = 80;
        int xStart = 120;
        int yStart = 15;
        int xOffset = (int) (w * 1.2f);

        for (int i = 0; i < 6; i++) {
            plantCards.add(new MyButton("", xStart + xOffset * i, yStart, w, h, i));
        }
	}

    public void updateButtons() {
        int i = 0;
        for (String name : playing.getGame().getPlaying().getPlantDeckNames()) {
            plantCards.get(i).setName(name);
            plantCards.get(i).setImage(playing.getGame().getPreparation().getPlantDeckButtons()[i].getImage());
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
            playing.clearPlants();
            playing.getGame().getPreparation().setSelectedClear(true);
            playing.getGame().getPreparation().refreshInventoryAndDeck();
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

    public MyButton getPlantCardsButton(int index) {
        return plantCards.get(index);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
