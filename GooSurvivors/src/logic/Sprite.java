package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/**
* default image class
*
*just to display picture
*/

public class Sprite{
	private Image image;
	private AffineTransform tx;
	
	int width, height;
	int x, y;						//position of the object
	double scaleWidth = 1;		//scale to screen size
	double scaleHeight = 1; 		

	public Sprite(String location, int x, int y, int w, int h) {
		image 	= getImage(location); //load the image for any image 
		//alter these
		width = w;
		height = h;
		this.x = x;
		this.y = y;
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public Sprite(String location, int x, int y, int w, int h, double scale) {
		image 	= getImage(location); //load the image for any image 
		//alter these
		width = w;
		height = h;
		this.x = x;
		this.y = y;
		scaleWidth = scale;
		scaleHeight = scale;
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}

	public void paint(Graphics g) {//displays the image. pretty important here
		//no physics here
		
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		init(x,y);

		g2.drawImage(image, tx, null);
	}
	
	//a few getters and setters
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

	public double getScaleWidth() {
		return scaleWidth;
	}

	public void setScaleWidth(double scaleWidth) {
		this.scaleWidth = scaleWidth;
	}

	public double getScaleHeight() {
		return scaleHeight;
	}

	public void setScaleHeight(double scaleHeight) {
		this.scaleHeight = scaleHeight;
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Sprite.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
