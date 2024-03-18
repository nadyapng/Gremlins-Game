package gremlins;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

public class GremlinTest {

    PImage p;
    PImage p2;
    HashMap<String, PImage> sprites;

    @Test
    public void constructorTest1() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        assertNotNull(new Gremlin(0, 0, sprites, p, 0, 0, 0, null));
    }

    @Test
    public void constructorTest2() {
        // check defaults
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 0, 0, null);
        assertEquals(1, g.vel);
        assertEquals(60, g.respawnCounter);
        boolean dir = g.moveLeft || g.moveRight || g.moveUp || g.moveDown;
        assertTrue(dir);
    }

    @Test
    public void setInitialDirectionTest() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 0, 0, null);
        g.setInitialDirection();
        boolean dir = g.moveLeft || g.moveRight || g.moveUp || g.moveDown;
        assertTrue(dir);
    }

    @Test
    public void randomMovementTest1() {
        //no collisions
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 0, 0, null);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        g.stop();
        g.moveUp = true;
        g.isCollided = true;
        g.randomMovement(tiles);
        boolean b = g.moveLeft || g.moveRight || g.moveUp || g.moveDown;
        assertTrue(b);
    }

    @Test
    public void randomMovementTest2() {
        // dead end
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 1, 1, 0, null);
        g.lastMovement = 1;
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(new StoneTile(p2, 0, 0, 1, 0));
        tiles.add(new StoneTile(p2, 0, 0, 1, 2));
        tiles.add(new StoneTile(p2, 0, 0, 0, 1));
        g.isCollided = true;
        g.randomMovement(tiles);
        boolean b = g.moveDown;
        assertTrue(b);
    }

    @Test
    public void randomMovementTest3() {
        // dead end
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 1, 1, 0, null);
        g.lastMovement = 3;
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(new StoneTile(p2, 0, 0, 1, 0));
        tiles.add(new StoneTile(p2, 0, 0, 1, 2));
        tiles.add(new StoneTile(p2, 0, 0, 2, 1));
        g.isCollided = true;
        g.randomMovement(tiles);
        boolean b = g.moveUp;
        assertTrue(b);
    }

    @Test
    public void randomMovementTes4() {
        // dead end
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 1, 1, 0, null);
        g.lastMovement = 1;
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(new StoneTile(p2, 0, 0, 0, 1));
        tiles.add(new StoneTile(p2, 0, 0, 1, 2));
        tiles.add(new StoneTile(p2, 0, 0, 2, 1));
        g.isCollided = true;
        g.randomMovement(tiles);
        boolean b = g.moveLeft;
        assertTrue(b);
    }

    @Test
    public void randomMovementTest5() {
        // dead end
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 1, 1, 0, null);
        g.lastMovement = 1;
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(new StoneTile(p2, 0, 0, 1, 0));
        tiles.add(new StoneTile(p2, 0, 0, 1, 2));
        tiles.add(new StoneTile(p2, 0, 0, 0, 1));
        tiles.get(0).isExist = false;
        g.isCollided = true;
        g.randomMovement(tiles);
        boolean b = g.moveDown;
        assertTrue(b);
    }

    @Test
    public void randomMovementTest6() {
        // dead end
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 1, 1, 0, null);
        g.lastMovement = 5;
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(new StoneTile(p2, 0, 0, 1, 0));
        tiles.add(new StoneTile(p2, 0, 0, 1, 2));
        tiles.add(new StoneTile(p2, 0, 0, 0, 1));
        tiles.get(0).isExist = false;
        g.isCollided = true;
        g.randomMovement(tiles);
    }
    
    @Test
    public void getValidDirectionsTest1() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        StoneTile t1 = new StoneTile(p, 0, 0, 0, 1);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(t1);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        assertNotNull(g.getValidDirections(tiles));
    }

    @Test
    public void getValidDirectionsTest2() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        StoneTile t1 = new StoneTile(p, 0, 0, 0, 1);
        StoneTile t2 = new StoneTile(p, 0, 0, 10, 10);
        t2.isExist = false;
        StoneTile t3 = new StoneTile(p, 0, 0, 2, 1);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(t1);
        tiles.add(t2);
        tiles.add(t3);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        assertNotNull(g.getValidDirections(tiles));
    }

    @Test
    public void getSideDirTest() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        StoneTile t1 = new StoneTile(p, 0, 0, 0, 1);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(t1);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.lastMovement = 0;
        ArrayList<Integer> ls = g.getSideDir();
        assertEquals(2, ls.get(0));
        assertEquals(3, ls.get(1));
    }

    @Test
    public void toggleMovementTest1() {
        // moveLeft
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.stop();
        g.toggleMovement(0);
        assertTrue(g.moveLeft);
    }

    @Test
    public void toggleMovementTest2() {
        // moveRight
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.stop();
        g.toggleMovement(1);
        assertTrue(g.moveRight);
    }

    @Test
    public void toggleMovementTest3() {
        // moveUp
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.stop();
        g.toggleMovement(2);
        assertTrue(g.moveUp);
    }

    @Test
    public void toggleMovementTest4() {
        // moveDown
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.stop();
        g.toggleMovement(3);
        assertTrue(g.moveDown);
    }

    @Test
    public void toggleMovementTest5() {
        // illegal number
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.stop();
        g.toggleMovement(4);
        assertFalse(g.moveLeft);
        assertFalse(g.moveRight);
        assertFalse(g.moveUp);
        assertFalse(g.moveDown);
    }

    @Test
    public void shootSlimeTest() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 10, null);
        ArrayList<Slime> slimes = new ArrayList<Slime>();
        g.shootSlime(null, slimes);
        assertTrue(g.shootProjectile);
        assertEquals(600, g.cooldownCounter);
        assertNotNull(slimes.get(0));
    }

    @Test
    public void respawnTest1() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.isExist = false;
        Wizard w = new Wizard(0, 0, sprites, p, 10, 10, 10);
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        ArrayList<Gremlin> allGremlins = new ArrayList<Gremlin>();
        g.respawn(w, allTiles, allGremlins);
        assertTrue(g.isExist);
        assertFalse(g.isCollided);
        assertTrue(g.row - w.row >= 10 || g.row - w.row <= -10);
        assertTrue(g.column - w.column >= 10 || g.column - w.column <= -10);
    }

    @Test
    public void respawnTest2() {
        // out of bounds
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.isExist = false;
        Wizard w = new Wizard(0, 0, sprites, p, 0, 0, 10);
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        ArrayList<Gremlin> allGremlins = new ArrayList<Gremlin>();
        g.respawn(w, allTiles, allGremlins);
        assertTrue(g.isExist);
        assertFalse(g.isCollided);
        // assertTrue(g.row - w.row >= 10 || g.row - w.row <= -10);
        // assertTrue(g.column - w.column >= 10 || g.column - w.column <= -10);
    }

    @Test
    public void respawnTest3() {
        // out of bounds
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.isExist = false;
        Wizard w = new Wizard(0, 0, sprites, p, 32, 35, 10);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        ArrayList<Gremlin> allGremlins = new ArrayList<Gremlin>();
        g.respawn(w, tiles, allGremlins);
        assertTrue(g.isExist);
        assertFalse(g.isCollided);
        // assertTrue(g.row - w.row >= 10 || g.row - w.row <= -10);
        // assertTrue(g.column - w.column >= 10 || g.column - w.column <= -10);
    }

    @Test
    public void respawnTest4() {
        // overlap
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.isExist = false;
        Wizard w = new Wizard(0, 0, sprites, p, 10, 10, 10);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 33; j++) {
                tiles.add(new StoneTile(null, 20*i, 20*j, i, j));
            }
        }
        ArrayList<Gremlin> allGremlins = new ArrayList<Gremlin>();
        g.respawn(w, tiles, allGremlins);
        assertTrue(g.isExist);
        assertFalse(g.isCollided);
        assertTrue(g.row - w.row >= 10 || g.row - w.row <= -10);
        assertTrue(g.column - w.column >= 10 || g.column - w.column <= -10);
    }

    @Test
    public void getRandomCoordTest() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        int coord = g.getRandomCoord(10);
        assertTrue(coord < 10);
    }

    @Test
    public void checkOverlapTest1() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.isExist = false;
        Gremlin g2 = new Gremlin(0, 0, sprites, p, 29, 32, 0, null);
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        gremlins.add(g);
        gremlins.add(g2);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 29; i++) {
            for (int j = 0; j < 32; j++) {
                tiles.add(new StoneTile(null, 20*i, 20*j, i, j));
            }
        }
        assertTrue(g.checkOverlap(10, 10, tiles, gremlins));
    }

    @Test
    public void checkOverlapTest2() {
        //gremlin does not exist
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.isExist = false;
        Gremlin g2 = new Gremlin(0, 0, sprites, p, 29, 32, 0, null);
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        gremlins.add(g);
        gremlins.add(g2);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 29; i++) {
            for (int j = 0; j < 32; j++) {
                tiles.add(new StoneTile(null, 20*i, 20*j, i, j));
            }
        }
        g.checkOverlap(29, 32, tiles, gremlins);
    }

    @Test
    public void checkOverlapTest3() {
        //tile does not exist
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.isExist = false;
        Gremlin g2 = new Gremlin(0, 0, sprites, p, 29, 32, 0, null);
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        gremlins.add(g);
        gremlins.add(g2);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 29; i++) {
            for (int j = 0; j < 32; j++) {
                tiles.add(new StoneTile(null, 20*i, 20*j, i, j));
            }
        }
        tiles.get(0).isExist = false;
    }

    @Test
    public void freezeTest() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.freeze();
        assertEquals(0, g.vel);
    }

    @Test
    public void unfreezeTest() {
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.unfreeze();
        assertEquals(1, g.vel);
    }

    @Test
    public void tickTest1() {
        // respawn
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        Wizard w = new Wizard(0, 0, sprites, p, 0, 0, 0);
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.isExist = false;
        g.respawnCounter = 0;
        g.tick(allTiles, w, gremlins);;
        assertTrue(g.isExist);
    }

    @Test
    public void tickTest2() {
        // cooldown counter
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        Wizard w = new Wizard(0, 0, sprites, p, 0, 0, 0);
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.cooldownCounter = 1;
        g.tick(allTiles, w, gremlins);
        assertEquals(0, g.cooldownCounter);
    }

    @Test
    public void tickTest3() {
        // cooldown counter
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        Wizard w = new Wizard(0, 0, sprites, p, 0, 0, 0);
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        PImage p = new PImage();
        PImage p2 = new PImage();
        sprites.put("normal", p);
        sprites.put("frozen", p2);
        Gremlin g = new Gremlin(0, 0, sprites, p, 0, 2, 0, null);
        g.cooldownCounter = -1;
        g.tick(allTiles, w, gremlins);
    }
}


