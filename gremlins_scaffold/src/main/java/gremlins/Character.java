package gremlins;

import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;

public class Character extends GameObject{
    
    public PImage projectileSprite;
    public int vel;
    public int projectileVel;
    public boolean isCollided;

    //movement attributes
    public boolean moveLeft;
    public boolean moveRight;
    public boolean moveUp;
    public boolean moveDown;
    /**
     * Last movement direction of Character (0 for left, 1 for right, 2 for up, 3 for down)
     */
    public int lastMovement;

    //projectile attributes
    public boolean shootProjectile;
    public float cooldown;
    public int cooldownCounter;


    /**
     * Constructor method for Character with one sprite
     * 
     * @param x         x-coordinate on window
     * @param y         y-coordinate on window
     * @param sprite    image of Character
     * @param projectileSprite  image of projectile that Character shoots
     * @param row   row number on window grid
     * @param column    column number on window grid
     * @param cooldown  cooldown time for shooting projectiles
     */
    public Character(int x, int y, PImage sprite, PImage projectileSprite, int row, int column, float cooldown) {

        super(sprite, x, y, row, column);

        this.isExist = true;
        this.projectileSprite = projectileSprite;
        this.vel = 0;
        this.projectileVel = 0;
        this.isCollided = false;
        this.moveLeft = false;
        this.moveRight = false;
        this.moveUp = false;
        this.moveDown = false;
        this.shootProjectile = false;
        this.cooldown = cooldown;

    }

    /**
     * Constructor method for Character with multiple sprites
     * 
     * @param x                 x-coordinate on window
     * @param y                 y-coordinate on window
     * @param sprites           images of Character
     * @param projectileSprite  image of projectile that Character shoots
     * @param row               row number on window grid
     * @param column            column number on window grid
     * @param cooldown          cooldown time for shooting projectiles
     */
    public Character(int x, int y, HashMap<String, PImage> sprites, PImage projectileSprite, int row, int column, float cooldown) {
        super(sprites, x, y, row, column);
        
        this.isExist = true;
        if (this.sprites != null) {
            this.sprite = sprites.get("left");
        }
        this.projectileSprite = projectileSprite;
        this.vel = 0;
        this.projectileVel = 0;
        this.isCollided = false;
        this.moveLeft = false;
        this.moveRight = false;
        this.moveUp = false;
        this.moveDown = false;
        this.cooldown = cooldown;
        this.cooldownCounter = 0;
    }

    /**
     * Takes in the row and column numbers of another game object and checks if character collides with it
     * 
     * @param row       row number of other game object on window grid
     * @param column    column number of other game object on window grid
     * @return true if character collides with object, and false if it does not
     */
    public boolean checkCollision(int row, int column){

        if (moveLeft && (this.column - 1 == column) && (this.row == row)) {
                this.isCollided = true;
                return true;
        }
        if (moveRight && (this.column + 1 == column) && (this.row == row)) {
            this.isCollided = true;
            return true;
        }
        if (moveUp && (this.row - 1 == row) && (this.column == column)) {
            this.isCollided = true;
            return true;
        }
        if (moveDown && (this.row + 1 == row) && (this.column == column)) {
            this.isCollided = true;
            return true;
        }
    
        return false;
        
    }

    /**
     * Moves Character depending on its direction attributes.
     * Borders are added to prevent movement off screen.
     * Snaps Character to next full tile.
     */
    public void move() {
        int leftBorder = 0;
        int rightBorder = 720;
        int topBorder = 0;
        int bottomBorder = 660;


        if ((this.x % 20 == 0) && (this.y % 20 == 0)){
            if (this.moveLeft) {
                if (this.x > leftBorder && !isCollided) {
                    this.x -= this.vel;
                    this.lastMovement = 0;
                }
            }

            else if (this.moveRight) {
                if (this.x < rightBorder && !isCollided) {
                    this.x += this.vel;
                    this.lastMovement = 1;
                }
            }

            else if (this.moveUp) {
                if (this.y > topBorder && !isCollided) {
                    this.y -= this.vel;
                    this.lastMovement = 2;
                }
            }

            else if (this.moveDown) {
                if (this.y < bottomBorder && !isCollided) {
                    this.y += this.vel;
                    this.lastMovement = 3;
                }
            }
        }
        else {
            //move wizard to next full tile
            if (this.lastMovement == 0) {
                this.x -= vel;
            }
            else if (this.lastMovement == 1) {
                this.x += vel;
            }
            else if (this.lastMovement == 2) {
                this.y -= vel;
            }
            else if (this.lastMovement == 3) {
                this.y += vel;
            }
        }
    }

    /**
     * Stops the Character from moving by switching all the movement attributes to false
     */
    public void stop() {
        this.moveLeft = false;
        this.moveRight = false;
        this.moveUp = false;
        this.moveDown = false;
        this.isCollided = false;
    }

    /**
     * Kills the Character
     */
    public void kill() {
        this.isExist = false;
        this.isCollided = false;
    }

    /**
     * Draws the Character onto the window
     * 
     * @param app   PApplet app that Character should be drawn in
     */
    public void draw(PApplet app) {

        app.image(this.sprite, x, y);
        
    }


}
