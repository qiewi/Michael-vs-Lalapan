package entity.Plants;

public class PlantFactory {
    
    public static Plant CreatePlant(String name, int x, int y) {
        Plant plant = null;

        if (name.equals("Sunflower")) {
            plant = new Sunflower(x, y);
        } else if (name.equals("Peashooter")) {
            plant = new Peashooter(x, y);
        } else if (name.equals("Snowpea")) {
            plant = new Snowpea(x, y);
        } else if (name.equals("Gatlingpea")) {
            plant = new Gatlingpea(x, y);
        } else if (name.equals("WallNut")) {
            plant = new WallNut(x, y);
        } else if (name.equals("LilyPad")) {
            plant = new Lilypad(x, y);
        }
            
        return plant;
    
    }
}
