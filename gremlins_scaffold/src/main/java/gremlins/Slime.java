package gremlins;

import java.util.ArrayList;

import processing.core.PImage;

public class Slime extends Projectile {
    
    /**
     * Constructor method for Slime
     * 
     * @param x         x-coordinate on window
     * @param y         y-coordinate on window
     * @param sprite    image of Slime
     * @param dir       direction of Slime movement, same as movement direction of Gremlin that shot it (0 for left, 1 for right, 2 for up, 3 for down)
     */
    public Slime(int x, int y, PImage sprite, int dir) {
        super(x, y, sprite, dir);
    }

    /**
     * Checks collision of Slime with other objects and triggers movement
     * 
     * @param allTiles      list containing all tiles on map
     * @param allGremlins   list containing all Gremlins on map
     * @param wizard        Wizard object on map
     * @param fireballs     list containing all fireballs on map
     */
    public void tick(ArrayList<Tile> allTiles, ArrayList<Gremlin> allGremlins, Wizard wizard, ArrayList<Fireball> fireballs) {

        int tileHeight = 20;
        int tileWidth = 20;

        if (isAbsorbed) {
            return;
        }

        //check collision
        for (Tile t : allTiles) {
            if (!t.isExist) {
                continue;
            }
            this.checkCollision(t.row, t.column);
        }

        //check hit wizard
        boolean isHitWizard = this.checkCollision(wizard.row, wizard.column);
        if (isHitWizard) {
            wizard.kill();
        }

        //check hit fireball
        for (Fireball f : fireballs) {
            if (f.isAbsorbed) {
                continue;
            }
            this.checkCollision(f.row, f.column);
        }

        this.move();

        this.row = (int) Math.floor(this.y/tileHeight);
        this.column = (int) Math.floor(this.x/tileWidth);

    }


}
