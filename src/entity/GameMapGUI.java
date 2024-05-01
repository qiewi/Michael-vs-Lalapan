package src.entity;
import javax.swing.*;
import java.awt.*;

public class GameMapGUI extends JFrame {
    private char[][] map;

    public GameMapGUI() {
        setTitle("Game Map");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        map = new char[6][9];
        initializeMap();

        JPanel mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMap(g);
            }
        };
        mapPanel.setPreferredSize(new Dimension(540, 300));

        add(mapPanel);
        setVisible(true);
    }

    private void initializeMap() {
        // Area Map Keseluruhan
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
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
        for (int i = 0; i < 6; i++) {
            map[i][0] = 'X';
        }
        // Area Zombie
        for (int i = 0; i < 6; i++) {
            map[i][8] = 'Z';
        }
    }

    private void drawMap(Graphics g) {
        int tileSize = 60;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                switch (map[i][j]) {
                    case 'X':
                        g.setColor(Color.CYAN);
                        break;
                    case 'P':
                        g.setColor(Color.BLUE);
                        break;
                    case 'Z':
                        g.setColor(Color.RED);
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
