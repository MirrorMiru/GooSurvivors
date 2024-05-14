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
	private int reverseX;
	private int initialX;
	private double scaleWidth = 0.5;		//scaled to half of original sprite
	private double scaleHeight = 0.5; 		
	private int timer = 0;
	private int timer2 = 0;
	private int dmg;
	private int cool;

	public WhipType(int cooldown, int damage, int x, int y) {
		
			image1 	= getImage("/img/whip.png");
			image2 	= getImage("/img/whip2.png");

		width = 0;
		height = 0;
		this.x = x-100;
		this.initialX = x-100;
		this.y = y;
		this.reverseX = x + 120;
		this.dmg = damage;
		cool = cooldown;
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}

	//just draw the image
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		init(x,y);
			if(x == initialX && width > 0) {
			g2.drawImage(image1, tx, null);
			}else if(x == reverseX && width > 0) {
				g2.drawImage(image2, tx, null);
			}
			g.setColor(Color.RED);
			g.drawRect(x, y, width, height);
			
			timer++;
			if(timer % cool == 0) {
				timer2++;
				timer  = 0;
			}
		System.out.println(timer2);
			
				if(timer2 > 4 && timer2 <= 6) {
					this.x = initialX;
					width = 100;
					height = 50;
					
					
				}else if(timer2 > 6 && timer2 <= 10) {
					width = 0;
					height = 0;
					
				}else if(timer2 > 10 && timer2 <=12) {
					this.x = reverseX;
					width = 100;
					height = 50;
					
				}else if(timer2 == 13) {
					width = 0;
					height = 0;
					timer2 = 0;
				}
			

				
			
			
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

