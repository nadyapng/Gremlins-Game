package gremlins;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PImage;

public class Powerup extends Tile {
    
    public int originalSpawnCounter;
    public int spawnCounter;
    public Random rand;
    public boolean firstSpawn;

    /**
     * Constructor method for Powerup.
     * Sets values of default attributes and sets seed for random object.
     * 
     * @param textureImage  image of Powerup
     * @param x             x-coordinate on window
     * @param y             y-coordinate on window
     * @param row           row number on window grid
     * @param column        column number on window grid
     */
    public Powerup(PImage textureImage, int x, int y, int row, int column) {
        
        super(textureImage, x, y, row, column);
        this.spawnCounter = 180;
        this.isExist = false;
        this.rand = new Random(hashCode());
        this.firstSpawn = true;

    }

    /**
     * Updates counter for spawning and respawns Powerup after counter runs out
     * 
     * @param wizard        Wizard object on map
     * @param allTiles      list containing all tiles on map
     * @param allGremlins   list containing all Gremlins on map
     * @param exitDoor      exit door on map
     * @param portals       list containing all portals on map
     */
    public void tick(Wizard wizard, ArrayList<Tile> allTiles, ArrayList<Gremlin> allGremlins, Door exitDoor, ArrayList<Door> portals) {

        if (this.spawnCounter > 0) {
            this.spawnCounter--;
        }
        else if (this.firstSpawn && this.spawnCounter == 0 && !this.isExist) {
            this.isExist = true;
            this.firstSpawn = false;
        }
        else if (this.spawnCounter == 0 && !this.isExist) {
            this.respawn(wizard, allTiles, allGremlins, exitDoor, portals);
        }
    }

    /**
     * Respawns Powerup at a random location that does not overlap with another object on map
     * 
     * @param wizard        Wizard object on map
     * @param allTiles      list containing all tiles on map
     * @param allGremlins   list containing all gremlins on map
     * @param exitDoor      exit door on map
     * @param portals       list containing all portals on map
     */
    public void respawn(Wizard wizard, ArrayList<Tile> allTiles, ArrayList<Gremlin> allGremlins, Door exitDoor, ArrayList<Door> portals) {

        while (true) {
            //get random coords
            int newRow = rand.nextInt(32);
            int newColumn = rand.nextInt(35);

            //check overlap
            boolean isOverlap = checkOverlap(newRow, newColumn, wizard, allTiles, allGremlins, exitDoor, portals);

            if (!isOverlap) {
                //update coords
                this.row = newRow;
                this.column = newColumn;
                this.x = this.column * 20;
                this.y = this.row * 20;
                isExist = true;
                break;
            }
            else {
                continue;
            }
        }

    }

    /**
     * Checks if coordinates overlap with another object on map
     * 
     * @param newRow        row number on window to be checked
     * @param newColumn     column number on window to be checked
     * @param wizard        Wizard object on map
     * @param allTiles      list containing all tiles on map
     * @param allGremlins   list containing Gremlins on map
     * @param exitDoor      exit door on map
     * @param portals       list containing all portals on map
     * @return true if coordinates overlap with another object on map
     */
    public boolean checkOverlap(int newRow, int newColumn, Wizard wizard, ArrayList<Tile> allTiles, ArrayList<Gremlin> allGremlins, Door exitDoor, ArrayList<Door> portals) {

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

            if (wizard.isExist) {
                if ((newRow == wizard.row) && (newColumn == wizard.column)) {
                    isOverlap = true;
                }
            }

            
            if ((newRow == exitDoor.row) && (newColumn == exitDoor.column)) {
                isOverlap = true;
            }

            for (Door s : portals) {
                if (!s.isExist) {
                    continue;
                }
                if ((newRow == s.row) && (newColumn == s.column)) {
                    isOverlap = true;
                    break;
                }
            }
            
            return isOverlap;
    }
    
}
