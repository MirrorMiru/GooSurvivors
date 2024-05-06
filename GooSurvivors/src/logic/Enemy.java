package logic;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Enemy {
	private AffineTransform tx;
	
	private int dir = 3; 
	private int lastDir;//0-forward, 1-backward, 2-left, 3-right
	private int width, height; //for hitbox
	private int x ;
	private int y ;						
	private int vx, vy;						//movement variable
	private int hp;
	private Rectangle hitbox;

	public Enemy(int ix, int iy, int iwidth, int iheight, int hp) {
		x = ix;
		y = iy;
		width = iwidth;
		height = iheight;
		hitbox = new Rectangle(x,y,width,height);//sextangle
		this.hp = hp;
	}
	
	protected void logic() {
		this.x += vx;
		this.y -= vy;
		
	}
	
	protected Rectangle getHitbox() {
		return hitbox;
	}

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
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
	public int getHp() {
		return hp;
	}

}
