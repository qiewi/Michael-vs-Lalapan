package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import entity.Plants.Plant;
import entity.Plants.PlantFactory;
import managers.ZombiesManager;
import objects.Sun;
import scenes.Playing;
// import entity.Sun;

public class FlagBar {
    
    private int x, y, width, height;
    private Rectangle progressBar;
    private Playing playing;

    public FlagBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;

        progressBar = new Rectangle(x, y, width, height);
    }


    private void drawProgressBar(Graphics g) {
        progressBar.x = x;
        progressBar.y = y;
        progressBar.width = width;
        progressBar.height = height;

        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }


    public void draw(Graphics g) {

        // Buttons
        drawProgressBar(g);
        
    }

	public void mouseClicked(int x, int y) {

	}

	
	public void mouseMoved(int x, int y) {

	}

	
	public void mousePressed(int x, int y) {

	}

	
	public void mouseReleased(int x, int y) {

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