package helpz;

public class LevelBuild {
    
    public static int[][] getLevelData() {

        // Create a 2D int array 
        // Where every value is a tile on the level.

        int map[][] = new int[6][9];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = 0;
            }
        }
        // Area Pool
        for (int i = 2; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                map[i][j] = 1;
            }
        }
        // Area Aman
        for (int i = 0; i < 6; i++) {
            map[i][0] = 2;
        }
        // Area Zombie
        for (int i = 0; i < 6; i++) {
            map[i][8] = 2;
        }

        return map;
    }
}
