package gremlins;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GameMapTest {

    GameMap map = new GameMap("doesnotexist", null, null, null, null, null, null, null, null, null, 0, 0, 0);
    GameMap map2 = new GameMap("level1.txt", null, null, null, null, null, null, null, null, null, 0, 0, 0);

    @Test
    public void constructorTest1() {
        assertNotNull(new GameMap("level1.txt", null, null, null, null, null, null, null, null, null, 0, 0, 0));
    }

    @Test
    public void constructorTest2() {
        //test defaults
        assertNotNull(map2.allTiles);
        assertNotNull(map2.brickTiles);
        assertNotNull(map2.stoneTiles);
        assertNotNull(map2.gremlins);
        assertNotNull(map2.fireballs);
        assertNotNull(map2.slimes);
        assertNotNull(map2.powerups);
        assertNotNull(map2.portals);
        assertEquals(30, map2.wizardSpawnCounter);
        assertFalse(map2.isLevelOver);
        assertFalse(map2.resetMap);
    }
    
    @Test
    public void parseLayoutFileTest1() {
        //failed parsing
        assertFalse(map.parseLayoutFile());
    }

    @Test
    public void parseLayoutFileTest2() {
        //successful parsing
        assertTrue(map2.parseLayoutFile());
        assertNotNull(map2.lines);
    }

    @Test
    public void createObjectsTest() {
        GameMap map = new GameMap("doesnotexist", null, null, null, null, null, null, null, null, null, 0, 0, 0);
        map.lines = new ArrayList<String>();
        map.lines.add("B EWGPDX I");
        map.createObjects();

        //check for null
        for (Tile t : map.allTiles) {
            assertNotNull(t);
        }

        for (Gremlin g : map.gremlins) {
            assertNotNull(g);
        }

        for (Door p : map.portals) {
            assertNotNull(p);
        }

        for (Powerup p : map.powerups) {
            assertNotNull(p);
        }

        assertNotNull(map.exitTile);
        assertNotNull(map.wizard);

    }

    @Test
    public void tickTest1() {
        //exit door reached
        GameMap map = new GameMap("level1.txt", null, null, null, null, null, null, null, null, null, 0, 0, 0);
        
        map.exitTile = new Door(null, 0, 0, 0, 0);
        map.exitTile.reach();
        map.tick();
        assertTrue(map.isLevelOver);
    }
}
