package gui;
import javax.swing.*;
import java.awt.*;

public class GameMapGUI extends JFrame {
    private char[][] map;
    private final int ROWS = 6;
    private final int COLS = 9;

    public GameMapGUI() {
        setTitle("Game Map");
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        map = new char[ROWS][COLS];
        initializeMap();

        JPanel mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMap(g);
            }
        };
        
        add(mapPanel);
        setVisible(true);
    }

    private void initializeMap() {
        // Area Map Keseluruhan
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                map[i][j] = '-';
            }
        }
        // Area Pool
        for (int i = 2; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                map[i][j] = 'P';
            }
        }
        // Area Aman
        for (int i = 0; i < ROWS; i++) {
            map[i][0] = 'X';
        }
        // Area Zombie
        for (int i = 0; i < ROWS; i++) {
            map[i][COLS - 1] = 'Z';
        }
    }

    private void drawMap(Graphics g) {
        int tileSize = Math.min(getWidth() / COLS, getHeight() / (ROWS + 1));
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                switch (map[i][j]) {
                    case 'X':
                        g.setColor(Color.LIGHT_GRAY);
                        break;
                    case 'P':
                        g.setColor(Color.CYAN);
                        break;
                    case 'Z':
                        g.setColor(Color.PINK);
                        break;
                    default:
                        g.setColor(Color.GREEN);
                        break;
                }
                g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameMapGUI::new);
    }
}
