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

public class Tile{
	protected Image image;
	private AffineTransform tx;
	
	int width, height;
	int x, y;
	//position of the object
	int type;
	double scaleWidth = 0.25;		//scaled to half of original sprite
	double scaleHeight = 0.25; 		

<<<<<<< HEAD
=======

>>>>>>> branch 'main' of https://github.com/MirrorMiru/GooSurvivors.git
	
	public Tile(int x, int y, int type) {
		if(type == 1) {
			image 	= getImage("/img/tile"+((int)(Math.random()*9+1))+".png"); //load the image for tile based on comstructor
		}else if(type == 2) {
			image 	= getImage("/img/crate.png");
		}else if(type == 3) {
			image 	= getImage("/img/snake.png");
		}else {
			System.out.println("oops");//shoudl neevr happen
		}
<<<<<<< HEAD

		this.type = type;
=======
		
		this.type = type;

>>>>>>> branch 'main' of https://github.com/MirrorMiru/GooSurvivors.git

		width = 75;
		height = 75;
		this.x = x;
		this.y = x;
		this.type = type;
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}

	//just draw the image
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		init(x,y);

			g2.drawImage(image, tx, null);
			g.setColor(Color.BLUE);
			g.drawRect(x, y, width, height);
	}
	
	//getters and setters
	//the getters are fairly useless here
	public int getX() {
		return x;
	}
	
	public int getType() {
		return type;
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
		Rectangle thisObj = new Rectangle(this.x,this.y,this.width,this.height);

	return thisObj.intersects(otherObj);
	}

}
