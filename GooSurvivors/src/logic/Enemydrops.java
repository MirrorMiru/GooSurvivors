
//DEPRECATED

//USE ITEM CLASS

/*package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Enemydrops {
	private int power;
	private Image picture;
	private int x; private int y;
	private int width; private int height;
	
	public Enemydrops(){
		power = 0;
		//picture = getImage(null);
		x= 0;
		y= 0;
		width = 0;
		height = 0;
	}
	public Enemydrops(int p, Image pi, int a, int b) {
		// TODO Auto-generated constructor stub
		setPower(p);
		setPicture(pi);
		setX(a);
		setY(b);
	}
	
	//getters and setters for variables
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	
	public Image getPicture() {
		return picture;
	}
	public void setPicture(Image picture) {
		this.picture = picture;
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



	public void drop (int powerType, int xLocation, int yLocation) {
		if( powerType == 0 ) {
			Enemydrops repel = new Enemydrops( 0 , new Image("./img/greenVial.png"), xLocation, yLocation);
			//make the repel object at the x and y location; 
		}
		if( powerType == 1) {
			//healing powerUp
		}
	}
	
	public void paint(Graphics g) {
		
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.green);
		g.drawRect(x, y, width, height);
		
	}
	
}
*/
