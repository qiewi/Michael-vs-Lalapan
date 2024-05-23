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
            plant = new GatlingPea(x, y);
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

    public static int getPlantCost(String name) {
        Plant plant = null;
        int x = 0;
        int y = 0;

        if (name.equals("Sunflower")) {
            return 50;
        } else if (name.equals("Peashooter")) {
            plant = new Peashooter(x, y);
        } else if (name.equals("Repeater")) {
            plant = new Repeater(x, y);
        } else if (name.equals("GatlingPea")) {
            plant = new GatlingPea(x, y);
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
            
        return plant.getCost();
    }

    public static int getPlantCooldown(String name) {
        int cooldown = 10;
        if (name.equals("Sunflower")) {
            cooldown = 5;
        } else if (name.equals("Peashooter")) {
            cooldown = 10;
        } else if (name.equals("Repeater")) {
            cooldown = 15;
        } else if (name.equals("GatlingPea")) {
            cooldown = 15;
        } else if (name.equals("WallNut")) {
            cooldown = 10;
        } else if (name.equals("TallNut")) {
            cooldown = 20;
        } else if (name.equals("Squash")) {
            cooldown = 20;
        } else if (name.equals("SnowPea")) {
            cooldown = 10;
        } else if (name.equals("LilyPad")) {
            cooldown = 10;
        } else if (name.equals("TangleKelp")) {
            cooldown = 20;
        }
        return cooldown;
    }
}