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

//tile class

public class BreakableTile extends Tile{
	private Image image;
	private AffineTransform tx;
	
	int width, height;
	int x, y;						//position of the object
	double scaleWidth = 0.25;		//scaled to half of original sprite
	double scaleHeight = 0.25; 		

	int hp = 1;
	
	public BreakableTile(int x, int y, int type) {
		super(x,y,type);
		

		width = 75;
		height = 75;
		this.x = x;
		this.y = x;
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}

	//just draw the image
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		init(x,y);

			g2.drawImage(super.image, tx, null);
	}
	
	public void takeDamage(int dmg, Graphics g) {
		hp -= dmg;
		g.setColor(Color.RED);
		g.setFont(new Font("Sans", Font.PLAIN, 25)); 
		g.drawString("-"+dmg, this.x+(this.width/2), this.y-20);
	}
	
	
	
	//getters and setters
	//the getters are fairly useless here
	public int getX() {
		return x;
	}

	public int getHp() {
		return hp;
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
			URL imageURL = BreakableTile.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public boolean collided(int x, int y, int width, int height) {
		//if scaling images with scale var, make sure w/h reflect what we see on screen
		Rectangle otherObj = new Rectangle(x,y,width,height);
		Rectangle thisObj = new Rectangle(this.x,this.y,this.width,this.height);

	return thisObj.intersects(otherObj);
	}

}
