package gremlins;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

public class WizardTest2 {
    
    @Test
    public void constructorTest1() {
        assertNotNull(new Wizard(0, 0, null, null, 0, 0, 0));
    }

    @Test
    public void constructorTest2() {
        // test defaults
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        assertEquals(2, w.vel);
        assertEquals(4, w.projectileVel);
        assertTrue(w.isStationary);
        assertFalse(w.isPoweredUp);
        assertEquals(0, w.powerupCounter);
    }

    @Test
    public void pressLeftTest1() {
        // pressLeft and correct last movement
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("left", null);
        sprites.put("right", null);
        sprites.put("up", null);
        sprites.put("down", null);
        Wizard w = new Wizard(0, 0, sprites, null, 0, 0, 0);
        w.lastMovement = 0;
        w.pressLeft();
        assertTrue(w.moveLeft);
        assertFalse(w.moveRight);
        assertFalse(w.moveUp);
        assertFalse(w.moveDown);
        assertEquals(0, w.lastMovement);
    }

    @Test
    public void pressLeftTest2() {
        // pressLeft and diff last movement
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("left", null);
        sprites.put("right", null);
        sprites.put("up", null);
        sprites.put("down", null);
        Wizard w = new Wizard(0, 0, sprites, null, 0, 0, 0);
        w.lastMovement = 2;
        w.pressLeft();
        assertTrue(w.moveLeft);
        assertFalse(w.moveRight);
        assertFalse(w.moveUp);
        assertFalse(w.moveDown);
        assertEquals(0, w.lastMovement);
    }

    @Test
    public void pressRightTest1() {
        // pressRight and correct last movement
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("left", null);
        sprites.put("right", null);
        sprites.put("up", null);
        sprites.put("down", null);
        Wizard w = new Wizard(0, 0, sprites, null, 0, 0, 0);
        w.lastMovement = 1;
        w.pressRight();
        assertTrue(w.moveRight);
        assertFalse(w.moveLeft);
        assertFalse(w.moveUp);
        assertFalse(w.moveDown);
        assertEquals(1, w.lastMovement);
    }

    @Test
    public void pressRightTest2() {
        // pressRight and diff last movement
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("left", null);
        sprites.put("right", null);
        sprites.put("up", null);
        sprites.put("down", null);
        Wizard w = new Wizard(0, 0, sprites, null, 0, 0, 0);
        w.lastMovement = 3;
        w.pressRight();
        assertTrue(w.moveRight);
        assertFalse(w.moveLeft);
        assertFalse(w.moveUp);
        assertFalse(w.moveDown);
        assertEquals(1, w.lastMovement);
    }

    @Test
    public void pressUpTest1() {
        // pressUp and correct last movement
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("left", null);
        sprites.put("right", null);
        sprites.put("up", null);
        sprites.put("down", null);
        Wizard w = new Wizard(0, 0, sprites, null, 0, 0, 0);
        w.lastMovement = 2;
        w.pressUp();
        assertTrue(w.moveUp);
        assertFalse(w.moveLeft);
        assertFalse(w.moveRight);
        assertFalse(w.moveDown);
        assertEquals(2, w.lastMovement);
    }

    @Test
    public void pressUpTest2() {
        // pressUp and diff last movement
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("left", null);
        sprites.put("right", null);
        sprites.put("up", null);
        sprites.put("down", null);
        Wizard w = new Wizard(0, 0, sprites, null, 0, 0, 0);
        w.lastMovement = 0;
        w.pressUp();
        assertTrue(w.moveUp);
        assertFalse(w.moveLeft);
        assertFalse(w.moveRight);
        assertFalse(w.moveDown);
        assertEquals(2, w.lastMovement);
    }

    @Test
    public void pressDownTest1() {
        // pressDown and correct last movement
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("left", null);
        sprites.put("right", null);
        sprites.put("up", null);
        sprites.put("down", null);
        Wizard w = new Wizard(0, 0, sprites, null, 0, 0, 0);
        w.lastMovement = 3;
        w.pressDown();
        assertTrue(w.moveDown);
        assertFalse(w.moveLeft);
        assertFalse(w.moveRight);
        assertFalse(w.moveUp);
        assertEquals(3, w.lastMovement);
    }

