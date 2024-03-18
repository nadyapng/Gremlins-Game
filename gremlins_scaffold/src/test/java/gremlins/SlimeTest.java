package gremlins;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class SlimeTest {
    
    ArrayList<Tile> allTiles = new ArrayList<Tile>();
    ArrayList<Gremlin> allGremlins = new ArrayList<Gremlin>();
    ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
    Wizard wizard = new Wizard(0, 0, null, null, 0, 0, 0);
    PImage p = new PImage();

    @Test
    public void constructorTest() { 
        assertNotNull(new Slime(0, 0, null, 0));
    }

    @Test
    public void tickTest1() {
        //runs normally
        Slime s = new Slime(0, 0, null, 0);
        s.tick(allTiles, allGremlins, wizard, fireballs);
    }

    @Test
    public void tickTest2() {
        //absorbed
        Slime s = new Slime(0, 0, null, 0);
        s.isAbsorbed = true;
        s.tick(allTiles, allGremlins, wizard, fireballs);
    }

    @Test
    public void tickTest3() {
        //collides with wall
        ArrayList<Tile> testTile = new ArrayList<Tile>();
        testTile.add(new Tile(p, 20, 60, 3, 1));
        Slime s = new Slime(20, 40, null, 3);
        s.tick(testTile, allGremlins, wizard, fireballs);
        //check
        assertTrue(s.isAbsorbed);
    }

    @Test
    public void tickTest4() {
        //checks wall does not exist
        ArrayList<Tile> testTile = new ArrayList<Tile>();
        testTile.add(new Tile(p, 0, 20, 1, 0));
        testTile.add(new Tile(p, 20, 20, 1, 1));
        testTile.get(0).isExist = false;
        testTile.get(1).isExist = false;
        Slime s = new Slime(0, 0, null, 3);
        s.tick(testTile, allGremlins, wizard, fireballs);
        assertFalse(s.isAbsorbed);
    }

    @Test
    public void tickTest5() {
        //collides with fireball
        ArrayList<Fireball> testFireballs = new ArrayList<Fireball>();
        testFireballs.add(new Fireball(20, 20, p, 3));
        Slime s = new Slime(20, 20, null, 2);
        s.tick(allTiles, allGremlins, wizard, testFireballs);
        //check
        assertTrue(s.isAbsorbed);
    }

    @Test
    public void tickTest6() {
        //cfireball does not exist
        ArrayList<Fireball> testFireballs = new ArrayList<Fireball>();
        testFireballs.add(new Fireball(20, 20, p, 3));
        testFireballs.get(0).isAbsorbed = true;
        Slime s = new Slime(20, 20, null, 1);
        s.tick(allTiles, allGremlins, wizard, testFireballs);
        assertFalse(s.isAbsorbed);
    }
}
