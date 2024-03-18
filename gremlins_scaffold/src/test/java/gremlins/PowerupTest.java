package gremlins;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class PowerupTest {

    ArrayList<Tile> allTiles = new ArrayList<Tile>();
    ArrayList<Gremlin> allGremlins = new ArrayList<Gremlin>();
    ArrayList<Slime> slimes = new ArrayList<Slime>();
    Wizard wizard = new Wizard(20, 20, null, null, 1, 1, 0);
    ArrayList<Door> portals = new ArrayList<Door>();
    Door exitDoor = new Door(null, 40, 40, 2, 2);
    
    @Test
    public void constructorTest() {
        assertNotNull(new Powerup(null, 0, 0, 0, 0));
    }

    @Test
    public void checkOverlapTest() {
        // no overlap
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        assertFalse(p.checkOverlap(30, 30, wizard, allTiles, allGremlins, exitDoor, portals));
    }

    @Test
    public void checkOverlapTest2() {
        // overlap with tile
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Tile t = new StoneTile(null, 0, 0, 0, 0);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(t);
        assertTrue(p.checkOverlap(0, 0, wizard, tiles, allGremlins, exitDoor, portals));
    }

    @Test
    public void checkOverlapTest3() {
        // tiles does not exist
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Tile t = new StoneTile(null, 0, 0, 0, 0);
        t.isExist = false;
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(t);
        assertFalse(p.checkOverlap(0, 0, wizard, tiles, allGremlins, exitDoor, portals));
    }

    @Test
    public void checkOverlapTest4() {
        // overlap with gremlin
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Gremlin g = new Gremlin(0, 0, null, null, 0, 0, 0, slimes);
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        gremlins.add(g);
        assertTrue(p.checkOverlap(0, 0, wizard, allTiles, gremlins, exitDoor, portals));
    }

    @Test
    public void checkOverlapTest5() {
        // gre,lin does not exist
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Gremlin g = new Gremlin(0, 0, null, null, 0, 0, 0, slimes);
        g.isExist = false;
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        gremlins.add(g);
        assertFalse(p.checkOverlap(0, 0, wizard, allTiles, gremlins, exitDoor, portals));
    }

    @Test
    public void checkOverlapTest6() {
        // overlap with wizard
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        assertTrue(p.checkOverlap(0, 0, w, allTiles, allGremlins, exitDoor, portals));
    }

    @Test
    public void checkOverlapTest7() {
        // wizard does not exist
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        w.isExist = false;
        assertFalse(p.checkOverlap(0, 0, w, allTiles, allGremlins, exitDoor, portals));
    }

    @Test
    public void checkOverlapTest8() {
        // overlap with door
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Door d = new Door(null, 0, 0, 0, 0);
        assertTrue(p.checkOverlap(0, 0, wizard, allTiles, allGremlins, d, portals));
    }

    @Test
    public void checkOverlapTest9() {
        // overlap with portal
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Door portal = new Door(null, 0, 0, 0, 0);
        ArrayList<Door> portalsTest = new ArrayList<Door>();
        portalsTest.add(portal);
        assertTrue(p.checkOverlap(0, 0, wizard, allTiles, allGremlins, exitDoor, portalsTest));
    }

    @Test
    public void checkOverlapTest10() {
        // portal does not exist
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Door portal = new Door(null, 0, 0, 0, 0);
        portal.isExist = false;
        ArrayList<Door> portalsTest = new ArrayList<Door>();
        portalsTest.add(portal);
        assertFalse(p.checkOverlap(0, 0, wizard, allTiles, allGremlins, exitDoor, portalsTest));
    }

    @Test
    public void checkOverlapTest11() {
        // almost overlap with tile
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Tile t = new StoneTile(null, 0, 0, 1, 0);
        Tile t2 = new StoneTile(null, 0, 0, 0, 1);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(t);
        tiles.add(t2);
        assertFalse(p.checkOverlap(0, 0, wizard, tiles, allGremlins, exitDoor, portals));
    }

    @Test
    public void checkOverlapTest12() {
        // almost overlap with gremlin
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Gremlin g = new Gremlin(0, 20, null, null, 1, 0, 0, slimes);
        Gremlin g2 = new Gremlin(20, 0, null, null, 0, 1, 0, slimes);
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        gremlins.add(g);
        gremlins.add(g2);
        assertFalse(p.checkOverlap(0, 0, wizard, allTiles, gremlins, exitDoor, portals));
    }

    @Test
    public void checkOverlapTest13() {
        // almost overlap with wizard
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Wizard w = new Wizard(20, 0, null, null, 0, 1, 0);
        Wizard w2 = new Wizard(0, 20, null, null, 1, 0, 0);
        assertFalse(p.checkOverlap(0, 0, w, allTiles, allGremlins, exitDoor, portals));
        assertFalse(p.checkOverlap(0, 0, w2, allTiles, allGremlins, exitDoor, portals));
    }

    @Test
    public void checkOverlapTest14() {
        // almost overlap with door
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Door d = new Door(null, 20, 0, 0, 1);
        Door d1 = new Door(null, 0, 20, 1, 0);
        assertFalse(p.checkOverlap(0, 0, wizard, allTiles, allGremlins, d, portals));
        assertFalse(p.checkOverlap(0, 0, wizard, allTiles, allGremlins, d1, portals));
    }

    @Test
    public void checkOverlapTest15() {
        // almost overlap with portal
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Door portal = new Door(null, 20, 0, 0, 1);
        Door portal2 = new Door(null, 0, 20, 1, 0);
        ArrayList<Door> portalsTest = new ArrayList<Door>();
        portalsTest.add(portal);
        portalsTest.add(portal2);
        assertFalse(p.checkOverlap(0, 0, wizard, allTiles, allGremlins, exitDoor, portalsTest));
    }

    @Test
    public void respawnTest1() {
        // no overlap 
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.isExist = false;
        p.respawn(wizard, allTiles, allGremlins, exitDoor, portals);
        assertTrue(p.isExist);
    }

    @Test
    public void respawnTest2() {
        // overlap 
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.isExist = false;
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 33; j++) {
                tiles.add(new StoneTile(null, 20*i, 20*j, i, j));
            }
        }
        p.respawn(wizard, tiles, allGremlins, exitDoor, portals);
        assertTrue(p.isExist);
    }

    @Test
    public void tickTest2() {
        // first spawn
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.firstSpawn = true;
        p.spawnCounter = 0;
        p.isExist = false;
        p.tick(wizard, allTiles, allGremlins, exitDoor, portals);;
        assertTrue(p.isExist);
        assertFalse(p.firstSpawn);
    }

    @Test
    public void tickTest3() {
        // not first spawn
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.firstSpawn = false;
        p.spawnCounter = 0;
        p.isExist = false;
        p.tick(wizard, allTiles, allGremlins, exitDoor, portals);;
        assertTrue(p.isExist);
    }

    @Test
    public void tickTest4() {
        // not some conditions
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.firstSpawn = true;
        p.spawnCounter = 0;
        p.isExist = true;
        p.tick(wizard, allTiles, allGremlins, exitDoor, portals);;
        assertTrue(p.isExist);
    }

    @Test
    public void tickTest5() {
        // not some conditions
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.firstSpawn = true;
        p.spawnCounter = 5;
        p.isExist = true;
        p.tick(wizard, allTiles, allGremlins, exitDoor, portals);;
        assertTrue(p.isExist);
    }

    @Test
    public void tickTest6() {
        // not some conditions
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.firstSpawn = false;
        p.spawnCounter = 0;
        p.isExist = true;
        p.tick(wizard, allTiles, allGremlins, exitDoor, portals);;
        assertTrue(p.isExist);
    }

    @Test
    public void tickTest7() {
        // not some conditions
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.firstSpawn = false;
        p.spawnCounter = 12;
        p.isExist = true;
        p.tick(wizard, allTiles, allGremlins, exitDoor, portals);;
        assertTrue(p.isExist);
    }

    @Test
    public void tickTest8() {
        // not some conditions
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.firstSpawn = false;
        p.spawnCounter = 10;
        p.isExist = false;
        p.tick(wizard, allTiles, allGremlins, exitDoor, portals);;
        assertFalse(p.isExist);
    }

    @Test
    public void tickTest9() {
        // not some conditions
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.firstSpawn = false;
        p.spawnCounter = 12;
        p.isExist = true;
        p.tick(wizard, allTiles, allGremlins, exitDoor, portals);;
        assertTrue(p.isExist);
    }

    @Test
    public void tickTest10() {
        // not some conditions
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        p.firstSpawn = false;
        p.spawnCounter = 0;
        p.isExist = true;
        p.tick(wizard, allTiles, allGremlins, exitDoor, portals);;
        assertTrue(p.isExist);
    }
}