    @Test
    public void pressDownTest2() {
        // pressDown and diff last movement
        HashMap<String, PImage> sprites = new HashMap<String, PImage>();
        sprites.put("left", null);
        sprites.put("right", null);
        sprites.put("up", null);
        sprites.put("down", null);
        Wizard w = new Wizard(0, 0, sprites, null, 0, 0, 0);
        w.lastMovement = 0;
        w.pressDown();
        assertTrue(w.moveDown);
        assertFalse(w.moveLeft);
        assertFalse(w.moveRight);
        assertFalse(w.moveUp);
        assertEquals(3, w.lastMovement);
    }

    @Test
    public void releaseLeftTest() {
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        w.moveLeft = true;
        w.releaseLeft();
        assertFalse(w.moveLeft);
        assertFalse(w.isCollided);
    }

    @Test
    public void releaseRightTest() {
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        w.moveRight = true;
        w.releaseRight();
        assertFalse(w.moveRight);
        assertFalse(w.isCollided);
    }

    @Test
    public void releaseUpTest() {
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        w.moveUp = true;
        w.releaseUp();
        assertFalse(w.moveUp);
        assertFalse(w.isCollided);
    }

    @Test
    public void releaseDownTest() {
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        w.moveDown = true;
        w.releaseDown();
        assertFalse(w.moveDown);
        assertFalse(w.isCollided);
    }

    @Test
    public void checkStationaryTest1() {
        // moving
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        w.moveLeft = true;
        w.checkStationary();
        assertFalse(w.isStationary);
        w.stop();
        w.moveRight = true;
        w.checkStationary();
        assertFalse(w.isStationary);
        w.stop();
        w.moveUp = true;
        w.checkStationary();
        assertFalse(w.isStationary);
        w.stop();
        w.moveDown = true;
        w.checkStationary();
        assertFalse(w.isStationary);
    }

    @Test
    public void checkStationaryTest2() {
        // stationary
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        w.stop();
        w.checkStationary();
        assertTrue(w.isStationary);
    }

    @Test
    public void checkStationaryTest3() {
        // moving
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        w.moveLeft = true;
        w.checkStationary();
        assertFalse(w.isStationary);
        w.moveRight = true;
        w.checkStationary();
        assertFalse(w.isStationary);
        w.moveUp = true;
        w.checkStationary();
        assertFalse(w.isStationary);
        w.moveDown = true;
        w.checkStationary();
        assertFalse(w.isStationary);
    }

