package gremlins;

import java.util.ArrayList;

import processing.core.PImage;

public class Fireball extends Projectile {
    
    /**
     * Constructor method for wizard's Fireball
     * 
     * @param x         x-coordinate on window
     * @param y         y-coordinate on window
     * @param sprite    image of Fireball
     * @param dir       direction that Fireball is travelling in (same direction as wizard's movement)
     */
    public Fireball(int x, int y, PImage sprite, int dir) {
        super(x, y, sprite, dir);
    }

    /**
     * Checks collisions and triggers movement of Fireball.
     * Updates row and column numbers of Fireball depending on x and y-coordinates.
     * 
     * @param allTiles      list containing all tile objects on the map
     * @param allGremlins   list containing all gremlin objects on the map
     * @param wizard        wizard object on the map
     * @param slimes        list containing all slimes on the map
     */
    public void tick(ArrayList<Tile> allTiles, ArrayList<Gremlin> allGremlins, Wizard wizard, ArrayList<Slime> slimes) {

        int tileHeight = 20;
        int tileWidth = 20;

        if (isAbsorbed) {
            return;
        }

        for (Tile t : allTiles) {
            if (!t.isExist) {
                continue;
            }
            boolean collideWall = this.checkCollision(t.row, t.column);
            if (collideWall) {
                t.hit();
                continue;
            }
        }

        for (Gremlin g : allGremlins) {
            if (!g.isExist) {
                continue;
            }
            boolean hitGremlin = this.checkCollision(g.row, g.column);
            if (hitGremlin) {
                g.kill();
                continue;
            }
        }

        //check hit slime
        for (Slime s : slimes) {
            if (s.isAbsorbed){
                continue;
            }
            this.checkCollision(s.row, s.column);
        }

        this.move();

        this.row = (int) Math.floor(this.y/tileHeight);
        this.column = (int) Math.floor(this.x/tileWidth);
    }

}
