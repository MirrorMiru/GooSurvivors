package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class SurroundWeapon{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	

	private int width, height;
	private int x = 220;
	private int y = 400;						//position of the object

	

	private double angle;
	private int dmg;
	
	boolean dir = true;
	
	Sprite image1 = new Sprite("/img/flame.png",x,y,width,height,0.5);
	Sprite image2 = new Sprite("/img/flame2.png",x+220,y,width,height,0.5);
	

	
	public SurroundWeapon(int cooldown, int damage) {
		
		
		width = 20;
		height = 20;
	
		this.dmg = damage;
		
	
	}

	/**
	* return true if an object represented by x, y , w , h occupies 
	* any space occupied by object
	*
	* @param none
	* @returns boolean- if rectanges collide
	*/
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		angle += 0.1;
		if(angle > 360.0) {
			angle = 0;
		}
		x = (int) (Math.cos(angle) * 250) + 380;
		y = (int) (Math.sin(angle) * 250) + 400;
		g.setColor(Color.blue);
		g.fillOval( x, y, width, height);
		//dont forget ot implement the paint method and others into main	
			
	}
	
	
	public boolean collidedWithEnemy(Enemy enemy) {
	    return enemy.collided(this.x, this.y, this.width, this.height);
	}
	
	public boolean collidedGeneral(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x,y,width,height);
	    return rect.intersects(this.x, this.y, this.width, this.height);
	}
	
	public void setDmg(int d) {
		this.dmg = d;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDmg() {
		return dmg;
	}
	
	public boolean getDir() {
		return dir;
	}
	
	//getters and setters
	//the getters are fairly useless here
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



}