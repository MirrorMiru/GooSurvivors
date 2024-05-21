package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class Item{
	private Image image;
	private AffineTransform tx;
	
	int width, height;
	int x, y;						//position of the object
	double scaleWidth = .2;		//change to scale image
	double scaleHeight = .2; 		//change to scale image
	int type;
	
	public Item(int x, int y, int type) {
		if(type == 0) {
		image 	= getImage("/img/greenVial.png"); //load the image for vial
		}else {
		image 	= getImage("/img/redVial.png");		
		}
		this.type = type;
		//hitbox values
		width = 20;
		height = 20;
		this.x = x;
		this.y = x;
		//this stuff
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}

	//display sock sprite
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		init(x,y);

		g2.drawImage(image, tx, null);

	}
	
	public int getType() {
		return type;
	}
	
	/*
	 * return true if an object represented by x, y , w , h occupies 
	 * any space occupied by object
	 */
	public boolean collided(int x, int y, int width, int height) {
		
	//if scaling images with scale var, make sure w/h reflect what we see on screen
	Rectangle otherObj = new Rectangle(x,y,width,height);
	Rectangle thisObj = new Rectangle(this.x+55,this.y+55,this.width,this.height);

	return thisObj.intersects(otherObj);
	}
	
	public Rectangle getHitbox() {
		return new Rectangle(this.x+55,this.y+55,this.width,this.height);//return own hitbox
	}
	
	//getters and setters
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
			URL imageURL = Item.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}