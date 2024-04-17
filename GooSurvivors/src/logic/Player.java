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
public class Player{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	private int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	private int width, height;
	private int x, y;						//position of the object
	private int vx, vy;						//movement variables
	private double scaleWidth = .25;		//change to scale image
	private double scaleHeight = .25; 		//change to scale image
	private int groundLevel;
	private int ay;
	
	private boolean inAir;
	
	public Player() {
		forward 	= getImage("/imgs/"+"player1.png"); //load the image for front
		backward 	= getImage("/imgs/"+"player3.png"); //load the image for back
		left 		= getImage("/imgs/"+"player4.png"); //load the image for left
		right 		= getImage("/imgs/"+"player2.png"); //load the image for right

		//hitbox size
		width = 70;
		height = 10;
		x = 350;
		y = 400;
		
		//jumping and physics
		groundLevel = this.y;
		inAir = false;
		vx = 0;
		vy = 0;
		ay = 0;
	
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y);//initialize the location of the image
		
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
		Rectangle thisObj = new Rectangle(this.x,this.y + 130,this.width,this.height);

	return otherObj.intersects(thisObj);
	}
	
	//getters and setter for instance variables
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public int getAy() {
		return ay;
	}

	public void setAy(int ay) {
		this.ay = ay;
	}

	//jump function, make player bounce and change direction
	public void jump(int dir) {
	
		if(y>=groundLevel) {
			vy = -10;
			this.dir = dir;
			inAir = true;
		}
		
	}
	
	public boolean getInAir() {
		return this.inAir;
	}
	
	//draw player image and apply physics
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		x+=vx;
		y+=vy;	
		vy+=ay;
		
		//jumping code
		if(y < groundLevel-10) {
			ay=3;
		}else if(y>=groundLevel){
			vy=0;
			ay=0;
			y=groundLevel;
			inAir = false;
		}
		
		init(x,y);//whatever this is
		
		switch(dir) {//switch image
		case 0:
			g2.drawImage(forward, tx, null);
			break;
		case 1:
			g2.drawImage(backward, tx, null);
			break;
		case 2:
			g2.drawImage(left, tx, null);
			break;
		case 3:
			g2.drawImage(right, tx, null);
			break;
		}
	}
	
	//image code
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Player.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

