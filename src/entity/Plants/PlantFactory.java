package entity.Plants;

public class PlantFactory {
    
    public static Plant CreatePlant(String name, int x, int y) {
        Plant plant = null;

        if (name.equals("Sunflower")) {
            plant = new Sunflower(x, y);
        } else if (name.equals("Peashooter")) {
            plant = new Peashooter(x, y);
        } else if (name.equals("Repeater")) {
            plant = new Repeater(x, y);
        } else if (name.equals("GatlingPea")) {
            plant = new GatlingpPea(x, y);
        } else if (name.equals("WallNut")) {
            plant = new WallNut(x, y);
        } else if (name.equals("TallNut")) {
            plant = new TallNut(x, y);
        } else if (name.equals("Squash")) {
            plant = new Squash(x, y);
        } else if (name.equals("SnowPea")) {
            plant = new SnowPea(x, y);
        } else if (name.equals("LilyPad")) {
            plant = new LilyPad(x, y);
        } else if (name.equals("TangleKelp")) {
            plant = new TangleKelp(x, y);
        }
            
        return plant;
    
    }
}
