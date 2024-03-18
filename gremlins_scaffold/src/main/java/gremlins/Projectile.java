package gremlins;


import processing.core.PApplet;
import processing.core.PImage;

public class Projectile extends GameObject{
    
    /**
     * Last movement direction of Character (0 for left, 1 for right, 2 for up, 3 for down)
     */
    public int lastMovement;
    public boolean isCollided;
    public boolean isHitCharacter;
    public boolean isAbsorbed;
    public int vel;

    public boolean moveLeft;
    public boolean moveRight;
    public boolean moveUp;
    public boolean moveDown;

    /**
     * Constructor method for Projectile.
     * Sets default values for velocity, direction and status attributes.
     * 
     * @param x         x-coordinate on window
     * @param y         y-coordinate on window
     * @param sprite    image of Projectile
     * @param dir       direction of Projectile movement, same as movement direction of Character that shot it (0 for left, 1 for right, 2 for up, 3 for down)
     */
    public Projectile(int x, int y, PImage sprite, int dir) {

        super(sprite, x, y);

        this.lastMovement = dir;

        //default
        this.vel = 4;

        //set default
        this.isCollided = false;
        this.isHitCharacter = false;
        this.isAbsorbed = false;

        //set row and column 
        this.row = (int) Math.floor(this.y/20);
        this.column = (int) Math.floor(this.x/20);

        //movement direction
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
     * Draws Projectile on window
     * @param app   PApplet app to draw Projectile on
     */
    public void draw(PApplet app) {

        app.image(this.sprite, x, y);
        
    }

    /**
     * Checks if object with coordinates given will collide with Projectile depending on its movement direction 
     * 
     * @param row       row number of object to be checked
     * @param column    column number of object to be checked
     * @return true if collision occurs, false if not
     */
    public boolean checkCollision(int row, int column){

        if (moveLeft && (this.column == column) && (this.row == row)) {
                this.isCollided = true;
                this.isAbsorbed = true;
                return true;
            }
            if (moveRight && (this.column + 1 == column) && (this.row == row)) {
                this.isCollided = true;
                this.isAbsorbed = true;
                return true;
            }
            if (moveUp && (this.row == row) && (this.column == column)) {
                this.isCollided = true;
                this.isAbsorbed = true;
                return true;
            }
            if (moveDown && (this.row + 1 == row) && (this.column == column)) {
                this.isCollided = true;
                this.isAbsorbed = true;

                return true;
            }
        
        return false;
        
    }

    /**
     * Moves the Projectile on the window according to chosen direction.
     * Prevents Projectile from moving off screen.
     */
    public void move(){
        int leftBorder = 0;
        int rightBorder = 720;
        int topBorder = 0;
        int bottomBorder = 660;

        if (this.moveLeft) {
            if (this.x > leftBorder && !isCollided) {
                this.x -= this.vel;
                lastMovement = 0;
            }
        }

        else if (this.moveRight) {
            if (this.x < rightBorder && !isCollided) {
                this.x += this.vel;
                lastMovement = 1;
            }
        }

        else if (this.moveUp) {
            if (this.y > topBorder && !isCollided) {
                this.y -= this.vel;
                lastMovement = 2;
            }
        }

        else if (this.moveDown) {
            if (this.y < bottomBorder && !isCollided) {
                this.y += this.vel;
                lastMovement = 3;
            }
        }
    }

   
}
