package main;
import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import java.awt.Dimension;
// import java.awt.Color;
import java.awt.Graphics;

public class GameScreen extends JPanel{

    private Game game;
    private Dimension size;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();        
    }

    public void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void setPanelSize() {
        size = new Dimension(1024, 768);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g);        
    }

}
