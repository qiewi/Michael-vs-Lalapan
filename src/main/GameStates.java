package main;

public enum GameStates {
    PLAYING,
    PREPARATION,
    HELP,
    MENU,
    PLANTSLIST,
    SETTINGS;

    public static GameStates gameState = MENU;

    public static void setGameState(GameStates gameState) {
        GameStates.gameState = gameState;
    }
}