    @Test
    public void pressSpaceTest1() {
        // shoot projectile
        ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 1);
        w.pressSpace(null, fireballs);
        assertTrue(w.shootProjectile);
        assertEquals(60, w.cooldownCounter);
        assertNotNull(fireballs.get(0));
    }

    @Test
    public void pressSpaceTest2() {
        // counter not 0
        ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 1);
        w.cooldownCounter = 10;
        w.pressSpace(null, fireballs);
        assertFalse(w.shootProjectile);
        assertEquals(10, w.cooldownCounter);
    }

    @Test
    public void pressSpaceTest3() {
        // wizard not exist
        ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 1);
        w.isExist = false;
        w.pressSpace(null, fireballs);
        assertFalse(w.shootProjectile);
        assertEquals(0, w.cooldownCounter);
    }

    @Test
    public void pressSpaceTest4() {
        // wizard not exist and counter not 0
        ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 1);
        w.isExist = false;
        w.cooldownCounter = 10;
        w.pressSpace(null, fireballs);
        assertFalse(w.shootProjectile);
        assertEquals(10, w.cooldownCounter);
    }

    @Test
    public void releaseSpaceTest() {
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 0);
        w.shootProjectile = true;
        w.releaseSpace();
        assertFalse(w.shootProjectile);
    }

    
    @Test
    public void checkHitGremlinTest1() {
        // not stationary and collision
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 0);
        w.moveRight = true;
        w.isStationary = false;
        Gremlin g = new Gremlin(40, 20, null, null, 1, 2, 0, null);
        g.moveLeft = true;
        assertTrue(w.checkHitGremlin(g));
        assertTrue(w.isCollided);
        assertFalse(w.isExist);
    }

    @Test
    public void checkHitGremlinTest2() {
        // stationary and moveLeft and collision
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(40, 20, null, null, 1, 2, 0, null);
        g.moveLeft = true;
        assertTrue(w.checkHitGremlin(g));
        assertTrue(w.isCollided);
        assertFalse(w.isExist);
    }

    @Test
    public void checkHitGremlinTest3() {
        // stationary and moveLeft and no collision
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(40, 20, null, null, 1, 4, 0, null);
        g.moveLeft = true;
        assertFalse(w.checkHitGremlin(g));
        assertFalse(w.isCollided);
        assertTrue(w.isExist);
    }

    @Test
    public void checkHitGremlinTest4() {
        // stationary and moveLeft and no collision
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(40, 20, null, null, 9, 2, 0, null);
        g.moveLeft = true;
        assertFalse(w.checkHitGremlin(g));
        assertFalse(w.isCollided);
        assertTrue(w.isExist);
    }

    @Test
    public void checkHitGremlinTest5() {
        // stationary and moveRight and collision
        Wizard w = new Wizard(40, 20, null, null, 1, 2, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(20, 20, null, null, 1, 1, 0, null);
        g.moveRight = true;
        assertTrue(w.checkHitGremlin(g));
        assertTrue(w.isCollided);
        assertFalse(w.isExist);
    }

    @Test
    public void checkHitGremlinTest6() {
        // stationary and moveLeft and no collision
        Wizard w = new Wizard(40, 20, null, null, 1, 2, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(40, 20, null, null, 1, 4, 0, null);
        g.moveRight = true;
        assertFalse(w.checkHitGremlin(g));
        assertFalse(w.isCollided);
        assertTrue(w.isExist);
    }

    @Test
    public void checkHitGremlinTest7() {
        // stationary and movemoveRightLeft and no collision
        Wizard w = new Wizard(40, 20, null, null, 1, 2, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(40, 20, null, null, 9, 1, 0, null);
        g.moveRight = true;
        assertFalse(w.checkHitGremlin(g));
        assertFalse(w.isCollided);
        assertTrue(w.isExist);
    }

    @Test
    public void checkHitGremlinTest8() {
        // stationary and moveUp and collision
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(20, 40, null, null, 2, 1, 0, null);
        g.moveUp = true;
        assertTrue(w.checkHitGremlin(g));
        assertTrue(w.isCollided);
        assertFalse(w.isExist);
    }

    @Test
    public void checkHitGremlinTest9() {
        // stationary and moveUp and no collision
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(40, 20, null, null, 2, 4, 0, null);
        g.moveUp = true;
        assertFalse(w.checkHitGremlin(g));
        assertFalse(w.isCollided);
        assertTrue(w.isExist);
    }

    @Test
    public void checkHitGremlinTest10() {
        // stationary and moveUp and no collision
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(40, 20, null, null, 9, 1, 0, null);
        g.moveUp = true;
        assertFalse(w.checkHitGremlin(g));
        assertFalse(w.isCollided);
        assertTrue(w.isExist);
    }

    @Test
    public void checkHitGremlinTest11() {
        // stationary and moveDown and collision
        Wizard w = new Wizard(20, 40, null, null, 2, 1, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(20, 20, null, null, 1, 1, 0, null);
        g.moveDown = true;
        assertTrue(w.checkHitGremlin(g));
        assertTrue(w.isCollided);
        assertFalse(w.isExist);
    }

    @Test
    public void checkHitGremlinTest12() {
        // stationary and moveDown and no collision
        Wizard w = new Wizard(20, 40, null, null, 2, 1, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(40, 20, null, null, 1, 4, 0, null);
        g.moveDown = true;
        assertFalse(w.checkHitGremlin(g));
        assertFalse(w.isCollided);
        assertTrue(w.isExist);
    }

    @Test
    public void checkHitGremlinTest13() {
        // stationary and moveDown and no collision
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 0);
        w.isStationary = true;
        Gremlin g = new Gremlin(40, 20, null, null, 9, 1, 0, null);
        g.moveDown = true;
        assertFalse(w.checkHitGremlin(g));
        assertFalse(w.isCollided);
        assertTrue(w.isExist);
    }

    @Test
    public void absorbPowerupTest1() {
        // check wizard attributes
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        HashMap<String, PImage> map = new HashMap<String, PImage>();
        map.put("normal", p1);
        map.put("frozen", p2);
        HashMap<String, PImage> map2 = new HashMap<String, PImage>();
        map2.put("left", p1);
        map2.put("right", p2);
        map2.put("up", p1);
        map2.put("down", p2);
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Gremlin g = new Gremlin(0, 0, map, null, 0, 0, 0, null);
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        gremlins.add(g);
        Wizard w = new Wizard(0, 0, map2, null, 0, 0, 0);
        w.absorbPowerup(p, gremlins);
        assertFalse(p.isExist);
        assertEquals(300, p.spawnCounter);
        assertTrue(w.isPoweredUp);
        assertEquals(300, w.powerupCounter);
    }

    @Test
    public void absorbPowerupTest2() {
        // check gremlin vel
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        HashMap<String, PImage> map = new HashMap<String, PImage>();
        map.put("normal", p1);
        map.put("frozen", p2);
        HashMap<String, PImage> map2 = new HashMap<String, PImage>();
        map2.put("left", p1);
        map2.put("right", p2);
        map2.put("up", p1);
        map2.put("down", p2);
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Wizard w = new Wizard(0, 0, map2, null, 0, 0, 0);
        Gremlin g = new Gremlin(0, 0, map, null, 0, 0, 0, null);
        Gremlin g2 = new Gremlin(20, 20, map, null, 1, 1, 0, null);
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        gremlins.add(g);
        gremlins.add(g2);
        w.absorbPowerup(p, gremlins);
        assertEquals(0, gremlins.get(0).vel);
    }

    @Test
    public void endPowerupTest1() {
        // check wizard attributes
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        HashMap<String, PImage> map = new HashMap<String, PImage>();
        map.put("normal", p1);
        map.put("frozen", p2);
        HashMap<String, PImage> map2 = new HashMap<String, PImage>();
        map2.put("left", p1);
        map2.put("right", p2);
        map2.put("up", p1);
        map2.put("down", p2);
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Gremlin g = new Gremlin(0, 0, map, null, 0, 0, 0, null);
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        gremlins.add(g);
        Wizard w = new Wizard(0, 0, map2, null, 0, 0, 0);
        w.absorbPowerup(p, gremlins);
        w.endPowerup(gremlins);
        assertFalse(w.isPoweredUp);
    }

    @Test
    public void endPowerupTest2() {
        // check gremlin vel
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        HashMap<String, PImage> map = new HashMap<String, PImage>();
        map.put("normal", p1);
        map.put("frozen", p2);
        HashMap<String, PImage> map2 = new HashMap<String, PImage>();
        map2.put("left", p1);
        map2.put("right", p2);
        map2.put("up", p1);
        map2.put("down", p2);
        Powerup p = new Powerup(null, 0, 0, 0, 0);
        Wizard w = new Wizard(0, 0, map2, null, 0, 0, 0);
        Gremlin g = new Gremlin(0, 0, map, null, 0, 0, 0, null);
        Gremlin g2 = new Gremlin(20, 20, map, null, 1, 1, 0, null);
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        gremlins.add(g);
        gremlins.add(g2);
        w.absorbPowerup(p, gremlins);
        w.endPowerup(gremlins);
        assertEquals(1, gremlins.get(0).vel);
        assertEquals(1, gremlins.get(1).vel);
    }

    @Test
    public void tickTest1() {
        // check cooldown counter 
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
        Door exitDoor = new Door(null, 0, 0, 0, 0);
        ArrayList<Powerup> powerups = new ArrayList<Powerup>();
        ArrayList<Door> portals = new ArrayList<Door>();
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 10);
        w.cooldownCounter = 10;
        w.tick(allTiles, gremlins, fireballs, exitDoor, powerups, portals);
        assertEquals(9, w.cooldownCounter);
        w.cooldownCounter = 0;
        w.tick(allTiles, gremlins, fireballs, exitDoor, powerups, portals);
        assertEquals(0, w.cooldownCounter);
    }

    @Test
    public void tickTest2() {
        // check exit door
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
        Door exitDoor = new Door(null, 0, 0, 0, 1);
        ArrayList<Powerup> powerups = new ArrayList<Powerup>();
        ArrayList<Door> portals = new ArrayList<Door>();
        Wizard w = new Wizard(0, 0, null, null, 0, 0, 10);
        w.moveRight = true;
        w.tick(allTiles, gremlins, fireballs, exitDoor, powerups, portals);
        assertTrue(exitDoor.isReached());
    }

    @Test
    public void tickTest3() {
        // check powerup
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        HashMap<String, PImage> map = new HashMap<String, PImage>();
        map.put("normal", p1);
        map.put("frozen", p2);
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        Gremlin g = new Gremlin(0, 0, map, null, 0, 0, 0, null);
        gremlins.add(g);
        ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
        Door exitDoor = new Door(null, 200, 200, 10, 10);
        ArrayList<Powerup> powerups = new ArrayList<Powerup>();
        Powerup p = new Powerup(null, 40, 20, 1, 2);
        p.isExist = true;
        powerups.add(p);
        ArrayList<Door> portals = new ArrayList<Door>();
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 10);
        w.moveRight = true;
        w.tick(allTiles, gremlins, fireballs, exitDoor, powerups, portals);
        assertTrue(w.isPoweredUp);
        w.isPoweredUp = true;
        w.powerupCounter = 300;
        w.tick(allTiles, gremlins, fireballs, exitDoor, powerups, portals);
        assertEquals(299, w.powerupCounter);
        w.powerupCounter = 0;
        w.tick(allTiles, gremlins, fireballs, exitDoor, powerups, portals);
        assertEquals(1, gremlins.get(0).vel);
        w.isPoweredUp = false;
        w.powerupCounter = 0;
        w.tick(allTiles, gremlins, fireballs, exitDoor, powerups, portals);
    }

    @Test
    public void tickTest4() {
        // check portal
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        HashMap<String, PImage> map = new HashMap<String, PImage>();
        map.put("normal", p1);
        map.put("frozen", p2);
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        ArrayList<Gremlin> gremlins = new ArrayList<Gremlin>();
        Gremlin g = new Gremlin(0, 0, map, null, 0, 0, 0, null);
        gremlins.add(g);
        ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
        Door exitDoor = new Door(null, 200, 200, 10, 10);
        ArrayList<Powerup> powerups = new ArrayList<Powerup>();
        Door portal = new Door(p2, 40, 20, 1, 2);
        Door portal2 = new Door(p2, 60, 40, 2, 3);
        ArrayList<Door> portals = new ArrayList<Door>();
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 10);
        portals.add(portal);
        portals.add(portal2);
        w.moveRight = true;
        w.tick(allTiles, gremlins, fireballs, exitDoor, powerups, portals);
        assertEquals(60, w.x);
        assertEquals(40, w.y);
    }

    @Test
    public void teleportTest1() {
        Door portal = new Door(null, 40, 20, 1, 2);
        Door portal2 = new Door(null, 60, 40, 2, 3);
        ArrayList<Door> portals = new ArrayList<Door>();
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 10);
        portals.add(portal);
        portals.add(portal2);
        w.teleport(portals, 0);
        assertEquals(2, w.row);
        assertEquals(3, w.column);
        assertEquals(60, w.x);
        assertEquals(40, w.y);
    }

    @Test
    public void teleportTest2() {
        // modulo
        Door portal = new Door(null, 40, 20, 1, 2);
        Door portal2 = new Door(null, 60, 40, 2, 3);
        ArrayList<Door> portals = new ArrayList<Door>();
        Wizard w = new Wizard(20, 20, null, null, 1, 1, 10);
        portals.add(portal);
        portals.add(portal2);
        w.teleport(portals, 1);
        assertEquals(1, w.row);
        assertEquals(2, w.column);
        assertEquals(40, w.x);
        assertEquals(20, w.y);
    }

}


