package main;

import java.awt.Graphics;;

public class Render {
    private Game game;


    public Render(Game game) {
       this.game = game;   
    }

    public void render(Graphics g) {
        switch (GameStates.gameState) {

            case MENU:    

                game.getMenu().render(g);          
                break;
            
            case HELP:

                game.getHelp().render(g);
                break;

            case PREPARATION:

                game.getPreparation().render(g);
                break;

            case PLAYING:

                game.getPlaying().render(g);
                break;
            
            case GAMEOVER:

                game.getGameOver().render(g);
                break;
            
            case PLANTSLIST:

                game.getPlantsList().render(g);
                break;

            case ZOMBIESLIST:

                game.getZombiesList().render(g);
                break;

            default:
                break;
        }
    }

    
}
