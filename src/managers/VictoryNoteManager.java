package managers;

import static main.GameStates.VICTORY;
import static main.GameStates.setGameState;

import java.awt.Graphics;
import java.util.Random;

import objects.Note;
import scenes.Music;
import scenes.Playing;

public class VictoryNoteManager {
    private Playing playing;
    private static Note note = null;

	public VictoryNoteManager(Playing playing) {
		this.playing = playing;
	}

	public void update() {
        if (ZombiesManager.isVictory() && note == null){
            addNoteDrop();
        }

        if (note != null) {
            if (!(note.getY() >= note.getDestructPos())) {
                note.move();
            }
        }
	}

    // For Game 
	public static void addNoteDrop() {
		Random ranX = new Random();
        Random ranDestructY = new Random();

        int[] xPositions = { 660, 750, 840 };
        int[] destructYPositions = { 200, 290, 380, 470, 560, 650 };

        int x = xPositions[ranX.nextInt(xPositions.length)];
        int destructY = destructYPositions[ranDestructY.nextInt(destructYPositions.length)];

        note = new Note(x, 100, destructY);
	}

	public void draw(Graphics g) {
        if (note != null)
		    g.drawImage(note.getImage(), (int) note.getX(), (int) note.getY(), null);
	}

    public static void clearNote() {
        note = null;
    }

    public Note getNote() {
        return note;
    }

	public void mouseClicked(int x, int y) {
		if (note.getBounds().contains(x, y)) {
            Music.playSound("Victory", false);
            setGameState(VICTORY);
        }
	}
}

