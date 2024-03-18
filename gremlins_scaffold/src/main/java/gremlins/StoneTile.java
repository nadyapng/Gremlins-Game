package gremlins;

import processing.core.PImage;

public class StoneTile extends Tile {
    
    /**
     * Constructor method for StoneTile
     * @param textureImage  image for StoneTile
     * @param x             x-coordinate on window
     * @param y             y-coordinate on window
     * @param row           row number on window grid
     * @param column        column number on window grid
     */
    public StoneTile(PImage textureImage, int x, int y, int row, int column) {

        super(textureImage, x, y, row, column);
        
    }
}
