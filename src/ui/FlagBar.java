package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import objects.Sun;
import scenes.Playing;
// import entity.Sun;

public class FlagBar {
    
    private int x, y;
    private BufferedImage[] barImages = new BufferedImage[17];
    private BufferedImage flagImage, waveNotice;
    private Playing playing;

    public FlagBar(int x, int y, Playing playing) {
        this.x = x;
        this.y = y;
        this.playing = playing;
    

        initBarImages();
        flagImage = barImages[0];
    }

    private void initBarImages() {
        for (int i = 0; i < 17; i++) {
            barImages[i] = getBarImage(String.valueOf(i));
        }
        waveNotice = getBarImage("WaveNotice");
    }

    private BufferedImage getBarImage(String name) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("resources/FlagBar/" + name + ".png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    private void updateProgressBar() {
        int tick = Sun.getTick();
        if (tick >= 20 && tick <= 180) {
            int tickIndex = ((int) Sun.getTick() / 10) - 2;
            flagImage = barImages[tickIndex];
        } else if (tick > 180) {
            flagImage = barImages[16];
        }
    }

    private void drawProgressBar(Graphics g) {
        g.drawImage(flagImage, x, y, null);
        if (Sun.getTick() >= 170 && Sun.getTick() <= 175){
            g.drawImage(waveNotice, 112, 360, null);
        }
    }


    public void draw(Graphics g) {

        // Buttons
        updateProgressBar();
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

}