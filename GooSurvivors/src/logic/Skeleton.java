package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class Skeleton extends Enemy{
	
	private int timer = 0;
	private int timer2 = 0;
	
	private int x, y;

	
	int dir = 0;
	
	
	Sprite[] left = {new Sprite("/img/SK1L.png",x,y,300,200,0.2),new Sprite("/img/SK2L.png",x,y,300,200,0.2)};
	
	Sprite[] right = {new Sprite("/img/SK1R.png",x,y,300,200,0.2),new Sprite("/img/SK2R.png",x,y,300,200,0.2)};

	
	public Skeleton() {
		super(100,10,200,300,1);
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
		x+=super.Vx;
		y+=super.Vy;
		
		timer++;
		
		if(timer % 10 == 0) {
			timer2++;
		}
		if(timer2 > 1) {
			timer = 0;
			timer2 = 0;
		}
		
		//System.out.println("vx: "+super.getVx()+" vy: "+super.getVy());
			
		direction();
		
		
		if(dir == 0) {
			left[timer2].setX(x);
			left[timer2].setY(y);
			left[timer2].paint(g);
		}
		else if(dir == 1) {
			right[timer2].setX(x);
			right[timer2].setY(y);
			right[timer2].paint(g);
		}
	
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

