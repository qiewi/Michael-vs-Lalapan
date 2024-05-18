package entity.Plants;

public class LilyPad extends Plant {
    public LilyPad(int x, int y) {
        super("LilyPad", 25, 100, 0, 0, 0, 10, true, x, y);
        this.setImage(getPlantImage("LilyPad"));
    }

    @Override
    public void action() {
    }

    @Override
    public void actionStop() {
    
    }
}
