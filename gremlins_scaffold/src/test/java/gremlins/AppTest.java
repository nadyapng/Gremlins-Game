package gremlins;

import org.junit.jupiter.api.Test;

import processing.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void drawTest1() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        //assertEquals(expected, app.powerUp(500));
    }

    @Test
    public void drawTest2() {
        //test wizard dead
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        app.map1.wizard.isExist = false;
        //assertEquals(expected, app.powerUp(500));
    }

    @Test
    public void drawTest3() {
        //test wizard counter and reset map
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        app.map1.wizardSpawnCounter = 0;
        app.map1.wizard.isExist = false;
        app.map1.fireballs.add(new Fireball(20, 20, null, 3));
        // assertTrue(app.map1.resetMap);
        // assertTrue(app.map1.wizard.isExist);
        //assertEquals(expected, app.powerUp(500));
    }

    @Test
    public void drawTest4() {
        //test objects not exist
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        app.map1.brickTiles.get(0).isExist = false;
        app.map1.gremlins.get(0).isExist = false;
        app.map1.fireballs.add(new Fireball(20, 20, null, 3));
        // app.map1.fireballs.add(new Fireball(60, 20, null, 3));
        app.map1.fireballs.get(0).isAbsorbed = true;
        app.map1.powerups.get(0).isExist = false;

    }


    @Test
    public void drawTest5() {
        //level
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        app.lives = 0;
    }

    @Test
    public void drawTest6() {
        //counters
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        app.map1.wizard.cooldownCounter = 60;

    }

    @Test
    public void drawTest7() {
        //counters
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        app.map1.wizard.isPoweredUp = true;
        app.map1.wizard.powerupCounter = 1200;
    }

    @Test
    public void drawTest8() {
        //level over
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        app.map1.isLevelOver = true;

    }

    @Test
    public void drawTest9() {
        //level over
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        app.level = 1;
        app.map1.isLevelOver = true;
    }
    
    @Test
    public void keyPressedTest1() {
        //left press
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(500);
        KeyEvent e = new KeyEvent(app, 0, 0, 0, 'a', 37);
        app.keyPressed(e);
        assertTrue(app.map1.wizard.moveLeft);  
        app.keyReleased(e);
        assertFalse(app.map1.wizard.moveLeft);   
    }

    @Test
    public void keyPressedTest2() {
        //right press
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        KeyEvent e = new KeyEvent(app, 0, 0, 0, 'a', 39);
        app.keyPressed(e);
        assertTrue(app.map1.wizard.moveRight);  
        app.keyReleased(e);
        assertFalse(app.map1.wizard.moveRight);   
    }

    @Test
    public void keyPressedTest3() {
        //up press
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        KeyEvent e = new KeyEvent(app, 0, 0, 0, 'a', 38);
        app.keyPressed(e);
        assertTrue(app.map1.wizard.moveUp);  
        app.keyReleased(e);
        assertFalse(app.map1.wizard.moveUp); 
    }

    @Test
    public void keyPressedTest4() {
        //left press
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        KeyEvent e = new KeyEvent(app, 0, 0, 0, 'a', 40);
        app.keyPressed(e);
        assertTrue(app.map1.wizard.moveDown);  
        app.keyReleased(e);
        assertFalse(app.map1.wizard.moveDown);   
    }

    @Test
    public void keyPressedTest5() {
        //spcae press
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        KeyEvent e = new KeyEvent(app, 0, 0, 0, 'a', 32);
        app.keyPressed(e);
        assertTrue(app.map1.wizard.shootProjectile);  
    }
}
