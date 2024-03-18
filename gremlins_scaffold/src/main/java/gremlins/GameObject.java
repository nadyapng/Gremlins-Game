package gremlins;

import java.util.HashMap;

import processing.core.PImage;

public class GameObject {

    public int row;
    public int column;
    public int x;
    public int y;
    public PImage sprite;
    public HashMap<String, PImage> sprites;
    public boolean isExist;

    /**
     * Constructor method for GameObject that only has one sprite
     * 
     * @param   sprite  image of the GameObject
     * @param   x       x-coordinate on the window
     * @param   y       y-coordinate on the window
     * @param   row     row number on the window grid
     * @param   column  column number on the window grid
     */
    public GameObject(PImage sprite, int x, int y, int row, int column) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.row = row;
        this.column = column;
    }

    /**
     * Constructor method for GameObject that has multiple sprites
     * 
     * @param   sprites images of the GameObject
     * @param   x       x-coordinate on the window
     * @param   y       y-coordinate on the window
     * @param   row     row number on the window grid
     * @param   column  column number on the window grid
     */
    public GameObject(HashMap<String, PImage> sprites, int x, int y, int row, int column) {
        this.sprites = sprites;
        this.x = x;
        this.y = y;
        this.row = row;
        this.column = column;
    }

    /**
     * Constructor method for GameObject that only has one sprite, without providing row and column
     * 
     * @param   sprite  image of the GameObject
     * @param   x       x-coordinate on the window
     * @param   y       y-coordinate on the window
     */
    public GameObject(PImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        //set row and column
        this.row = (int) Math.floor(this.y/20);
        this.column = (int) Math.floor(this.x/20);
    }


    
}
