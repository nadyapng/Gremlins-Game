package gremlins;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class CharacterTest {

    @Test
    public void constructorTest() {
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        assertNotNull(new Character(0, 0, p1, p2, 0, 0, 0));
    }

    @Test
    public void constructor2Test() {
        HashMap<String, PImage> map = new HashMap<String, PImage>();
        PImage p = new PImage();
        assertNotNull(new Character(0, 0, map, p, 0, 2, 0));
    }

    @Test
    public void checkCollisionTest1() {
        //moveLeft and collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 0, 1, 0);

        c.moveLeft = true;
        assertTrue(c.checkCollision(0, 0));

    }

    @Test
    public void checkCollisionTest2() {
        //moveRight and collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 0, 1, 0);

        c.moveRight = true;
        assertTrue(c.checkCollision(0, 2));

    }

    @Test
    public void checkCollisionTest3() {
        //moveUp and collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveUp = true;
        assertTrue(c.checkCollision(0, 1));

    }

    @Test
    public void checkCollisionTest4() {
        //moveDown and collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveDown = true;
        assertTrue(c.checkCollision(2, 1));

    }

    @Test
    public void checkCollisionTest5() {
        //moveDown and no collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveDown = true;
        assertFalse(c.checkCollision(2, 0));

    }

    @Test
    public void checkCollisionTest6() {
        //moveLeft and no collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveLeft = true;
        assertFalse(c.checkCollision(2, 0));

    }

    @Test
    public void checkCollisionTest7() {
        //moveRight and no collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveRight = true;
        assertFalse(c.checkCollision(2, 2));

    }

    @Test
    public void checkCollisionTest8() {
        //moveUp and no collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveDown = true;
        assertFalse(c.checkCollision(0, 2));

    }

    @Test
    public void stopTest1() {
        //no movement
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.stop();
        assertFalse(c.moveLeft);
        assertFalse(c.moveRight);
        assertFalse(c.moveUp);
        assertFalse(c.moveDown);
    }

    @Test
    public void stopTest2() {
        //moveLeft
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveLeft = true;
        c.stop();
        assertFalse(c.moveLeft);
        assertFalse(c.moveRight);
        assertFalse(c.moveUp);
        assertFalse(c.moveDown);
    }

    @Test
    public void stopTest3() {
        //moveRight
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveRight = true;
        c.stop();
        assertFalse(c.moveLeft);
        assertFalse(c.moveRight);
        assertFalse(c.moveUp);
        assertFalse(c.moveDown);
    }

    @Test
    public void stopTest4() {
        //moveUp
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveUp = true;
        c.stop();
        assertFalse(c.moveLeft);
        assertFalse(c.moveRight);
        assertFalse(c.moveUp);
        assertFalse(c.moveDown);
    }

    @Test
    public void stopTest5() {
        //moveDown
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveDown = true;
        c.stop();
        assertFalse(c.moveLeft);
        assertFalse(c.moveRight);
        assertFalse(c.moveUp);
        assertFalse(c.moveDown);
    }

    @Test
    public void stopTest6() {
        //multiple movement
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.moveLeft = true;
        c.moveRight = true;
        c.moveUp = true;
        c.moveDown = true;
        c.stop();
        assertFalse(c.moveLeft);
        assertFalse(c.moveRight);
        assertFalse(c.moveUp);
        assertFalse(c.moveDown);
    }
    
    @Test
    public void killTest1() {
        //alive and collided
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.isExist = true;
        c.isCollided = true;
        c.kill();
        assertFalse(c.isExist);
        assertFalse(c.isCollided);
    }

    @Test
    public void killTest2() {
        //not alive and collided
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.isExist = false;
        c.isCollided = true;
        c.kill();
        assertFalse(c.isExist);
        assertFalse(c.isCollided);
    }

    @Test
    public void killTest3() {
        //alive and not collided
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.isExist = true;
        c.isCollided = false;
        c.kill();
        assertFalse(c.isExist);
        assertFalse(c.isCollided);
    }

    @Test
    public void killTest4() {
        //not alive and not collided
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character c = new Character(0, 0, p1, p2, 1, 1, 0);

        c.isExist = false;
        c.isCollided = false;
        c.kill();
        assertFalse(c.isExist);
        assertFalse(c.isCollided);
    }

    @Test
    public void moveTest1() {
        //moveLeft
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 40, p1, p2, 2, 1, 0);
        p.moveLeft = true;
        p.move();
        assertEquals(20-p.vel, p.x);
        assertEquals(40, p.y);
        assertEquals(0, p.lastMovement);
    }

    @Test
    public void moveTest2() {
        //moveRight
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 40, p1, p2, 2, 1, 0);
        p.moveRight = true;      
        p.move();
        assertEquals(20+p.vel, p.x);
        assertEquals(40, p.y);
        assertEquals(1, p.lastMovement);
    }

    @Test
    public void moveTest3() {
        //moveUp
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 40, p1, p2, 2, 1, 0);
        p.moveUp = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(40-p.vel, p.y);
        assertEquals(2, p.lastMovement);
    }

    @Test
    public void moveTest4() {
        //mvoeDown
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 40, p1, p2, 2, 1, 0);
        p.moveDown = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(40+p.vel, p.y);
        assertEquals(3, p.lastMovement);
    }

    @Test
    public void moveTest5() {
        //moveLeft and left border
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(0, 40, p1, p2, 2, 1, 0);
        p.moveLeft = true;
        p.move();
        assertEquals(0, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest6() {
        //moveRight and right border
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(720, 40, p1, p2, 2, 1, 0);
        p.moveRight = true;       
        p.move();
        assertEquals(720, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest7() {
        //moveUp and top border
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 0, p1, p2, 2, 1, 0);
        p.moveUp = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(0, p.y);
    }

    @Test
    public void moveTest8() {
        //moveDown and bottom border
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 660, p1, p2, 2, 1, 0);
        p.moveDown = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(660, p.y);
    }

    @Test
    public void moveTest9() {
        //moveLeft and collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 40, p1, p2, 2, 1, 0);
        p.moveLeft = true;
        p.isCollided = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest10() {
        //moveRight and collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 40, p1, p2, 2, 1, 0);
        p.moveRight = true;
        p.isCollided = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest11() {
        //moveUp and collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 40, p1, p2, 2, 1, 0);
        p.moveUp = true;
        p.isCollided = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest12() {
        //moveDown and collision
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 40, p1, p2, 2, 1, 0);
        p.moveDown = true;
        p.isCollided = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest13() {
        //moveLeft and half tile
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(22, 40, p1, p2, 2, 1, 0);
        p.moveLeft = true;
        p.lastMovement = 0;
        p.move();
        assertEquals(22-p.vel, p.x);
        assertEquals(40, p.y);
    }
    
    @Test
    public void moveTest14() {
        //moveRight and half tile
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(35, 40, p1, p2, 2, 1, 0);
        p.moveRight = true;
        p.lastMovement = 1;
        p.move();
        assertEquals(35+p.vel, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest15() {
        //moveUp and half tile
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 42, p1, p2, 2, 1, 0);
        p.moveUp = true;
        p.lastMovement = 2;
        p.move();
        assertEquals(20, p.x);
        assertEquals(42-p.vel, p.y);
    }

    @Test
    public void moveTest16() {
        //moveUp and half tile
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 55, p1, p2, 2, 1, 0);
        p.moveDown = true;
        p.lastMovement = 3;
        p.move();
        assertEquals(20, p.x);
        assertEquals(55+p.vel, p.y);
    }

    @Test
    public void moveTest17() {
        //half tile and illegal lastMovement
        PImage p1 = new PImage();
        PImage p2 = new PImage();
        Character p = new Character(20, 55, p1, p2, 2, 1, 0);
        p.moveDown = true;
        p.lastMovement = 5;
        p.move();
        assertEquals(20, p.x);
        assertEquals(55, p.y);
    }

    
}
