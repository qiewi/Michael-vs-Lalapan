package scenes;

import main.Game;

public class GameScene {
    protected Game game;
	protected int animationIndex;
	protected int ANIMATION_SPEED = 25;
	protected int tick;

    public GameScene(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

}