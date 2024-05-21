
package logic;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Enemy {
	private int width;
	private int height;
	private int health;
	private int damage; 
	protected int Vx;
	protected int Vy;
	protected int x;
	protected int y;
	private int speed;
	
	public Enemy() {
		health = -1;
		damage = 0;
		width = 0;
		height = 0;
		speed = 0;
	}
	public Enemy(int hp, int dmg, int w, int h, int s) {
		health = hp;
		damage = dmg;
		width  = w;
		height = h;
		speed = s;
	}
	
	public void takeDamage(int dmg, Graphics g) {
		health -= dmg;
		g.setColor(Color.RED);
		g.setFont(new Font("Sans", Font.PLAIN, 25)); 
		g.drawString("-"+dmg, this.x+(this.width/2), this.y-20);
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
	
	public void setVx(int speed) {
		this.Vx = speed;
	}
	
	public void setVy(int speed) {
		this.Vy = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean collided(int x, int y, int width, int height) {
		//if scaling images with scale var, make sure w/h reflect what we see on screen
		System.out.println("called");
		Rectangle otherObj = new Rectangle(x,y,width,height);
		Rectangle thisObj = new Rectangle(this.x,this.y,this.width,this.height);

	return thisObj.intersects(otherObj);
	}

	public void paint(Graphics g) {
		
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.green);
		//g.drawRect(x, y, width, height);
		
	}
	public int getHp() {
		// TODO Auto-generated method stub
		return health;
	}
	
	
	
	
}
