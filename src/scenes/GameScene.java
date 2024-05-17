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

    protected void updateTick() {
		tick++;
		if (tick >= ANIMATION_SPEED) {
			tick = 0;
			animationIndex++;
			if (animationIndex >= 4)
				animationIndex = 0;
		}
	}
}