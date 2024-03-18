package gremlins;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PImage;

public class Wizard extends Character {

    public boolean isStationary;
    public boolean isPoweredUp;
    public int powerupCounter;

    /**
     * Constructor method for Wizard.
     * Sets default values for velocity, projctile velocity, counter and boolean attributes.
     * 
     * @param x                 x-coordinate on window
     * @param y                 y-coordinate on window
     * @param sprites           images of Wizard, including images for all directions
     * @param projectileSprite  image of Wizard's fireball
     * @param row               row number on window grid
     * @param column            column number on column grid
     * @param cooldown          cooldown for Wizard shooting fireball
     */
    public Wizard(int x, int y, HashMap<String, PImage> sprites, PImage projectileSprite, int row, int column, float cooldown) {
        
        super(x, y, sprites, projectileSprite, row, column, cooldown);
        this.vel = 2;
        this.projectileVel = 4;
        this.isStationary = true;
        this.isPoweredUp = false;
        this.powerupCounter = 0;

    }

    /**
     * Checks Wizard movement and checks collision of Wizard with other objects on map.
     * Checks if Wizard reaches exit door and triggers level change sequence if so.
     * Checks if Wizard collects powerup and triggers powerup effects if so.
     * Checks if Wizard enters portal and teleports Wizard.
     * Updates counters for fireball shooting countdown.
     * Triggers Wizard movement.
     * 
     * @param allTiles  list containing all tiles on map
     * @param gremlins  list containing all gremlins on map
     * @param fireballs list containing all fireballs on map
     * @param exitDoor  exit door on map
     * @param powerups  list containing all powerups on map
     * @param portals   list containing all portals on map
     */
    public void tick(ArrayList<Tile> allTiles, ArrayList<Gremlin> gremlins, ArrayList<Fireball> fireballs, Door exitDoor, ArrayList<Powerup> powerups, ArrayList<Door> portals) {

        int tileHeight = 20;
        int tileWidth = 20;

        // check if wizard is stationary
        this.checkStationary();

        //check cooldown counter
        if (cooldownCounter > 0) {
            this.cooldownCounter--;
        }
    
        //check if chosen movement will cause collision
        for (Tile t : allTiles) {
            if(!t.isExist) {
                continue;
            }
            this.checkCollision(t.row, t.column);
        }

        for (Gremlin g : gremlins) {
            if (!g.isExist) {
                continue;
            }
            this.checkHitGremlin(g);
        }

        //check if reach exit door
        boolean reachExit = this.checkCollision(exitDoor.row, exitDoor.column);
        if (reachExit) {
            exitDoor.reach();
        }

        //check hit powerup
        for (Powerup p : powerups) {
            if (!p.isExist) {
                continue;
            }
            boolean collectPowerup = this.checkCollision(p.row, p.column);
            if (collectPowerup) {
                this.absorbPowerup(p, gremlins);
            }
        }
        //check powerup counter
        if (isPoweredUp && powerupCounter > 0) {
            powerupCounter--;
        }
        else if (isPoweredUp && powerupCounter == 0) {
            this.endPowerup(gremlins);
        }

        //check portal
        for (Door d : portals) {
            if (checkCollision(d.row, d.column)) {
                this.teleport(portals, portals.indexOf(d));
            }
        }

        if (!isExist) {
            return;
        }

        this.move();

        this.row = (int) Math.floor(this.y/tileHeight);
        this.column = (int) Math.floor(this.x/tileWidth);

        
    }

    /**
     * Changes Wizard's movement direction to left 
     */
    public void pressLeft() {
        if (lastMovement != 0) {
            this.stop();
        }
        this.moveLeft = true;
        this.sprite = this.sprites.get("left");
        this.lastMovement = 0;

    }

    /**
     * Changes Wizard's movement direction to right 
     */
    public void pressRight() {
        if (lastMovement != 1) {
            this.stop();
        }
        this.moveRight = true;
        this.sprite = this.sprites.get("right");
        this.lastMovement = 1;

    }

    /**
     * Changes Wizard's movement direction to up 
     */
    public void pressUp() {
        if (lastMovement != 2) {
            this.stop();
        }
        this.moveUp = true;
        this.sprite = this.sprites.get("up");
        this.lastMovement = 2;

    }

