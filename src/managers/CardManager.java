package managers;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import helpz.LoadSave;

public class CardManager {
    
    public PlantCard SUNFLOWER, PEASHOOTER, SNOWPEA, GATLINGPEA, WALLNUT, LILYPAD;
    public BufferedImage atlas;
    public ArrayList<PlantCard> cards = new ArrayList<PlantCard>();

    public CardManager() {
        
        CardChosen();
    }

    private void CardChosen() {
        
        int id = 0;
        cards.add(SUNFLOWER = new PlantCard(getSprite("sunflower"), id++, "Sunflower"));
        cards.add(PEASHOOTER = new PlantCard(getSprite("peashooter"), id++, "Peashooter"));
        cards.add(SNOWPEA = new PlantCard(getSprite("snowpea"), id++, "SnowPea"));
        cards.add(GATLINGPEA = new PlantCard(getSprite("gatlingpea"), id++, "GatlingPea"));
        cards.add(WALLNUT = new PlantCard(getSprite("wallnut"), id++, "WallNut"));
        cards.add(LILYPAD= new PlantCard(getSprite("lilypad"), id++, "LilyPad"));
        
    }

    public BufferedImage getSprite(int id) {
        BufferedImage sprite = cards.get(id).getSprite();
        return sprite;
    }

    public BufferedImage getSprite(String name) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("PlantCards/card_" + name + ".jpg");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord  * 32, yCord * 32, 32, 32);
    }
}
