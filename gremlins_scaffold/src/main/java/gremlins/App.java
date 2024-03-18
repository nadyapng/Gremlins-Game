package gremlins;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;

import java.util.HashMap;
import java.util.Random;

import processing.event.KeyEvent;

import java.io.*;


public class App extends PApplet {

    public static final int WIDTH = 720;
    public static final int HEIGHT = 720;
    public static final int SPRITESIZE = 20;
    public static final int BOTTOMBAR = 60;

    public static final int FPS = 60;

    public static final Random randomGenerator = new Random();

    public String configPath;
    
    public PImage brickwall;
    public PImage stonewall;
    public PImage gremlin;
    public PImage wizard;
    public PImage exitwall;
    public PImage fireball;
    public PImage slime;
    public PImage powerup;
    public PImage portal;

    //list of all wizard images and brick images
    public HashMap<String, PImage> wizardSprites;
    public HashMap<String, PImage> brickwallSprites;
    public HashMap<String, PImage> gremlinSprites;

    public boolean isConfigRead;

    //json object attrubutes
    public int level;
    public int totalLevels;
    public int lives;
    public String layoutFilename;
    public float wizardCooldown;
    public float enemyCooldown;

    //set map
    public GameMap map1;

    //game over 
    public boolean isGameOver;


    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);

        // Load images during setup
        this.stonewall = loadImage(this.getClass().getResource("stonewall.png").getPath().replace("%20", ""));

        this.brickwall = loadImage(this.getClass().getResource("brickwall.png").getPath().replace("%20", ""));
        PImage brickwall_destroyed0 = loadImage(this.getClass().getResource("brickwall_destroyed0.png").getPath().replace("%20", ""));
        PImage brickwall_destroyed1 = loadImage(this.getClass().getResource("brickwall_destroyed1.png").getPath().replace("%20", ""));
        PImage brickwall_destroyed2 = loadImage(this.getClass().getResource("brickwall_destroyed2.png").getPath().replace("%20", ""));
        PImage brickwall_destroyed3 = loadImage(this.getClass().getResource("brickwall_destroyed3.png").getPath().replace("%20", ""));
        this.brickwallSprites = new HashMap<String, PImage>();
        this.brickwallSprites.put("normal", brickwall);
        this.brickwallSprites.put("destroyed0", brickwall_destroyed0);
        this.brickwallSprites.put("destroyed1", brickwall_destroyed1);
        this.brickwallSprites.put("destroyed2", brickwall_destroyed2);
        this.brickwallSprites.put("destroyed3", brickwall_destroyed3);

        PImage normalGremlin = loadImage(this.getClass().getResource("gremlin.png").getPath().replace("%20", ""));
        PImage frozenGremlin = loadImage(this.getClass().getResource("gremlin_frozen.png").getPath().replace("%20", ""));
        this.gremlinSprites = new HashMap<String, PImage>();
        this.gremlinSprites.put("normal", normalGremlin);
        this.gremlinSprites.put("frozen", frozenGremlin);

        PImage wizard0 = loadImage(this.getClass().getResource("wizard0.png").getPath().replace("%20", ""));
        PImage wizard1 = loadImage(this.getClass().getResource("wizard1.png").getPath().replace("%20", ""));
        PImage wizard2 = loadImage(this.getClass().getResource("wizard2.png").getPath().replace("%20", ""));
        PImage wizard3 = loadImage(this.getClass().getResource("wizard3.png").getPath().replace("%20", ""));

        this.wizardSprites = new HashMap<String, PImage>();
        this.wizardSprites.put("left", wizard0);
        this.wizardSprites.put("right", wizard1);
        this.wizardSprites.put("up", wizard2);
        this.wizardSprites.put("down", wizard3);

        this.exitwall = loadImage(this.getClass().getResource("door.png").getPath().replace("%20", ""));
        this.slime = loadImage(this.getClass().getResource("slime.png").getPath().replace("%20", ""));
        this.fireball = loadImage(this.getClass().getResource("fireball.png").getPath().replace("%20", ""));
        this.powerup = loadImage(this.getClass().getResource("potion.png").getPath().replace("%20", ""));
        this.portal = loadImage(this.getClass().getResource("portal.png").getPath().replace("%20", ""));

        this.level = 0;
        this.isConfigRead = false;
        this.isGameOver = false;

    }

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if (!isGameOver) {
            if (key == 37 && (map1.wizard.x % 20 == 0) && (map1.wizard.y % 20 == 0)) {
                map1.wizard.pressLeft();
            }

            if (key == 38 && (map1.wizard.x % 20 == 0) && (map1.wizard.y % 20 == 0)) {
                map1.wizard.pressUp();
            }

            if (key == 39 && (map1.wizard.x % 20 == 0) && (map1.wizard.y % 20 == 0)) {
                map1.wizard.pressRight();
            }

            if (key == 40 && (map1.wizard.x % 20 == 0) && (map1.wizard.y % 20 == 0)) {
                map1.wizard.pressDown();
            }

            if (key == 32) {

                map1.wizard.pressSpace(this.fireball, map1.fireballs);
                
            }
        }
        else {
            isGameOver = false;
            level = 0;
            isConfigRead = false;
        }
        
    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(KeyEvent e){

        int key = e.getKeyCode();
        
        if (key == 37) {
            map1.wizard.releaseLeft();
        }

        if (key == 38) {
            map1.wizard.releaseUp();
        }

        if (key == 39) {
            map1.wizard.releaseRight();
        }

        if (key == 40) {
            map1.wizard.releaseDown();
        }

        if (key == 49) {
            map1.wizard.releaseSpace();
        }
    }

    public void readConfig(int level) {
        JSONObject conf = loadJSONObject(new File(this.configPath));
        JSONArray levelsConf = conf.getJSONArray("levels");
        this.totalLevels = levelsConf.size();
        this.lives = conf.getInt("lives");
        JSONObject levelInfo = levelsConf.getJSONObject(level);

        this.layoutFilename = levelInfo.getString("layout");


        this.wizardCooldown = levelInfo.getFloat("wizard_cooldown");
        this.enemyCooldown = levelInfo.getFloat("enemy_cooldown");

        this.isConfigRead = true;

    }

    /**
     * Draw all elements in the game by current frame. 
	 */
    public void draw() {

        if (!isConfigRead && !isGameOver) {
            readConfig(this.level);
            this.map1 = new GameMap(this.layoutFilename, this.brickwallSprites, this.stonewall, this.exitwall, this.gremlinSprites, this.wizardSprites, this.fireball, this.slime, this.powerup, this.portal, this.lives, this.wizardCooldown, this.enemyCooldown); 
        }
    
        //if map1.isGameOver then reread config
        if (map1.isLevelOver && level < totalLevels) {
            isConfigRead = false;
            level++;
        }
        if (level >= totalLevels) {
            this.isGameOver = true;
            background(191, 153, 114);
            textSize(50);
            text("You win", 250, 360);
            textSize(20);
            text("Press any key to start over", 220, 450);
            return;
        }

        //reset map if wizard dies
        //check for game over
        if (map1.resetMap && lives > 0) {
            this.lives = lives - 1;
            this.map1 = new GameMap(this.layoutFilename, this.brickwallSprites, this.stonewall, this.exitwall, this.gremlinSprites, this.wizardSprites, this.fireball, this.slime, this.powerup, this.portal, this.lives, this.wizardCooldown, this.enemyCooldown); 
        }
        else if (lives == 0) {
            this.isGameOver = true;
            background(191, 153, 114);
            textSize(50);
            text("Game over", 225, 360);
            textSize(20);
            text("Press any key to start over", 220, 450);
            return;
        }

        background(191, 153, 114);
        textSize(18);
        fill(255, 255, 255);
        text("Lives: ", 10, HEIGHT - 30);
        //draw wizard sprites for lives
        for (int i = 0 ; i < map1.lives ; i++ ) {
            image(wizardSprites.get("right"), 3 * 25 + (20 * i), HEIGHT - 45);
        }
        //display level
        String levelDisplay = "Level " + (level+1) + "/ " + totalLevels;
        text(levelDisplay, 9*20+10, HEIGHT - 30);
        
    
        if (map1.wizard.cooldownCounter > 0) {
            //draw fireball cooldown
            stroke(0, 0, 0);
            fill(255, 255, 255);
            rect(31 * 20, 720 - 40, 80, 5);
            //draw overlapping rectangle
            stroke(0,0,0);
            fill(0, 0, 0);
            int totalCounter = (int) Math.ceil(wizardCooldown * 60);
            int currentWidth = (int) Math.ceil(((totalCounter+1) - map1.wizard.cooldownCounter)*80/totalCounter);
            rect(31 * 20, 720 - 40, currentWidth, 5);
        }

        this.map1.tick();
        this.map1.draw(this);

        //draw powerup countdown
        if (this.map1.wizard.isPoweredUp) {
            
            int counter = map1.wizard.powerupCounter;
            int timer = (int) Math.ceil(counter/60)+1;
            textSize(18);
            fill(255, 255, 255);
            text("Gremlins FROZEN for... " + timer, 16 * 20, HEIGHT-30);
        }

    }

    public static void main(String[] args) {
        PApplet.main("gremlins.App");
    }
}