    /**
     * Changes Wizard's movement direction to down 
     */
    public void pressDown() {
        if (lastMovement != 3) {
            this.stop();
        }
        this.moveDown = true;
        this.sprite = this.sprites.get("down");
        this.lastMovement = 3;

    }

    /**
     * Stop Wizard from moving left and updates collision attribute
     */
    public void releaseLeft() {
        
        this.moveLeft = false;
        this.isCollided = false;

    }

    /**
     * Stop Wizard from moving right and updates collision attribute
     */
    public void releaseRight() {
        
        this.moveRight = false;
        this.isCollided = false;

    }

    /**
     * Stop Wizard from moving up and updates collision attribute
     */
    public void releaseUp() {
        
        this.moveUp = false;
        this.isCollided = false;

    }

    /**
     * Stop Wizard from moving down and updates collision attribute
     */
    public void releaseDown() {
        
        this.moveDown = false;
        this.isCollided = false;

    }

    /**
     * Checks if Wizard is moving
     */
    public void checkStationary() {
        if (moveLeft || moveRight || moveUp || moveDown) {
            isStationary = false;
        }
        else {
            isStationary = true;
        }
    }

    /**
     * Triggers fireball shooting sequence and starts cooldown counter.
     * Adds newly created fireball into list of all fireballs.
     * 
     * @param fireballSprite    image of Fireball
     * @param fireballs         list containing all Fireballs on map
     */
    public void pressSpace(PImage fireballSprite, ArrayList<Fireball> fireballs) {

        if (cooldownCounter == 0 && isExist) {
            shootProjectile = true;
            cooldownCounter = (int) Math.ceil(cooldown * 60);
            Fireball fireball = new Fireball(this.x, this.y, fireballSprite, this.lastMovement);
            fireballs.add(fireball);
        }
    
    }

    /**
     * Stops fireball shooting after releasing the spacebar
     */
    public void releaseSpace() {

        shootProjectile = false;

    }

    /**
     * Checks if Wizard hits Gremlin and kills Wizard if true
     * 
     * @param g Gremlin object to check for collision
     * @return true if Wizard is hit
     */
    public boolean checkHitGremlin(Gremlin g){

        boolean collision = false;

        //check when stationary
        if (!isStationary) {
            collision = checkCollision(g.row, g.column);
        }
        else{
            if (g.moveLeft && ((this.column + 1 == g.column) && (this.row == g.row))) {
                collision = true;
            }
            else if (g.moveRight && ((this.column - 1 == g.column) && (this.row == g.row))) {
                collision = true;
            }
            else if (g.moveUp && ((this.row + 1 == g.row) && (this.column == g.column))) {
                collision = true;
            }
            else if (g.moveDown && ((this.row - 1 == g.row) && (this.column == g.column))){
                collision = true;
            }
            
        }

        if (collision) {
            this.isCollided = true;
            this.isExist = false;
            return true;
        }
    
        return false;
    }

    /**
     * Absorbs Powerup and starts Powerup spawn counter.
     * Freezes Gremlins for a period of time.
     * 
     * @param p         Powerup to be absorbed
     * @param gremlins  list containing all gremlins on map
     */
    public void absorbPowerup(Powerup p, ArrayList<Gremlin> gremlins) {
        //set possibility of collecting another powerup while still powered up (reset counter)
        
        p.isExist = false;
        p.spawnCounter = 300;
        this.isPoweredUp = true;
        this.powerupCounter = 300;

        //freeze gremlins 
        for (Gremlin g : gremlins) {
            g.freeze();
        }

    }

    /**
     * Ends effects of Powerup and unfreezes all gremlins
     * 
     * @param gremlins  list containing all gremlins on map
     */
    public void endPowerup(ArrayList<Gremlin> gremlins) {
        //set possibility of collecting another powerup while still powered up (reset counter)
        this.isPoweredUp = false;

        //unfreeze gremlin
        for (Gremlin g : gremlins) {
            g.unfreeze();
        }

    }

    /**
     * Teleports Wizard to location of next portal
     * 
     * @param portals   list containing all portals on map
     * @param index     index of current portal in list
     */
    public void teleport(ArrayList<Door>  portals, int index) {

        index = (index+1) % portals.size();
        this.row = portals.get(index).row;
        this.column = portals.get(index).column;
        this.x = this.column * 20;
        this.y = this.row * 20;
    }

}


    

