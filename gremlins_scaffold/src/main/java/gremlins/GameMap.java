package gremlins;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;

public class GameMap {
    
    public String layoutFilename; 
    public ArrayList<Tile> allTiles;
    public ArrayList<StoneTile> stoneTiles;
    public ArrayList<BrickTile> brickTiles;
    public ArrayList<Gremlin> gremlins;
    public Door exitTile;
    public Wizard wizard;
    public ArrayList<Fireball> fireballs;
    public ArrayList<Slime> slimes;
    public ArrayList<Powerup> powerups;
    public ArrayList<Door> portals;

    public ArrayList<String> lines;

    //images
    public HashMap<String, PImage> brickTileSprites;
    public PImage stoneTileSprite;
    public PImage exitTileSprite;
    public HashMap<String, PImage> wizardSprites;
    public HashMap<String, PImage> gremlinSprites;
    public PImage fireballSprite;
    public PImage slimeSprite;
    public PImage powerupSprite;
    public PImage portalSprite;

    public int wizardSpawnCounter;
    public boolean isLevelOver;
    public int lives;
    public boolean resetMap;

    public float wizardCooldown;
    public float fireballCooldownCounter;
    public float gremlinCooldown;

    /**
     * Constructor method for a GameMap 
     * 
     * @param layoutFilename    filename of text file containing layout of map
     * @param brickwallSprites  images of BrickTile
     * @param stoneTileSprite   image of StoneTile
     * @param exitTileSprite    image of ExitTile
     * @param gremlinSprites    images of Gremlin
     * @param wizardSprites     images of Wizard, for all directions
     * @param fireballSprite    image of wizard's fireball
     * @param slimeSprite       image of gremlin's Slime
     * @param powerupSprite     image of Powerup
     * @param portalSprite      image of portal
     * @param lives             current wizard lives remaining
     * @param wizardCooldown    cooldown period for wizard to shoot fireballs
     * @param gremlinCooldown   cooldown period for gremlin to shoot slimes
     */
    public GameMap(String layoutFilename, HashMap<String, PImage> brickwallSprites, PImage stoneTileSprite, PImage exitTileSprite, HashMap<String, PImage> gremlinSprites, HashMap<String, PImage> wizardSprites, PImage fireballSprite, PImage slimeSprite, PImage powerupSprite, PImage portalSprite, int lives, float wizardCooldown, float gremlinCooldown) {

        this.layoutFilename = layoutFilename; 
        this.allTiles = new ArrayList<Tile>();
        this.stoneTiles = new ArrayList<StoneTile>();
        this.brickTiles = new ArrayList<BrickTile>();
        this.gremlins = new ArrayList<Gremlin>();
        this.fireballs = new ArrayList<Fireball>();
        this.slimes = new ArrayList<Slime>();
        this.powerups = new ArrayList<Powerup>();
        this.portals = new ArrayList<Door>();

        this.brickTileSprites = brickwallSprites;
        this.stoneTileSprite = stoneTileSprite;
        this.exitTileSprite = exitTileSprite;
        this.gremlinSprites = gremlinSprites;
        this.wizardSprites = wizardSprites;
        this.fireballSprite = fireballSprite;
        this.slimeSprite = slimeSprite;
        this.powerupSprite = powerupSprite;
        this.portalSprite = portalSprite;

        this.wizardSpawnCounter = 30; 
        this.isLevelOver = false;
        this.lives = lives;
        this.resetMap = false;
        //cooldown multiplied by fps
        this.wizardCooldown = wizardCooldown;
        this.gremlinCooldown = gremlinCooldown;

        boolean passParse = this.parseLayoutFile();
        if (passParse) {
            this.createObjects();
        }

    }


