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
	
	private int dir = 3; 
	private int lastDir;//0-forward, 1-backward, 2-left, 3-right
	private int width, height;
	private int x = 320;
	private int y = 320;						//position of the object
	private int vx, vy;						//movement variables

	private int ay;
	private int timer = 0;
	private int timer2 = 0;
	
	private int xp = 0;
	private int hp = 100;
	Sprite[] playerLeft = {new Sprite("/img/PL1.png",x,y,300,200,0.2),new Sprite("/img/PL2.png",x,y,300,200,0.2)
			,new Sprite("/img/PL3.png",x,y,300,200,0.2),new Sprite("/img/PL2.png",x,y,300,200,0.2)};
	
	Sprite[] playerRight = {new Sprite("/img/PR1.png",x,y,300,200,0.2),new Sprite("/img/PR2.png",x,y,300,200,0.2)
			,new Sprite("/img/PR3.png",x,y,300,200,0.2),new Sprite("/img/PR2.png",x,y,300,200,0.2)};
	
	

	
	public Player() {
		
		
		//hitbox size
		width = 70;
		height = 10;
	
		
		//jumping and physics
	
		
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

	public void addXp(int xp) {
		this.xp += xp;
	}
	
	public void getHurt(int dmg) {
		this.hp -= dmg;
	}
	
	public int getXp() {
		return xp;
	}


	



	public int getHp() {
		return hp;
	}
	
	public void setXp(int n) {
		xp = n;
	}
	
	public void setHp(int n) {
		hp = n;
	}

	

	//draw player image and apply physics
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		timer++;
		
		if(timer % 10 == 0) {
			timer2++;
		}
		if(timer2 > 3) {
			timer = 0;
			timer2 = 0;
		}
		
	//	System.out.println("vx: "+vx+" vy: "+vy);
		
		
		init(x,y);//whatever this is
		
		direction();
		g.translate(0, -100);
		
		if(dir == 0) {
			lastDir = 2;
			
			playerLeft[timer2].paint(g);
		}
		else if(dir == 1) {
			lastDir = 3;
			
			playerRight[timer2].paint(g);
		}
		else if(dir == 2) {
			playerLeft[1].paint(g);
		}
		else if(dir == 3) {
			playerRight[1].paint(g);
		
		}
	}
	
	private void direction() {
		if(this.vy == 0 && this.vx == 0) {
			dir = lastDir;
		}else {
			if(this.vx < 0 ) {
				dir = 0;
			} 
			else if(this.vx > 0 ) {
				dir = 1;
			}else if(this.vy > 0){
				dir = 0;
			}else {
				dir = 1;
			}
		}
	}
	
	//image code
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
	
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

	public boolean collidedWithEnemy(Enemy enemy) {
	    return enemy.collided(this.x, this.y, this.width, this.height);
	}

}

