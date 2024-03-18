package gremlins;

import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;

public class Tile extends GameObject{

    /**
     * Constructor method for Tile that only has one sprite
     * 
     * @param sprite    image of Tile
     * @param x         x-coordinate on window
     * @param y         y-coordinate on window
     * @param row       row number on window grid
     * @param column    column number on window grid
     */
    public Tile(PImage sprite, int x, int y, int row, int column){

        super(sprite, x, y, row, column);
        this.isExist = true;

    }

    /**
     * Constructor method for Tile that has multiple sprites
     * 
     * @param sprites   images of Tile
     * @param x         x-coordinate on window
     * @param y         y-coordinate on window
     * @param row       row number on window grid
     * @param column    column number on window grid
     */
    public Tile(HashMap<String, PImage> sprites, int x, int y, int row, int column){

        super(sprites, x, y, row, column);
        this.isExist = true;

    }

    /**
     * Hit tile
     * 
     * @return true if trigger hit sequence
     */
    public boolean hit() {
        return false; 
    }

    /**
     * Draws Tile onto window
     * 
     * @param app PApplet app to draw Tile on
     */
    public void draw(PApplet app) {

        app.image(this.sprite, x, y);
        
    }
}
