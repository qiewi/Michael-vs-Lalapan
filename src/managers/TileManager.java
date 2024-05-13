package managers;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.LoadSave;
import objects.Tile;

public class TileManager {
    
    public Tile GRASS, WATER, ROAD;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<Tile>();

    public TileManager() {
        
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(8, 1), id++, "Grass"));
        tiles.add(WATER = new Tile(getSprite(0, 6), id++, "Water"));
        tiles.add(ROAD = new Tile(getSprite(9, 0), id++, "Road"));
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id) {
        BufferedImage sprite = tiles.get(id).getSprite();
        BufferedImage resizedSprite = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(64.0 / sprite.getWidth(), 64.0 / sprite.getHeight());
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        resizedSprite = scaleOp.filter(sprite, resizedSprite);
        return resizedSprite;
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord  * 32, yCord * 32, 32, 32);
    }
}
