package gremlins;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectileTest {

    PImage sprite = new PImage();
    
    @Test
    public void constructorTest1() {
        assertNotNull(new Projectile(10, 20, sprite, 0));
    }

    @Test
    public void constructorTest2() {
        //test defaults
        Projectile p = new Projectile(10, 20, sprite, 0);
        assertEquals(4, p.vel);
        assertFalse(p.isCollided);
        assertFalse(p.isHitCharacter);
        assertFalse(p.isAbsorbed);
    }

    @Test
    public void constructorTest3() {
        //test direction
        Projectile p = new Projectile(10, 20, sprite, 0);
        Projectile p1 = new Projectile(10, 20, sprite, 1);
        Projectile p2 = new Projectile(10, 20, sprite, 2);
        Projectile p3 = new Projectile(10, 20, sprite, 3);
        assertTrue(p.moveLeft);
        assertTrue(p1.moveRight);
        assertTrue(p2.moveUp);
        assertTrue(p3.moveDown);
        assertFalse(p.moveDown);
        assertFalse(p1.moveLeft);
    }

    @Test
    public void constructorTest4() {
        //invalid direction
        Projectile p = new Projectile(10, 20, sprite, 10);
        assertFalse(p.moveLeft);
        assertFalse(p.moveRight);
        assertFalse(p.moveUp);
        assertFalse(p.moveDown);
    }

    @Test
    public void constructorTest5() {
        //test row and column
        Projectile p = new Projectile(20, 40, sprite, 0);
        assertEquals(1, p.column);
        assertEquals(2, p.row);
    }

    @Test
    public void checkCollisionTest1() {
        //moveLeft and collision
        Projectile p = new Projectile(20, 40, sprite, 0);
        p.moveLeft = true;
        assertTrue(p.checkCollision(2, 1));
        assertTrue(p.isAbsorbed);
        assertTrue(p.isCollided);
    }

    @Test
    public void checkCollisionTest2() {
        //moveRight and collision
        Projectile p = new Projectile(20, 40, sprite, 1);
        p.moveRight = true;
        assertTrue(p.checkCollision(2, 2));
        assertTrue(p.isAbsorbed);
        assertTrue(p.isCollided);
    }

    @Test
    public void checkCollisionTest3() {
        //moveUp and collision
        Projectile p = new Projectile(20, 40, sprite, 2);
        p.moveUp = true;
        assertTrue(p.checkCollision(2, 1));
        assertTrue(p.isAbsorbed);
        assertTrue(p.isCollided);
    }

    @Test
    public void checkCollisionTest4() {
        //moveDown and collision
        Projectile p = new Projectile(20, 40, sprite, 3);
        p.moveDown = true;
        assertTrue(p.checkCollision(3, 1));
        assertTrue(p.isAbsorbed);
        assertTrue(p.isCollided);
    }

    @Test
    public void checkCollisionTest5() {
        //moveDown and no collision
        Projectile p = new Projectile(20, 40, sprite, 3);
        p.moveDown = true;
        assertFalse(p.checkCollision(3, 2));
        assertFalse(p.isAbsorbed);
        assertFalse(p.isCollided);
        assertFalse(p.checkCollision(10, 10));
        assertFalse(p.checkCollision(10, 1));
    }

    @Test
    public void checkCollisionTest6() {
        //moveLeft and no collision
        Projectile p = new Projectile(20, 40, sprite, 0);
        p.moveLeft = true;
        assertFalse(p.checkCollision(3, 0));
        assertFalse(p.isAbsorbed);
        assertFalse(p.isCollided);
        assertFalse(p.checkCollision(10, 10));
        assertFalse(p.checkCollision(2, 10));
    }

    @Test
    public void checkCollisionTest7() {
        //moveRight and no collision
        Projectile p = new Projectile(20, 40, sprite, 1);
        p.moveRight = true;
        assertFalse(p.checkCollision(3, 2));
        assertFalse(p.isAbsorbed);
        assertFalse(p.isCollided);
        assertFalse(p.checkCollision(10, 10));
        assertFalse(p.checkCollision(2, 20));
    }

    @Test
    public void checkCollisionTest8() {
        //moveUp and no collision
        Projectile p = new Projectile(20, 40, sprite, 2);
        p.moveUp = true;
        assertFalse(p.checkCollision(1, 2));
        assertFalse(p.isAbsorbed);
        assertFalse(p.isCollided);
        assertFalse(p.checkCollision(10, 10));
        assertFalse(p.checkCollision(10, 1));
    }

    @Test
    public void moveTest1() {
        //moveLeft
        Projectile p = new Projectile(20, 40, sprite, 0);
        p.move();
        assertEquals(16, p.x);
        assertEquals(40, p.y);
        assertEquals(0, p.lastMovement);
    }

    @Test
    public void moveTest2() {
        //moveRight
        Projectile p = new Projectile(20, 40, sprite, 1);
        p.move();
        assertEquals(24, p.x);
        assertEquals(40, p.y);
        assertEquals(1, p.lastMovement);
    }

    @Test
    public void moveTest3() {
        //moveUp
        Projectile p = new Projectile(20, 40, sprite, 2);
        p.move();
        assertEquals(20, p.x);
        assertEquals(36, p.y);
        assertEquals(2, p.lastMovement);
    }

    @Test
    public void moveTest4() {
        //mvoeDown
        Projectile p = new Projectile(20, 40, sprite, 3);
        p.move();
        assertEquals(20, p.x);
        assertEquals(44, p.y);
        assertEquals(3, p.lastMovement);
    }

    @Test
    public void moveTest5() {
        //moveLeft and left border
        Projectile p = new Projectile(0, 40, sprite, 0);
        p.move();
        assertEquals(0, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest6() {
        //moveRight and right border
        Projectile p = new Projectile(720, 40, sprite, 1);
        p.move();
        assertEquals(720, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest7() {
        //moveUp and top border
        Projectile p = new Projectile(20, 0, sprite, 2);
        p.move();
        assertEquals(20, p.x);
        assertEquals(0, p.y);
    }

    @Test
    public void moveTest8() {
        //moveDown and bottom border
        Projectile p = new Projectile(20, 660, sprite, 3);
        p.move();
        assertEquals(20, p.x);
        assertEquals(660, p.y);
    }

    @Test
    public void moveTest9() {
        //moveLeft and collision
        Projectile p = new Projectile(20, 40, sprite, 0);
        p.isCollided = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest10() {
        //moveRight and collision
        Projectile p = new Projectile(20, 40, sprite, 1);
        p.isCollided = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest11() {
        //moveUp and collision
        Projectile p = new Projectile(20, 40, sprite, 2);
        p.isCollided = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(40, p.y);
    }

    @Test
    public void moveTest12() {
        //moveDown and collision
        Projectile p = new Projectile(20, 40, sprite, 3);
        p.isCollided = true;
        p.move();
        assertEquals(20, p.x);
        assertEquals(40, p.y);
    }

}
