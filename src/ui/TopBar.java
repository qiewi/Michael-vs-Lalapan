package ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import entity.Plants.Plant;
import entity.Plants.PlantFactory;
import managers.ZombiesManager;
import objects.Sun;
import scenes.Menu;
import scenes.Playing;
// import entity.Sun;

import static main.GameStates.*;

public class TopBar {
    
    private int x, y, width, height;

    private Playing playing;
    private MyButton bMenu;

    private static ArrayList<MyButton> plantCards = new ArrayList<MyButton>();

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

        checkCardsCost(g);
        checkCardsCooldown();
        for (MyButton b: plantCards) {
            b.draw(g);
        }
	}

    private void checkCardsCooldown() {
        for (MyButton b: plantCards) {
            if (b.isOnCooldown()) {
                if (Sun.getTick() - b.getCooldownTick() >= PlantFactory.getPlantCooldown(b.getName())) {
                    b.setOnCooldown(false);
                }
            }
        }
    }

    private void checkCardsCost(Graphics g) {
        for (MyButton b: plantCards) {
            if (PlantFactory.getPlantCost(b.getName()) > playing.getPlayingSun().getSun()) {
                b.setEnabled(false);
            } else {
                b.setEnabled(true);
            }
        }
    }

    private void initButtons() {
		bMenu = new MyButton("Menu", 824, 2, 130, 40);

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

        // Buttons
        drawButtons(g);
        
    }

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
            playing.clearAll();
            playing.getGame().getPreparation().setSelectedClear(true);
            playing.getGame().getPreparation().refreshInventoryAndDeck();
            ZombiesManager.shutScheduler();
            Menu.playSound("Menu");
            setGameState(MENU);
        }
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

    public void makePlantCardsCooldown(int index) {
        plantCards.get(index).setOnCooldown(true);
        plantCards.get(index).setCooldownTick(Sun.getTick());;
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