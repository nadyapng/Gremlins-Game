package gremlins;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class TileTest {
    
    PImage sprite = new PImage();
    HashMap<String, PImage> sprites = new HashMap<String, PImage>();

    @Test
    public void constructorTest1() {
        assertNotNull(new Tile(sprite, 0, 0, 1, 1));
    }

    @Test
    public void constructorTest2() {
        assertNotNull(new Tile(sprites, 0, 0, 1, 1));
    }

    @Test
    public void hitTest() {
        Tile t = new Tile(sprite, 0, 0, 1, 1);
        assertFalse(t.hit());
    }

}
