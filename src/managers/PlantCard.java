package managers;

import java.awt.image.BufferedImage;

public class PlantCard {

    private BufferedImage sprite;
    private int id;
    private String name;

    public PlantCard(BufferedImage sprite, int id, String name) {
        this.sprite = sprite;
        this.id = id;   
        this.name = name;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    
}


