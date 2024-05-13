package logic;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

//tile class

public class WhipType{
	private Image image1, image2;
	private AffineTransform tx;
	
	private int width, height;
	private int x, y;						//position of the object
	private double scaleWidth = 0.25;		//scaled to half of original sprite
	private double scaleHeight = 0.25; 		
	private int timer = 0;
	private int dmg;
	private int cool;

	public WhipType(int cooldown, int damage, int x, int y) {
		
			image1 	= getImage("/img/whip.png");
			image1 	= getImage("/img/whip2.png");

		width = 0;
		height = 50;
		this.x = x;
		this.y = x;
		this.dmg = damage;
		cool = cooldown+200;
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}

	//just draw the image
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		init(x,y);
			if(width > 0) {
			g2.drawImage(image1, tx, null);
			}else if(width < 0) {
				g2.drawImage(image2, tx, null);
			}
			g.setColor(Color.RED);
			g.drawRect(x, y, width, height);
			
			timer++;
			if(timer % cool == 0) {
				timer = 1;
				attack();	
			}
			
	}
	
	public void attack() {
		int timer2 = 0;
		while(timer < 200) {
		timer2++;
		if(timer < 100) {
			width = 100;
			height = 50;
		}
		if(timer > 100 && timer < 150) {
			width = 0;
			height = 0;
		}
		if(timer > 150) {
			width = -100;
			height = 50;
		}
	
		}
		width = 0;
		height = 0;
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

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Tile.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public boolean collided(int x, int y, int width, int height) {
		//if scaling images with scale var, make sure w/h reflect what we see on screen
		Rectangle otherObj = new Rectangle(x,y,width,height);
		Rectangle thisObj = new Rectangle(this.x,this.y + this.height/2,this.width,this.height/2);

	return thisObj.intersects(otherObj);
	}

}

