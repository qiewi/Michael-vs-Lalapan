package managers;

import static main.GameStates.VICTORY;
import static main.GameStates.setGameState;

import java.awt.Graphics;

import objects.Note;
import scenes.Music;
import scenes.Playing;

public class VictoryNoteManager implements ManagersUI {
    private Playing playing;
    private static Note note = null;

	public VictoryNoteManager(Playing playing) {
		this.playing = playing;
	}

	public void update() {
        if (note != null) {
            if (!(note.getY() >= note.getDestructPos())) {
                note.move();
            }
        }
	}

    // For Game 
	public static void addNoteDrop(int x, int y) {
        System.out.println("Note added at " + x + ", " + y);
        note = new Note(x, y, y + 40);
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

