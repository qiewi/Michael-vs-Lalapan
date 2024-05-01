package entity;

public class GameMapCLI {
    private char[][] map;

    public GameMapCLI() {
        map = new char[6][9];
        initializeMap();
        drawMap();
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

    private void drawMap() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new GameMapCLI();
    }
}
