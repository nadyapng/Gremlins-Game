package gremlins;

import processing.core.PImage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;


public class GameObjectTest {

    @Test
    public void constructorTest() {
        PImage p = new PImage();
        assertNotNull(new GameObject(p, 10, 20, 0, 0));
    }

    @Test
    public void constructor2Test() {
        HashMap<String, PImage> p = new HashMap<String, PImage>();
        assertNotNull(new GameObject(p, 1, 10, 2, 0));
    }

    @Test
    public void constructor3Test() {
        PImage p = new PImage();
        assertNotNull(new GameObject(p, 10, 20));
    }

    @Test
    public void constructor4Test() {
        //test rows and columns
        PImage p = new PImage();
        GameObject obj = new GameObject(p, 20, 40);
        assertEquals(2, obj.row);
        assertEquals(1, obj.column);
    }    
}
