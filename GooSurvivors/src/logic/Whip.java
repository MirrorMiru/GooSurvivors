package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


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
	
	boolean dir = true;
	
	Sprite image1 = new Sprite("/img/flame.png",x,y,width,height,0.5);
	Sprite image2 = new Sprite("/img/flame2.png",x+220,y,width,height,0.5);
	

	
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
		
		if( width > 0 && dir) {
			image1.paint(g);
			
			}else if( width > 0 && !dir) {
				image2.paint(g);
			}
			
	
		
		
			timer++;
			if(timer % cool == 0) {
				timer2++;
				timer  = 0;
			}

			if(timer2 > 5 && timer2 <= 12) {
				
				width = 150;
				height = 70;
				x = 220;
			
				dir = true;
				
			}else if(timer2 > 13 && timer2 <= 20) {
				
				width = 150;
				height = 70;
				x = 520;
			
				dir=false;
				
			}else if(timer2 > 21 && timer2 <= 22) {
				width = 0;
				height = 0;
				
			}else if(timer2 == 23) {
				width = 0;
				height = 0;
				dir = true;
				timer2 = 0;
			}
		

			
			
	}
	
	
	public boolean collidedWithEnemy(Enemy enemy) {
	    return enemy.collided(this.x, this.y, this.width, this.height);
	}
	
	public boolean collidedGeneral(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x,y,width,height);
	    return rect.intersects(this.x, this.y, this.width, this.height);
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