    /**
     * Parses the layout text file for the GameMap, and add lines from file to a list.
     * Handles errors with invalid files.
     * 
     * @return true if file can be found and is properly parsed
     */
    public boolean parseLayoutFile() {

        try{
            File f = new File(this.layoutFilename);
            Scanner scan = new Scanner(f);
            ArrayList<String> lines = new ArrayList<String>();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                lines.add(line);
            }
            scan.close();
            this.lines = lines;
            return true;
            
        }catch(FileNotFoundException e) {
            System.out.print("File does not exist");
            return false;
        }

        }
    
    
    /**
     * Creates objects of map according to list of text from layout file.
     * Type of object is decided by current chracter in the list of text.
     * Handles illegal chracters in layout file.
     */
    public void createObjects() {

        for (int i = 0 ; i < lines.size() ; i++) {

            for(int j = 0 ; j < lines.get(i).length() ; j++) {
                int currentX = j * 20;
                int currentY = i * 20;
                //create brick wall
                if (lines.get(i).charAt(j) == 'B') {
                    BrickTile b = new BrickTile(brickTileSprites, currentX, currentY, i, j);
                    this.allTiles.add(b);
                    this.brickTiles.add(b);
                }
                //create stone wall
                else if (lines.get(i).charAt(j) == 'X') {
                    StoneTile s = new StoneTile(stoneTileSprite, currentX, currentY, i, j);
                    this.allTiles.add(s);
                    this.stoneTiles.add(s);
                }
                //create exit door
                else if (lines.get(i).charAt(j) == 'E') {
                    Door e = new Door(exitTileSprite, currentX, currentY, i, j);
                    this.exitTile = e;
                }
                //create wizard
                else if (lines.get(i).charAt(j) == 'W') {
                    Wizard w = new Wizard(currentX, currentY, wizardSprites, fireballSprite, i ,j, wizardCooldown);
                    this.wizard = w;
                }
                //create gremlin
                else if (lines.get(i).charAt(j) == 'G') {
                    Gremlin g = new Gremlin(currentX, currentY, gremlinSprites, slimeSprite, i, j, gremlinCooldown, slimes);
                    this.gremlins.add(g);
                }
                //create powerup
                else if (lines.get(i).charAt(j) == 'P') {
                    Powerup p = new Powerup(powerupSprite, currentX, currentY, i, j);
                    this.powerups.add(p);
                }
                //create extension
                else if (lines.get(i).charAt(j) == 'D') {
                    Door d = new Door(portalSprite, currentX, currentY, i, j);
                    this.portals.add(d);
                }
                //skip empty 
                else if (lines.get(i).charAt(j) == ' ') {
                    continue;
                }
                else{
                    continue;
                }
              
            }
        }
    }
    
    /**
     * Checks if exit door is reached by wizard
     */
    public void tick() {
        
        if (exitTile.isReached()) {
            this.isLevelOver = true;
        }
    }

    /**
     * Draws the entire GameMap onto the window, including all the GameObjects.
     * Updates status of all GameObjects before drawing.
     * 
     * @param app   PApplet app to draw on
     */
    public void draw(PApplet app) {

        //draw extensions
        for (Door d : portals) {
            d.draw(app);
        }

        //tick all elements
        this.wizard.tick(this.allTiles, this.gremlins, this.fireballs, this.exitTile, this.powerups, this.portals);
        if (wizard.isExist) {
            //draw wizard
            this.wizard.draw(app);
        }
        else if (wizardSpawnCounter > 0) {
            wizardSpawnCounter--;
        }
        else {
            resetMap = true;
            wizard.isExist = true;
        }
        
        //draw brick tiles
        for (BrickTile b : brickTiles){
            if (!b.isExist){
                continue;
            }
            else {
                b.tick();
                b.draw(app);
            }
        }

        //draw stone tiles 
        for (StoneTile s : stoneTiles) {
            s.draw(app);
        }

        //draw door
        exitTile.draw(app);

        //draw gremlins
        for (Gremlin g : gremlins) {
            g.tick(this.allTiles, this.wizard, this.gremlins);
            if (!g.isExist){
                continue;
            }
            else {
                g.draw(app);
            }
        }

        //draw fireballs
        for (Fireball f : fireballs) {
            f.tick(allTiles, gremlins, wizard, slimes);
            if (f.isAbsorbed) {
                continue;
            }
            else {
                f.draw(app);
            }
    
        }

        //draw slimes
        for (Slime s : slimes) {
            s.tick(allTiles, gremlins, wizard, fireballs);
            if (s.isAbsorbed) {
                continue;
            }
            else {
                s.draw(app);
            }
    
        }

        //draw powerups
        for (Powerup p : powerups) {

            p.tick(wizard, allTiles, gremlins, exitTile, portals);
            if (p.isExist) {
                p.draw(app);
            }
        }   

        

    }


}
