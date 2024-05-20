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
		
			image1 	= getImage("/img/flame.png");
			image2 	= getImage("/img/flame2.png");

		width = 0;
		height = 0;
		this.x =x;
		this.y = y;
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
			if( width > 0) {
			g2.drawImage(image1, tx, null);
			}else if( width < 0) {
				g2.drawImage(image2, tx, null);
			}
		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
			
			timer++;
			if(timer % cool == 0) {
				timer2++;
				timer  = 0;
			}
		
			
				if(timer2 > 4 && timer2 <= 6) {
					
					width = 150;
					height = 70;
					
					
				}else if(timer2 > 6 && timer2 <= 10) {
					width = 0;
					height = 0;
					
				}else if(timer2 > 10 && timer2 <=12) {
					
				    width = -150;
					height = 70;
					
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
	
	public boolean collidedWithEnemy(Enemy enemy) {
	    return enemy.collided(this.x, this.y, this.width, this.height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

