package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/**
* player class
*
* its the player
*/
public class Whip{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	

	private int width, height;
	private int x = 220;
	private int y = 400;						//position of the object

	private int timer = 0;
	private int timer2 = 0;
	

	
	private int dmg;
	private int cool;
	
	Sprite image1 = new Sprite("/img/flame.png",x,y,width,height,0.5);
	Sprite image2 = new Sprite("/img/flame2.png",x,y,width,height,0.5);
	

	
	public Whip(int cooldown, int damage) {
		
		
		width = 0;
		height = 0;
	
		this.dmg = damage;
		cool = cooldown;
	
		
		
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
		
		if( width > 0) {
			image1.paint(g);
			
			}else if( width < 0) {
				image2.paint(g);
			}
			
		
		
			timer++;
			if(timer % cool == 0) {
				timer2++;
				timer  = 0;
			}

			if(timer2 > 6 && timer2 <= 8) {
				
				width = 150;
				height = 70;
				
				
			}else if(timer2 > 9 && timer2 <= 15) {
				width = 0;
				height = 0;
				
			}else if(timer2 == 16) {
				width = 0;
				height = 0;
				timer2 = 0;
			}
		

			
			
	}
	
	
	public boolean collidedWithEnemy(Enemy enemy) {
	    return enemy.collided(this.x, this.y, this.width, this.height);
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