
package logic;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Enemy {
	private int width;
	private int height;
	private int health;
	private int damage; 
	
	public Enemy() {
		health = -1;
		damage = 0;
		width = 0;
		height = 0;
	}
	public Enemy(int hp, int dmg, int w, int h) {
		health = hp;
		damage = dmg;
		width  = w;
		height = h;
	}
	
	
	//we need to get the pathfinding for this before we can do much
	//todo: get sprites and connect it with collision
	public int getWidth() {
		return width;
	}
	public int setWidth(int thisWidth) {
		return thisWidth;
	}
	public int getHeight() {
		return height;
	}
	public int setHeight(int thisHeight) {
		return thisHeight;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int thisHealth) {
		health = thisHealth;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int thisDamage) {
		health = thisDamage;
	}	
	
	

	public void paint(Graphics g) {
		
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.green);
		//g.drawRect(x, y, width, height);
		
	}
	
	
	
	
}
