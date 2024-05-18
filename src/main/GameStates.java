package main;

public enum GameStates {
    PLAYING,
    GAMEOVER,
    PREPARATION,
    HELP,
    MENU,
    PLANTSLIST,
    ZOMBIESLIST,
    SETTINGS;

    public static GameStates gameState = MENU;

    public static void setGameState(GameStates gameState) {
        GameStates.gameState = gameState;
    }
}
