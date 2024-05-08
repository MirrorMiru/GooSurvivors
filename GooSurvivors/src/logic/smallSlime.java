package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class smallSlime extends Enemy{
	
	private int timer = 0;
	private int timer2 = 0;
	
	private int x, y;
	private int Vx, Vy;
	
	int dir = 0;
	
	
	Sprite[] anim = {new Sprite("/img/SS1.png",x,y,300,200,0.2),new Sprite("/img/SS1.png",x,y,300,200,0.2)};

	
	public smallSlime() {
		super(50,1,50,50,2);
		this.x = 0;
		this.y = 0;
	}

	/**
	* return true if an object represented by x, y , w , h occupies 
	* any space occupied by object
	*
	* @param none
	* @returns boolean- if rectanges collide
	*/
	public boolean collided(int x, int y, int width, int height) {
		
	//if scaling images with scale var, make sure w/h reflect what we see on screen
	Rectangle otherObj = new Rectangle(x,y,width,height);
	Rectangle thisObj = new Rectangle(this.x+55,this.y+55,super.getWidth(),super.getHeight());

	return thisObj.intersects(otherObj);
	}
	

	//draw player image and apply physics
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//super.logic();
		//this.x += super.getVx();
		//this.y -= super.getVy();
		super.x = this.x;
		super.y = this.y;
		
		x+=Vx;
		y+=Vy;
		
		timer++;
		
		if(timer % 15 == 0) {
			timer2++;
		}
		if(timer2 > 1) {
			timer = 0;
			timer2 = 0;
		}
		
		//System.out.println("vx: "+super.getVx()+" vy: "+super.getVy());
		
		
	
			anim[timer2].setX(x);
			anim[timer2].setY(y);
			anim[timer2].paint(g);
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

	private void direction() {
			if(Vx < 0 ) {
				dir = 0;
			} 
			else if(Vx > 0 ) {
				dir = 1;
			}else if(Vy > 0){
				dir = 0;
			}else {
				dir = 1;
			}
		}
	
	
	//image code




}

