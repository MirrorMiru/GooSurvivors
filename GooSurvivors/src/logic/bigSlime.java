package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class bigSlime extends Enemy {

    // Timers for animation
    private int timer = 0;
    private int timer2 = 0;

    // Coordinates and velocities
    private int x, y;
    private int Vx, Vy;

    // Direction indicator
    int dir = 0;

    // Array of Sprite objects for animation
    Sprite[] anim = {
        new Sprite("/img/BS1.png", x, y, 300, 200, 0.2),
        new Sprite("/img/BS2.png", x, y, 300, 200, 0.2)
    };

    // Constructor to initialize bigSlime object with default position and superclass properties
    public bigSlime() {
        super(200, 1, 100, 80, 2);//hp, dmg, w, h, s
        this.x = 0;
        this.y = 0;
    }

    /**
     * Checks if this object collides with another rectangular object.
     *
     * @param x      X-coordinate of the other object
     * @param y      Y-coordinate of the other object
     * @param width  Width of the other object
     * @param height Height of the other object
     * @returns boolean - true if the rectangles collide, false otherwise
     */
    public boolean collided(int x, int y, int width, int height) {
        // Create rectangles representing this object and the other object
        Rectangle otherObj = new Rectangle(x, y, width, height);
        Rectangle thisObj = new Rectangle(this.x, this.y, super.getWidth(), super.getHeight());

        // Check for intersection
        return thisObj.intersects(otherObj);
    }

    // Draw the bigSlime object and handle its animation and physics
    public void paint(Graphics g) {
        // Cast Graphics to Graphics2D for better control
        Graphics2D g2 = (Graphics2D) g;

        // Update position with velocities
        x += Vx;
        y += Vy;

        // Increment animation timer
        timer++;

        // Update frame every 10 ticks
        if (timer % 10 == 0) {
            timer2++;
        }
        // Reset timers if necessary
        if (timer2 > 1) {
            timer = 0;
            timer2 = 0;
        }

        // Set animation frame positions
        anim[timer2].setX(x);
        anim[timer2].setY(y);
        // Draw the current frame
        anim[timer2].paint(g);
    }

    // Getter and setter methods for position and velocities
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVx() {
        return Vx;
    }

    public void setVx(int vx) {
        Vx = vx;
    }

    public int getVy() {
        return Vy;
    }

    public void setVy(int vy) {
        Vy = vy;
    }

    // Determine the direction based on velocities
    private void direction() {
        if (Vx < 0) {
            dir = 0;
        } else if (Vx > 0) {
            dir = 1;
        } else if (Vy > 0) {
            dir = 0;
        } else {
            dir = 1;
        }
    }

    // Additional image handling code can be added here

}
