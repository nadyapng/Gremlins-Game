package gremlins;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class BrickTileTest {

    HashMap<String, PImage> sprites = new HashMap<String, PImage>();
    
    @Test
    public void constructorTest1() {
        assertNotNull(new BrickTile(null, 0, 0, 0, 0));
    }

    @Test
    public void constructorTest2() {
        // test defaults
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        assertFalse(b.isHit);
        assertEquals(-1, b.destructionStatus);
        assertEquals(4, b.counter);
    }

    @Test
    public void hitTest() {
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        assertTrue(b.hit());
        assertTrue(b.isHit);
    }

    @Test
    public void decreaseCounterTest() {
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.counter = 40;
        b.decreaseCounter();
        assertEquals(39, b.counter);
    }

    @Test
    public void resetCounterTest() {
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.counter = 0;
        b.resetCounter();
        assertEquals(4, b.counter);
    }

    @Test
    public void increaseDestructionStatus1() {
        // increase to 0
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("normal", null);
        sprites.put("destroyed0", null);
        sprites.put("destroyed1", null);
        sprites.put("destroyed2", null);
        sprites.put("destroyed3", null);
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.destructionStatus = -1;
        b.increaseDestructionStatus();
        assertEquals(0, b.destructionStatus);
    }

    @Test
    public void increaseDestructionStatus2() {
        // increase to 1
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("normal", null);
        sprites.put("destroyed0", null);
        sprites.put("destroyed1", null);
        sprites.put("destroyed2", null);
        sprites.put("destroyed3", null);
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.destructionStatus = 0;
        b.increaseDestructionStatus();
        assertEquals(1, b.destructionStatus);
    }

    @Test
    public void increaseDestructionStatus3() {
        // increase to 2
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("normal", null);
        sprites.put("destroyed0", null);
        sprites.put("destroyed1", null);
        sprites.put("destroyed2", null);
        sprites.put("destroyed3", null);
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.destructionStatus = 1;
        b.increaseDestructionStatus();
        assertEquals(2, b.destructionStatus);
    }

    @Test
    public void increaseDestructionStatus4() {
        // increase to 3
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("normal", null);
        sprites.put("destroyed0", null);
        sprites.put("destroyed1", null);
        sprites.put("destroyed2", null);
        sprites.put("destroyed3", null);
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.destructionStatus = 2;
        b.increaseDestructionStatus();
        assertEquals(3, b.destructionStatus);
    }

    @Test
    public void increaseDestructionStatus5() {
        // innvalid number
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("normal", null);
        sprites.put("destroyed0", null);
        sprites.put("destroyed1", null);
        sprites.put("destroyed2", null);
        sprites.put("destroyed3", null);
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.destructionStatus = 3;
        b.increaseDestructionStatus();
        assertEquals(4, b.destructionStatus);
    }

    @Test
    public void tickTest1() {
        // hit but still exists
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.isHit = false;
        b.tick();
        assertEquals(4, b.counter);
        assertEquals(-1, b.destructionStatus);
        b.isExist = false;
        b.tick();
        assertEquals(4, b.counter);
        assertEquals(-1, b.destructionStatus);
        b.isHit = true;
        b.tick();
        assertEquals(4, b.counter);
        assertEquals(-1, b.destructionStatus);
    }

    @Test
    public void tickTest2() {
        // destroy
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.isExist = true;
        b.isHit = true;
        b.destructionStatus = 3;
        b.counter = 0;
        b.tick();
        assertFalse(b.isExist);
    }

    @Test
    public void tickTest3() {
        // increase destruction
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.isExist = true;
        b.isHit = true;
        b.destructionStatus = 2;
        b.counter = 0;
        b.tick();
        assertEquals(3, b.destructionStatus);
        assertEquals(4, b.counter);
    }

    @Test
    public void tickTest4() {
        // decrease counter
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.isExist = true;
        b.isHit = true;
        b.destructionStatus = 2;
        b.counter = 4;
        b.tick();
        assertEquals(2, b.destructionStatus);
        assertEquals(3, b.counter);
    }

    @Test
    public void tickTest5() {
        // decrease counter
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.isExist = true;
        b.isHit = true;
        b.destructionStatus = 0;
        b.counter = 4;
        b.tick();
        assertEquals(0, b.destructionStatus);
        assertEquals(3, b.counter);
    }

    @Test
    public void tickTes6() {
        // decrease counter
        BrickTile b = new BrickTile(sprites, 0, 0, 0, 0);
        b.isExist = true;
        b.isHit = true;
        b.destructionStatus = 3;
        b.counter = 4;
        b.tick();
        assertEquals(3, b.destructionStatus);
        assertEquals(3, b.counter);
    }
}
