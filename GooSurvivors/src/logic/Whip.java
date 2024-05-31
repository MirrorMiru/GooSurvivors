package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Whip {
    // Images for different directions of the whip
    private Image forward, backward, left, right;     
    private AffineTransform tx;
    
    // Dimensions and position of the whip
    private int width, height;
    private int x = 220;
    private int y = 400; // Initial position of the whip
    
    // Timers for controlling whip animations
    private int timer = 0;
    private int timer2 = 0;
    
    // Damage and cooldown properties of the whip
    private int dmg;
    private int cool;
    
    // Direction of the whip (true for one direction, false for the other)
    boolean dir = true;
    
    // Sprites for the whip's animation
    Sprite image1 = new Sprite("/img/flame.png", x, y, width, height, 0.5);
    Sprite image2 = new Sprite("/img/flame2.png", x + 220, y, width, height, 0.5);
    
    // Constructor to initialize whip with cooldown and damage values
    public Whip(int cooldown, int damage) {
        width = 0;
        height = 0;
        this.dmg = damage;
        cool = cooldown;
    }

    /**
     * Method to paint the whip on the screen
     * 
     * @param g Graphics object for drawing
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Draw the appropriate image based on the whip's direction and state
        if (width > 0 && dir) {
            image1.paint(g);
        } else if (width > 0 && !dir) {
            image2.paint(g);
        }

        // Update timers to control the whip's animation cycle
        timer++;
        if (timer % cool == 0) {
            timer2++;
            timer = 0;
        }

        // Update whip's dimensions and direction based on the timer
        if (timer2 > 5 && timer2 <= 12) {
            width = 150;
            height = 70;
            x = 220;
            dir = true;
        } else if (timer2 > 13 && timer2 <= 20) {
            width = 150;
            height = 70;
            x = 520;
            dir = false;
        } else if (timer2 > 21 && timer2 <= 22) {
            width = 0;
            height = 0;
        } else if (timer2 == 23) {
            width = 0;
            height = 0;
            dir = true;
            timer2 = 0;
        }
    }

    /**
     * Method to check collision with an enemy
     * 
     * @param enemy Enemy object to check collision with
     * @return boolean - true if collided with the enemy
     */
    public boolean collidedWithEnemy(Enemy enemy) {
        return enemy.collided(this.x, this.y, this.width, this.height);
    }
    
    /**
     * General method to check collision with any object
     * 
     * @param x X-coordinate of the object
     * @param y Y-coordinate of the object
     * @param width Width of the object
     * @param height Height of the object
     * @return boolean - true if collided with the object
     */
    public boolean collidedGeneral(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        return rect.intersects(this.x, this.y, this.width, this.height);
    }
    
    // Setter method for damage
    public void setDmg(int d) {
        this.dmg = d;
    }
    
    // Getter and setter methods for width
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // Getter and setter methods for height
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Getter method for damage
    public int getDmg() {
        return dmg;
    }
    
    // Getter method for direction
    public boolean getDir() {
        return dir;
    }
    
    // Getter and setter methods for x-coordinate
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // Getter and setter methods for y-coordinate
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
