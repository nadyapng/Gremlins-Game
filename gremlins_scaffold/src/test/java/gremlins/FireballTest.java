package gremlins;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class FireballTest {

    ArrayList<Tile> allTiles = new ArrayList<Tile>();
    ArrayList<Gremlin> allGremlins = new ArrayList<Gremlin>();
    ArrayList<Slime> slimes = new ArrayList<Slime>();
    Wizard wizard = new Wizard(0, 0, null, null, 0, 0, 0);
    
    @Test 
    public void constructorTest() {
        assertNotNull(new Fireball(0, 0, null, 0));
    }

    @Test
    public void tickTest1() {
        //runs normally
        Fireball f = new Fireball(0, 0, null, 0);
        f.tick(allTiles, allGremlins, wizard, slimes);;
    }

    @Test
    public void tickTest2() {
        //absorbed
        Fireball f = new Fireball(0, 0, null, 0);
        f.isAbsorbed = true;
        f.tick(allTiles, allGremlins, wizard, slimes);
    }

    @Test
    public void tickTest3() {
        //collides with wall
        ArrayList<Tile> testTile = new ArrayList<Tile>();
        testTile.add(new StoneTile(null, 20, 60, 3, 1));
        Fireball f = new Fireball(20, 40, null, 3);
        f.tick(testTile, allGremlins, wizard, slimes);
        //check
        assertTrue(f.isAbsorbed);
    }

    @Test
    public void tickTest4() {
        //checks wall does not exist
        ArrayList<Tile> testTile = new ArrayList<Tile>();
        testTile.add(new StoneTile(null, 0, 20, 1, 0));
        testTile.add(new StoneTile(null, 20, 20, 1, 1));
        testTile.get(0).isExist = false;
        testTile.get(1).isExist = false;
        Fireball f = new Fireball(0, 0, null, 3);
        f.tick(testTile, allGremlins, wizard, slimes);
        assertFalse(f.isAbsorbed);
    }

    @Test
    public void tickTest5() {
        //collides with fireball
        ArrayList<Slime> testSlimes = new ArrayList<Slime>();
        testSlimes.add(new Slime(20, 20, null, 3));
        Fireball f = new Fireball(20, 20, null, 2);
        f.tick(allTiles, allGremlins, wizard, testSlimes);
        //check
        assertTrue(f.isAbsorbed);
    }

    @Test
    public void tickTest6() {
        //fireball does not exist
        ArrayList<Slime> testSlimes = new ArrayList<Slime>();
        testSlimes.add(new Slime(20, 20, null, 3));
        testSlimes.get(0).isAbsorbed = true;
        Fireball f = new Fireball(20, 20, null, 1);
        f.tick(allTiles, allGremlins, wizard, testSlimes);
        assertFalse(f.isAbsorbed);
    }

    @Test
    public void tickTest7() {
        //hit gremlin
        ArrayList<Gremlin> testGremlins = new ArrayList<Gremlin>();
        testGremlins.add(new Gremlin(20, 60, null, null, 3, 1, 0, slimes));
        testGremlins.get(0).moveUp = true;
        Fireball f = new Fireball(20, 40, null, 3);
        f.tick(allTiles, testGremlins, wizard, slimes);
        assertTrue(f.isCollided);
        assertFalse(testGremlins.get(0).isExist);
    }

    @Test
    public void tickTest8() {
        //gremlin does not exist
        ArrayList<Gremlin> testGremlins = new ArrayList<Gremlin>();
        testGremlins.add(new Gremlin(20, 60, null, null, 3, 1, 0, slimes));
        testGremlins.get(0).isExist = false;
        Fireball f = new Fireball(20, 40, null, 3);
        f.tick(allTiles, testGremlins, wizard, slimes);
        assertFalse(f.isCollided);
    }

}
