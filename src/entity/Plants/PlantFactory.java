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
        }
            
        return plant;
    
    }
}
