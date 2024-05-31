package logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy {
    // Attributes of the enemy: dimensions, health, damage, velocity, position, and speed
    private int width;
    private int height;
    private int health;
    private int damage; 
    protected int Vx;
    protected int Vy;
    protected int x;
    protected int y;
    private int speed;

    // Default constructor initializes attributes with default values
    public Enemy() {
        health = -1;
        damage = 0;
        width = 0;
        height = 0;
        speed = 0;
    }

    // Parameterized constructor initializes attributes with given values
    public Enemy(int hp, int dmg, int w, int h, int s) {
        health = hp;
        damage = dmg;
        width  = w;
        height = h;
        speed = s;
    }

    // Method to reduce health by the damage taken and display the damage taken on the screen
    public void takeDamage(int dmg, Graphics g) {
        health -= dmg;
        g.setColor(Color.RED);
        g.setFont(new Font("Sans", Font.PLAIN, 25)); 
        g.drawString("-" + dmg, this.x + (this.width / 2), this.y - 20);
    }

    // Getter method for width
    public int getWidth() {
        return width;
    }

    // Setter method for width (returns the new width)
    public int setWidth(int thisWidth) {
        return thisWidth;
    }

    // Getter method for height
    public int getHeight() {
        return height;
    }

    // Setter method for height (returns the new height)
    public int setHeight(int thisHeight) {
        return thisHeight;
    }

    // Getter method for health
    public int getHealth() {
        return health;
    }

    // Setter method for health
    public void setHealth(int thisHealth) {
        health = thisHealth;
    }

    // Getter method for damage
    public int getDamage() {
        return damage;
    }

    // Setter method for damage
    public void setDamage(int thisDamage) {
        health = thisDamage;
    }    

    // Setter method for horizontal velocity
    public void setVx(int speed) {
        this.Vx = speed;
    }

    // Setter method for vertical velocity
    public void setVy(int speed) {
        this.Vy = speed;
    }

    // Getter method for speed
    public int getSpeed() {
        return speed;
    }

    // Getter method for x position
    public int getX() {
        return x;
    }

    // Getter method for y position
    public int getY() {
        return y;
    }

    // Method to check collision with another rectangular object
    public boolean collided(int x, int y, int width, int height) {
        // Create rectangles representing this object and the other object
        Rectangle otherObj = new Rectangle(x, y, width, height);
        Rectangle thisObj = new Rectangle(this.x, this.y, this.width, this.height);

        // Check for intersection
        return thisObj.intersects(otherObj);
    }

    // Method to paint the enemy on the screen (currently empty)
    public void paint(Graphics g) {
        // Cast Graphics to Graphics2D for better control
        Graphics2D g2 = (Graphics2D) g;
    }

    // Getter method for health (duplicate of getHealth())
    public int getHp() {
        return health;
    }
}
