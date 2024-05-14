
package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ProjectileBullet{
	private int width;
	private int height; 
	private int damage; 
	protected int Vx;
	protected int Vy;
	protected int x;
	protected int y; //these initial positions are so that (with some help), it'll spawn on the enemy
	private int playerX;
	private int playerY;

	private int speed;
	
	public ProjectileBullet() {
		damage = 0;
		width = 0;
		height = 0;
		speed = 0;
	}
	
	public ProjectileBullet(int dmg, int w, int h, int pX, int pY, int s) {
		damage = dmg;
		width = w;
		height = h;
		playerX = pX;
		playerY = pY;
		speed = s;
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
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
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
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.green);
		//g.drawRect(x, y, width, height);
		g.drawOval(x, y, width, height);
	}
	
	public boolean collided(int x, int y, int width, int height) {
		
		//if scaling images with scale var, make sure w/h reflect what we see on screen
		Rectangle otherObj = new Rectangle(x,y,width,height);
		Rectangle thisObj = new Rectangle(this.x+55,this.y+55, getWidth(),getHeight());

		return thisObj.intersects(otherObj);
	}
}

