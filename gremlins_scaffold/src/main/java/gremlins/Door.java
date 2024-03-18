package gremlins;

import processing.core.PImage;

//for both exit and portals
public class Door extends Tile {

    private boolean isReached;

    /**
     * Constructor method for a Door
     * 
     * @param textureImage  image for Door
     * @param x             x-coordinate on window
     * @param y             y-coordinate on window
     * @param row           row number on wondow grid
     * @param column        column number on window grid
     */
    public Door(PImage textureImage, int x, int y, int row, int column) {

        super(textureImage, x, y, row, column);
        
        isReached = false;
    }

    /**
     * Getter method for isReached.
     * 
     * @return whether wizard reaches/ collides with Door
     */
    public boolean isReached() {
        return isReached;
    }
    /**
     * Door is reached by wizard
     */
    public void reach() {
        this.isReached = true;
    }

}
