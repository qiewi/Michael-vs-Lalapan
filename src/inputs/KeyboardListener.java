package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

import main.Game;
import main.GameStates;

public class KeyboardListener implements KeyListener{
    Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameStates.gameState) {
            case MENU:
                break;
            case PLAYING:
                game.getPlaying().keyPressed(e);
                break;
            case SETTINGS:
                break;
            default:
                break;
    
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
    
}
