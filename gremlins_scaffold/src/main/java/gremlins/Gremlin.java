package gremlins;

import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Gremlin extends Character {

    public int slimeCounter;
    public ArrayList<Slime> slimes;
    public int respawnCounter;
    /**
     * Random object to handle gremlin's random movement
     */
    private Random rand;

    /**
     * Constructor method for Gremlin.
     * Sets values of default attributes.
     * Sets random initial direction of Gremlin.
     * 
     * @param x                 x-coordinate on window
     * @param y                 y-coordinate on window
     * @param sprites           images of Gremlin (normal and frozen versions)
     * @param projectileSprite  image of Gremlin's slime
     * @param row               row number on window grid
     * @param column            column number on window grid
     * @param cooldown          cooldown period for Gremlin to shoot slimes
     * @param slimes            list of all slimes shot by Gremlin
     */
    public Gremlin(int x, int y, HashMap<String, PImage> sprites, PImage projectileSprite, int row, int column, float cooldown, ArrayList<Slime> slimes) {
        
        super(x, y, sprites, projectileSprite, row, column, cooldown);
        this.vel = 1;
        this.slimes = slimes;
        this.respawnCounter = 60;
        this.rand = new Random(hashCode());
        if (this.sprites != null) {
            this.sprite = sprites.get("normal");
        }
        
        this.setInitialDirection();

    }

    /**
     * Updates counter for slime shooting and counter for respawn if Gremlin is not alive.
     * Checks collisions with other GameObjects and triggers movement.
     * Updates row and column numbers of Gremlin.
     * 
     * @param allTiles      list containing all tiles on the GameMap
     * @param wizard        Wizard object on the GameMap
     * @param allGremlins   list containing all Gremlin objects (including this instance) on GameMap
     */
    public void tick(ArrayList<Tile> allTiles, Wizard wizard, ArrayList<Gremlin> allGremlins) {
  
        int tileHeight = 20;
        int tileWidth = 20;

        if (!isExist) {
            if (respawnCounter > 0) {
                //decrease respawn counter
                respawnCounter--;
            }
            else {
                this.respawn(wizard, allTiles, allGremlins);
            }
            return;
        }

        // //check cooldown counter
        if (cooldownCounter > 0) {
            this.cooldownCounter--;
        } else if (cooldownCounter == 0) {
            this.shootSlime(projectileSprite, slimes);
        }

        //check if chosen movement will cause collision
        for (Tile t : allTiles) {
            if(!t.isExist) {
                continue;
            }
            this.checkCollision(t.row, t.column);
        }

        if (this.isCollided) {
            this.randomMovement(allTiles);
        }

        this.move();

        this.row = (int) Math.floor(this.y/tileHeight);
        this.column = (int) Math.floor(this.x/tileWidth);

    }

    /**
     * Sets random initial direction for Gremlin's movement when game starts up
     */
    public void setInitialDirection() {

        rand.setSeed(hashCode());
        int dir = -1;
        dir = rand.nextInt(4);
        if (dir == 0) {
            this.moveLeft = true;
        }
        else if (dir == 1) {
            this.moveRight = true;
        }
        else if (dir == 2) {
            this.moveUp = true;
        }
        else if (dir == 3) {
            this.moveDown = true;
        }

        this.lastMovement = dir;
    }

    /**
     * Chooses random direction for Gremlin movement from available directions that will not cause collision
     * 
     * @param allTiles  list containing all tile objects on GameMap
     */
    public void randomMovement(ArrayList<Tile> allTiles) {

        this.stop();

        ArrayList<Integer> sideDir = getSideDir();

        //test sideMovements
        boolean emptySide = false;
        while (sideDir.size() > 0) {

            int index = rand.nextInt(sideDir.size());
            int dir = sideDir.get(index);
    
            toggleMovement(dir);

            //check collision
            for (Tile t : allTiles) {
                if(!t.isExist) {
                    continue;
                }
                this.checkCollision(t.row, t.column);
            }

            if (!isCollided) {
                emptySide = true;
                break;
            }
            else {
                sideDir.remove(index);
                this.stop();
                continue;
            }
        }

        //only allow move back if no sides are available
        if (!emptySide){
            if (lastMovement == 0 || lastMovement == 2) {
                toggleMovement(lastMovement + 1);
            }
            else if (lastMovement == 1 || lastMovement == 3) {
                toggleMovement(lastMovement - 1);
            }
        }
        this.isCollided = false;

        
    }

    /**
     * Checks collision for each direction 
     * 
     * @param allTiles  list containing all tile objects on GameMap
     * @return list of all possible movements for Gremlin
     */
    public ArrayList<Integer> getValidDirections(ArrayList<Tile> allTiles) {
        
        ArrayList<Integer> sideDir = this.getSideDir();
        ArrayList<Integer> validDir = new ArrayList<Integer>();

        for (int i : sideDir) {
            toggleMovement(i);
         
            for (Tile t : allTiles) {
                if(!t.isExist) {
                    continue;
                }
                this.checkCollision(t.row, t.column);
            }
            if (isCollided) {
                continue;
            }
            validDir.add(i);
        }

        this.stop();
        return validDir;
    }

    /**
     * Finds side directions (left and right) depending on where Gremlin is facing
     * @return list of side directions
     */
    public ArrayList<Integer> getSideDir() {

        ArrayList<Integer> sideDir = new ArrayList<Integer>();

        //oppoite directions
        int oppDir = -1;
        if (lastMovement == 0 || lastMovement == 2) {
            oppDir = lastMovement + 1;
        }
        else if (lastMovement == 1 || lastMovement == 3) {
            oppDir = lastMovement - 1;
        }

        for (int i = 0 ; i < 4 ; i++) {
            if (i == lastMovement || i == oppDir) 
                continue;
            sideDir.add(i);
        }
        
        return sideDir;
    }

    /**
     * Switches movement directions according to direction number given 
     * @param dir   direction to make Gremlin move in (0 for left, 1 for right, 2 for up, 3 for down)
     */
    public void toggleMovement(int dir) {

        if (dir == 0) {
            this.moveLeft = true;
        }
        else if (dir == 1) {
            this.moveRight = true;
        }
        else if (dir == 2) {
            this.moveUp = true;
        }
        else if (dir == 3) {
            this.moveDown = true;
        }

    }

    /**
     * Starts sequence for slime shooting and adds slime object into list of all slimes on map.
     * Starts counter for cooldown before next shoot
     * 
     * @param slimeSprite   image of slime  
     * @param slimes        list containing all slime objects on GameMap
     */
    public void shootSlime(PImage slimeSprite, ArrayList<Slime> slimes) {

        shootProjectile = true;
        cooldownCounter = (int) Math.ceil(cooldown * 60);
        Slime slime = new Slime(this.x, this.y, slimeSprite, this.lastMovement);
        slimes.add(slime);

    }

    /**
     * Respawns Gremlin at a random location on map.
     * Location is at least 10 tiles away from Wizard and does not overlap with other objects
     * 
     * @param wizard        Wizard object on map    
     * @param allTiles      list containing all tiles on map
     * @param allGremlins   list containing all Gremlins on map
     */
    public void respawn(Wizard wizard, ArrayList<Tile> allTiles, ArrayList<Gremlin> allGremlins) {
        this.isExist = true;
        this.isCollided = false;

        //get radius of wizard 
        int leftBound = wizard.column - 10;
        int rightBound = wizard.column + 10;
        int upBound = wizard.row - 10;
        int downBound = wizard.row + 10;
        //check against window bounds
        if (leftBound < 0) {
            leftBound = 0;
        }
        if (rightBound > 35) {
            rightBound = 35;
        }
        if (upBound < 0) {
            upBound = 0;
        }
        if (downBound > 32) {
            downBound = 32;
        }


        //get eligible numbers
        ArrayList<Integer> newRowRange = new ArrayList<Integer>();
        addNumbersToRange(newRowRange, 0, upBound);
        addNumbersToRange(newRowRange, downBound, 32);
        ArrayList<Integer> newColumnRange = new ArrayList<Integer>();
        addNumbersToRange(newColumnRange, 0, leftBound);
        addNumbersToRange(newColumnRange, rightBound, 35);

        while (true) {
            
            int newRow = newRowRange.get(rand.nextInt(newRowRange.size()));
            int newColumn = newColumnRange.get(rand.nextInt(newColumnRange.size()));
            
            boolean isOverlap = checkOverlap(newRow, newColumn, allTiles, allGremlins);

            if (!isOverlap) {
                //update coords
                this.row = newRow;
                this.column = newColumn;
                this.x = this.column * 20;
                this.y = this.row * 20;
                break;
            }
            else {
                continue;
            }
        }

    }

    /**
     * Helper function for Gremlin respawn.
     * Adds numbers between a range into a existing list.
     * 
     * @param rangeNumbers  existing list 
     * @param min           lower bound of range
     * @param max           upper bound of range
     * @return updated list containing new range of numbers
     */
    public ArrayList<Integer> addNumbersToRange(ArrayList<Integer> rangeNumbers, int min, int max) {
        for (int i = min; i <= max; i++) {
            rangeNumbers.add(i);
        }
        return rangeNumbers;
    }

    /**
     * Generates random coordinate
     * 
     * @param bound upper bound for coordinate
     * @return random coordinate within bound
     */
    public int getRandomCoord(int bound) {
        int coord = rand.nextInt(bound);
        return coord;

    }

    /**
     * Checks if the row and column numbers overlap with other objects on map
     * 
     * @param newRow        row number on window grid to be checked
     * @param newColumn     column number on window grid to be checked
     * @param allTiles      list containing all tiles on map
     * @param allGremlins   list containing all Gremlins on map
     * @return true if new coordinates overlap with another object on map
     */
    public boolean checkOverlap(int newRow, int newColumn, ArrayList<Tile> allTiles, ArrayList<Gremlin> allGremlins) {

        boolean isOverlap = false;
            for (Tile t : allTiles) {
                if (!t.isExist) {
                    continue;
                }
                if ((newRow == t.row) && (newColumn == t.column)) {
                    isOverlap = true;
                    break;
                }
            }

            for (Gremlin g : allGremlins) {
                if (!g.isExist) {
                    continue;
                }
                if ((newRow == g.row) && (newColumn == g.column)) {
                    isOverlap = true;
                    break;
                }
            }

            return isOverlap;
    }
    
    /**
     * Freezes Gremlin when Powerup is absorbed by Wizard by stopping Gremlin movement.
     * Chnages sprite of Gremlin to its frozen version
     * 
     * @return current velocity of Gremlin
     */
    public int freeze() {

        this.vel = 0;
        this.sprite = this.sprites.get("frozen");
        return this.vel;
    }

    /**
     * Unfreezes Gremlin and changes sprite back to its normal version
     * 
     * @return current velocity of Gremlin
     */
    public int unfreeze() {
        this.vel = 1;
        this.sprite = this.sprites.get("normal");
        return this.vel;
    }
}
