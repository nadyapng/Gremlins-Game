package gremlins;

import java.util.HashMap;

import processing.core.PImage;

public class BrickTile extends Tile {

    public boolean isHit;
    /**
     * Status represented how much the BrickTile is destroyed.
     * Status is -1 if BrickTile has not been destroyed
     */
    public int destructionStatus;
    /*
     * Counter for counting the frames before changing the sprite when hit
     */
    public int counter;

    /**
     * Constructor method for BrickTile
     * 
     * @param sprites   all images for BrickTile, containing the normal and destroyed BrickTile images
     * @param x         x-coordinate on the window  
     * @param y         y-coordinate on the window
     * @param row       row number on window grid
     * @param column    column number on window grid
     */
    public BrickTile(HashMap<String, PImage> sprites, int x, int y, int row, int column) {

        super(sprites, x, y, row, column);
        this.isHit = false;
        if (this.sprites != null) {
            this.sprite = sprites.get("normal");
        }
        this.destructionStatus = -1;
        this.counter = 4;

    }
    
    /**
     * Starts destruction sequence of BrickTile once hit by a fireball
     */
    public boolean hit() {

        this.isHit = true;
        return true;

    }

    /**
     * Change the sprite of the BrickTile depending on its destruction status
     */
    public void increaseDestructionStatus() {
        this.destructionStatus++;
        //change sprite accordingly
        if (destructionStatus == 0) {
            this.sprite = sprites.get("destroyed0");
        }
        else if (destructionStatus == 1) {
            this.sprite = sprites.get("destroyed1");
        }
        else if (destructionStatus == 2) {
            this.sprite = sprites.get("destroyed2");
        }
        else if (destructionStatus == 3) {
            this.sprite = sprites.get("destroyed3");
        }
    }

    /**
     * Decreases counter for current destruction status
     */
    public void decreaseCounter() {
         this.counter--;
    }

    /**
     * Resets counter after changing destruction status
     */
    public void resetCounter() {
        this.counter = 4;
    }

    /**
     * Checks if BrickTile is hit and runs the destruction sequence.
     * Removes BrickTile once it is fully destroyed.
     */
    public void tick() {

        if (isHit && isExist) {
            //remove brick wall if fully destroyed
            if (destructionStatus == 3 && counter == 0) {
                isExist = false;
            }
            //increase destruction status and reset counter if counter reaches 0 for current status
            else if (counter == 0) {
                increaseDestructionStatus();
                resetCounter();
            }
            //decrease counter
            else {
                decreaseCounter();
            }    

        }
    }
}
