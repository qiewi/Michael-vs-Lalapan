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
            plant = new Wallnut(x, y);
        } else if (name.equals("TallNut")) {
            plant = new Tallnut(x, y);
        } else if (name.equals("Squash")) {
            plant = new Squash(x, y);
        } else if (name.equals("SnowPea")) {
            plant = new Snowpea(x, y);
        } else if (name.equals("LilyPad")) {
            plant = new Lilypad(x, y);
        } else if (name.equals("TangleKelp")) {
            plant = new Tanglekelp(x, y);
        }
            
        return plant;
    
    }
}
